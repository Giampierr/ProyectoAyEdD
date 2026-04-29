package tienda.repositorio;
import tienda.modelo.Cliente;
import tienda.modelo.Producto;

import java.util.List;

public class ClientesRepositorio {
    private List<Cliente> clientes;

    public ClientesRepositorio(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void guardar(Cliente misClientes) {
        clientes.add(misClientes);
    }

    public String busquedaLinea(String nombreBuscado) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return cliente.mostrar();
            }
        }
        return "No se encontro el cliente";
    }

    public String busquedaDni(String dniBuscado) {
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equalsIgnoreCase(dniBuscado)) {
                return cliente.mostrar();
            }
        }
        return "No se encontro el cliente ";
    }
}
