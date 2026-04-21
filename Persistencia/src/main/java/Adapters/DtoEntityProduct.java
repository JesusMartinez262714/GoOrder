
package Adapters;

import Entitys.Producto;
import GoOrderDTO.ProductoDTO;

/**
 *
 * @author
 */
public class DtoEntityProduct {
    
    /**
     * Adapter que recibe una DTO de producto y regresa una Entity Producto
     * @param producto Parametro de conversion
     * @return Una Entity
     */
    public static Producto toEntity(ProductoDTO producto){
        if (producto == null) {
            return null;
        }
        return new Producto(producto.getNombre(),producto.getDescripcion(),producto.getPrecio(),producto.getImagen());
    }
    
    /**
     * Adapter que recibe un Producto Entity y regresa una DTO Producto
     * @param producto Parametro de conversion
     * @return Una DTO
     */
    public static ProductoDTO toDTO(Producto producto){
        if (producto == null) {
            return null;
        }
        return new ProductoDTO(producto.getNombre(), producto.getDescripcion(), producto.getPrecio(),producto.getImagen());
    }    
}