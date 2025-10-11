package com.Biblioteca.gestLibros.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_reserva;
    private LocalDate fechaRserva;
    private LocalDate fechaExpirac;
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usar;
    @OneToMany
    private List<Libro> libros;


}
