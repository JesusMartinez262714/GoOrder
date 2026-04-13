package Control;

import Entitys.Sucursal;
import Entitys.SucursalesDisponibles;
import GUI.*;
import GoOrderDTO.ProductoDTO;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Control {

    private double descuento = 0;
    private List<ProductoDTO> carrito = new ArrayList<>();
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
        this.agregarProducto(new ProductoDTO("Panini Clasico", 95.00));
        this.agregarProducto(new ProductoDTO("Latte Vainilla", 55.00));
        this.agregarProducto(new ProductoDTO("Galleta de Chispas", 25.00));
        mostrarPantallas(new Carrito(this));
    }
    public void mostrarSeleccionSucursalesDisponibles(){
        SucursalesDisponibles mockSucursales = new SucursalesDisponibles();
        mockSucursales.agregarSucursal(new Sucursal(1, "Cafe del compa miguel", "Colonia Diamante", "Casa blanca #456", "Sucursal mas cercana", "sucursal_casa.png"));
        mockSucursales.agregarSucursal(new Sucursal(2, "Cafe del compa miguel", "Colonia Esperanza", "Paris #765", " ", "sucursal_miguel.png"));
        mockSucursales.agregarSucursal(new Sucursal(3, "Cafe del compa miguel", "Colonia Libertad", "Boulevard Lincon", " ", "sucursal_cafesito.png"));
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
