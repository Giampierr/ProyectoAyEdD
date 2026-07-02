package tienda.repositorio;

import tienda.modelo.Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientesRepositorio   {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private Map<String,Cliente> clientePorDni = new HashMap<>();

    public ClientesRepositorio() {
        cargarClientesIniciales();
    }
    private void cargarClientesIniciales() {
        //Aqui se cargan los  datos (100 clientes)

        guardar(new Cliente("Juan Perez", "12345678", "juan@gmail.com", "987654321"));
        guardar(new Cliente("Maria Lopez", "87654321", "maria@gmail.com", "912345678"));
        guardar(new Cliente("Carlos Ramos", "45678912", "carlos@gmail.com", "999888777"));
        guardar(new Cliente("Ana Torres", "74125896", "ana@gmail.com", "955444333"));
        guardar(new Cliente("Luis Diaz", "36925814", "luis@gmail.com", "900111222"));
    }
    public void guardar(Cliente misClientes) {
        clientes.add(misClientes);
        clientePorDni.put(misClientes.getDni(),misClientes);
    }

    public String busquedaLinea(String nombreBuscado) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return cliente.mostrar();
            }
        }
        return "No se encontro el cliente";
    }
    //Igual
    public Cliente busquedaDni(String dniBuscado) {
        Cliente cliente = clientePorDni.get(dniBuscado);

        return cliente;
    }

}
