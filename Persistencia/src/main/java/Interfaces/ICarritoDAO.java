/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import GoOrderDTO.CarritoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoSeleccionadoDTO;
import goorderpersistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author juanl
 */
public interface ICarritoDAO {
    
    public abstract CarritoDTO AgregarCarrito(CarritoDTO carrito) throws PersistenciaException;
    
    public abstract CarritoDTO ActualizarCarrito(CarritoDTO carrito) throws PersistenciaException;
    
    public abstract CarritoDTO LimpiarCarrito() throws PersistenciaException;
    
    public abstract CarritoDTO ObtenerCarrito() throws PersistenciaException;
    
    
}
