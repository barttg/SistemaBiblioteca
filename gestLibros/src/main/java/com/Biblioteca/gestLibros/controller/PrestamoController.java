package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.dto.edit.PrestamoEditDto;
import com.Biblioteca.gestLibros.dto.PrestamoRequestDto;
import com.Biblioteca.gestLibros.dto.response.PrestamoResponseDto;
import com.Biblioteca.gestLibros.services.PrestamoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prestamos")
public class PrestamoController {


    private final PrestamoService prestService;

    @GetMapping
    public List<PrestamoResponseDto> prestamos(){
        return prestService.prestamosGet();
    }

    @PostMapping("/crear")
    public String nuevoPrestamo(@RequestBody PrestamoRequestDto prestamoDto){
        prestService.savePrestamo(prestamoDto);
        return "Se a registrado un nuevo prestamo con exito ";
    }

    @GetMapping("/{id_prestamo}")
    public PrestamoResponseDto prestamoX(@PathVariable Long id_prestamo){
      return  prestService.findPrest(id_prestamo);
    }

    @DeleteMapping("/borrar/{id_prestamo}")
    public String eliminarPrest(@PathVariable Long id_prestamo){
        prestService.deletePrst(id_prestamo);
        return "El prestamo con el id " + id_prestamo + " a sido eliminado exitosamente";
    }

    @PutMapping("/edit/{id_prestamo}")
    public String editarPrestamo(@PathVariable Long id_prestamo, @RequestBody PrestamoEditDto prestamo){
        prestService.editPrstm(id_prestamo, prestamo);
        return "El prestamo con el id " + id_prestamo + " a sido actualizado exitosamente";
    }
}
