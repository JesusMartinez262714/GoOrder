/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import GoOrderDTO.SucursalDTO;
import goorderpersistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author juanl
 */
public interface ISucursalesDAO {
    
    public List<SucursalDTO> consultarSucursales() throws PersistenciaException;
    
}
