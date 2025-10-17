package com.Biblioteca.gestLibros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "copias")
public class Copia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_copia")
    private Long id_copia;

    private String codigoCopia;
    private boolean disponible;
    private String estado;// "nuevo", "desgastado", "malo"

    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libro libro;

    @OneToMany(mappedBy = "copia")
    @Column(name = "detalles_prestamo")
    private List<DetallePrestamo> detallesPrestamo = new ArrayList<>();

}
