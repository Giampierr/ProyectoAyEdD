package tienda.modelo;

import tienda.base.Venta;

import java.util.ArrayList;



public class VentaPedido extends Venta {
    private EstadoPedido estado;

    public VentaPedido(Cliente cliente, ArrayList<Item> listaItems) {
        super(cliente, listaItems);
        this.estado = EstadoPedido.PENDIENTE;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    @Override
    public String mostrar() {
        return super.mostrar()+"estado:"+estado;
    }

    @Override
    public String mostrarResumen() {
        return super.mostrarResumen()+"estado:"+estado;
    }
}
