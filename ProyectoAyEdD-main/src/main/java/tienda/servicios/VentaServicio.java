package tienda.servicios;

import tienda.interfaces.RepositorioGenerico;
import tienda.modelo.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class VentaServicio implements RepositorioGenerico<Item> {
    private ArrayList<Item> items = new ArrayList<>();
    private HashMap<Integer, Double> subtotal = new HashMap<>();

    @Override
    public void agregar(Item entidad) {

    }

    @Override
    public Item buscar() {
        return null;
    }

    @Override
    public void eliminar(Item producto) {

    }

    @Override
    public String listar() {
        StringBuilder miBuilder ) new
    }
}
