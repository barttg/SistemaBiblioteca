package com.Biblioteca.gestLibros.dto.Edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEditDto {


    private String nombre;
    private String apellido;
    private String email;
    private String tipoUser;
    private Integer prestamosVig;
    private Integer reservas;


    //Metodos utilitarios para validar si algun campo se mando vacio
    public boolean hasNombre(){ return nombre !=null; }
    public boolean hasEmail(){ return email !=null; }
    public boolean hasApellido(){ return apellido !=null; }
    public boolean hasTiposUser(){ return  tipoUser !=null; }
    public boolean hastPrestamosVig(){ return  prestamosVig !=null; }
    public boolean hasReservas(){ return reservas !=null; }


}
