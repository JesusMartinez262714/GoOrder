package Entitys;

import java.util.ArrayList;
import java.util.List;

public class SucursalesDisponibles {
    private List<Sucursal> sucursalesDisponibles;

    public SucursalesDisponibles() {
        this.sucursalesDisponibles = new ArrayList<>();
    }

    public void agregarSucursal(Sucursal sucursal){
        this.sucursalesDisponibles.add(sucursal);
    }

    public List<Sucursal> getSucursalesDisponibles() {
        return sucursalesDisponibles;
    }
}
