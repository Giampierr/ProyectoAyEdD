package tienda.nodos;

import tienda.modelo.Producto;

public class NodoProducto {
    private Producto producto;
    private NodoProducto siguiente;

    public NodoProducto(Producto producto) {
        this.producto = producto;
    }
    public Producto getProducto() {
        return producto;
    }

    public NodoProducto getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(NodoProducto siguiente){
        this.siguiente = siguiente;
    }
}
