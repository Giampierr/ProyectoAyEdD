package tienda.repositorio;

import tienda.interfaces.RepositorioGenerico;
import tienda.modelo.Ventas;

public class RepositorioVentas implements RepositorioGenerico<Ventas> {
    @Override
    public void agregar(Ventas entidad) {

    }

    @Override
    public Ventas buscar() {
        return null;
    }

    @Override
    public void eliminar(Ventas producto) {

    }

    @Override
    public String listar() {
        return "";
    }
}
