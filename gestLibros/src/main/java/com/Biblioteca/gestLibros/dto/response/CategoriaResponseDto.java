package com.Biblioteca.gestLibros.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponseDto {

    private Long id_categoria;
    private String nombre;
    private String descripcion;
}
