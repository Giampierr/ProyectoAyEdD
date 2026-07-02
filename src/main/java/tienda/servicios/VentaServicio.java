package tienda.servicios;

import tienda.Factory.TipoVenta;
import tienda.Factory.VentaFactory;
import tienda.base.Venta;
import tienda.modelo.*;
import tienda.repositorio.ClientesRepositorio;
import tienda.repositorio.ProductosRepositorio;
import tienda.repositorio.RepositorioPedidos;
import tienda.repositorio.RepositorioVentas;

import java.util.ArrayList;
import java.util.HashMap;


public class VentaServicio  {
    private ProductosRepositorio productosRepositorio;
    private RepositorioVentas repositorioVentas;
    private RepositorioPedidos  repositorioPedido;
    private ArrayList<Item> listaItems = new ArrayList<>();
    private HashMap<Integer, Item> hashItem = new HashMap<>();

    public VentaServicio(ProductosRepositorio productosRepositorio, RepositorioPedidos repositorioPedido, RepositorioVentas repositorioVentas) {
        this.productosRepositorio = productosRepositorio;
        this.repositorioPedido = repositorioPedido;
        this.repositorioVentas = repositorioVentas;
    }


    public String agregar(int id) {

        Producto producto = productosRepositorio.buscarPorId(id);

        if (producto == null) {
            return "Producto no encontrado";
        }

        Item itemCarrito = hashItem.get(id);

        if (itemCarrito != null) {

            if (itemCarrito.getCantidad() + 1 > producto.getStock()) {
                return "No hay suficiente stock";
            }

            itemCarrito.setCantidad(itemCarrito.getCantidad() + 1);
            System.out.println(itemCarrito.getCantidad());
            return "Cantidad actualizada";
        }

        Item nuevoItem = new Item(producto, 1);

        listaItems.add(nuevoItem);
        hashItem.put(id, nuevoItem);

        return "Producto agregado";
    }


    public String eliminarPorId(int id) {

        if (listaItems.isEmpty()) {
            return "Carrito vacío";
        }

        Item productoEliminar = null;

        for (Item item : listaItems) {
            if (item.obtenerId() == id) {
                productoEliminar = item;
                break;
            }
        }

        if (productoEliminar != null) {
            hashItem.remove(id);
            listaItems.remove(productoEliminar);
            return "Producto eliminado del carrito";
        }

        return "El producto no se encuentra en el carrito";
    }

    public String listar() {
        if (!listaItems.isEmpty()) {
            StringBuilder miBuilder = new StringBuilder();

            miBuilder.append("-----Productos en Carrito-----").append("\n");
            for (Item forListaItem : listaItems) {
                miBuilder.append(forListaItem.mostrar()).append("\n");
            }
            miBuilder.append("=========================");
            return miBuilder.toString();
        } else {
            return "Carrito vacio";
        }
    }

    public void vaciarCarrito(){
        listaItems.clear();
        hashItem.clear();
    }

    public void regularStock(){
        for (Item forListaItem : listaItems) {
            Producto producto = forListaItem.getProducto();
            int nuevoStock = producto.getStock() - forListaItem.getCantidad();
            producto.setStock(nuevoStock);
        }
    }
    private boolean hayStockSuficiente() {

        for (Item item : listaItems) {

            if (item.getCantidad() > item.obtenerStock()) {
                return false;
            }
        }

        return true;
    }

    public String procesarVenta(Cliente cliente) {

        if (listaItems.isEmpty()) {
            return "Carrito vacío";
        }

        if (!hayStockSuficiente()) {
            return "No hay suficiente stock para completar la venta";
        }

        ArrayList<Item> copiaItems = new ArrayList<>(listaItems);

        Venta venta = VentaFactory.crearVenta(
                TipoVenta.DIRECTA,
                cliente,
                copiaItems
        );

        regularStock();
        repositorioVentas.agregar(venta);
        vaciarCarrito();

        return venta.mostrar();
    }

    public String procesarPedido(Cliente cliente) {
        if (!listaItems.isEmpty()) {
            ArrayList<Item> copiaItems = new ArrayList<>(listaItems);
            VentaPedido venta = (VentaPedido) VentaFactory.crearVenta(TipoVenta.PEDIDO,cliente,copiaItems);
            repositorioPedido.agregar(venta);
            vaciarCarrito();
            return venta.mostrar();
        } else {
            return "Carrito vacio";
        }
    }

    public String atenderPrimerPedido() {

        if (repositorioPedido.getColaPedidos().isEmpty()) {
            return "No hay pedidos pendientes";
        }

        VentaPedido pedido =
                repositorioPedido.getColaPedidos().poll();

        for (Item item : pedido.getListaItems()) {
            Producto producto = item.getProducto();

            if (item.getCantidad() > producto.getStock()) {
                return "Stock insuficiente para aprobar el pedido";
            }

            producto.setStock(
                    producto.getStock() - item.getCantidad()
            );
        }

        pedido.setEstado(EstadoPedido.APROBADO);
        repositorioVentas.agregar(pedido);

        return "Pedido aprobado";
    }

    public String rechazarPedido(){
        if (repositorioPedido.getColaPedidos().isEmpty()) {
            return "No hay pedidos pendientes";
        }
        VentaPedido pedido = repositorioPedido.getColaPedidos().poll();

        pedido.setEstado(EstadoPedido.RECHAZADO);

        return pedido.mostrarResumen();
    }
}
