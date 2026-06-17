package tienda.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Ventas {
    // ATRIBUTOS 
    private static int contador = 0; // Contador estático global
    private int id;
    private String empresa;
    private String cliente;
    private List<Item> listProductos; // Usamos Item para manejar Producto + Cantidad de forma óptima
    private String fecha;
    private double total;

    // Constructor privado: Solo el Builder interno puede instanciar esta clase
    private Ventas() {
        this.listProductos = new ArrayList<>();
    }

    // MÉTODOS DE ACCIÓN 
    
    /**
     * Procesar(Cliente, list<Producto>): Carga los datos principales de la transacción
     */
    public void procesar(String cliente, List<Item> productos) {
        this.cliente = cliente;
        this.listProductos = productos;
        calcularTotal();
    }

    /**
     * Calcular Total: Ejecuta la operación matemática de los subtotales
     */
    public void calcularTotal() {
        this.total = 0.0;
        for (Item item : listProductos) {
            if (item.getProducto() != null) {
                this.total += item.getProducto().getPrecio() * item.getCantidad();
            }
        }
    }

    // GETTERS (Para que el Repositorio pueda leer los datos e imprimir el ticket)
    public int getId() { return id; }
    public String getEmpresa() { return empresa; }
    public String getCliente() { return cliente; }
    public List<Item> getListProductos() { return listProductos; }
    public String getFecha() { return fecha; }
    public double getTotal() { return total; }


    // =========================================================================
    // PATRÓN BUILDER INTERNO (Para construir la factura/boleta fluidamente)
    // =========================================================================
    public static class VentasBuilder {
        private Ventas ventaInstancia;

        public VentasBuilder() {
            this.ventaInstancia = new Ventas();
            
            // Auto-incremento del identificador único
            Ventas.contador++;
            this.ventaInstancia.id = Ventas.contador;
            
            // Datos comerciales por defecto de la empresa
            this.ventaInstancia.empresa = "SUPERMERCADO MATRIZ\nR.U.C. 20123456789\nAv. Prolongación Paseo de la República, Chorrillos";
            
            // Captura automática de Fecha y Hora del sistema en vivo
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            this.ventaInstancia.fecha = ahora.format(formato);
        }

        public VentasBuilder conCliente(String cliente) {
            this.ventaInstancia.cliente = cliente;
            return this;
        }

        public VentasBuilder agregarProducto(Item item) {
            this.ventaInstancia.listProductos.add(item);
            return this;
        }

        public VentasBuilder conProductos(List<Item> productos) {
            this.ventaInstancia.listProductos = productos;
            return this;
        }

        // Cierre del ciclo de construcción del objeto
        public Ventas build() {
            this.ventaInstancia.calcularTotal();
            return this.ventaInstancia;
        }
    }
}