package com.Biblioteca.gestLibros.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_reserva;

    @Column(name = "fechaA_Tomar")
    private LocalDate fechaATomar;

    @Column(name = "fecha_expiracion")
    private LocalDate fechaExpirac;

    private LocalDate fechaReserva;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usar;

    @OneToMany
    @Column(name = "libros")
    private List<Libro> libros;


}
