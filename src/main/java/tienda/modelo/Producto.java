package tienda.modelo;
import tienda.interfaces.MostrarInformación;
public class Producto implements MostrarInformación{
    private static int contadorId = 1;
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto( String nombre, double precio, int stock) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    @Override
    public String mostrar() {
        return String.format("Producto : %s Precio : %s Stock : %s",nombre,precio,stock);
    }
}
