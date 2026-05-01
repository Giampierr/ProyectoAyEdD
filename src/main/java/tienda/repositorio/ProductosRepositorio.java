package tienda.repositorio;
import tienda.interfaces.Actualizar;
import tienda.modelo.Producto;
import tienda.interfaces.AlertarBajoStock;
import java.util.List;

public class ProductosRepositorio implements AlertarBajoStock, Actualizar {
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

    public String busquedaLineal(String nombreBuscado) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return producto.mostrar();
            }
        }
        return "No se encontro el producto";
    }

    //Lo usaré para actualizar stock
    public boolean busquedaLinealBoolean(String nombreBuscado) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return true;
            }
        }
        return false;
    }

    public String busquedaLinealId(int Id){
        for (Producto producto : productos) {
            if (producto.getId() == Id) {
                return producto.mostrar();
            }
        }
        return "No se encontro el producto";
    }

    public void ordenarPorNombre() {

        for (int i = 0; i < productos.size() - 1; i++) {

            for (int j = 0; j < productos.size() - 1 - i; j++) {

                String actual = productos.get(j).getNombre();
                String siguiente = productos.get(j + 1).getNombre();

                if (actual.compareToIgnoreCase(siguiente) > 0) {

                    Producto temp = productos.get(j);
                    productos.set(j, productos.get(j + 1));
                    productos.set(j + 1, temp);

                }
            }
        }
    }

    public void ordenarPorPrecio() {

        for (int i = 0; i < productos.size() - 1; i++) {

            for (int j = 0; j < productos.size() - 1 - i; j++) {

                double actual = productos.get(j).getPrecio();
                double siguiente = productos.get(j + 1).getPrecio();

                if (actual > siguiente) {

                    Producto temp = productos.get(j);
                    productos.set(j, productos.get(j + 1));
                    productos.set(j + 1, temp);

                }
            }
        }
    }
    @Override
    public String alertar(){
        StringBuilder miBuilder = new StringBuilder();
        for (int i = 0; i<productos.size();i++){
            if (productos.get(i).getStock() <10){
                miBuilder.append(String.format("!!!!!Alerta Stock bajo Id: %s Producto : %s Stock : %s ¡¡¡¡¡¡\n",productos.get(i).getId(),productos.get(i).getNombre(),productos.get(i).getStock()));
            }
        }
        return miBuilder.toString();
    }

    @Override
    public String actualizarStock(int miStockNuevo, String miProducto) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(miProducto)) {
                int stockActual = producto.getStock();
                producto.setStock(stockActual + (int)miStockNuevo);
            }
        }
        return "Producto ya encontrado , stock actualizado";
    }
}
