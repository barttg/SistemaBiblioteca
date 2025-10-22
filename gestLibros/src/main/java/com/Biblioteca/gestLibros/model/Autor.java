package com.Biblioteca.gestLibros.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_autor")
    private Long id_autor;

    @Column(nullable = false)
    private String nombre;
    private String nacionalidad;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNac;

}
