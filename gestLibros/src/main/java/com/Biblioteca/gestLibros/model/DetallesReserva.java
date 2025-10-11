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
public class DetallesReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_reserva;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "id_copia")
    private Copia copia;

    private LocalDate fechaATomar;
}
