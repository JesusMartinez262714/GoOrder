package org.example;

import GoOrderDTO.ResultadoCanjeoDTO;


public class CanjeoCodigoServicio implements ICodigoDescuentoServicio {

    @Override
    public ResultadoCanjeoDTO verificarCodigo(String codigo) {

        if (codigo.equalsIgnoreCase("DESC10")) {
            return new ResultadoCanjeoDTO(true, 10);
        }

        if (codigo.equalsIgnoreCase("DESC20")) {
            return new ResultadoCanjeoDTO(true, 20);
        }

        return new ResultadoCanjeoDTO(false, 0);
    }

    @Override
    public double validarCodigo(String codigo) {
        return 0;
    }
}
