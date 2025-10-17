package com.Biblioteca.gestLibros.dto.Edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorEditDto {

    private String nombre;
    private String nacionalidad;
    private LocalDate fechaNacimiento;

    //metodos utilitarios para validar si un campo no fue asignado

    public boolean hasNombre(){ return  nombre !=null; }
    public boolean hasNacionalidad(){ return nacionalidad !=null; }
    public boolean hasFechaNacimiento(){ return fechaNacimiento !=null; }
}
