package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.UsuarioDto;
import com.Biblioteca.gestLibros.model.Prestamo;
import com.Biblioteca.gestLibros.model.Reserva;
import com.Biblioteca.gestLibros.model.Usuario;
import com.Biblioteca.gestLibros.repository.IPrestamoRepository;
import com.Biblioteca.gestLibros.repository.IReservaRepository;
import com.Biblioteca.gestLibros.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {


    private final IUsuarioRepository usuarioRepo;

    private final IReservaRepository reseRepo;

    private final IPrestamoRepository prestRepo;

    @Override
    public List<Usuario> GetUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public void saveUser(Usuario user) {
        user.setPrestamosVig(0);
        usuarioRepo.save(user);
    }

    @Override
    public void deleteUser(Long id_usuario) {

        usuarioRepo.deleteById(id_usuario);
    }

    @Override
    public Usuario findUser(Long id_usuario) {
        return usuarioRepo.findById(id_usuario).orElseThrow(()->new RuntimeException("El usuario no a sido encontrado"));
    }

    @Override
    public void editUser(Usuario user) {
        Usuario usuario = this.findUser(user.getId_usuario());
        usuario.setPrestamosVig(user.getPrestamosVig());
        usuario.setEmail(user.getEmail());
        usuario.setNombre(user.getNombre());
        usuario.setApellido(user.getApellido());
        usuario.setTipoUser(user.getTipoUser());
        //usuario.setPrestamosL(user.getPrestamosL());
        this.saveUser(user);
    }

    @Override
    public UsuarioDto usuarioPrest(Long id_usuario) {

        UsuarioDto usuarioConPrest = new UsuarioDto();
        Usuario user = usuarioRepo.findById(id_usuario).orElseThrow(()-> new RuntimeException("No existe ningun usuario registrado" +
                "con ese id, vuelve a intenarlo "));
        List<Reserva> reservaList = new ArrayList<>();
        List<Prestamo> prestamosL = new ArrayList<>();
        for(Prestamo  presta : prestRepo.findAll()){
            if(presta.getUsuario().getId_usuario().equals(id_usuario)){
                prestamosL.add(presta);
            }
        }
        for(Reserva reserv : reseRepo.findAll()){
            if (reserv.getUsar().getId_usuario().equals(id_usuario)){
                reservaList.add(reserv);
            }
        }
        usuarioConPrest.setId_usuario(id_usuario);
        usuarioConPrest.setNombreUsuario(user.getNombre());
        usuarioConPrest.setPrestamos(prestamosL);
        usuarioConPrest.setReservas(reservaList);
        return usuarioConPrest;
    }
}
