package org.example;

public class VerificarCodigoCasoDeUso {

    private ICodigoDescuentoServicio servicio;

    public VerificarCodigoCasoDeUso(ICodigoDescuentoServicio servicio) {
        this.servicio = servicio;
    }

    public double ejecutar(String codigo) {
        return servicio.validarCodigo(codigo);
    }
}