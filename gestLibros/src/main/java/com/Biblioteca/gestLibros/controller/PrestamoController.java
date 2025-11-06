package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.dto.edit.PrestamoEditDto;
import com.Biblioteca.gestLibros.dto.PrestamoRequestDto;
import com.Biblioteca.gestLibros.dto.response.PrestamoResponseDto;
import com.Biblioteca.gestLibros.services.PrestamoService;
import com.Biblioteca.gestLibros.services.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prestamos")
public class PrestamoController {


    private final PrestamoService prestService;

    @GetMapping()
    public ResponseEntity<List<PrestamoResponseDto>> prestList(){
        return new ResponseEntity<>(prestService.prestamosGet(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> prestNew(@RequestBody PrestamoRequestDto prest){
        prestService.savePrestamo(prest);
        LocalDate hoy = LocalDate.now().plusDays(21);
        return new ResponseEntity<>("El prestamo a sido registrado correctamente, la fecha maxima a devolver es: " + hoy, HttpStatus.CREATED);
    }

    @GetMapping("/{id_prestamo}")
    public ResponseEntity<PrestamoResponseDto> findPrest(@PathVariable Long id_prestamo){
        return new ResponseEntity<>(prestService.findPrest(id_prestamo), HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id_prestamo}")
    public ResponseEntity<String> eliminarPrest(@PathVariable Long  id_presamo){
        prestService.deletePrst(id_presamo);
        return new ResponseEntity<>("El prestamo a sido eliminado correctamente", HttpStatus.OK);
    }

    @PutMapping("/edit/{id_prestamo}")
    public ResponseEntity<String> editPrest(@PathVariable Long id_prestamo, @RequestBody PrestamoEditDto prestEdit){
        prestService.editPrstm(id_prestamo, prestEdit);
        return new ResponseEntity<>("El prestamo a sido actualizado correctamente", HttpStatus.OK);
    }

}
