
package Main;

import Control.Control;
import Interfaces.IProductoBO;
import Interfaces.IProductoDAO;
import goorderpersistencia.ProductoDAO;
import org.example.ProductoBO;
import org.itson.realizarpedidocue.IRealizarPedidoCUE;
import org.itson.realizarpedidocue.RealizarPedidoCUE;

/**
 *
 * @author 
 */
public class Main {    
    public static void main(String[] args) {
        IProductoDAO productoDAO = new ProductoDAO();
        IProductoBO productoBO = new ProductoBO(productoDAO);
        IRealizarPedidoCUE realizarPedido = new RealizarPedidoCUE(productoBO);
        Control control = new Control(realizarPedido);
        control.mostrarInicio();
    }    
}