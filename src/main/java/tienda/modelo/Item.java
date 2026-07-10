package tienda.modelo;

public class Item {
    private Producto producto;
    private int cantidad;


    public Item(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }


    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int obtenerId(){
        return producto.getId();
    }

    public int obtenerStock(){
        return producto.getStock();
    }

    public double getSubtotal(){
        return calcularSubtotal();
    }
    public double calcularSubtotal(){
        return producto.getPrecio()*cantidad;
    }

    public String mostrar(){
        return String.format("Nombre : %-30s p/u : %.2f Cantidad : %-3s Subtotal : %-4.2f",producto.getNombre(),producto.getPrecio(),cantidad,calcularSubtotal());
    }
}
