
package org.example;

import GoOrderDTO.CodigoDescuentoDTO;
import Interfaces.IDescuentosBO;
import Interfaces.IDescuentosDAO;
import goorderpersistencia.DescuentosDAO;
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
    public CodigoDescuentoDTO BuscarDescuento(String codigo) throws NegocioException {
        try{
            return descuentosDAO.BuscarDescuento(codigo);
        
        }catch(PersistenciaException ex){
           throw new NegocioException("No se pudo consultar el codigo"); 
            
        }
    }

    @Override
    public CodigoDescuentoDTO CambiarEstadoDescuento(String codigo) throws NegocioException {
        try{
            return descuentosDAO.cambiarEstado(codigo);
        
        }catch(PersistenciaException ex){
           throw new NegocioException("No se pudo cambiar estado el codigo"); 
            
        }
    }
    
}
