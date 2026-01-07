package com.Biblioteca.gestLibros.dto.edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaEditDto {
    private String nombre;
    private String descripcion;

    //Metodos utilitarios para validar si algun campo se envio vacio

    public boolean hasNombre(){return nombre != null; }
    public boolean hasDescripcion(){return descripcion != null; }
}
