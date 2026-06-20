package tienda.repositorio;
import tienda.interfaces.Actualizar;
import tienda.modelo.Producto;
import tienda.interfaces.AlertarBajoStock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductosRepositorio  {
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private Map<Integer,Producto> hashProductos = new HashMap<>();
    //Aqui agrega el tipo de dato Arbol (arbolProducto)


    public ProductosRepositorio() {
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        //Aqui se cargan los  datos (100 productos)
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

    //Regresa una lista Producto para usarse en otra clase


    public List<Producto>listar(){
        return listaProductos;
    }

    public void guardar(Producto misProducto) {
        listaProductos.add(misProducto);
        hashProductos.put(misProducto.getId(),misProducto);

        //Guardalo Aqui
    }

    public String listarProductos() {
        StringBuilder sb = new StringBuilder();
        for (Producto producto : listaProductos) {
            sb.append(producto.mostrar()+"\n");
        }
        return sb.toString();
    }

    //Completa aqui el filtrado
    public String filtrarProducto(double precio){
        return "";
    }
    

    public String busquedaNombre(String nombreBuscado) {
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return producto.mostrar();
            }
        }
        return "No se encontro el producto";
    }
    //Se renovo(Ya no es lineal ahora es más rapida O(1))
    public String busquedaId(int Id){

        Producto producto = hashProductos.get(Id);

        if (producto != null) {
             return producto.mostrar();
        } else {
            return "No se encontro el producto";
        }
    }
    public Producto buscarPorId(int id) {
        return hashProductos.get(id);
    }

}
