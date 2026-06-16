package tienda.modelo;

public class Item {
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public Item() {
        this.subtotal = calcularSubtotal();
    }

    public double calcularSubtotal(){
        return producto.getPrecio()*cantidad;
    }

}
