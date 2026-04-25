
package org.itson.realizarpedidocue;


import GoOrderDTO.ProductoDTO;
import java.util.List;

import GoOrderDTO.SucursalDTO;
import org.example.NegocioException;

/**
 *
 * @author
 */
public interface IRealizarPedidoCUE {
    
    public abstract ProductoDTO buscarProducto(String nombreProducto) throws NegocioException;
    
    public abstract List<ProductoDTO> listarProductos() throws NegocioException;

    List<SucursalDTO> consultarSucursales() throws NegocioException;
}