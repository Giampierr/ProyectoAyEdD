package tienda.repositorio;

import tienda.interfaces.RepositorioGenerico;
import tienda.modelo.Pedido;

public class RepositorioPedidos implements RepositorioGenerico<Pedido> {
    @Override
    public void agregar(Pedido entidad) {

    }

    @Override
    public Pedido buscar() {
        return null;
    }

    @Override
    public void eliminar(Pedido producto) {

    }

    @Override
    public String listar() {
        return "";
    }
}
