package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.edit.UsuarioEditDto;
import com.Biblioteca.gestLibros.dto.UsuarioDto;
import com.Biblioteca.gestLibros.model.Prestamo;
import com.Biblioteca.gestLibros.model.Reserva;
import com.Biblioteca.gestLibros.model.Usuario;
import com.Biblioteca.gestLibros.repository.IPrestamoRepository;
import com.Biblioteca.gestLibros.repository.IReservaRepository;
import com.Biblioteca.gestLibros.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
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
        user.setReservas(0);
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
    public void editUser(Long id_original, UsuarioEditDto userEdit) {
        Usuario userExist = usuarioRepo.findById(id_original).orElseThrow(()-> new RuntimeException("No se encontro un usuario con ese Id"));

        if(userEdit.hasNombre()){
            userExist.setNombre(userExist.getNombre());
        }
        if(userEdit.hasApellido()){
            userExist.setApellido(userExist.getApellido());
        }
        if(userEdit.hasTiposUser()){
            userExist.setTipoUser(userEdit.getTipoUser());
        }
        if(userEdit.hasEmail()){
            userExist.setEmail(userEdit.getEmail());
        }
        if(userEdit.hasReservas()){
            userExist.setReservas(userEdit.getReservas());
        }
        if(userEdit.hastPrestamosVig()){
            userExist.setPrestamosVig(userEdit.getPrestamosVig());
        }
        // guardar los cambios realizados
        usuarioRepo.save(userExist);

    }

    @Override
    public UsuarioDto usuarioPrest(Long id_usuario) {
        Usuario user = this.findUser(id_usuario);
        UsuarioDto dto = new UsuarioDto();
        List<Long> reservas = new ArrayList<>();
        List<Long> prestamos = new ArrayList<>();
        for( Prestamo prest :  prestRepo.findAll()){
            if (prest.getUsuario().getId_usuario().equals(id_usuario)){
                prestamos.add(prest.getId_prestamo());
            }
        }
        for(Reserva reserv : reseRepo.findAll()){
            if(reserv.getUsar().getId_usuario().equals(id_usuario)){
                reservas.add(reserv.getId_reserva());
            }
        }
        dto.setId_usuario(id_usuario);
        dto.setNombre(user.getNombre());
        dto.setPrestamos(prestamos);
        dto.setReservas(reservas);
        return dto;
    }

}
