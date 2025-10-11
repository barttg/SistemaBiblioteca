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
public class Copia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_copia;
    private String codigoCopia;
    private boolean disponible;
    private String estado;// "nuevo", "desgastado", "malo"

    @ManyToOne
    @JoinColumn(name = "id_libro")
    //@JsonIgnore
    private Libro libro;

    @OneToMany(mappedBy = "copia")
    private List<DetallePrestamo> detallesPrestamo = new ArrayList<>();

}
