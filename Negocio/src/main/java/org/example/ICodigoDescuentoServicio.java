package org.example;

import GoOrderDTO.ResultadoCanjeoDTO;

public interface ICodigoDescuentoServicio {
    double validarCodigo(String codigo);
    public ResultadoCanjeoDTO verificarCodigo(String codigo);
}