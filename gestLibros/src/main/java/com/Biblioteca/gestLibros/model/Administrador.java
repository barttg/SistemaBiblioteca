package com.Biblioteca.gestLibros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Aministradores")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String nombre;
    private int numAdmin;

}
