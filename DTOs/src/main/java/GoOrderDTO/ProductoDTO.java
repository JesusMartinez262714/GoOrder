
package GoOrderDTO;

/**
 *
 * @author 
 */
public class ProductoDTO {
    
    
    private String nombre;
    private Double precio;
    private String imagen;

    public ProductoDTO(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    public ProductoDTO(String nombre, Double precio, String imagen) {
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

    public String getImagen() {
        return imagen;
    }

   
}