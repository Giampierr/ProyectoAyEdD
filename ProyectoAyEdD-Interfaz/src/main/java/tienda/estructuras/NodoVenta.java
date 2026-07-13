package tienda.estructuras;

import tienda.base.Venta;

public class NodoVenta {
    public Venta dato;
    public NodoVenta siguiente;
    public NodoVenta anterior;

    public NodoVenta(Venta dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }
}