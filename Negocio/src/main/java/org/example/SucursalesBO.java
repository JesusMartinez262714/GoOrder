/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import GoOrderDTO.SucursalDTO;
import Interfaces.ISucursalesBO;
import Interfaces.ISucursalesDAO;
import goorderpersistencia.PersistenciaException;
import goorderpersistencia.SucursalesDAO;
import java.util.List;

/**
 *
 * @author juanl
 */
public class SucursalesBO implements ISucursalesBO{

    private ISucursalesDAO sucursalDAO;

    public SucursalesBO( ) {
        this.sucursalDAO = new SucursalesDAO();
    }
    
    
    @Override
    public List<SucursalDTO> consultarSucursales() throws NegocioException {
        try{
            List<SucursalDTO> sucursales = sucursalDAO.consultarSucursales();
            if(sucursales.isEmpty()){
                throw new NegocioException("No hay sucursales disponibles");
            }
            return sucursales;
        
        }catch(PersistenciaException e) {
            throw new NegocioException("No fue posible consultar productos.");
        }
    }
    
}
