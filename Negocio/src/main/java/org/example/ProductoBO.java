
package org.example;

import Adapters.DtoEntityProduct;
import Entitys.Producto;
import GoOrderDTO.ProductoDTO;
import Interfaces.IProductoBO;
import Interfaces.IProductoDAO;
import goorderpersistencia.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maild
 */
public class ProductoBO implements IProductoBO {

    private IProductoDAO productoDAO;

    public ProductoBO(IProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public List<ProductoDTO> buscarProducto(String nombreProducto) throws NegocioException {
        try {
            List<Producto> listaEntity = productoDAO.buscarProducto(nombreProducto);
            List<ProductoDTO> listaDTo = new ArrayList<>();
            for (Producto p: listaEntity) {
                listaDTo.add(DtoEntityProduct.toDTO(p));
            }
            return listaDTo;
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