
package Interfaces;

import GoOrderDTO.ProductoDTO;
import org.example.NegocioException;

/**
 *
 * @author 
 */
public interface IProductoBO {
    
    public abstract ProductoDTO buscarProducto(String nombreProducto) throws NegocioException;
    
}
