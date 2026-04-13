package org.example;

import GoOrderDTO.ResultadoCanjeoDTO;

public class VerificarCodigo {

    private ICodigoDescuentoServicio servicio;

    public VerificarCodigo(ICodigoDescuentoServicio servicio) {
        this.servicio = servicio;
    }

    public ResultadoCanjeoDTO ejecutar(String codigo) {
        return servicio.verificarCodigo(codigo);
    }
}