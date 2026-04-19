
package Interfaces;

import Entidades.Producto;
import goorderpersistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author
 */
public interface IProductoDAO {
    
    public abstract Producto buscarProducto(String nombreProducto) throws PersistenciaException;
    
    public abstract List<Producto> listarProductos() throws PersistenciaException;
    
}
