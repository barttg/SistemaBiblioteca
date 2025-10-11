package com.Biblioteca.gestLibros.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_libro;
    private String titulo;
    private int anioPublicacion;
    private String editorial;
    private String isbn;


    @ManyToOne
    @JoinColumn(name = "id_autor")
    //@JsonIgnore
    private Autor autor;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Copia> copias = new ArrayList<>();
}
