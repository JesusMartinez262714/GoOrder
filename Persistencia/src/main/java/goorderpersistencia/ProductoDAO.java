
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
        productos.add(new Producto("Latte", "Un tipo de cafe", 50.00,"latte_vainilla.png"));
        productos.add(new Producto("Paninni", "Queso y Jamon", 50.00,"panini_clasico.png"));
        productos.add(new Producto("galleta", "con chispas de chocolate", 15.00,"galleta_chispas.png"));
    }

    @Override
    public Producto buscarProducto(String nombreProducto) throws PersistenciaException {
        for (Producto p: productos) {
            if (p.getNombre().toLowerCase().contains(nombreProducto.toLowerCase())) {
                return p;
            }
        }
        throw new PersistenciaException("Producto no encontrado.");
    }

    @Override
    public List<Producto> listarProductos() throws PersistenciaException {
        return productos;
    }
}