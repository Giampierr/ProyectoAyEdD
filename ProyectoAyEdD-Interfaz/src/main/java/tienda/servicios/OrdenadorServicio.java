package tienda.servicios;

import tienda.modelo.Producto;
import tienda.repositorio.ProductosRepositorio;

import java.util.ArrayList;

public class OrdenadorServicio {
    private ProductosRepositorio repo;

    public OrdenadorServicio(ProductosRepositorio repo) {
        this.repo = repo;
    }


    public String ordenarPorNombre(){

        ArrayList<Producto> copiaProductos = new ArrayList<>(repo.listar());


        for (int i = 0; i < copiaProductos.size()- 1; i++) {

            for (int j = 0; j < copiaProductos.size() - 1 - i; j++) {

                String actual = copiaProductos.get(j).getNombre();
                String siguiente =copiaProductos.get(j + 1).getNombre();

                if (actual.compareToIgnoreCase(siguiente) > 0) {

                    Producto temp =copiaProductos.get(j);
                    copiaProductos.set(j, copiaProductos.get(j + 1));
                    copiaProductos.set(j + 1, temp);

                }
            }
        }

        StringBuilder miBuilder = new StringBuilder();

        for (Producto producto : copiaProductos) {
            miBuilder.append(producto.mostrar()).append("\n");
        }

        return miBuilder.toString();
    }

    public String ordenarPorPrecio(){

        ArrayList<Producto> copiaProductos = new ArrayList<>(repo.listar());

        for (int i = 0; i < copiaProductos.size() - 1; i++) {

            for (int j = 0; j < copiaProductos.size() - 1 - i; j++) {

                double actual = copiaProductos.get(j).getPrecio();
                double siguiente = copiaProductos.get(j + 1).getPrecio();

                if (actual > siguiente) {

                    Producto temp = copiaProductos.get(j);
                    copiaProductos.set(j, copiaProductos.get(j + 1));
                    copiaProductos.set(j + 1, temp);

                }
            }
        }

        StringBuilder miBuilder = new StringBuilder();

        for (Producto producto : copiaProductos) {
            miBuilder.append(producto.mostrar()).append("\n");
        }

        return miBuilder.toString();    }
}
