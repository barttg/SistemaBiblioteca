package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.ReservaRequestDto;
import com.Biblioteca.gestLibros.dto.edit.ReservEditDto;
import com.Biblioteca.gestLibros.dto.response.ReservaResponseDto;
import com.Biblioteca.gestLibros.model.*;
import com.Biblioteca.gestLibros.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReservaService implements IReservaService {

    private final IReservaRepository reservRepo;

    private final IUsuarioRepository useRepo;

    private final ILibroRepository libroRepo;

    private final IPrestamoRepository prestRepo;

    private final ICopiaRepository copiRepo;


    @Override
    public List<ReservaResponseDto> reservas() {
        List<ReservaResponseDto> response = new ArrayList<>();
        for (Reserva reserva : reservRepo.findAll()){
            ReservaResponseDto respuesta = new ReservaResponseDto();
            respuesta.setId_reserva(reserva.getId_reserva());
            respuesta.setId_usuario(reserva.getUsar().getId_usuario());
            respuesta.setFechaReserv(reserva.getFechaATomar());
            respuesta.setFechaExpira(reserva.getFechaExpirac());
            respuesta.setFechaReservacion(reserva.getFechaReserva());
            response.add(respuesta);
        }

        return response;
    }

    @Override
    public void saveReserv(ReservaRequestDto request) {
        Usuario user = useRepo.findById(request.getId_usuario()).orElseThrow(()->new RuntimeException("El usuario no se encuentra registrado"));
       //Vlidaciones de usuario-->

        
        //1- Validacion maximo de prestamos vigentes
        List<Prestamo> prestamosUser = new ArrayList<>();


       for(Prestamo prestamo :  prestRepo.findAll()){
           if(prestamo.getUsuario().getId_usuario().equals(request.getId_usuario())){
               prestamosUser.add(prestamo);
           }
       }

       if(prestamosUser.size() >= 3){
           throw new IllegalArgumentException("El usuario cuenta con el maximo de prestamos permitido. No puede solicitar ni reservar");
       }
       if (user.getReservas().equals(1)){
           throw  new RuntimeException("El usuario ya tiene una reserva activa. No puede realizar otra");
       }

       //Validacion de prestamos vencidos

        List<Long> prestamosVencidos = new ArrayList<>();
        for (Prestamo prestamo : prestamosUser){
            LocalDate vigPrestamo = prestamo.getFechaADevolver();
            if(vigPrestamo.isBefore(LocalDate.now())){
                prestamosVencidos.add(prestamo.getId_prestamo());
            }
        }

        if(!prestamosVencidos.isEmpty()){
            String prestamosR = prestamosVencidos.stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(","));
            throw new RuntimeException("El usuario cuenta con los siguientes prestamos vencidos. No puede solicitar otro" + prestamosR);
        }

        List<String> librosNoDisp = new ArrayList<>();
        for(Long idLibro : request.getLibrosId()){
            Libro libro  = libroRepo.findById(idLibro).orElseThrow(()-> new RuntimeException("No se encontro el libro con el id: " + idLibro));

            List<Copia> copiasDispo = new ArrayList<>();
            for(Copia copia : copiRepo.findAll()){
                if (copia.getLibro().getId_libro().equals(idLibro) && copia.isDisponible()){
                    copiasDispo.add(copia);
                }
            }

            if(copiasDispo.isEmpty()){
                librosNoDisp.add(libro.getTitulo());
            }
        }

        if(!librosNoDisp.isEmpty()){
            throw new IllegalArgumentException("No existen copias disponibles de los siguientes libros: " + String.join(",", librosNoDisp));
        }

        //Despues de las validaciones de usuario se empieza a armar la reserva

        Reserva reserva = new Reserva();



        reserva.setUsar(user);
        reserva.setFechaReserva(LocalDate.now());
        reserva.setFechaATomar(request.getFechaReservada());
        reserva.setFechaExpirac(LocalDate.now().plusDays(15));

        user.setReservas(user.getReservas() + 1);
        reservRepo.save(reserva);


    }

    @Override
    public void deleteReserv(Long idRerserva) {
        reservRepo.deleteById(idRerserva);
    }

    @Override
    public ReservaResponseDto reserva(Long idReserva) {
        ReservaResponseDto response = new ReservaResponseDto();
        Reserva rese = reservRepo.findById(idReserva).orElseThrow(()->new RuntimeException("No se encontro una reserva con ese id"));
        response.setId_reserva(rese.getId_reserva());
        response.setFechaExpira(rese.getFechaExpirac());
        response.setId_usuario(rese.getUsar().getId_usuario());
        response.setFechaReserv(rese.getFechaReserva());

        return response;
    }

    @Override
    public ReservaResponseDto editres(Long idOriginal, ReservEditDto reservN) {

        Reserva reservaExist = reservRepo.findById(idOriginal).orElseThrow(()-> new RuntimeException("No se encontro ningun libro con el id ingresado."));

        if(reservN.hasIdUsuario()){
            Usuario usuario = useRepo.findById(reservN.getIdUsuario()).orElseThrow(()-> new RuntimeException("No se encontro ningun usuario con el id ingresado"));
            reservaExist.setUsar(usuario);
        }

        if(reservN.hasIdReserva()){
            reservaExist.setId_reserva(reservN.getId_reserva());
        }
        if(reservN.hasFechaATomar()){
            reservaExist.setFechaATomar(reservN.getFechaATomar());
        }


        //Guardar los cambios realizados
        reservRepo.save(reservaExist);
        return armandoResponse(reservaExist);
    }

    private ReservaResponseDto armandoResponse(Reserva reserva){
        ReservaResponseDto response = new ReservaResponseDto();

        response.setFechaReserv(reserva.getFechaReserva());
        response.setFechaExpira(reserva.getFechaExpirac());
        response.setId_usuario(reserva.getUsar().getId_usuario());
        response.setId_reserva(reserva.getId_reserva());
        return response;
    }
}
