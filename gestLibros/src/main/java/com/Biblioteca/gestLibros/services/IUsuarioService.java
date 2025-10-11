package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.UsuarioDto;
import com.Biblioteca.gestLibros.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsuarioService {

    public List<Usuario> GetUsuarios();

    public void saveUser(Usuario user);

    public void deleteUser(Long id_usuario);

    public Usuario findUser(Long id_usuario);

    public void editUser(Usuario user);

    public UsuarioDto usuarioPrest(Long id_usuario);
}
