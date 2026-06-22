package tienda.repositorio;

import tienda.base.Venta;
import tienda.interfaces.RepositorioGenerico;
import tienda.modelo.VentaDirecta;

import java.util.ArrayList;
import java.util.HashMap;

import static tienda.Factory.VentaFactory.crearVenta;

public class RepositorioVentas implements RepositorioGenerico<Venta> {
    ArrayList<Venta> listaVentaDirecta = new ArrayList<>();
    HashMap <Integer, Venta> hashVentaDirecta = new HashMap<>();

    public RepositorioVentas() {
        cargarDatosIniciales();
    }

    public void cargarDatosIniciales(){
    }

    @Override
    public String agregar(Venta entidad) {
        listaVentaDirecta.add(entidad);
        hashVentaDirecta.put(entidad.getId(),entidad);

        return "Venta Agregada";
    }

    @Override
    public Venta buscar() {
        return null;
    }

    @Override
    public void eliminar(Venta producto) {

    }

    @Override
    public String listar() {
        StringBuilder miBuilder = new StringBuilder();
        miBuilder.append("--------Lista Venta---------").append("\n");

        for (Venta venta : listaVentaDirecta) {
            miBuilder.append(venta.mostrarResumen()).append("\n");
        }


        return miBuilder.toString();
    }


}
