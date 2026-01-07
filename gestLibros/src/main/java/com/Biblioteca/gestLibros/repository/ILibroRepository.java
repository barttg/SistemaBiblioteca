package com.Biblioteca.gestLibros.repository;

import com.Biblioteca.gestLibros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILibroRepository extends JpaRepository<Libro, Long> {

    //Optional<Libro> findByTitle(String titulo);




}
