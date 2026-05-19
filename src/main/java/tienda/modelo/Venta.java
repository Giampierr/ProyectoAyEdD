package tienda.modelo;

import java.util.ArrayList;
import java.util.List;

public class Venta {
    private static int contadorId = 1;
    private int id;
    private List<Producto> productos;
    private List<Integer> cantidades;

    public Venta() {
        this.id = contadorId++;
        this.productos = new ArrayList<>();
        this.cantidades = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        // Si el producto ya está en el carrito, suma la cantidad
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == producto.getId()) {
                cantidades.set(i, cantidades.get(i) + cantidad);
                return;
            }
        }
        // Si no está, lo agrega nuevo
        productos.add(producto);
        cantidades.add(cantidad);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Integer> getCantidades() {
        return cantidades;
    }

    public boolean estaVacio() {
        return productos.isEmpty();
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < productos.size(); i++) {
            total += productos.get(i).getPrecio() * cantidades.get(i);
        }
        return total;
    }

    public String mostrarCarritoActual() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- Carrito actual ---\n");
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            int cant = cantidades.get(i);
            sb.append(String.format("  %s x%d | Subtotal: S/%.2f%n",
                    p.getNombre(), cant, p.getPrecio() * cant));
        }
        sb.append(String.format("Total acumulado: S/%.2f%n", calcularTotal()));
        sb.append("----------------------");
        return sb.toString();
    }

    public String mostrarResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== RESUMEN DE VENTA #").append(id).append(" =====\n");
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            int cant = cantidades.get(i);
            sb.append(String.format("  %s | Cantidad: %d | Subtotal: S/%.2f%n",
                    p.getNombre(), cant, p.getPrecio() * cant));
        }
        sb.append(String.format("TOTAL: S/%.2f%n", calcularTotal()));
        sb.append("================================");
        return sb.toString();
    }
}