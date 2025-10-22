package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.CrearLibroDto;
import com.Biblioteca.gestLibros.dto.edit.LibroEditDto;
import com.Biblioteca.gestLibros.dto.response.ResponseLibroDto;
import com.Biblioteca.gestLibros.model.Autor;
import com.Biblioteca.gestLibros.model.Copia;
import com.Biblioteca.gestLibros.model.Libro;
import com.Biblioteca.gestLibros.repository.IAutorRepository;
import com.Biblioteca.gestLibros.repository.ICopiaRepository;
import com.Biblioteca.gestLibros.repository.ILibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LibroService implements ILibroService{


    private final ILibroRepository libroRepo;

    private final ICopiaRepository copirepo;

    private final IAutorRepository autrepo;

    @Override
    public List<ResponseLibroDto> libros() {

        List<ResponseLibroDto> libs = new ArrayList<>();
        for( Libro libro : libroRepo.findAll()){
            ResponseLibroDto response = new ResponseLibroDto();

            response.setIdLibro(libro.getId_libro());
            response.setIsbn(libro.getIsbn());
            response.setEditorial(libro.getEditorial());
            response.setTitulo(libro.getTitulo());
            response.setAnioPublicacion(libro.getAnioPublicacion());
            response.setAutorNombre(libro.getAutor().getNombre());
            libs.add(response);
        }
         return libs;
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

            for (int i = 0; i >= request.getCantidadCopias(); i++){
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
    public ResponseLibroDto editLibro(Long id_original, LibroEditDto editLibroDt) {

        Libro libroExist = libroRepo.findById(id_original).orElseThrow(()-> new RuntimeException("No existe ningun libro con el id proporcionado"));
        if(editLibroDt.hasTitulo()){
            libroExist.setTitulo(editLibroDt.getTitulo());
        }
        if(editLibroDt.hastEditorial()){
            libroExist.setEditorial(editLibroDt.getEditorial());
        }
        if(editLibroDt.hasIsbn()){
            libroExist.setIsbn(editLibroDt.getIsbn());
        }
        if(editLibroDt.hasAnioPublicacion()){
            libroExist.setAnioPublicacion(editLibroDt.getAnioPublicacion());
        }
        if(editLibroDt.hasIdAutor()){
            Autor autor = autrepo.findById(editLibroDt.getIdAutor()).orElseThrow(()->new RuntimeException("No se encontro el autor señalado"));
            libroExist.setAutor(autor);
        }

        //Guardar los cambios realizados
        Libro libroActualizado = libroRepo.save(libroExist);

        return convertirResponseDto(libroExist);
    }

    @Override
    public Optional<ResponseLibroDto> obtenerLibro(Long id) {
        return libroRepo.findById(id)
                .map(this::convertirResponseDto);
    }

    private ResponseLibroDto convertirResponseDto(Libro libro){
        ResponseLibroDto dto = new ResponseLibroDto();
        dto.setAnioPublicacion(libro.getAnioPublicacion());
        dto.setIdLibro(libro.getId_libro());
        dto.setTitulo(libro.getTitulo());
        dto.setIsbn(libro.getIsbn());
        dto.setAutorNombre(libro.getAutor().getNombre());
        dto.setEditorial(libro.getEditorial());
        return dto;
    }


}
