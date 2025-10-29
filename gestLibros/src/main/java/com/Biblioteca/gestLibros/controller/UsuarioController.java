package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.dto.edit.UsuarioEditDto;
import com.Biblioteca.gestLibros.dto.UsuarioDto;
import com.Biblioteca.gestLibros.model.Usuario;
import com.Biblioteca.gestLibros.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {


    private final UsuarioService userVice;

    @GetMapping
    public List<Usuario> users(){
        return userVice.GetUsuarios();
    }

    @PostMapping("/crear")
    public String nuevoUser(@RequestBody Usuario user){
        userVice.saveUser(user);
        return"El usuario se a registrado con exito!";
    }

    @GetMapping("/{id_usuario}")
    public Usuario usuariobusq(@PathVariable Long id_usuario){
        return userVice.findUser(id_usuario);
    }

    @DeleteMapping("/eliminar/{id_usuario}")
    public String elimnar(@PathVariable Long id_usuario){
        userVice.deleteUser(id_usuario);
        return "El usuario " + id_usuario + "  a sido eliminado exitosamente";
    }

    @PutMapping("/edit/{id_usuario}")
    public String editUser(@PathVariable Long id_usuario, @RequestBody UsuarioEditDto user){
        userVice.editUser(id_usuario, user);
        return "El usuario a sido actualizado exitosamente";
    }

    @GetMapping("/List/{id_usuario}")
    public UsuarioDto userdto(@PathVariable Long id_usuario){
        return userVice.usuarioPrest(id_usuario);
    }
}
