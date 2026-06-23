package tienda.vista;

import tienda.modelo.Producto;
import tienda.repositorio.ClientesRepositorio;
import tienda.repositorio.ProductosRepositorio;
import tienda.servicios.VentaServicio;

import java.util.List;
import java.util.Scanner;

public class MenuVenta {
    private ClientesRepositorio repoClientes;
    private ProductosRepositorio repoProductos;
    private VentaServicio ventaServicio;
    private Scanner scanner = new Scanner(System.in);

    public MenuVenta(ClientesRepositorio repoClientes, ProductosRepositorio repoProductos, VentaServicio ventaServicio) {
        this.repoClientes = repoClientes;
        this.repoProductos = repoProductos;
        this.ventaServicio = ventaServicio;
    }

    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n1. Agregar producto al carrito");
            System.out.println("2. Eliminar producto del carrito");
            System.out.println("3. Listar producto del carrito");
            System.out.println("4. Vaciar Carrito");
            System.out.println("5. Procesar Venta");
            System.out.println("6. Procesar Pedido");
            System.out.println("7. Filtrar precios menores a");
            System.out.println("0. Salir");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 7:
                    System.out.print("Ingrese un precio: ");
                    double precio = scanner.nextDouble();
                    List<Producto> resultado = ventaServicio.filtrarPreciosMenoresA(precio);
                    if (resultado.isEmpty()) {
                        System.out.println("No se encontraron productos menores a " + precio);
                    } else {
                        System.out.println("Productos menores a " + precio + ":");
                        for (Producto p : resultado) {
                            System.out.println("- " + p.getNombre() + " S/ " + p.getPrecio());
                        }
                    }
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no implementada aún");
            }
        }
    }
}