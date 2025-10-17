package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.Edit.UsuarioEditDto;
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
    public UsuarioDto usuarioPrest(Long id_original, UsuarioEditDto editDto) {

        Usuario usuarioExist = usuarioRepo.findById(id_original).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        if(editDto.hasNombre()){
            usuarioExist.setNombre(editDto.getNombre());
        }
        if(editDto.hasApellido()){
            usuarioExist.setApellido(editDto.getApellido());
        }
        if(editDto.hasEmail()){
            usuarioExist.setEmail(editDto.getEmail());
        }
        if(editDto.hasTiposUser()){
            usuarioExist.setTipoUser(editDto.getTipoUser());
        }
        if(editDto.hastPrestamosVig()){
            usuarioExist.setPrestamosVig(editDto.getPrestamosVig());
        }
        //guardar cambio
        usuarioRepo.save(usuarioExist);
        return null;
    }

    private UsuarioDto crearDto(Usuario usuario){
        UsuarioDto dto= new UsuarioDto();
        dto.setId_usuario(usuario.getId_usuario());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setPrestamos(usuario.getPrestamosVig());
        dto.setTipoUser(usuario.getTipoUser());
        dto.setEmail(usuario.getEmail());
    }
}
