package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.PrestamoRequestDto;
import com.Biblioteca.gestLibros.model.Copia;
import com.Biblioteca.gestLibros.model.Prestamo;
import com.Biblioteca.gestLibros.model.Usuario;
import com.Biblioteca.gestLibros.repository.ILibroRepository;
import com.Biblioteca.gestLibros.repository.IPrestamoRepository;
import com.Biblioteca.gestLibros.repository.IUsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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

        Prestamo presta = new Prestamo();
        Usuario user = userepo.findById(prestamo.getUsuarioId()).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        List<Copia> copiasAprestar = new ArrayList<>();

        for(Long idLibro : prestamo.getLibrosIds()){
        }

        presta.setFecha_prestamo(LocalDate.now());
        presta.setEstado("Vigente");
        presta.setUsuario(user);
        presta.setFecha_devolucion(LocalDate.now().plusDays(20));


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
