
package RealizarPedido;

import CURealizarPedido.IRealizarPedidoCU;
import GoOrderDTO.ProductoDTO;
import Interfaces.IProductoBO;
import org.example.NegocioException;
import org.example.ProductoBO;

/**
 *
 * @author maild
 */
public class RealizarPedido implements IRealizarPedidoCU {
    
   private IProductoBO productoBO;
    
   public RealizarPedido(){
       productoBO = new ProductoBO();
   }
   
    @Override
    public ProductoDTO buscarProducto(String nombreProducto) throws NegocioException {
        if (nombreProducto.isEmpty()) {
            throw new NegocioException("El nombre no puede quedar vacio al realizar busqueda.");
        }
        
        if (nombreProducto.matches("\\d+")) {
            throw new NegocioException("El nombre del producto no puede contener números.");
        }
        
        try {
            return productoBO.buscarProducto(nombreProducto);
        } catch (NegocioException e) {
            throw new NegocioException("No fue posible realizar busqueda.");
        }
    }    
}