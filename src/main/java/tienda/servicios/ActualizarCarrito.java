package tienda.servicios;

import tienda.modelo.Producto;
import tienda.modelo.Venta;
import tienda.repositorio.ProductosRepositorio;

public class ActualizarCarrito {

    }

    public boolean agregarAlCarrito(Venta venta, Producto producto, int cantidad) {
        if (producto.getStock() < cantidad) {
            return false;
        }
        producto.setStock(producto.getStock() - cantidad);
        venta.agregarProducto(producto, cantidad);
        return true;
    }

    public String procesarVenta(Venta venta) {
        return null;
    }
}