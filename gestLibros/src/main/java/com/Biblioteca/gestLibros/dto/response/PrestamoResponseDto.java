package com.Biblioteca.gestLibros.dto.response;

import com.Biblioteca.gestLibros.dto.LibroPrestadoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoResponseDto {

    private Long idPrestamo;
    private String nameUser;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEstimada;
    private List<LibroPrestadoDto> librosPrestados;
    private String estado;
    private LocalDate fechaDevolucion;
}
