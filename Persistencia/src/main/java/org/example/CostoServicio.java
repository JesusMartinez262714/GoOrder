package org.example;

import GoOrderDTO.ProductoDTO;
import GoOrderDTO.CostoDTO;

import java.util.List;
import org.example.ICostoServicio;

public class CostoServicio implements ICostoServicio {

    @Override
    public CostoDTO calcularCosto(List<ProductoDTO> productos, double descuento) {

        double subtotal = 0;

        for (ProductoDTO p : productos) {
            subtotal += p.getPrecio();
        }

        double iva = subtotal * 0.16;
        double total = subtotal + iva - descuento;

        return new CostoDTO(subtotal, iva, descuento, total);
    }
}