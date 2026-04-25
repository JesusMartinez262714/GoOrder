/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import GoOrderDTO.CodigoDescuentoDTO;
import goorderpersistencia.PersistenciaException;

/**
 *
 * @author juanl
 */
public interface IDescuentosDAO {
    
    public abstract CodigoDescuentoDTO AplicarCodigo(String codigo) throws PersistenciaException; 
    
}
