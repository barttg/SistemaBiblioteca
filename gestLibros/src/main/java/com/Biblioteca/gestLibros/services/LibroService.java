package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.CrearLibroDto;
import com.Biblioteca.gestLibros.model.Autor;
import com.Biblioteca.gestLibros.model.Copia;
import com.Biblioteca.gestLibros.model.Libro;
import com.Biblioteca.gestLibros.repository.IAutorRepository;
import com.Biblioteca.gestLibros.repository.ILibroRepository;
import lombok.RequiredArgsConstructor;
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
        // Validar si autor en el dto que se recibe es null
        if(request.getId_autor() !=null){
            Autor autor = autrepo.findById(request.getId_autor()).orElseThrow(()-> new RuntimeException("Autor no encontrado"));

            libro.setAutor(autor);
            libro.setEditorial(request.getEditorial());
            libro .setIsbn(request.getIsbn());
            libro.setAnioPublicacion(request.getAnioPublicacion());
            libro.setTitulo(request.getTitulo());

            List<Copia> copias = new ArrayList<>();

            for (int i = 0; i == request.getCantidadCopias(); i++){
                Copia copia = new Copia();

                //Generar un codigo para cada copia de manera alaeatoria y unica

                Random random = new Random();

                int numeroAleato = random.nextInt(90000) + 10000;
                String codigoCopia = "C.P" +  numeroAleato;

                //Setear valores a cada copia creada
                copia.setLibro(libro);
                copia.setCodigoCopia(codigoCopia);
                copia.setEstado("Nuevo");
                copia.setDisponible(true);

                copias.add(copia);
            }

            libro.setCopias(copias);
            libroRepo.save(libro);
        }

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
    public void editLibro(CrearLibroDto libro) {
        Libro libroN = this.findLibro(libro.getId_libro());
        libroN.setCopiasDisponibles(libroN.getCopiasDisponibles());
        libroN.setAutor(libro.getAutor());
        libroN.setAnioPublicacion(libro.getAnioPublicacion());
        libroN.setTitulo(libro.getTitulo());
        libroN.setEditorial(libro.getEditorial());

        this.saveLibro(libroN);
    }
}
