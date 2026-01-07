package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.NuevaCategoriaDto;
import com.Biblioteca.gestLibros.dto.edit.CategoriaEditDto;
import com.Biblioteca.gestLibros.dto.response.CategoriaResponseDto;
import com.Biblioteca.gestLibros.model.Categoria;
import com.Biblioteca.gestLibros.repository.ICategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService implements ICategoriaService{

    private final ICategoriaRepository categoRepo;

    @Override
    public List<CategoriaResponseDto> categorias() {

        List<CategoriaResponseDto> response = new ArrayList<>();

        for (Categoria categoria : categoRepo.findAll()){
            CategoriaResponseDto dtoReponse = new CategoriaResponseDto();

            dtoReponse.setId_categoria(categoria.getId());
            dtoReponse.setNombre(categoria.getNombre());
            dtoReponse.setDescripcion(categoria.getDescripcion());
            response.add(dtoReponse);
        }

        return response;
    }

    @Override
    public void saveCategoria(NuevaCategoriaDto categoriadto) {

        Categoria categoria = new Categoria();

        categoria.setDescripcion(categoriadto.getDescripcion());
        categoria.setNombre(categoriadto.getNombre());

        categoRepo.save(categoria);
    }

    @Override
    public void deleteCategoria(Long idCategoria) {
        categoRepo.deleteById(idCategoria);
    }

    @Override
    public CategoriaResponseDto categoriaFind(Long idCategoria) {
        Categoria categoria = categoRepo.findById(idCategoria).orElseThrow(()-> new RuntimeException("No se encontro la categoria ingresada"));

        CategoriaResponseDto response = new CategoriaResponseDto();
        response.setId_categoria(categoria.getId());
        response.setDescripcion(categoria.getDescripcion());
        response.setNombre(categoria.getNombre());

        return response;
    }

    @Override
    public void editCategoria(Long id_original, CategoriaEditDto categEdito) {

        Categoria categoExist = categoRepo.findById(id_original).orElseThrow(()-> new RuntimeException("No se encontro la Categoria ingresada"));

        if (categEdito.hasNombre()){
            categoExist.setNombre(categEdito.getNombre());
        }

        if(categEdito.hasDescripcion()){
            categoExist.setDescripcion(categEdito.getDescripcion());
        }

    }
}
