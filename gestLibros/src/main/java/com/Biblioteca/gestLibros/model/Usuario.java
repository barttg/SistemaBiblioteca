package com.Biblioteca.gestLibros.model;

import jakarta.persistence.*;
import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_usuario;
    private String nombre;
    private String apellido;
    private String email;
    private String tipoUser;
    private int prestamosVig;
}
