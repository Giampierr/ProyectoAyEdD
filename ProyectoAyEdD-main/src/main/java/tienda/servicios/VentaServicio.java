package tienda.servicios;

import tienda.estructuras.ArbolBST;
import tienda.interfaces.RepositorioGenerico;
import tienda.modelo.Item;
import tienda.modelo.Producto;
import tienda.repositorio.ProductosRepositorio;
import tienda.repositorio.RepositorioPedidos;
import tienda.repositorio.RepositorioVentas;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class VentaServicio implements RepositorioGenerico<Item> {
    private ArrayList<Item> items = new ArrayList<>();
    private ProductosRepositorio repoProductos;
    private RepositorioPedidos repoPedidos;
    private RepositorioVentas repoVentas;
    private ArbolBST arbol;

    public VentaServicio(ProductosRepositorio repoProductos, RepositorioPedidos repoPedidos, RepositorioVentas repoVentas) {
        this.repoProductos = repoProductos;
        this.repoPedidos = repoPedidos;
        this.repoVentas = repoVentas;
        this.arbol = new ArbolBST();
        cargarProductosEnArbol();
    }

    private void cargarProductosEnArbol() {
        for (Producto p : repoProductos.getProductos()) {
            arbol.insertar(p);
        }
    }

    public List<Producto> filtrarPreciosMenoresA(double precio) {
        return arbol.filtrarMenoresA(precio);
    }

    @Override
    public void agregar(Item entidad) {
        items.add(entidad);
    }

    @Override
    public Item buscar() {
        return null;
    }

    @Override
    public void eliminar(Item producto) {
        items.remove(producto);
    }

    @Override
    public String listar() {
        StringBuilder miBuilder = new StringBuilder();
        for (Item item : items) {
            miBuilder.append(item.toString()).append("\n");
        }
        return miBuilder.toString();
    }
}