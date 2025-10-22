package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.AutrDto;
import com.Biblioteca.gestLibros.dto.edit.AutorEditDto;
import com.Biblioteca.gestLibros.model.Autor;

import java.util.List;

public interface IAutorService {

    public List<Autor> autGet();

    public void saveAutor(Autor autor);

    public Autor findaut(Long id_autor);

    public void deleteAutor(Long id_autor);

    public void editAutor(Long id_autor, AutorEditDto autor);

    public AutrDto autorLibros(Long id_autor);
}
