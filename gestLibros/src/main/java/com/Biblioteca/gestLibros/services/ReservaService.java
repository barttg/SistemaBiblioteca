package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.model.Libro;
import com.Biblioteca.gestLibros.model.Prestamo;
import com.Biblioteca.gestLibros.model.Reserva;
import com.Biblioteca.gestLibros.repository.ILibroRepository;
import com.Biblioteca.gestLibros.repository.IReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService implements  IReservaService{

    private final IReservaRepository reservRepo;

    private final ILibroRepository librorepo;

    private final UsuarioService uservce;

    @Override
    public List<Reserva> reservasL() {
        return reservRepo.findAll();
    }

    @Override
    public void saveReserva(Reserva reserv) {
        if (reserv.getUsar().getPrestamosVig() >= 3){
            throw new IllegalArgumentException("El usuario ya cuenta con el maximo de prestamos vigentes. No puede solicitar otro");
        }

        List<Long> prestVencid = new ArrayList<>();
        List<Prestamo> prestamos = uservce.usuarioPrest(reserv.getUsar().getId_usuario()).getPrestamos();
        for(Prestamo prestamo : prestamos){
            if (!prestamo.isVigente()){
                prestVencid.add(prestamo.getId_prestamo());
            }
        }
        if(!prestVencid.isEmpty()){
            String prestMs = prestVencid.stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(","));
            throw new IllegalArgumentException("El usuario presenta los siguientes prestamos vencidos, No puede solicitar ni Reservar hasta regularizar" + prestMs);
        }
        List<String> librosNoDisp = new ArrayList<>();
        for (Libro libro: reserv.getLibros()){
            if (libro.getCopiasDisponibles() < 1){
                librosNoDisp.add(libro.getTitulo());
            }
        }
        if (!librosNoDisp.isEmpty()){
            throw  new IllegalArgumentException("los siguientes libros no se encuentran disponibles"+
                    String.join(",", librosNoDisp));
        }

        for (Libro libro: reserv.getLibros()){
            libro.setCopiasDisponibles(libro.getCopiasDisponibles() -1);
            librorepo.save(libro);
        }

    }

    @Override
    public void deleteRes(Long id_reserva) {
        reservRepo.deleteById(id_reserva);
    }

    @Override
    public Reserva findReserva(Long id_reserva) {
        return reservRepo.findById(id_reserva).orElseThrow(()-> new RuntimeException("No existe ninguna reserva con el id ingresado"));
    }

    @Override
    public void editRserv(Reserva resv) {
        Reserva reserv = this.findReserva(resv.getId_reserva());
        reserv.setUsar(resv.getUsar());
        reserv.setLibros(resv.getLibros());
        reserv.setFechaExpirac(resv.getFechaExpirac());
        reserv.setFechaRserva(resv.getFechaRserva());
        this.saveReserva(resv);
    }
}
