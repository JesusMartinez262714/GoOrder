package GoOrderDTO;

public class ResultadoCanjeoDTO {

    private boolean valido;
    private double descuento;

    public ResultadoCanjeoDTO(boolean valido, double descuento) {
        this.valido = valido;
        this.descuento = descuento;
    }

    public boolean isValido() {
        return valido;
    }

    public double getDescuento() {
        return descuento;
    }
}
