package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.AutrDto;
import com.Biblioteca.gestLibros.dto.edit.AutorEditDto;
import com.Biblioteca.gestLibros.model.Autor;
import com.Biblioteca.gestLibros.model.Libro;
import com.Biblioteca.gestLibros.repository.IAutorRepository;
import com.Biblioteca.gestLibros.repository.ILibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AutorService implements IAutorService{


    private final ILibroRepository libRepo;
    private final IAutorRepository autRepo;

    @Override
    public List<Autor> autGet() {
        return autRepo.findAll();
    }

    @Override
    public void saveAutor(Autor request) {


        autRepo.save(request);
    }

    @Override
    public Autor findaut(Long id_autor) {
        return autRepo.findById(id_autor).orElseThrow(()-> new RuntimeException("No se a encontrado ningun autor con el id ingresado. Intentalo de nuevo"));
    }

    @Override
    public void deleteAutor(Long id_autor) {
        autRepo.deleteById(id_autor);
    }

    @Override
    public void editAutor(Long id_autor, AutorEditDto autor) {
        Autor autorExist = autRepo.findById(id_autor).orElseThrow(()->new RuntimeException("No se encontro el autor con ese id"));

        if(autor.hasNombre()){
            autorExist.setNombre(autor.getNombre());
        }
        if(autor.hasNacionalidad()){
            autorExist.setNacionalidad(autor.getNacionalidad());
        }
        if(autor.hasFechaNacimiento()){
            autorExist.setFechaNac(autor.getFechaNacimiento());
        }
        //Guardar los cambios realizados
        autRepo.save(autorExist);

    }

    @Override
    public AutrDto autorLibros(Long id_autor) {
        AutrDto autorConLbs = new AutrDto();
        Autor autor = this.findaut(id_autor);
        List<Libro> librosAut = new ArrayList<>();

        for(Libro libro : libRepo.findAll()){
            if(libro.getAutor().getId_autor().equals(id_autor)){
                librosAut.add(libro);
            }
        }
        autorConLbs.setId_autor(id_autor);
        autorConLbs.setNombreAutor(autor.getNombre());
        autorConLbs.setLibros(librosAut);
        return autorConLbs;
    }
}
