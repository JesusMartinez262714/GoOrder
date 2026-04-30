
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
 *
 * @author juanl
 */
public class CarritoBO implements ICarritoBO{

    private static final Logger LOGGER = Logger.getLogger(CarritoBO.class.getName());
    
    private ICarritoDAO carritoDAO;
    private IDescuentosBO descuentosBO;
    private CarritoDTO carrito;
    public CarritoBO(ICarritoDAO carritoDAO,IDescuentosBO descuentosBO) {
        this.carritoDAO = carritoDAO;
        this.descuentosBO = descuentosBO;
    }
    
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

    @Override
    public CarritoDTO LimpiarCarrito() throws NegocioException {
        try {
            return carritoDAO.LimpiarCarrito();
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo limpiar el carrito");
        }
    }

    @Override
    public CarritoDTO ObtenerCarrito() throws NegocioException {
        try {
            return carritoDAO.ObtenerCarrito();
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo obtener el carrito");
        }
    }

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