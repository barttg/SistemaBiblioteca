package com.Biblioteca.gestLibros.dto.edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CopiaEditDto {
    private String codigoCopia;
    private boolean disponible;
    private String estado;
    private Long idLibro;
    private Long idDetalle;

    //Metodos utilitarios para validar si en el envio un campo no fue especificado
    public boolean hasCodigoCopia(){ return codigoCopia !=null; }
    public boolean hasDisponilbe(){ return disponible; }
    public boolean hasEstado(){ return estado !=null; }
    public boolean hasIdlibro(){ return idLibro !=null; }
   // public boolean hasIdDetalle(){ return idDetalle =!null; }
}
