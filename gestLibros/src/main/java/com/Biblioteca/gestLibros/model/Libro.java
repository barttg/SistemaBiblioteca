package com.Biblioteca.gestLibros.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_libro")
    private Long id_libro;

    @Column(nullable = false)
    private String titulo;

    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;

    private String editorial;
    private String isbn;


    @ManyToOne
    @JoinColumn(name = "id_autor")
    //@JsonIgnore
    private Autor autor;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Copia> copias = new ArrayList<>();
}
