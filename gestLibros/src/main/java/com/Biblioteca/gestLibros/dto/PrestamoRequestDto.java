package com.Biblioteca.gestLibros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoRequestDto {

    private Long usuarioId;
    private List<Long> librosIds;
    private int diasPrestamo;
}
