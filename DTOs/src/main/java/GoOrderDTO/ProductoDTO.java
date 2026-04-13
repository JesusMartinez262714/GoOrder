
package GoOrderDTO;

/**
 *
 * @author 
 */
public class ProductoDTO {
    
    
    private String nombre;
    private Double precio;
    private byte[] imagen;

    public ProductoDTO(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    public ProductoDTO(String nombre, Double precio, byte[] imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }



    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public byte[] getImagen() {
        return imagen;
    }

   
}