package tienda.modelo;
import tienda.interfaces.AlertarBajoStock;
import tienda.interfaces.MostrarInformación;
public class Producto implements MostrarInformación {
    private static int contadorId = 1;
    private int id;
    private String nombre;
    private TipoCategoria tipo;
    private double precio;
    private int stock;

    public Producto( String nombre,TipoCategoria tipo ,double precio, int stock) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Producto.contadorId = contadorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoCategoria getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }



    @Override
    public String mostrar() {
        return String.format("Id: %-4d | Producto: %-30s | Precio: %-10.2f | Stock: %-5d",id,nombre,precio,stock);
    }

}
