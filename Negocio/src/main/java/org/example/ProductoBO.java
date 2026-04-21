
package org.example;

import Adapters.DtoEntityProduct;
import Entitys.Producto;
import GoOrderDTO.ProductoDTO;
import Interfaces.IProductoBO;
import Interfaces.IProductoDAO;
import goorderpersistencia.PersistenciaException;
import goorderpersistencia.ProductoDAO;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ProductoDTO> listarProductos() throws NegocioException {
        try {
            List<Producto> lista = productoDAO.listarProductos();
            List<ProductoDTO> listaDTO = new ArrayList<>();

            for (Producto p: lista) {
                listaDTO.add(DtoEntityProduct.toDTO(p));
            }
            return listaDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("No fue posible consultar productos.");
        }
    }

}