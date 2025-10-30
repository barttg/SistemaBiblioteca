package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.edit.PrestamoEditDto;
import com.Biblioteca.gestLibros.dto.LibroPrestadoDto;
import com.Biblioteca.gestLibros.dto.PrestamoRequestDto;
import com.Biblioteca.gestLibros.dto.response.PrestamoResponseDto;
import com.Biblioteca.gestLibros.model.*;
import com.Biblioteca.gestLibros.repository.ICopiaRepository;
import com.Biblioteca.gestLibros.repository.ILibroRepository;
import com.Biblioteca.gestLibros.repository.IPrestamoRepository;
import com.Biblioteca.gestLibros.repository.IUsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PrestamoService implements IPrestamoService {


    private final IPrestamoRepository prestRepo;

    private final ILibroRepository librorepo;

    private final LibroService libroserv;

    private final UsuarioService usuaserv;

    private final CopiaService copiserv;

    private final IUsuarioRepository userepo;

    private final ICopiaRepository copiRepo;

    @Override
    public List<PrestamoResponseDto> prestamosGet() {
        //Declaras la lista a responder
        List<PrestamoResponseDto> response = new ArrayList<>();

        for (Prestamo prestamo : prestRepo.findAll()) {
            //Declaras un elemento de esa lista
            PrestamoResponseDto dtoResponse = new PrestamoResponseDto();

            //Declaras la lista que pertenece a cada elemento
            List<LibroPrestadoDto> libros = new ArrayList<>();
            for(DetallePrestamo detalle : prestamo.getDetalles()){
                LibroPrestadoDto libroPrest = new LibroPrestadoDto();
                libroPrest.setDevuelto(detalle.isDevuelto());
                libroPrest.setTitulo(detalle.getCopia().getLibro().getTitulo());
                libroPrest.setCodigoCopia(detalle.getCopia().getCodigoCopia());
                libros.add(libroPrest);
            }
            //Seteas cada componente de esa sublista
            dtoResponse.setIdPrestamo(prestamo.getId_prestamo());
            dtoResponse.setFechaPrestamo(prestamo.getFecha_prestamo());
            dtoResponse.setNameUser(prestamo.getUsuario().getNombre());
            dtoResponse.setLibrosPrestados(libros);
            dtoResponse.setFechaDevolucionEstimada(prestamo.getFechaADevolver());
            dtoResponse.setEstado(prestamo.getEstado());
            if (prestamo.getEstado().equals("Devuelto")){
                dtoResponse.setFechaDevolucion(prestamo.getFechaDevolucion());
            }
            response.add(dtoResponse);
        }


        return response;
    }

    @Transactional
    @Override
    public void savePrestamo(PrestamoRequestDto prestamo) {

        Usuario user = userepo.findById(prestamo.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        /*Validaciones de usuario */
        List<Prestamo> prestamosUser = new ArrayList<>();
        for (Prestamo prest : prestRepo.findAll()) {
            if (prest.getUsuario().getId_usuario().equals(user.getId_usuario())) {
                prestamosUser.add(prest);
            }
        }

        if (prestamosUser.size() >= 3) {
            throw new IllegalArgumentException("El usuario ya cuenta con el maximo de prestamos vigentes");
        }

        List<Long> prestamosVencidos = new ArrayList<>();
        for (Prestamo prest : prestamosUser) {
            LocalDate prestamoVig = prest.getFechaADevolver();
            LocalDate hoy = LocalDate.now();

            if (prestamoVig.isBefore(hoy)) {
                prestamosVencidos.add(prest.getId_prestamo());
            }
        }
        if (!prestamosVencidos.isEmpty()) {
            String prestamosR = prestamosVencidos.stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(","));
            throw new IllegalArgumentException("El usuario tiene los siguientes prestamos vencidos. No puede solicitar otro hasta regularizar" +
                    prestamosR);
        }

        List<String> librosNoDisponibles = new ArrayList<>();
        for (Long idLibro : prestamo.getLibrosIds()) {
            Libro libroComp = librorepo.findById(idLibro).orElseThrow(() -> new RuntimeException("No se a encontrado ningun libro con el id " + idLibro));

            List<Copia> copiasDispo = new ArrayList<>();
            for(Copia copia : copiRepo.findAll()){
                if(copia.getLibro().getId_libro().equals(idLibro) && copia.isDisponible()){
                    copiasDispo.add(copia);
                }
            }

            if (copiasDispo.isEmpty()) {
                librosNoDisponibles.add(libroComp.getTitulo());
            }
        }
        if (!librosNoDisponibles.isEmpty()) {
            throw new IllegalArgumentException("No hay copias disponibles de los siguientes libros: " +
                    String.join(",", librosNoDisponibles));
        }
        if (prestamo.getDiasPrestamo() > 21){
            throw new IllegalArgumentException("Los dias solicitados son mas de lo permitido, el maximo son 21 dias");
        }

        /*Si el usuario pasa con las validaciones se comienza a armar el prestamo completo*/
        Prestamo presta = new Prestamo();
        presta.setFecha_prestamo(LocalDate.now());
        presta.setEstado("Vigente");
        presta.setUsuario(user);
        presta.setFechaADevolver(LocalDate.now().plusDays(prestamo.getDiasPrestamo()));


        List<DetallePrestamo> detalles = new ArrayList<>();

        for (Long idlibro : prestamo.getLibrosIds()) {
            Libro libro = librorepo.findById(idlibro).orElseThrow(() -> new IllegalArgumentException("Libro no encontrado"));
            DetallePrestamo detalle = new DetallePrestamo();


          //  int cantidad = prestamo.getLibrosIds().size();

            List<Copia> copiasTt = copiRepo.findAll();
            List<Copia> copiasLib = new ArrayList<>();

            for (Copia copia : copiasTt){
                if(copia.getLibro().getId_libro().equals(idlibro) && copia.isDisponible()){
                    copiasLib.add(copia);
                    //  copia.setDisponible(false);
                }
            }

            Copia copi =  copiasLib.get(0);
            copi.setDisponible(false);

            //for (Copia copia : copiaSolic) {

                detalle.setPrestamo(presta);
                detalle.setCopia(copi);
                detalle.setDevuelto(false);
                detalle.setFechaDevolcion(LocalDate.now().plusDays(prestamo.getDiasPrestamo()));

            detalles.add(detalle);
        }
        presta.setDetalles(detalles);
        prestRepo.save(presta);
        user.setPrestamosVig(user.getPrestamosVig() + 1);
    }

    @Override
    public void deletePrst(Long id_prestamo) {
        prestRepo.deleteById(id_prestamo);
    }

    @Override
    public PrestamoResponseDto findPrest(Long id_prestamo) {
        PrestamoResponseDto prestamoResponse = new PrestamoResponseDto();
        Prestamo prestamo = prestRepo.findById(id_prestamo).orElseThrow(()-> new RuntimeException("No se encontro el prestamo con ese id"));
        List<LibroPrestadoDto> libros = new ArrayList<>();
        for (DetallePrestamo detalle : prestamo.getDetalles()){
            LibroPrestadoDto libroPrest = new LibroPrestadoDto();
            libroPrest.setCodigoCopia(detalle.getCopia().getCodigoCopia());
            libroPrest.setDevuelto(detalle.isDevuelto());
            libroPrest.setTitulo(detalle.getCopia().getLibro().getTitulo());
            libros.add(libroPrest);
        }
        prestamoResponse.setEstado(prestamo.getEstado());
        prestamoResponse.setIdPrestamo(prestamo.getId_prestamo());
        prestamoResponse.setFechaPrestamo(prestamo.getFecha_prestamo());
        prestamoResponse.setFechaDevolucionEstimada(prestamo.getFechaADevolver());
        prestamoResponse.setLibrosPrestados(libros);
        prestamoResponse.setNameUser(prestamo.getUsuario().getNombre());

        if(prestamo.getEstado().equals("Devuelto")){
            prestamoResponse.setFechaDevolucion(prestamo.getFechaDevolucion());
        }
        return prestamoResponse;
    }

    @Override
    public PrestamoResponseDto editPrstm(Long id_prestamo, PrestamoEditDto prestEdit) {
        Prestamo prestamoExist = prestRepo.findById(id_prestamo).orElseThrow(()->new RuntimeException("No se encontro ningun prestamo con ese id"));

        if(prestEdit.hasEstado()){
            prestamoExist.setEstado(prestEdit.getEstado());
        }
        if(prestEdit.hasFechaPrestamo()){
            prestamoExist.setFecha_prestamo(prestEdit.getFechaPrestamo());
        }
        if(prestEdit.hasIdUsuario()){
            Usuario usuario =  userepo.findById(prestEdit.getId_usuario()).orElseThrow(()->new RuntimeException("No se encontro el usuario con el id ingresado"));
            prestamoExist.setUsuario(usuario);
        }
        if(prestEdit.hasFechaDevolucionn()){
            prestamoExist.setFechaADevolver(prestEdit.getFechaDevolucion());
        }
        //Guardarmos los cambios realizados
        prestRepo.save(prestamoExist);
        return armandoReponse(prestamoExist);

    }
    private PrestamoResponseDto armandoReponse(Prestamo prestamo){
        PrestamoResponseDto response = new PrestamoResponseDto();
        List<LibroPrestadoDto> libros = new ArrayList<>();

        for (DetallePrestamo detalle : prestamo.getDetalles()){
            LibroPrestadoDto libroPrest = new LibroPrestadoDto();
            libroPrest.setTitulo(detalle.getCopia().getLibro().getTitulo());
            libroPrest.setDevuelto(detalle.isDevuelto());
            libroPrest.setCodigoCopia(detalle.getCopia().getCodigoCopia());
            libros.add(libroPrest);
        }
        //Seteamos valores a el response
        response.setNameUser(prestamo.getUsuario().getNombre());
        response.setEstado(prestamo.getEstado());
        response.setIdPrestamo(prestamo.getId_prestamo());
        response.setFechaDevolucionEstimada(prestamo.getFechaADevolver());
        response.setFechaPrestamo(prestamo.getFecha_prestamo());
        response.setLibrosPrestados(libros);
        return response;
    }


}