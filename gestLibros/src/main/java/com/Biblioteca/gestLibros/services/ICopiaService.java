package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.response.CopiaResponseDto;
import com.Biblioteca.gestLibros.dto.CopiaRquestDto;
import com.Biblioteca.gestLibros.dto.edit.CopiaEditDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICopiaService {

    public List<CopiaResponseDto> getCopias();

    public void createCopia(CopiaRquestDto request);

    public CopiaResponseDto findCopia(Long id_copia);

    public void deleteCopia(Long id_copia);

    public CopiaResponseDto editCopia(Long id_original, CopiaEditDto copiaEdit);
}
