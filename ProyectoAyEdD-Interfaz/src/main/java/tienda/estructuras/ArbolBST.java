package tienda.estructuras;

import tienda.modelo.Producto;
import tienda.nodos.NodoArbol;

import java.util.ArrayList;
import java.util.List;

public class ArbolBST {
    private NodoArbol raiz;

    public ArbolBST() {
        this.raiz = null;
    }

    public void insertar(Producto producto) {
        raiz = insertarRec(raiz, producto);
    }

    private NodoArbol insertarRec(NodoArbol nodo, Producto producto) {
        if (nodo == null) return new NodoArbol(producto);
        if (producto.getPrecio() < nodo.producto.getPrecio())
            nodo.izquierdo = insertarRec(nodo.izquierdo, producto);
        else
            nodo.derecho = insertarRec(nodo.derecho, producto);
        return nodo;
    }

    public List<Producto> filtrarMenoresA(double precio) {
        List<Producto> resultado = new ArrayList<>();
        filtrarRec(raiz, precio, resultado);
        return resultado;
    }

    private void filtrarRec(NodoArbol nodo, double precio, List<Producto> resultado) {
        if (nodo == null) return;
        if (nodo.producto.getPrecio() < precio) {
            resultado.add(nodo.producto);
            filtrarRec(nodo.derecho, precio, resultado);
        }
        filtrarRec(nodo.izquierdo, precio, resultado);
    }
}
