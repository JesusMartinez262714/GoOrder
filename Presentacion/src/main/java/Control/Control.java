package Control;

import Entitys.Producto;
import Entitys.Sucursal;
import Entitys.SucursalesDisponibles;
import GUI.*;
import GoOrderDTO.ProductoDTO;
import GoOrderDTO.SucursalDTO;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Control {
    public final Color COLOR_FONDO = new Color(18, 18, 18);
    public final Color COLOR_NEON = new Color(0, 255, 150);
    public final Color COLOR_TARJETA = new Color(35, 35, 35);
    public final Color COLOR_BOTON = new Color(35, 35, 35);
    public final Color COLOR_ERROR = new Color(255, 80, 80);
    public final Color COLOR_INPUT = new Color(25, 25, 25);
    public final Color COLOR_BORDE = new Color(60, 60, 60);
    private final String rutaImagenes = "Resources/";

    private double descuento = 0;
    private List<ProductoDTO> carrito = new ArrayList<>();
    List<ProductoDTO> listaProductos = new ArrayList<>();
    public Control() {
        cargarMenuProductos();
    }

    public ImageIcon obtenerImagen(String nombreImagen) {
        String rutaCompleta = "Resources/" + nombreImagen;
        ImageIcon icono = new ImageIcon(rutaCompleta);
        return icono;
    }

    private void cargarMenuProductos() {
        listaProductos.add(new ProductoDTO("Panini Clasico", 95.00, "panini_clasico.png"));
        listaProductos.add(new ProductoDTO("Latte Vainilla", 55.00, "latte_vainilla.png"));
        listaProductos.add(new ProductoDTO("Galleta de Chispas", 25.00, "galleta_chispas.png"));
    }
    public List<ProductoDTO> obtenerListaProductos() {
        return listaProductos;
    }
    public void agregarProducto(ProductoDTO producto) {
        carrito.add(producto);
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
    public void mostrarProductosFORM(){
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
        SucursalesDisponibles mockSucursales = new SucursalesDisponibles();
        mockSucursales.agregarSucursal(new SucursalDTO(1, "Cafe del compa miguel", "Colonia Diamante", "Casa blanca #456", "Sucursal mas cercana", "sucursal_casa.png"));
        mockSucursales.agregarSucursal(new SucursalDTO(2, "Cafe del compa miguel", "Colonia Esperanza", "Paris #765", " ", "sucursal_miguel.png"));
        mockSucursales.agregarSucursal(new SucursalDTO(3, "Cafe del compa miguel", "Colonia Libertad", "Boulevard Lincon", " ", "sucursal_cafesito.png"));
        mostrarPantallas(new SeleccionSucursalesDisponibles(this,mockSucursales));
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
