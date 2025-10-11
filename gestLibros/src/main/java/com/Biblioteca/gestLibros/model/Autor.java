package com.Biblioteca.gestLibros.model;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_autor;
    private String nombre;
    private String biografia;
    private String nacionalidad;
    private LocalDate fechaNac;

}
