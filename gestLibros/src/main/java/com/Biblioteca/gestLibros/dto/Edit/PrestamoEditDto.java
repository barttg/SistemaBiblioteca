package com.Biblioteca.gestLibros.dto.Edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoEditDto {

    private Long id_usuario;
    private Long id_copia;
    private String estado;

    //metodos utilitarios para validar si algun campo no fue asignado
    public boolean hasIdUsuario(){ return id_usuario !=null; }
    public boolean
}
