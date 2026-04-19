
package CURealizarPedido;

import GoOrderDTO.ProductoDTO;
import org.example.NegocioException;

/**
 *
 * @author
 */
public interface IRealizarPedidoCU {
    
    public abstract ProductoDTO buscarProducto(String nombreProducto) throws NegocioException;
    
}