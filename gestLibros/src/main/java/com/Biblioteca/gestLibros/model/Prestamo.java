package com.Biblioteca.gestLibros.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_prestamo;
    private LocalDate fecha_prestamo;
    private LocalDate fecha_devolucion;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    Usuario usuario;

    //Detalles de el prestamo en cuestion, sobre que copias de cada libro  se prestaron

    @OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL)
    private List<DetallePrestamo> detalles = new ArrayList<>();

}
