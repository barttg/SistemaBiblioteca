package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.PrestamoRequestDto;
import com.Biblioteca.gestLibros.model.*;
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
public class PrestamoService implements IPrestamoService{


    private final IPrestamoRepository prestRepo;

    private final ILibroRepository librorepo;

    private final LibroService libroserv;

    private final UsuarioService usuaserv;

    private final IUsuarioRepository userepo;

    @Override
    public List<Prestamo> prestamosGet() {
        return prestRepo.findAll();
    }

    @Transactional
    @Override
    public void savePrestamo(PrestamoRequestDto prestamo) {

        Usuario user = userepo.findById(prestamo.getUsuarioId()).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));


      /*Validaciones de usuario */
        List<Prestamo> prestamosUser = new ArrayList<>();
        for(Prestamo prest: this.prestamosGet()){
            if (prest.getUsuario().getId_usuario().equals(user.getId_usuario())){
                prestamosUser.add(prest);
            }
        }

        if (prestamosUser.size() >= 3){
            throw new IllegalArgumentException("El ya cuenta con el maximo de prestamos vigentes");
        }

        List<Long> prestamosVencidos = new ArrayList<>();
        for(Prestamo prest : prestamosUser){
            LocalDate vigPrestamo = prest.getFecha_devolucion();
            LocalDate hoy = LocalDate.now();

            if(vigPrestamo.isBefore(hoy)){
                prestamosVencidos.add(prest.getId_prestamo());
            }
        }
        if(!prestamosVencidos.isEmpty()){
            String prestamosR = prestamosVencidos.stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(","));
            throw new IllegalArgumentException("El usuario tiene los siguientes prestamos vencidos. No puede solicitar otro hasta regularizar" +
                    prestamosR);
        }

        List<String> librosNoDisponibles = new ArrayList<>();
        for(Long idLibro : prestamo.getLibrosIds()) {
            Libro libroComp = librorepo.findById(idLibro).orElseThrow(() -> new RuntimeException("No se a encontrado ningun libro con el id " + idLibro));
            if (libroComp.getCopias().equals(0)) {
                librosNoDisponibles.add(libroComp.getTitulo());
            }
        }
        if (!librosNoDisponibles.isEmpty()){
            throw new IllegalArgumentException("No hay copias disponibles de los siguientes libros" +
                    String.join(",", librosNoDisponibles));
        }

        /*Si el usuario pasa con las validaciones se comienza a aramar el prestamo completo*/
        Prestamo presta = new Prestamo();
        presta.setFecha_prestamo(LocalDate.now());
        presta.setEstado("Vigente");
        presta.setUsuario(user);
        presta.setFecha_devolucion(LocalDate.now().plusDays(prestamo.getDiasPrestamo()));

        List<DetallePrestamo> detalles = new ArrayList<>();

        for (Long idlibro : prestamo.getLibrosIds()){
            Libro libro = librorepo.findById(idlibro).orElseThrow(()-> new IllegalArgumentException("Libro no encontrado"));

            int cantidad = prestamo.getLibrosIds().size();

            List<Copia> copiaSolic = libro.getCopias().subList(0,cantidad);

            for (Copia copia : copiaSolic){
                DetallePrestamo detalle = new DetallePrestamo();
                detalle.setPrestamo(presta);
                detalle.setCopia(copia);
                detalle.setDevuelto(false);
                detalle.setFechaDevolcion(LocalDate.now().plusDays(prestamo.getDiasPrestamo()));
            }
        }

        prestRepo.save(presta);

    }

@Override
public void deletePrst(Long id_prestamo) {
    prestRepo.deleteById(id_prestamo);
}

@Override
public Prestamo findPrest(Long id_prestamo) {
    return prestRepo.findById(id_prestamo).orElseThrow(()-> new RuntimeException("el prestamo no existe "));
}

@Override
public void editPrstm(Prestamo prest) {
    Prestamo pretsmo = this.findPrest(prest.getId_prestamo());
    pretsmo.setVigente(prest.isVigente());
    pretsmo.setFecha_prestamo(prest.getFecha_prestamo());
    pretsmo.setUsuario(prest.getUsuario());
    pretsmo.setFecha_devolucion(prest.getFecha_devolucion());
    pretsmo.setLibros(prest.getLibros());
    this.savePrestamo(pretsmo);
}
}
