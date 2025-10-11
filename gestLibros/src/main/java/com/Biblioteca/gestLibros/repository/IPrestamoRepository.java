package com.Biblioteca.gestLibros.repository;

import com.Biblioteca.gestLibros.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrestamoRepository extends JpaRepository<Prestamo, Long> {
}
