package com.Biblioteca.gestLibros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NuevaCategoriaDto {

    private String nombre;
    private String descripcion;
}
