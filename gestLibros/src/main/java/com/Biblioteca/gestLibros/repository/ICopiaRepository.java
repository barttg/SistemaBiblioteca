package com.Biblioteca.gestLibros.repository;

import com.Biblioteca.gestLibros.model.Copia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICopiaRepository extends JpaRepository<Copia, Long> {
}
