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
    public void mostrarDescripcionFORM() {
        new DescripcionProductoFORM(this).setVisible(true);
    }

    public void agregarProducto(ProductoDTO producto) {
        carrito.add(producto);
    }

    public List<ProductoDTO> getCarrito() {
        return carrito;
    }

    public void mostrarDescripcionProductoFORM() {
        new DescripcionProductoFORM(this).setVisible(true);
    }

   // public void mostrarPantallaCanjeo() {
    //    new GUI.CodigoDescuentoFORM(this).setVisible(true);
   // }

    //Simulacion de informacion del producto


    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getDescuento() {
        return descuento;
    }

    public void mostrarCodigoDescuento() {
    }

    public void mostrarFormaPago() {
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
        mostrarPantallas(new InicioFORM(this));
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
        mostrarPantallas(new Carrito2(this));
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
        this.agregarProducto(new ProductoDTO("Baguel", 85.00));
        this.agregarProducto(new ProductoDTO("Frappe Mocha", 65.00));
        this.setDescuento(30.00);

        mostrarPantallas(new TotalPrecioProductos(this));
    }
}
