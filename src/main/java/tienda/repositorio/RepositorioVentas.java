package tienda.repositorio;

import tienda.base.Venta;
import tienda.interfaces.RepositorioGenerico;
import tienda.modelo.VentaDirecta;

import java.util.ArrayList;
import java.util.HashMap;

public class RepositorioVentas implements RepositorioGenerico<Venta> {
    ArrayList<Venta> listaVentaDirecta = new ArrayList<>();
    HashMap <Integer, Venta> hashVentaDirecta = new HashMap<>();

    public RepositorioVentas() {
        cargarDatosIniciales();
    }

    public void cargarDatosIniciales(){
        //Aqui se cargan las ventas(15)
        agregar(crearVenta("Juan Perez", "12345678", "Laptop Lenovo", 2500.0, 1, "Mouse Logitech G203", 120.0, 2));
        agregar(crearVenta("Maria Lopez", "87654321", "Laptop HP", 2800.0, 1, "Teclado Logitech", 150.0, 1));
        agregar(crearVenta("Carlos Ramos", "45678912", "SSD Kingston 1TB", 320.0, 2, "RAM 16GB DDR4", 250.0, 2));
        agregar(crearVenta("Ana Torres", "74125896", "Monitor Samsung 24", 650.0, 1, "Cable HDMI", 25.0, 3));
        agregar(crearVenta("Luis Diaz", "36925814", "RTX 4060", 2200.0, 1, "Fuente 650W", 320.0, 1));
        agregar(crearVenta("Pedro Sanchez", "11223344", "Procesador Ryzen 5", 900.0, 1, "Placa Madre B550", 700.0, 1));
        agregar(crearVenta("Lucia Gomez", "22334455", "Audifonos HyperX", 320.0, 1, "Webcam Full HD", 90.0, 1));
        agregar(crearVenta("Jorge Castillo", "33445566", "Impresora Epson", 750.0, 1, "Disco HDD 2TB", 280.0, 1));
        agregar(crearVenta("Valeria Ruiz", "44556677", "Router TP-Link", 180.0, 1, "Switch 8 Puertos", 150.0, 2));
        agregar(crearVenta("Diego Mendoza", "55667788", "Tablet Samsung", 950.0, 1, "Power Bank 10000mAh", 90.0, 1));
        agregar(crearVenta("Sofia Herrera", "66778899", "Laptop Dell", 3100.0, 1, "Base Refrigerante Laptop", 85.0, 1));
        agregar(crearVenta("Ricardo Flores", "77889900", "Monitor Gamer MSI", 1200.0, 1, "Mouse Razer DeathAdder", 220.0, 1));
        agregar(crearVenta("Camila Torres", "88990011", "RAM 32GB DDR4", 450.0, 1, "SSD Samsung 1TB", 450.0, 1));
        agregar(crearVenta("Fernando Rojas", "99001122", "Case Gamer RGB", 250.0, 1, "Fuente 750W", 450.0, 1));
        agregar(crearVenta("Paula Diaz", "10112233", "iPad 10", 1800.0, 1, "Cargador USB-C", 45.0, 2));
    }

    @Override
    public String agregar(Venta entidad) {
        listaVentaDirecta.add(entidad);
        hashVentaDirecta.put(entidad.getId(),entidad);

        return "Venta Agregada";
    }

    @Override
    public Venta buscar() {
        return null;
    }

    @Override
    public void eliminar(Venta producto) {

    }

    @Override
    public String listar() {
        StringBuilder miBuilder = new StringBuilder();
        miBuilder.append("--------Lista Venta---------").append("\n");

        for (Venta venta : listaVentaDirecta) {
            miBuilder.append(venta.mostrarResumen()).append("\n");
        }


        return miBuilder.toString();
    }


}
