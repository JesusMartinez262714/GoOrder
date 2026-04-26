/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GoOrderDTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juanl
 */
public class CarritoDTO {
    
    private List<ProductoSeleccionadoDTO> productos = new ArrayList<>();
    //private Long idCliente;
    private Double subTotal = 0.0; 
    private Double total = 0.0;
    private Double descuento = 0.0;

    public CarritoDTO() {
    }

    public CarritoDTO(List<ProductoSeleccionadoDTO> productos, double subTotal, double descuento, double total) {
        this.productos = productos;
        this.subTotal = subTotal;
        this.descuento = descuento;
        this.total = total;
    }

    public void setProductos(List<ProductoSeleccionadoDTO> productos) {
        this.productos = productos;
    }

    
    public List<ProductoSeleccionadoDTO> getProductos() {
        return productos;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    

    

    
    
}
