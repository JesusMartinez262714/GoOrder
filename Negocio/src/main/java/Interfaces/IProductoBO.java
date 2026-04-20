
package Interfaces;

import Entidades.Producto;
import GoOrderDTO.ProductoDTO;
import java.util.List;
import org.example.NegocioException;

/**
 *
 * @author 
 */
public interface IProductoBO {
    
    public abstract ProductoDTO buscarProducto(String nombreProducto) throws NegocioException;
    
    public abstract List<ProductoDTO> listarProductos() throws NegocioException;

    
}
