package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.dto.edit.UsuarioEditDto;
import com.Biblioteca.gestLibros.dto.UsuarioDto;
import com.Biblioteca.gestLibros.model.Usuario;
import com.Biblioteca.gestLibros.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {


    private final UsuarioService userVice;

    @GetMapping
    public ResponseEntity<List<Usuario>> users(){
        return new ResponseEntity<>(userVice.GetUsuarios(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> nuevoUs(@RequestBody Usuario usuario){
        userVice.saveUser(usuario);
        return new ResponseEntity<>("El usuario a sido registrado exitosamente", HttpStatus.CREATED);
    }


    @GetMapping("/{id_usuario}")
    public ResponseEntity<Usuario> usuario(@PathVariable Long id_usuario){

        return new ResponseEntity<>(userVice.findUser(id_usuario), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id_usuario}")
    public ResponseEntity<String> eliminar(@PathVariable Long id_usuario){
        userVice.deleteUser(id_usuario);
        return new ResponseEntity<>("El usuario a sido eliminado exitosamente", HttpStatus.OK);
    }

    @PutMapping("/edit/{id_usuario}")
    public ResponseEntity<String> editUser(@PathVariable Long id_usuario, @RequestBody UsuarioEditDto user){
        userVice.editUser(id_usuario, user);
        return new ResponseEntity<>("El usuario a sido actualizado exitosamente", HttpStatus.OK);
    }

    @GetMapping("/List/{id_usuario}")
    public ResponseEntity<UsuarioDto> userDto(@PathVariable Long id_usuario){
        return new ResponseEntity<>(userVice.usuarioPrest(id_usuario), HttpStatus.OK);
    }
}
