package com.Biblioteca.gestLibros.services;

import com.Biblioteca.gestLibros.dto.response.CopiaResponseDto;
import com.Biblioteca.gestLibros.dto.CopiaRquestDto;
import com.Biblioteca.gestLibros.dto.edit.CopiaEditDto;
import com.Biblioteca.gestLibros.model.Copia;
import com.Biblioteca.gestLibros.model.DetallePrestamo;
import com.Biblioteca.gestLibros.model.Libro;
import com.Biblioteca.gestLibros.repository.ICopiaRepository;
import com.Biblioteca.gestLibros.repository.ILibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CopiaService implements ICopiaService{

    private final ILibroRepository libroRepo;

    private final ICopiaRepository copiRepo;

    @Override
    public List<CopiaResponseDto> getCopias() {

        List<CopiaResponseDto> responseList = new ArrayList<>();
        for(Copia copia : copiRepo.findAll()){
            CopiaResponseDto respuesta = new CopiaResponseDto();

            respuesta.setId_copia(copia.getId_copia());
            respuesta.setCodigo_copia(copia.getCodigoCopia());
            respuesta.setDisponible(copia.isDisponible());
            respuesta.setId_libro(copia.getLibro().getId_libro());
            for(DetallePrestamo detalle : copia.getDetallesPrestamo()){
                if(detalle.getCopia().equals(copia)){
                    respuesta.setId_prestamo(detalle.getPrestamo().getId_prestamo());
                }
            }
            responseList.add(respuesta);

        }
        return responseList;
    }

    @Override
    public void createCopia(CopiaRquestDto request) {
        Libro libro = libroRepo.findById(request.getId_libro()).orElseThrow(()-> new RuntimeException("No se encontro ningun libro con el id ingresado"));
        Copia  copia = new Copia();

        Random random = new Random();
        int numeroAleato = random.nextInt(90000) + 10000;
        String codigoCopia = "C.P" +  numeroAleato;

        copia.setCodigoCopia(codigoCopia);
        copia.setDisponible(true);
        copia.setLibro(libro);
        copia.setEstado(request.getEstado());
        copiRepo.save(copia);
    }


    @Override
    public CopiaResponseDto findCopia(Long id_copia) {
        Copia copia = copiRepo.findById(id_copia).orElseThrow(()->new RuntimeException("No se ecncontro ninguna copia con ese id"));
        CopiaResponseDto response = new CopiaResponseDto();

        response.setCodigo_copia(copia.getCodigoCopia());
        response.setId_copia(copia.getId_copia());
        response.setDisponible(copia.isDisponible());
        response.setId_libro(copia.getLibro().getId_libro());

        for(DetallePrestamo detalle : copia.getDetallesPrestamo()){
            if(detalle.getCopia().equals(copia)){
                response.setId_prestamo(detalle.getPrestamo().getId_prestamo());
            }
        }

        return response;
    }

    @Override
    public void deleteCopia(Long id_copia) {
        copiRepo.deleteById(id_copia);
    }

    @Override
    public CopiaResponseDto editCopia(Long id_original, CopiaEditDto copiaEdit) {
        Copia copiaExist = copiRepo.findById(id_original).orElseThrow(()-> new RuntimeException("No se encontro ninguna copia con el id ingresado"));
        if (copiaEdit.hasCodigoCopia()){
            copiaExist.setCodigoCopia(copiaEdit.getCodigoCopia());
        }
        if(copiaEdit.hasDisponilbe()){
            copiaExist.setDisponible(copiaEdit.isDisponible());
        }
        if(copiaEdit.hasIdlibro()){
            Libro libro = libroRepo.findById(copiaEdit.getIdLibro()).orElseThrow(()->new RuntimeException("No se encontro ingun libro que coincida con el id ingresado"));
            copiaExist.setLibro(libro);
        }
        if(copiaEdit.hasEstado()){
            copiaExist.setEstado(copiaEdit.getEstado());
        }
        //Guardar cambios
        copiRepo.save(copiaExist);
        return creandoRepsonse(copiaExist);
    }

    private CopiaResponseDto creandoRepsonse(Copia response){
        CopiaResponseDto  respuesta = new CopiaResponseDto();

        respuesta.setCodigo_copia(response.getCodigoCopia());
        respuesta.setId_copia(response.getId_copia());
        respuesta.setId_libro(response.getLibro().getId_libro());
        respuesta.setDisponible(response.isDisponible());

        for(DetallePrestamo detalle : response.getDetallesPrestamo()){
            if(detalle.getCopia().equals(response)){
                respuesta.setId_prestamo(detalle.getPrestamo().getId_prestamo());
            }
        }
        return respuesta;
    }

}
