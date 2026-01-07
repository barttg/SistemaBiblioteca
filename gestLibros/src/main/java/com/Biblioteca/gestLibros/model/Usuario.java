package com.Biblioteca.gestLibros.model;

import com.Biblioteca.gestLibros.model.enums.TipoUsuario;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipoUsuario;

    @Column(name = "prestamos_vigentes")
    private Integer prestamosVig;

    private Integer reservas;
}
