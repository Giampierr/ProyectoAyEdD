package tienda.estructuras;

import tienda.modelo.Producto;
import tienda.nodos.NodoProducto;

public class ListaEnlazadaProductos {
    private NodoProducto cabeza;

    public void agregar(Producto producto) {

        NodoProducto nuevo = new NodoProducto(producto);

        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }

        NodoProducto actual = cabeza;

        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        actual.setSiguiente(nuevo);
    }
    public NodoProducto getCabeza() {
        return cabeza;
    }
}
