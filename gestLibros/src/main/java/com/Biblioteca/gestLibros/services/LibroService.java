package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.CrearLibroDto;
import com.Biblioteca.gestLibros.model.Autor;
import com.Biblioteca.gestLibros.model.Copia;
import com.Biblioteca.gestLibros.model.Libro;
import com.Biblioteca.gestLibros.repository.IAutorRepository;
import com.Biblioteca.gestLibros.repository.ILibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LibroService implements ILibroService{


    private final ILibroRepository libroRepo;

    private final IAutorRepository autrepo;

    @Override
    public List<Libro> libros() {
        return libroRepo.findAll();
    }

    @Override
    public void saveLibro(CrearLibroDto request) {
        Libro libro = new Libro();
        if(request.getId_autor() !=null){
            Autor autor = autrepo.findById(request.getId_autor()).orElseThrow(()-> new RuntimeException("Autor no encontrado"));
            List<Copia> copias = new ArrayList<>();

            List<Integer> codigos = new ArrayList<>();
            int i= 0;
            while( i == request.getCantidadCopias()){
                Random random = new Random();
                int numeroAleatorio = random.nextInt(90000) + 10000;
                codigos.add(numeroAleatorio);

                i++;
            }
            for (Integer into : codigos){
                String codig = String.valueOf(into);
                copias.add(codig);
            }


            libro.setAutor(autor);
            libro.setEditorial(request.getEditorial());
            libro .setIsbn(request.getIsbn());
            libro.setAnioPublicacion(request.getAnioPublicacion());
            libro.set

            request.set(autor);
        }
        libroRepo.save(lib);
    }

    @Override
    public Libro findLibro(Long id_libro) {
        return libroRepo.findById(id_libro).orElseThrow(()->new RuntimeException("El libro no a sido localizado en ningun estante, intenta con otro"));
    }

    @Override
    public void deleteLibro(Long id_libro) {
        libroRepo.deleteById(id_libro);
    }

    @Override
    public void editLibro(Libro libro) {
        Libro libroN = this.findLibro(libro.getId_libro());
        libroN.setCopiasDisponibles(libroN.getCopiasDisponibles());
        libroN.setAutor(libro.getAutor());
        libroN.setAnioPublicacion(libro.getAnioPublicacion());
        libroN.setTitulo(libro.getTitulo());
        libroN.setEditorial(libro.getEditorial());

        this.saveLibro(libro);
    }
}
