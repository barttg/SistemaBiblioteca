package com.Biblioteca.gestLibros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.Name;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detalles_prestamo")
public class DetallePrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_detalle")
    private Long id_detalle;

    @ManyToOne
    @JoinColumn(name = "id_prestamo")
    private Prestamo prestamo;

    @ManyToOne
    @JoinColumn(name = "id_copia")
    private Copia copia;

    private boolean devuelto;

    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolcion;

    private String observaciones;
}
