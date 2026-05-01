package tienda.repositorio;
import tienda.interfaces.Actualizar;
import tienda.modelo.Producto;
import tienda.interfaces.AlertarBajoStock;
import java.util.List;

public class ProductosRepositorio  {
    private List<Producto> productos;

    public ProductosRepositorio(List<Producto> productos) {
        this.productos = productos;
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        guardar(new Producto("Laptop", 2500.0, 5));
        guardar(new Producto("Laptop Gamer", 4200.0, 3));
        guardar(new Producto("RAM 8GB", 120.0, 15));
        guardar(new Producto("RAM 16GB", 250.0, 7));
        guardar(new Producto("SSD 500GB", 180.0, 10));
        guardar(new Producto("SSD 1TB", 300.0, 8));
        guardar(new Producto("Procesador Ryzen 5", 900.0, 6));
        guardar(new Producto("Procesador Intel i7", 1200.0, 4));
        guardar(new Producto("Tarjeta Gráfica RTX 4060", 1800.0, 2));
        guardar(new Producto("Tarjeta Gráfica RTX 4080", 3500.0, 1));
    }
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Producto>listar(){
        return productos;
    }
    public void guardar(Producto misProducto) {
        productos.add(misProducto);
    }
    public String listarProductos() {
        StringBuilder sb = new StringBuilder();
        for (Producto producto : productos) {
            sb.append(producto.mostrar()+"\n");
        }
        return sb.toString();
    }

    public String busquedaLineal(String nombreBuscado) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return producto.mostrar();
            }
        }
        return "No se encontro el producto";
    }

    public String busquedaLinealId(int Id){
        for (Producto producto : productos) {
            if (producto.getId() == Id) {
                return producto.mostrar();
            }
        }
        return "No se encontro el producto";
    }
}
