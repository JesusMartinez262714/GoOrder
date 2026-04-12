
package Control;

import org.example.DescripcionProductoFORM;
import org.example.InicioFORM;
import org.example.ProductosFORM;

public class Control {
    
    
    public void mostrarInicioFORM() {
        new InicioFORM(this).setVisible(true);
    }
    
    public void mostrarProductosFORM() {
        new ProductosFORM(this).setVisible(true);
    }
    
    public void mostrarDescripcionFORM() {
        new DescripcionProductoFORM(this).setVisible(true);
    }
    
    public void mostrarDescripcionProductoFORM(){
        new DescripcionProductoFORM(this).setVisible(true);
    }
    
    //Simulacion de informacion del producto
    public void DescripcionProducto(String nombre, String precio) {
        DescripcionProductoFORM form = new DescripcionProductoFORM(this);
        form.DescripcionProductoFORM(nombre, precio);
        form.setVisible(true);
    }    
}