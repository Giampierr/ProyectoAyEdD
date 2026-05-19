package tienda.modelo;


public class Venta {
    private static int contadorId = 1;
    private int id;

    public Venta() {
        this.id = contadorId++;
    }

    public void agregarProducto(Producto producto, int cantidad) {
    }

    }

    }

    public double calcularTotal() {
        double total = 0;
        }
        return total;
    }

    public String mostrarCarritoActual() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- Carrito actual ---\n");
            sb.append(String.format("  %s x%d | Subtotal: S/%.2f%n",
        }
        sb.append(String.format("Total acumulado: S/%.2f%n", calcularTotal()));
        sb.append("----------------------");
        return sb.toString();
    }

    public String mostrarResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== RESUMEN DE VENTA #").append(id).append(" =====\n");
                    p.getNombre(), cant, p.getPrecio() * cant));
        }
        sb.append("================================");
        return sb.toString();
    }
}