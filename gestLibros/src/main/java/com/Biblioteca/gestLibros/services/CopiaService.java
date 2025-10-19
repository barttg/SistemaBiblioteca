package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.CopiaRquestDto;
import com.Biblioteca.gestLibros.dto.Edit.CopiaEditDto;
import com.Biblioteca.gestLibros.model.Copia;
import com.Biblioteca.gestLibros.model.Libro;
import com.Biblioteca.gestLibros.repository.ICopiaRepository;
import com.Biblioteca.gestLibros.repository.ILibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CopiaService implements ICopiaService{

    private final ILibroRepository libroRepo;

    private final ICopiaRepository copiRepo;

    @Override
    public List<Copia> getCopias() {
        return List.of();
    }

    @Override
    public void createCopia(CopiaRquestDto request) {
        Libro libro = libroRepo.findById(request.getId_libro()).orElseThrow(()-> new RuntimeException("No se encontro ningun libro con el id ingresado"));
        Copia  copia = new Copia();

        Random random = new Random();
        int numeroAleato = random.nextInt(90000) + 10000;
        String codigoCopia = "C.P" +  numeroAleato;

        copia.setCodigoCopia(codigoCopia);
        copia.setDisponible(true);
        copia.setLibro(libro);
        copia.setEstado(request.getEstado());
    }


    @Override
    public Copia findCopia(Long id_copia) {
        return null;
    }

    @Override
    public void deleteCopia(Long id_copia) {

    }

    @Override
    public void editCopia(CopiaEditDto copiaEdit) {

    }

}
