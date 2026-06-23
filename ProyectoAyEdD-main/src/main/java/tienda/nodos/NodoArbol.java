package tienda.nodos;

import tienda.modelo.Producto;

public class NodoArbol {
    public Producto producto;
    public NodoArbol izquierdo;
    public NodoArbol derecho;

    public NodoArbol(Producto producto) {
        this.producto = producto;
        this.izquierdo = null;
        this.derecho = null;
    }
}
