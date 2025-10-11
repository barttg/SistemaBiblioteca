package com.Biblioteca.gestLibros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearAutorDto {

    private String nombre;
    private String nacionalidad;
    private LocalDate fechaNacimiento;
}
