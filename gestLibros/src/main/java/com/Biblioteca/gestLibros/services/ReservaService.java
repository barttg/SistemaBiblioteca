package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.ReservaRequestDto;
import com.Biblioteca.gestLibros.dto.response.ReservaResponseDto;
import com.Biblioteca.gestLibros.model.Libro;
import com.Biblioteca.gestLibros.model.Reserva;
import com.Biblioteca.gestLibros.model.Usuario;
import com.Biblioteca.gestLibros.repository.IReservaRepository;
import com.Biblioteca.gestLibros.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReservaService implements IReservaService {

    private final IReservaRepository reservRepo;

    private final IUsuarioRepository useRepo;


    @Override
    public List<ReservaResponseDto> reservas() {
        List<ReservaResponseDto> response = new ArrayList<>();
        for (Reserva reserva : reservRepo.findAll()){
            ReservaResponseDto respuesta = new ReservaResponseDto();
            respuesta.setId_reserva(reserva.getId_reserva());
            respuesta.setId_usuario(reserva.getUsar().getId_usuario());
            respuesta.setFechaReserv(reserva.getFechaReserva());
            respuesta.setFechaExpira(reserva.getFechaExpirac());
            response.add(respuesta);
        }

        return response;
    }

    @Override
    public void saveReserv(ReservaRequestDto request) {
        Usuario user = useRepo.findById(request.getId_usuario()).orElseThrow(()->new RuntimeException("El usuario no se encuentra registrado"));
        Reserva reserva = new Reserva();
        List<Libro> librosLst = new ArrayList<>();
        reserva.setUsar(user);
        reserva.setFechaReserva(request.getFechaReservada());
        reserva.setFechaExpirac(request.getFechaReservada().plusDays(15));

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
    public ReservaResponseDto editres(Long idOriginal, ReservaRequestDto reservN) {
        return null;
    }
}
