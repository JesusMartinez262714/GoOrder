package org.example;

import GoOrderDTO.CarritoDTO;
import GoOrderDTO.CodigoDescuentoDTO;
import GoOrderDTO.ProductoSeleccionadoDTO;
import Interfaces.ICarritoBO;
import Interfaces.ICarritoDAO;
import Interfaces.IDescuentosBO;
import goorderpersistencia.PersistenciaException;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * Esta clase es el "cerebro" o la capa de negocio (BO) del carrito de compras.
 * Se encarga de hacer las matemáticas (sumar, restar, aplicar descuentos) y de
 * decidir qué pasa con los productos antes de mandarlos a guardar a la base de datos (DAO).
 *
 * @author juanl
 */
public class CarritoBO implements ICarritoBO{

    private static final Logger LOGGER = Logger.getLogger(CarritoBO.class.getName());

    private ICarritoDAO carritoDAO;
    private IDescuentosBO descuentosBO;
    private CarritoDTO carrito;

    /**
     * Constructor de la clase. Recibe las herramientas que necesita para trabajar:
     * una para guardar cosas en la base de datos (carritoDAO) y otra para revisar
     * si los cupones de descuento existen (descuentosBO).
     *
     * @param carritoDAO Objeto que se encarga de guardar y leer el carrito.
     * @param descuentosBO Objeto que se encarga de validar las promociones.
     */
    public CarritoBO(ICarritoDAO carritoDAO,IDescuentosBO descuentosBO) {
        this.carritoDAO = carritoDAO;
        this.descuentosBO = descuentosBO;
    }

