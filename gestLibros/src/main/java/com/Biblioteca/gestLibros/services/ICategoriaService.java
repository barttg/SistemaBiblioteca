package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.NuevaCategoriaDto;
import com.Biblioteca.gestLibros.dto.edit.CategoriaEditDto;
import com.Biblioteca.gestLibros.dto.response.CategoriaResponseDto;
import com.Biblioteca.gestLibros.model.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoriaService {

    public List<CategoriaResponseDto> categorias();

    public void saveCategoria(NuevaCategoriaDto categoria);

    public void deleteCategoria(Long idCategoria);

    public CategoriaResponseDto categoriaFind(Long idCategoria);

    public void editCategoria(Long id_original, CategoriaEditDto categEdito);
}
