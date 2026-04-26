
package goorderpersistencia;

import Entitys.Producto;
import Interfaces.IProductoDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class ProductoDAO implements IProductoDAO {

    private List<Producto> productos;

    public ProductoDAO() {
        productos = new ArrayList<>();
        productos.add(new Producto("Latte", "Un tipo de cafe", 50.00, "latte_vainilla.png"));
        productos.add(new Producto("Paninni", "Queso y Jamon", 50.00, "panini_clasico.png"));
        productos.add(new Producto("galleta", "con chispas de chocolate", 15.00, "galleta_chispas.png"));
    }

    @Override
    public List<Producto> buscarProducto(String nombreProducto) throws PersistenciaException {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p: productos) {
            if (p.getNombre().toLowerCase().contains(nombreProducto.toLowerCase())) {
                resultado.add(p);
            }
        }
        if (resultado.isEmpty()) {
            throw new PersistenciaException("Producto(s) no encontrado(s)");
        }
        return resultado;        
    }

    @Override
    public List<Producto> listarProductos() throws PersistenciaException {
        return productos;
    }

}