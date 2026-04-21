
package Control;

import CURealizarPedido.IRealizarPedidoCU;
import Entitys.SucursalesDisponibles;
import GUI.*;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.SucursalDTO;
import RealizarPedido.RealizarPedido;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import org.example.NegocioException;

public class Control {
    public final Color COLOR_FONDO = new Color(18, 18, 18);
    public final Color COLOR_NEON = new Color(0, 255, 150);
    public final Color COLOR_TARJETA = new Color(35, 35, 35);
    public final Color COLOR_BOTON = new Color(35, 35, 35);
    public final Color COLOR_ERROR = new Color(255, 80, 80);
    public final Color COLOR_INPUT = new Color(25, 25, 25);
    public final Color COLOR_BORDE = new Color(60, 60, 60);
    private final String rutaImagenes = "Resources/";

    
    private IRealizarPedidoCU realizarPedido;
    
    private double descuento = 0;
    private List<ProductoDTO> carrito = new ArrayList<>();
    List<ProductoDTO> listaProductos = new ArrayList<>();
    
    
    
    public Control() {
        cargarMenuProductos();
        realizarPedido = new RealizarPedido();
    }

    public ImageIcon obtenerImagen(String nombreImagen) {
        String rutaCompleta = "Resources/" + nombreImagen;
        ImageIcon icono = new ImageIcon(rutaCompleta);
        return icono;
    }
    
    public ProductoDTO buscarProducto(String nombreProducto) throws NegocioException {
        return realizarPedido.buscarProducto(nombreProducto);
    }
    
    public List<ProductoDTO> listarProductos() throws NegocioException {
        return realizarPedido.listarProductos();
    }

    private void cargarMenuProductos() {
    }
    public List<ProductoDTO> obtenerListaProductos() {
        return listaProductos;
    }
    public void agregarProducto(ProductoDTO producto) {
        for (ProductoDTO p : carrito) {
            if (p.getNombre().equalsIgnoreCase(producto.getNombre())) {
                p.setCantidad(p.getCantidad() + 1); 
                return;
            }
        }
        producto.setCantidad(1);
        carrito.add(producto);
    }
    
    public void incrementarCantidad(ProductoDTO producto) {
        for (ProductoDTO p : carrito) {
            if (p.getNombre().equalsIgnoreCase(producto.getNombre())) {
                p.setCantidad(p.getCantidad() + 1);
                return;
            }
        }
    }
    
    public void decrementarCantidad(ProductoDTO producto) {
        for (ProductoDTO p : carrito) {
            if (p.getNombre().equalsIgnoreCase(producto.getNombre())) {
                if (p.getCantidad() > 1) {
                    p.setCantidad(p.getCantidad() - 1);
                } else {
                    carrito.remove(p);
                }
                return;
            }
        }
    }
    
    public void eliminarProducto(ProductoDTO producto) {
        carrito.removeIf(p -> p.getNombre().equalsIgnoreCase(producto.getNombre()));
    }
    
    public List<ProductoDTO> getCarrito() {
        return carrito;
    }
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getDescuento() {
        return descuento;
    }

    //NAVEGACION DEL SISTEMA
    private JFrame ventanaActual = null;
    private void mostrarPantallas(JFrame nuevaVentana){
        if(ventanaActual != null){
            ventanaActual.dispose();
        }
        ventanaActual = nuevaVentana;
        ventanaActual.setVisible(true);
    }
    public void mostrarInicio(){
        mostrarPantallas(new Inicio(this));
    }
    public void mostrarProductosFORM() throws NegocioException{
        mostrarPantallas(new ProductosFORM(this));
    }
    public void DescripcionProducto(String nombre, String precio) {
        DescripcionProductoFORM form = new DescripcionProductoFORM(this);
        form.DescripcionProductoFORM(nombre, precio);
        form.setVisible(true);
    }
    public void mostrarSeleccionMetodoEntrega(){
        mostrarPantallas(new SeleccionMetodoEntrega(this));
    }
    public void mostrarCarrito(){
        mostrarPantallas(new Carrito(this));
    }
    public void mostrarSeleccionSucursalesDisponibles(){

        try {
            List<SucursalDTO>  sucursales = realizarPedido.consultarSucursales();
            mostrarPantallas(new SeleccionSucursalesDisponibles(this,sucursales));
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar sucursales: " + e.getMessage());
        }

    }
    public void mostrarDomicilioFORM(){
        mostrarPantallas(new DomicilioFORM(this));
    }

    public void mostrarAjustarDireccionMapa(){
        mostrarPantallas(new AjustarDireccionMapa(this));
    }

    public void mostrarTotalPrecioProductos(){

        this.setDescuento(30.00);

        mostrarPantallas(new TotalPrecioProductos(this));
    }
    public void mostrarSeleccionFormaPago(){
        mostrarPantallas(new SeleccionFormaPago(this));
    }

    public void mostrarCodigoDescuento() {

        mostrarPantallas(new CodigoDescuentoFORM(this));
    }

    public void mostrarPagoEfectivo(){
        mostrarPantallas(new PagoEfectivo(this));
    }
    public void mostrarPagoTarjeta(){
        mostrarPantallas(new PagoTarjeta(this));
    }
    public void mostrarPagoRechazado(){
        mostrarPantallas(new PagoRechazado(this));
    }
    public void mostrarPagoReferencia(){
        mostrarPantallas(new PagoReferencia(this));
    }
    public void mostrarAgradecimiento(){
        mostrarPantallas(new Agradecimiento(this));
    }
    public void mostrarCodigoDescuentoRechazado(){
        mostrarPantallas(new CodigoDescuentoRechazado(this));
    }
}
