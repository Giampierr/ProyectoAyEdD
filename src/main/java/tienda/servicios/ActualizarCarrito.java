package tienda.servicios;

import tienda.modelo.Producto;
import tienda.modelo.Venta;
import tienda.repositorio.ProductosRepositorio;

public class ActualizarCarrito {
    private ProductosRepositorio repo;

    public ActualizarCarrito(ProductosRepositorio repo) {
        this.repo = repo;
    }

    public boolean agregarAlCarrito(Venta venta, Producto producto, int cantidad) {
        if (producto.getStock() < cantidad) {
            return false;
        }
        // Descuenta el stock inmediatamente
        producto.setStock(producto.getStock() - cantidad);
        venta.agregarProducto(producto, cantidad);
        return true;
    }

    public String procesarVenta(Venta venta) {
        // El stock ya fue descontado al agregar, solo retorna null (éxito)
        return null;
    }
}