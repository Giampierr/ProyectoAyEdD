package tienda.repositorio;
import tienda.estructuras.ArbolBST;
import tienda.estructuras.ListaEnlazadaProductos;
import tienda.interfaces.Actualizar;
import tienda.modelo.Producto;
import tienda.interfaces.AlertarBajoStock;
import tienda.modelo.TipoCategoria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductosRepositorio  {
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private ListaEnlazadaProductos listaEnlazadaProductosProductos = new ListaEnlazadaProductos();
    private Map<Integer,Producto> hashProductos = new HashMap<>();
    private final ArbolBST arbol = new ArbolBST();
    //Aqui agrega el tipo de dato Arbol (arbolProducto)


    public ProductosRepositorio() {
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        //Aqui se cargan los  datos (100 productos)
        guardar(new Producto("Laptop", TipoCategoria.LAPTOP,2500.0, 5));
        guardar(new Producto("Laptop Gamer", TipoCategoria.LAPTOP,4200.0, 3));
        guardar(new Producto("RAM 8GB",TipoCategoria.RAM, 120.0, 15));
        guardar(new Producto("RAM 16GB", TipoCategoria.RAM,250.0, 7));
        guardar(new Producto("SSD 500GB", TipoCategoria.ALMACENAMIENTO,180.0, 10));
        guardar(new Producto("SSD 1TB", TipoCategoria.ALMACENAMIENTO,300.0, 8));
        guardar(new Producto("Procesador Ryzen 5", TipoCategoria.PROCESADOR,900.0, 6));
        guardar(new Producto("Procesador Intel i7", TipoCategoria.PROCESADOR,1200.0, 4));
        guardar(new Producto("Tarjeta Gráfica RTX 4060", TipoCategoria.GRAFICA,1800.0, 2));
        guardar(new Producto("Tarjeta Gráfica RTX 4080", TipoCategoria.GRAFICA,3500.0, 1));
    }



    //Regresa una lista Producto para usarse en otra clase


    public List<Producto>listar(){
        return listaProductos;
    }

    public void guardar(Producto misProducto) {
        Producto miProducto = hashProductos.putIfAbsent(misProducto.getId(), misProducto);


        if(miProducto != null){
            int nuevoStock = miProducto.getStock()+misProducto.getStock();
            misProducto.setStock(nuevoStock);
        }
        listaProductos.add(misProducto);
        hashProductos.put(misProducto.getId(),misProducto);
        listaEnlazadaProductosProductos.agregar(misProducto);
        arbol.insertar(misProducto);
    }

    public String listarProductos() {
        StringBuilder sb = new StringBuilder();
        for (Producto producto : listaProductos) {
            sb.append(producto.mostrar()).append("\n");
        }
        sb.append("=========================");

        return sb.toString();
    }

    //Completa aqui el filtrado
    public List<Producto> filtrarPreciosMenoresA(double precio){

        return arbol.filtrarMenoresA(precio);
    }
    

    public Producto busquedaNombre(String nombreBuscado) {

        for (Producto producto : listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return producto;
            }
        }
        return null;
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

    public String actualizarPrecio(double precio,int id){
        Producto producto = hashProductos.get(id);

        if (producto == null){
            return "Id no encontrado";
        }

        if (precio >=0){
            producto.setPrecio(precio);
            return "Precio actualizado";
        }else{
            return "Precio inválido";
        }
    }

    public String actualizarStock(int stock, int id){

        Producto producto = hashProductos.get(id);

        if (producto == null){
            return "Id no encontrado";
        }

        if (stock >=0){
            producto.setStock(stock);
            return "Stock actualizado";
        }else{
            return "Stock inválido";
        }
    }
}
