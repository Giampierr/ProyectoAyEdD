package tienda.modelo;

import java.util.LinkedHashMap;
import java.util.Map;

public class Venta {
    private static int contadorId = 1;
    private int id;
    private Map<Producto, Integer> carrito;

    public Venta() {
        this.id = contadorId++;
        this.carrito = new LinkedHashMap<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        carrito.merge(producto, cantidad, Integer::sum);
    }

    public Map<Producto, Integer> getCarrito() {
        return carrito;
    }

    public int getId() {
        return id;
    }

    public double calcularTotal() {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : carrito.entrySet()) {
            total += entry.getKey().getPrecio() * entry.getValue();
        }
        return total;
    }

    public String mostrarCarritoActual() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- Carrito actual ---\n");
        for (Map.Entry<Producto, Integer> entry : carrito.entrySet()) {
            Producto p = entry.getKey();
            sb.append(String.format("  %s x%d | Subtotal: S/%.2f%n",
                    p.getNombre(),
                    entry.getValue(),
                    p.getPrecio() * entry.getValue()));
        }
        sb.append(String.format("Total acumulado: S/%.2f%n", calcularTotal()));
        sb.append("----------------------");
        return sb.toString();
    }

    public String mostrarResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== RESUMEN DE VENTA #").append(id).append(" =====\n");
        for (Map.Entry<Producto, Integer> entry : carrito.entrySet()) {
            Producto p = entry.getKey();
            int cant = entry.getValue();
            sb.append(String.format("  %s | Cantidad: %d | Subtotal: S/%.2f\n",
                    p.getNombre(), cant, p.getPrecio() * cant));
        }
        sb.append(String.format("TOTAL: S/%.2f\n", calcularTotal()));
        sb.append("================================");
        return sb.toString();
    }
}