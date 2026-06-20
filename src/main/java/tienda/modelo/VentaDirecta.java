package tienda.modelo;

import tienda.base.Venta;

import java.util.ArrayList;

public class VentaDirecta extends Venta {

    public VentaDirecta(Cliente cliente, ArrayList<Item> listaItems) {
        super(cliente, listaItems);
    }

    @Override
    public String mostrar() {
        return super.mostrar();
    }

    @Override
    public String mostrarResumen() {
        return super.mostrarResumen();
    }
}