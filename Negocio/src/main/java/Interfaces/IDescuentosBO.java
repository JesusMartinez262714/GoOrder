/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import GoOrderDTO.CodigoDescuentoDTO;
import org.example.NegocioException;

/**
 *
 * @author juanl
 */
public interface IDescuentosBO {
    
    public CodigoDescuentoDTO aplicarCodigo(String codigo) throws NegocioException;
}
