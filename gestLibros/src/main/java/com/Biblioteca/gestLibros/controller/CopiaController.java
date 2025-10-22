package com.Biblioteca.gestLibros.controller;

import com.Biblioteca.gestLibros.dto.response.CopiaResponseDto;
import com.Biblioteca.gestLibros.dto.CopiaRquestDto;
import com.Biblioteca.gestLibros.dto.edit.CopiaEditDto;
import com.Biblioteca.gestLibros.services.CopiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/copia")
public class CopiaController {

    private final CopiaService copService;

    @GetMapping
    public List<CopiaResponseDto> copias(){
        return copService.getCopias();
    }

    @PostMapping("/crear")
    public String crearCopia(@RequestBody CopiaRquestDto copia){
        copService.createCopia(copia);
        return"Se a registrado con exito la nueva copia";
    }

     @GetMapping("/{id_copia}")
    public CopiaResponseDto copia(@PathVariable Long id_copia){
      return copService.findCopia(id_copia);
     }

     @DeleteMapping("/eliminar/{id_copia}")
    public String eliminar(@PathVariable Long id_copia){
        copService.deleteCopia(id_copia);
        return"Se a eliminado correctamente la copia";
     }

     @PutMapping("/editar/{id_copia}")
    public CopiaResponseDto devolver(@PathVariable Long id_copia, @RequestBody CopiaEditDto copian){
        return copService.editCopia(id_copia, copian);
     }
}
