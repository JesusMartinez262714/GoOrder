/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import GoOrderDTO.CodigoDescuentoDTO;
import Interfaces.IDescuentosBO;
import Interfaces.IDescuentosDAO;
import goorderpersistencia.PersistenciaException;

/**
 *
 * @author juanl
 */
public class DescuentosBO implements IDescuentosBO{

    private IDescuentosDAO descuentosDAO;
    
    public DescuentosBO(IDescuentosDAO descuentosDAO) {
        this.descuentosDAO = descuentosDAO;
    }

    
    
    @Override
    public CodigoDescuentoDTO aplicarCodigo(String codigo) throws NegocioException {
        try{
            return descuentosDAO.AplicarCodigo(codigo);
        
        }catch(PersistenciaException ex){
           throw new NegocioException("No se pudo consultar el codigo"); 
            
        }
    }
    
}
