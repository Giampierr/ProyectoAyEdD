package tienda.repositorio;

import tienda.base.Venta;
import tienda.interfaces.RepositorioGenerico;
import tienda.modelo.VentaPedido;
import tienda.servicios.VentaServicio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RepositorioPedidos implements RepositorioGenerico<VentaPedido> {
    private ArrayList<VentaPedido> listaPedidos = new ArrayList<>();
    private Queue<VentaPedido> colaPedidos = new LinkedList<>();

    public RepositorioPedidos() {
        cargarDatosIniciales();
    }

    public void cargarDatosIniciales(){
        //Aqui se cargan los pedidos(15)
    }

    public Queue<VentaPedido> getColaPedidos() {
        return colaPedidos;
    }
    @Override
    public String agregar(VentaPedido entidad) {
        listaPedidos.add(entidad);
        colaPedidos.offer(entidad);

        return "Pedido agregado";
    }

    @Override
    public VentaPedido buscar() {
        return null;
    }

    @Override
    public void eliminar(VentaPedido producto) {
        ;
    }

    @Override
    public String listar() {


        return "";
    }
    public String listarResumen() {
        StringBuilder miBuilder = new StringBuilder();

        miBuilder.append("-------Pedido listado-------").append("\n");
        for (Venta entidad : listaPedidos) {
            miBuilder.append(entidad.mostrarResumen()).append("\n");
        }


        return miBuilder.toString();
    }

    public void atenderPrimero(){
        colaPedidos.poll();
    }
}
