package com.Biblioteca.gestLibros.model;

import jakarta.persistence.*;
import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Long id_usuario;

    @Column(nullable = false)
    private String nombre;
    private String apellido;
    private String email;

    @Column(name = "tipo_usuario")
    private String tipoUser;

    @Column(name = "prestamos_vigentes")
    private Integer prestamosVig;

    private Integer reservas;
}
