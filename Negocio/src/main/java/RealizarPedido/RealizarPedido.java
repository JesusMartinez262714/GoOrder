
package RealizarPedido;

import CURealizarPedido.IRealizarPedidoCU;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.SucursalDTO;
import Interfaces.IProductoBO;
import java.util.List;

import Interfaces.ISucursalesDAO;
import goorderpersistencia.PersistenciaException;
import goorderpersistencia.SucursalesDAO;
import org.example.NegocioException;
import org.example.ProductoBO;

/**
 *
 * @author maild
 */
public class RealizarPedido implements IRealizarPedidoCU {
    
   private IProductoBO productoBO;
   private ISucursalesDAO sucursalesDAO;
    
   public RealizarPedido(){
       productoBO = new ProductoBO();
       sucursalesDAO = new SucursalesDAO();
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

    @Override
    public List<ProductoDTO> listarProductos() throws NegocioException {
        try {
            List<ProductoDTO> lista = productoBO.listarProductos();

            if (lista.isEmpty()) {
                throw new NegocioException("No existen productos.");
            }
            return lista;

        } catch (NegocioException e) {
            throw new NegocioException("No fue posible realizar listado de productos.");
        }
    }

    @Override
    public List<SucursalDTO> consultarSucursales() throws NegocioException{
       try{
           List<SucursalDTO> listaSucursales = sucursalesDAO.consultarSucursales();
           if(listaSucursales.isEmpty()){
               throw new NegocioException("No hay sucursales disponibles en este momento");
           }
           return listaSucursales;
       }catch (PersistenciaException e ){
           throw new NegocioException("No se pudo establecer conexion con la base de datos");
       }
    }
}