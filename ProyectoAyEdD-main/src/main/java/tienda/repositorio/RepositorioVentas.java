package tienda.repositorio;

import tienda.interfaces.RepositorioGenerico;
import tienda.modelo.Item;
import tienda.modelo.Ventas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioVentas implements RepositorioGenerico<Ventas> {
    // ATRIBUTOS 
    private List<Ventas> historialVentas = new ArrayList<>();
    private Map<Integer, Double> mapaSubtotales = new HashMap<>();

    @Override
    public void agregar(Ventas entidad) {
        // Guardar() -> Agrega al historial y registra su total en el HashMap
        historialVentas.add(entidad);
        mapaSubtotales.put(entidad.getId(), entidad.getTotal());
    }

    @Override
    public Ventas buscar() {
        // Buscar() -> Retorna la última venta registrada
        if (!historialVentas.isEmpty()) {
            return historialVentas.get(historialVentas.size() - 1);
        }
        return null;
    }

    @Override
    public void eliminar(Ventas producto) {
        if (producto != null) {
            historialVentas.remove(producto);
            mapaSubtotales.remove(producto.getId());
        }
    }

    @Override
    public String listar() {
        // Listar() -> Si está vacío
        if (historialVentas.isEmpty()) {
            return "🛒 El repositorio de ventas está vacío. No hay comprobantes emitidos.";
        }

        // Usamos el StringBuilder (Builder) para armar de forma óptima el reporte de los tickets
        StringBuilder reporteBuilder = new StringBuilder();

        for (Ventas venta : historialVentas) {
            reporteBuilder.append("\n================================================\n")
                          .append("          ✨ ").append(venta.getEmpresa().replace("\n", " ✨\n          ")).append("\n")
                          .append("================================================\n");
            
            reporteBuilder.append(String.format(" TICKET FACTURA  : FT-%06d\n", venta.getId()))
                          .append(" FECHA Y HORA    : ").append(venta.getFecha()).append("\n")
                          .append("------------------------------------------------\n")
                          .append(" CLIENTE DETALLE:\n")
                          .append(" ").append(venta.getCliente()).append("\n")
                          .append("------------------------------------------------\n")
                          .append(String.format("%-22s %-6s %-8s %-8s\n", "PRODUCTO", "CANT.", "P.UNIT", "SUBTOTAL"))
                          .append("------------------------------------------------\n");

            // Listar los productos agregados en el detalle
            for (Item item : venta.getListProductos()) {
                String nombreProd = item.getProducto().getNombre();
                if (nombreProd.length() > 20) {
                    nombreProd = nombreProd.substring(0, 18) + "..";
                }
                int cant = item.getCantidad();
                double precioUnit = item.getProducto().getPrecio();
                double subtotalItem = precioUnit * cant;

                reporteBuilder.append(String.format("%-22s %-6d S/.%-6.2f S/.%-6.2f\n", 
                        nombreProd, cant, precioUnit, subtotalItem));
            }

            // Recuperamos el total desde nuestro HashMap de subtotales (con el ID de la venta)
            double totalNeto = mapaSubtotales.getOrDefault(venta.getId(), venta.getTotal());
            double igv = totalNeto * 0.18;
            double subtotalBase = totalNeto - igv;

            reporteBuilder.append("------------------------------------------------\n")
                          .append(String.format(" SUB-TOTAL (Base Imponible):         S/. %.2f\n", subtotalBase))
                          .append(String.format(" I.G.V. (18%%):                        S/. %.2f\n", igv))
                          .append("------------------------------------------------\n")
                          .append(String.format(" TOTAL A PAGAR:                      S/. %.2f\n", totalNeto))
                          .append("================================================\n")
                          .append("          ¡Gracias por su preferencia!          \n")
                          .append("================================================\n\n");
        }

        return reporteBuilder.toString();
    }
}