/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitys;

/**
 *
 * @author juanl
 */
public class Producto {
    private String nombre;
    private Double precio;
    private byte[] imagen;

    public Producto(String nombre, Double precio, byte[] imagen) {
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
