package com.Biblioteca.gestLibros.repository;

import com.Biblioteca.gestLibros.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}
