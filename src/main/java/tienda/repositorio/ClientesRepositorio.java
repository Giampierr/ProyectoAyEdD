package tienda.repositorio;
import tienda.interfaces.MostrarInformación;
import tienda.modelo.Cliente;
import tienda.modelo.Producto;

import java.util.List;

public class ClientesRepositorio implements MostrarInformación {
    private List<Cliente> clientes;

    public ClientesRepositorio(List<Cliente> clientes) {
        this.clientes = clientes;
        cargarClientesIniciales();
    }
    private void cargarClientesIniciales() {
        guardar(new Cliente("Juan Perez", "12345678", "juan@gmail.com", "987654321"));
        guardar(new Cliente("Maria Lopez", "87654321", "maria@gmail.com", "912345678"));
        guardar(new Cliente("Carlos Ramos", "45678912", "carlos@gmail.com", "999888777"));
        guardar(new Cliente("Ana Torres", "74125896", "ana@gmail.com", "955444333"));
        guardar(new Cliente("Luis Diaz", "36925814", "luis@gmail.com", "900111222"));
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

    @Override
    public String mostrar() {
        return "";
    }
}
