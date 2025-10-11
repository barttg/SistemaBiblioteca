package com.Biblioteca.gestLibros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DetallePrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_detalle;

    @ManyToOne
    @JoinColumn(name = "id_prestamo")
    private Prestamo prestamo;

    @ManyToOne
    @JoinColumn(name = "id_copia")
    private Copia copia;

    private boolean devuelto;
    private LocalDate fechaDevolcion;
    private String observaciones;
}
