package tienda.servicios;

import tienda.modelo.Producto;
import tienda.repositorio.ProductosRepositorio;

public class OrdenadorServicio {
    private ProductosRepositorio repo;

    public OrdenadorServicio(ProductosRepositorio repo) {
        this.repo = repo;
    }
    public String ordenarPorNombre(){
        for (int i = 0; i < repo.listar().size()- 1; i++) {

            for (int j = 0; j < repo.listar().size() - 1 - i; j++) {

                String actual = repo.listar().get(j).getNombre();
                String siguiente = repo.listar().get(j + 1).getNombre();

                if (actual.compareToIgnoreCase(siguiente) > 0) {

                    Producto temp = repo.listar().get(j);
                    repo.listar().set(j, repo.listar().get(j + 1));
                    repo.listar().set(j + 1, temp);

                }
            }
        }
        return repo.listarProductos().toString();
    }

    public String ordenarPorPrecio(){
        for (int i = 0; i < repo.listar().size() - 1; i++) {

            for (int j = 0; j < repo.listar().size() - 1 - i; j++) {

                double actual = repo.listar().get(j).getPrecio();
                double siguiente = repo.listar().get(j + 1).getPrecio();

                if (actual > siguiente) {

                    Producto temp = repo.listar().get(j);
                    repo.listar().set(j, repo.listar().get(j + 1));
                    repo.listar().set(j + 1, temp);

                }
            }
        }
        return repo.listarProductos().toString();
    }
}
