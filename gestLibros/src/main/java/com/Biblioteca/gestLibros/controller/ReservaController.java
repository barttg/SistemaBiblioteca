package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.dto.ReservaRequestDto;
import com.Biblioteca.gestLibros.dto.edit.ReservEditDto;
import com.Biblioteca.gestLibros.dto.response.ReservaResponseDto;
import com.Biblioteca.gestLibros.model.Reserva;
import com.Biblioteca.gestLibros.services.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reserVice;

    @GetMapping()
    public List<ReservaResponseDto> reservas() {
        return reserVice.reservas();
    }

    @GetMapping("/{id_reserva}")
    public ReservaResponseDto reserva(@PathVariable Long id_reserva) {
        return reserVice.reserva(id_reserva);
    }

    @PostMapping("/crear")
    public String reservando(@RequestBody ReservaRequestDto reserva) {
        reserVice.saveReserv(reserva);
        LocalDate hoy = LocalDate.now().plusDays(5);
        return "Se a registrado correctamente tu reserva, el dia maximo a recoger es: " + hoy;
    }

    @DeleteMapping("/eliminar/{id_reserva}")
    public String eliminando(@PathVariable Long id_reserva) {
        reserVice.deleteReserv(id_reserva);
        return "Se a eliminado correctamente la reserva";
    }

    @PutMapping("/editar/{id_reserva}")
    public String editar(@PathVariable Long id_reserva, @RequestBody ReservEditDto reservaEd){
        reserVice.editres(id_reserva, reservaEd);
        return"Se a actualizado con exito la reserva";
    }





}
