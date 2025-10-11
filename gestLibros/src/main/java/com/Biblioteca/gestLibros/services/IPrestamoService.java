package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.PrestamoRequestDto;
import com.Biblioteca.gestLibros.model.Prestamo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IPrestamoService {

    public List<Prestamo> prestamosGet();

    public void savePrestamo(PrestamoRequestDto prestamo);

    public void deletePrst(Long id_prestamo);

    public Prestamo findPrest(Long id_prestamo);

    public  void editPrstm(Prestamo prest);
}
