package com.Biblioteca.gestLibros.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CopiaResponseDto {
    private Long id_copia;
    private String codigo_copia;
    private Long id_libro;
    private boolean disponible;
    private Long id_prestamo;

}
