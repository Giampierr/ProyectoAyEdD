package tienda.repositorio;
import tienda.modelo.Producto;
import java.util.List;

public class ProductosRepositorio {
    private List<Producto> productos;

    public ProductosRepositorio(List<Producto> productos) {
        this.productos = productos;
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
}
