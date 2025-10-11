package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.model.Reserva;
import com.Biblioteca.gestLibros.services.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservas")
public class ReservaController {


    private final ReservaService reserVice;

    @GetMapping
    public List<Reserva> reservas(){
        return reserVice.reservasL();
    }
    @PostMapping("/crear")
    public String nuevareserva(@RequestBody Reserva reserva){
        reserVice.saveReserva(reserva);
        return"Se a registrado con exito la reservación, favor de recoger los libros a tiempo para evitar se pongan a disposición";
    }
    @GetMapping("/{id_reserva}")
    public Reserva reservaB(@PathVariable Long id_reserva){
        return reserVice.findReserva(id_reserva);
    }
    @DeleteMapping("/eliminar/{id_reserva}")
    public String eliminar(@PathVariable Long id_reserva){
        reserVice.deleteRes(id_reserva);
        return"La reserva " + id_reserva + " a sido eliminada exitosamente";
    }

    @PutMapping("/edit/{id_usuario}")
    public String editReserva(@PathVariable Long id_reserva, @RequestBody Reserva reserv){
        reserv.setId_reserva(id_reserva);
        reserVice.editRserv(reserv);
        return "La reserva se a actualizado exitosamente";
    }
}
