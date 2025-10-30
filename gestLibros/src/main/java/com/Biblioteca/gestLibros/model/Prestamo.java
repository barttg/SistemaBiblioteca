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
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_prestamo")
    private Long id_prestamo;

    private LocalDate fecha_prestamo;
    private LocalDate fechaADevolver;
    private LocalDate fechaDevolucion;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    Usuario usuario;

    //Detalles de el prestamo en cuestion, sobre que copias de cada libro  se prestaron

    @OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL)
    @Column(name = "detalles")
    private List<DetallePrestamo> detalles = new ArrayList<>();

}
