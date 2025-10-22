package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.edit.PrestamoEditDto;
import com.Biblioteca.gestLibros.dto.PrestamoRequestDto;
import com.Biblioteca.gestLibros.dto.response.PrestamoResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IPrestamoService {

    public List<PrestamoResponseDto> prestamosGet();

    public void savePrestamo(PrestamoRequestDto prestamo);

    public void deletePrst(Long id_prestamo);

    public PrestamoResponseDto findPrest(Long id_prestamo);

    public  PrestamoResponseDto editPrstm(Long id_prestamo, PrestamoEditDto prestEdit);
}
