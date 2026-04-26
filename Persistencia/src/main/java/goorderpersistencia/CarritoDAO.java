/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package goorderpersistencia;

import GoOrderDTO.CarritoDTO;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.ProductoSeleccionadoDTO;
import Interfaces.ICarritoDAO;
import java.util.List;

/**
 *
 * @author juanl
 */
public class CarritoDAO implements ICarritoDAO{

    private CarritoDTO carritoGuardado;
    
    public CarritoDAO() {
        this.carritoGuardado = new CarritoDTO();
    }

    @Override
    public CarritoDTO AgregarCarrito(CarritoDTO carrito) throws PersistenciaException {
        return this.carritoGuardado = carrito;
        
    }

    @Override
    public CarritoDTO ActualizarCarrito(CarritoDTO carrito) throws PersistenciaException {
         return this.carritoGuardado = carrito;
    }

    @Override
    public CarritoDTO LimpiarCarrito() throws PersistenciaException {
        return this.carritoGuardado = new CarritoDTO();
    }

    @Override
    public CarritoDTO ObtenerCarrito() throws PersistenciaException {
        return carritoGuardado;
    }

    
    
}
