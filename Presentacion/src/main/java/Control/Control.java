package Control;

import GoOrderDTO.ProductoDTO;
import java.util.ArrayList;
import java.util.List;
import org.example.DescripcionProductoFORM;
import org.example.InicioFORM;
import org.example.ProductosFORM;

public class Control {

    private double descuento = 0;
    private List<ProductoDTO> carrito = new ArrayList<>();

    public void mostrarInicioFORM() {
        new InicioFORM(this).setVisible(true);
    }

    public void mostrarProductosFORM() {
        new ProductosFORM(this).setVisible(true);
    }

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

    public void mostrarPantallaCanjeo() {
        new GUI.CodigoDescuentoFORM(this).setVisible(true);
    }

    //Simulacion de informacion del producto
    public void DescripcionProducto(String nombre, String precio) {
        DescripcionProductoFORM form = new DescripcionProductoFORM(this);
        form.DescripcionProductoFORM(nombre, precio);
        form.setVisible(true);
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getDescuento() {
        return descuento;
    }
}
