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
    public String devolverProducto(Venta venta, int idProducto, int cantidadDevolver) {
        for (int i = 0; i < venta.getProductos().size(); i++) {
            if (venta.getProductos().get(i).getId() == idProducto) {
                Producto p = venta.getProductos().get(i);
                int cantidadEnCarrito = venta.getCantidades().get(i);

                if (cantidadDevolver > cantidadEnCarrito) {
                    return "No puedes devolver más de lo que tienes en el carrito. Cantidad en carrito: " + cantidadEnCarrito;
                }

                // Regresa el stock al inventario
                p.setStock(p.getStock() + cantidadDevolver);

                if (cantidadDevolver == cantidadEnCarrito) {
                    // Quita el producto completo del carrito
                    venta.getProductos().remove(i);
                    venta.getCantidades().remove(i);
                } else {
                    // Solo reduce la cantidad
                    venta.getCantidades().set(i, cantidadEnCarrito - cantidadDevolver);
                }
                return "Producto devuelto: " + p.getNombre() + " x" + cantidadDevolver;
            }
        }
        return "Producto no encontrado en el carrito.";
    }
}