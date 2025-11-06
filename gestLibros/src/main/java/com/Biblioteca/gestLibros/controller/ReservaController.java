package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.dto.ReservaRequestDto;
import com.Biblioteca.gestLibros.dto.edit.ReservEditDto;
import com.Biblioteca.gestLibros.dto.response.ReservaResponseDto;
import com.Biblioteca.gestLibros.model.Reserva;
import com.Biblioteca.gestLibros.services.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reserVice;

    @GetMapping()
    public ResponseEntity<List<ReservaResponseDto>> reservas(){
        return new ResponseEntity<>(reserVice.reservas(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id_reserva}")
    public ResponseEntity<ReservaResponseDto> reserv(@PathVariable Long id_reserva){
        return new ResponseEntity<>(reserVice.reserva(id_reserva), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> reservando(@RequestBody ReservaRequestDto request){
        reserVice.saveReserv(request);
        LocalDate fecha = LocalDate.now().plusDays(5);
        return new ResponseEntity<>("La reserva a sido registrada exitosamente. El dia maximo a recoger es: " + fecha, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id_reserva}")
    public ResponseEntity<String> eliminar(@PathVariable Long id_reserva){
        return new ResponseEntity<>("La reserva a sido eliminado correctamente", HttpStatus.OK);
    }


    @PutMapping("/editar/{id_reserva}")
    public ResponseEntity<String> editando(@PathVariable Long id_reserva, @RequestBody ReservEditDto reserva){
        reserVice.editres(id_reserva, reserva);
        return new ResponseEntity<>("La reserva a sido actualizada exitosamente", HttpStatus.OK);
    }





}
