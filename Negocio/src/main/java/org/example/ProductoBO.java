
package org.example;

import Adapters.DtoEntityProduct;
import Entidades.Producto;
import GoOrderDTO.ProductoDTO;
import Interfaces.IProductoBO;
import Interfaces.IProductoDAO;
import goorderpersistencia.PersistenciaException;
import goorderpersistencia.ProductoDAO;

/**
 *
 * @author maild
 */
public class ProductoBO implements IProductoBO {

    private IProductoDAO productoDAO;
    
    public ProductoBO() {
        productoDAO = new ProductoDAO();
    }
    
    @Override
    public ProductoDTO buscarProducto(String nombreProducto) throws NegocioException {
        try {
            Producto producto = productoDAO.buscarProducto(nombreProducto);
            return DtoEntityProduct.toDTO(producto);
        } catch (PersistenciaException e) {
            throw new NegocioException("No fue posible realizar busqueda.");
        }
    }    
}