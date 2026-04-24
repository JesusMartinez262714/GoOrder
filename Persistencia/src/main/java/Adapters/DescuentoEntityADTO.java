/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapters;

import Entidades.CodigoDescuento;
import Enums.EstadoCodigoDesc;
import GoOrderDTO.CodigoDescuentoDTO;

/**
 *
 * @author juanl
 */
public class DescuentoEntityADTO {
    
    public static CodigoDescuentoDTO converitADTO(CodigoDescuento codigo){
        if(codigo == null){
            return null;
        }
       
        EstadoCodigoDesc estado = EstadoCodigoDesc.valueOf(codigo.getEstado().name());
        CodigoDescuentoDTO codigoDto = new CodigoDescuentoDTO(
                codigo.getCodigo(),
                codigo.getMonto(),
                estado
        );
                
                
        return codigoDto;
    }
}
