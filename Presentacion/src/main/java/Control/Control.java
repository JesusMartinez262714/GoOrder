package Control;

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
        mostrarPantallas(new Carrito(this));
    }
}
