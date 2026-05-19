package tienda.servicios;

import tienda.modelo.Producto;
import tienda.modelo.Venta;
import tienda.repositorio.ProductosRepositorio;

import java.util.Map;

public class ActualizarCarrito {
    private ProductosRepositorio productosRepo;

    public ActualizarCarrito(ProductosRepositorio productosRepo) {
        this.productosRepo = productosRepo;
    }

    // Descuenta el stock inmediatamente al agregar al carrito
    public boolean agregarAlCarrito(Venta venta, Producto producto, int cantidad) {
        if (producto.getStock() < cantidad) {
            return false;
        }
        producto.setStock(producto.getStock() - cantidad);
        venta.agregarProducto(producto, cantidad);
        return true;
    }

    public String procesarVenta(Venta venta) {
        // El stock ya fue descontado al agregar, solo mostramos el resumen
        return null;
    }
}