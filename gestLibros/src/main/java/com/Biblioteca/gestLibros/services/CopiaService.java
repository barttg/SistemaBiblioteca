package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.model.Copia;
import com.Biblioteca.gestLibros.repository.ICopiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CopiaService implements ICopiaService{

    private final ICopiaRepository copiRepo;

    @Override
    public List<Copia> getCopias() {
        return List.of();
    }

    @Override
    public void createCopia(Copia copia) {

    }

    @Override
    public Copia findCopia(Long id_copia) {
        return null;
    }

    @Override
    public void deleteCopia(Long id_copia) {

    }

    @Override
    public void editCopia(Copia copia) {

    }
}
