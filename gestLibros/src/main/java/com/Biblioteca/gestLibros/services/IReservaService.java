package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.ReservaRequestDto;
import com.Biblioteca.gestLibros.dto.edit.ReservEditDto;
import com.Biblioteca.gestLibros.dto.response.ReservaResponseDto;
import com.Biblioteca.gestLibros.model.Reserva;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public interface IReservaService {

    public List<ReservaResponseDto> reservas();

    public void saveReserv(ReservaRequestDto request);

    public void deleteReserv(Long idRerserva);

    public ReservaResponseDto reserva(Long idReserva);

    public ReservaResponseDto editres(Long idOriginal, ReservEditDto reservN );



}
