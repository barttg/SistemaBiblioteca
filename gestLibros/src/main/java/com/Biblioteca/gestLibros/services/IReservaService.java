package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.model.Reserva;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IReservaService {

    public List<Reserva> reservasL();

    public void saveReserva(Reserva reserv);

    public void deleteRes(Long id_reserva);

    public Reserva findReserva(Long id_reserva);

    public void editRserv(Reserva resv);
}
