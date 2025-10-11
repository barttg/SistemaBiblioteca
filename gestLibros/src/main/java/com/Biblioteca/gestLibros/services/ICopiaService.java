package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.model.Copia;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICopiaService {

    public List<Copia> getCopias();

    public void createCopia(Copia copia);

    public Copia findCopia(Long id_copia);

    public void deleteCopia(Long id_copia);

    public void editCopia(Copia copia);
}