    /**
     * Mete un producto nuevo al carrito. Si el producto ya estaba antes,
     * en lugar de agregarlo dos veces, solo le suma 1 a la cantidad.
     * Al final, recalcula cuánto dinero es en total.
     *
     * @param producto El producto que el usuario quiere comprar.
     * @return El carrito actualizado con los nuevos totales.
     * @throws NegocioException Si hay un problema de conexión al intentar guardar.
     */
    @Override
    public CarritoDTO AgregarProductoCarrito(ProductoSeleccionadoDTO producto) throws NegocioException {
        try {
            carrito = carritoDAO.ObtenerCarrito();
            if (this.carrito == null) {
                this.carrito = new CarritoDTO();
            }
            boolean existe = false;
            for (ProductoSeleccionadoDTO pro: carrito.getProductos()) {
                if (pro.getNombre().equals(producto.getNombre())) {
                    pro.setCantidad(pro.getCantidad()+1);
                    pro.setImporte(pro.getPrecioActual() * pro.getCantidad());
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                carrito.getProductos().add(producto);
            }
            carrito.setSubTotal(carrito.getSubTotal() + producto.getPrecioActual());
            carrito.setTotal(carrito.getSubTotal() - carrito.getDescuento());
            carritoDAO.ActualizarCarrito(carrito);
            return carrito;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo agregar el producto al carrito");
        }
    }

    /**
     * Vacía completamente el carrito, borrando todos los productos y regresando
     * los totales a cero.
     *
     * @return Un carrito nuevo y vacío.
     * @throws NegocioException Si ocurre un error al intentar borrar los datos.
     */
    @Override
    public CarritoDTO LimpiarCarrito() throws NegocioException {
        try {
            return carritoDAO.LimpiarCarrito();
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo limpiar el carrito");
        }
    }

    /**
     * Va a la base de datos o almacenamiento y trae el carrito tal y como
     * lo dejó el usuario la última vez.
     *
     * @return El carrito con todos sus productos y totales actuales.
     * @throws NegocioException Si no se puede leer la información guardada.
     */
    @Override
    public CarritoDTO ObtenerCarrito() throws NegocioException {
        try {
            return carritoDAO.ObtenerCarrito();
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo obtener el carrito");
        }
    }

    /**
     * Le suma 1 a la cantidad de un producto que ya está dentro del carrito
     * (por ejemplo, cuando el usuario le pica al botón "+").
     *
     * @param producto El producto al que se le va a aumentar la cantidad.
     * @return El carrito con las cantidades y el dinero actualizados.
     * @throws NegocioException Si falla al guardar la actualización.
     */
    @Override
    public CarritoDTO IncrementarCantidad(ProductoSeleccionadoDTO producto) throws NegocioException {
        try {
            carrito = carritoDAO.ObtenerCarrito();
            for(ProductoSeleccionadoDTO pro : carrito.getProductos()) {
                if(pro.equals(producto)){
                    pro.setCantidad(pro.getCantidad()+1);
                    pro.setImporte(pro.getPrecioActual()*pro.getCantidad());
                    carrito.setSubTotal(carrito.getSubTotal()+ producto.getPrecioActual());
                    carrito.setTotal(carrito.getSubTotal()-carrito.getDescuento());
                }
            }
            carritoDAO.ActualizarCarrito(carrito);
            return carrito;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo incrementar la cantidad");        }
    }

    /**
     * Le resta 1 a la cantidad de un producto en el carrito (cuando le pican al botón "-").
     * Si la cantidad llega a cero, manda a llamar al método de eliminar para sacarlo del carrito.
     *
     * @param producto El producto al que se le va a restar la cantidad.
     * @return El carrito actualizado.
     * @throws NegocioException Si falla al actualizar los datos.
     */
    @Override
    public CarritoDTO DescrementarCantidad(ProductoSeleccionadoDTO producto) throws NegocioException {
        try {
            carrito = carritoDAO.ObtenerCarrito();
            for(ProductoSeleccionadoDTO pro : carrito.getProductos()) {
                if(pro.equals(producto)&& pro.getCantidad()>0){
                    pro.setCantidad(pro.getCantidad()-1);
                    pro.setImporte(pro.getPrecioActual()*pro.getCantidad());
                    carrito.setSubTotal(carrito.getSubTotal()- producto.getPrecioActual());

                    carrito.setTotal(carrito.getSubTotal()-carrito.getDescuento());
                    if(pro.getCantidad() == 0){
                        EliminarProductoCarrito(producto);
                    }
                }
                break;
            }
            carritoDAO.ActualizarCarrito(carrito);
            return carrito;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo disminuir la cantidad");        }
    }

    /**
     * Recibe un texto con un código promocional, busca cuánto vale ese descuento,
     * se lo resta al subtotal del carrito y actualiza el total a pagar.
     *
     * @param codigo La palabra o texto del cupón (ej. "PROMO50").
     * @return El carrito con el precio total ya rebajado.
     * @throws NegocioException Si el código no existe o hubo un error al aplicarlo.
     */
    @Override
    public CarritoDTO AplicarDescuento(String codigo) throws NegocioException {
        try{
            CarritoDTO carrito = carritoDAO.ObtenerCarrito();
            CodigoDescuentoDTO cupon = descuentosBO.BuscarDescuento(codigo);
            carrito.setDescuento(cupon.getMonto());
            carrito.setTotal(carrito.getSubTotal() - carrito.getDescuento());
            carritoDAO.ActualizarCarrito(carrito);
            return carrito;

        } catch (PersistenciaException ex) {
            throw new NegocioException("Ocurrió un error al intentar guardar el carrito con descuento");
        }
    }

    /**
     * Saca completamente un producto del carrito y descuenta el dinero que
     * costaba de los totales, asegurándose de que la cuenta no quede en números negativos.
     * Utiliza un 'Iterator' para borrarlo de la lista sin causar errores en el sistema.
     *
     * @param producto El producto que se va a ir a la basura.
     * @return El carrito ya sin el producto y con el nuevo total.
     * @throws NegocioException Si hay problemas al actualizar la base de datos.
     */
    @Override
    public CarritoDTO EliminarProductoCarrito(ProductoSeleccionadoDTO producto) throws NegocioException {
        try {
            CarritoDTO carrito = carritoDAO.ObtenerCarrito();

            double importeARestar = 0.0;
            boolean encontrado = false;
            Iterator<ProductoSeleccionadoDTO> iterador = carrito.getProductos().iterator();

            while (iterador.hasNext()) {
                ProductoSeleccionadoDTO pro = iterador.next();
                if (pro.equals(producto)) {
                    importeARestar = pro.getImporte();
                    iterador.remove();
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                return carrito;
            }
            carrito.setSubTotal(carrito.getSubTotal() - importeARestar);
            if (carrito.getSubTotal() < 0) {
                carrito.setSubTotal(0.0);
            }
            carrito.setTotal(carrito.getSubTotal() - carrito.getDescuento());

            if (carrito.getTotal() < 0) {
                carrito.setTotal(0.0);
            }
            carritoDAO.ActualizarCarrito(carrito);
            return carrito;

        } catch (PersistenciaException ex) {
            throw new NegocioException("Ocurrió un error al intentar eliminar el producto del carrito");
        }
    }
}