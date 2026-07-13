package tienda.vista;

import tienda.modelo.Cliente;
import tienda.modelo.Producto;
import tienda.repositorio.ClientesRepositorio;
import tienda.repositorio.ProductosRepositorio;
import tienda.servicios.VentaServicio;

import java.util.List;
import java.util.Scanner;

public class MenuVenta {
    private ProductosRepositorio miRepoProductos;
    private ClientesRepositorio miRepoClientes;
    private VentaServicio ventaServicio;


    public MenuVenta(ClientesRepositorio miRepoClientes, ProductosRepositorio miRepoProductos, VentaServicio ventaServicio) {
        this.miRepoClientes = miRepoClientes;
        this.miRepoProductos = miRepoProductos;
        this.ventaServicio = ventaServicio;
    }

    void iniciar(){
        Scanner miScanner = new Scanner(System.in);
        String dni;
        Cliente cliente;
        int miVista = -1,id;
        double precio;
        System.out.println("Iniciando venta ...");
        do{
            System.out.println("1.Agregar producto al carrito");
            System.out.println("2.Eliminar producto del carrito");
            System.out.println("3.Listar producto del carrito");
            System.out.println("4.Vaciar Carrito");
            System.out.println("5.Procesar Venta");
            System.out.println("6.Procesar Pedido");
            System.out.println("7.Filtrar precios menores a");
            System.out.println("0.Salir");
            miVista = miScanner.nextInt();
            switch (miVista){
                case 1:
                    System.out.println(miRepoProductos.listarProductos());
                    miScanner.nextLine();
                    id = miScanner.nextInt();
                    System.out.println(ventaServicio.agregar(id));
                    break;
                case 2:
                    System.out.println(ventaServicio.listar());
                    System.out.println("Ingrese el id del producto para quitar del carrito");
                    miScanner.nextLine();
                    id = miScanner.nextInt();
                    System.out.println(ventaServicio.eliminarPorId(id));
                    break;
                case 3:
                    System.out.println(ventaServicio.listar());
                    break;
                case 4:
                    ventaServicio.vaciarCarrito();
                    break;
                case 5:
                    System.out.print("Ingrese el dni del cliente :");
                    dni = miScanner.next();
                    cliente = miRepoClientes.busquedaDni(dni);
                    if (cliente != null) {
                        System.out.println(ventaServicio.procesarVenta(cliente));
                    }else {
                        System.out.println("No existe el cliente con ese DNI");
                    }
                    break;
                case 6:
                    System.out.print("Ingrese el dni del cliente :");
                    dni = miScanner.next();
                    cliente = miRepoClientes.busquedaDni(dni);
                    if (cliente != null) {
                        System.out.println(ventaServicio.procesarPedido(cliente));
                    }else {
                        System.out.println("No existe el cliente con ese DNI");
                    }
                    break;
                case 7:
                    System.out.println("Ingrese un precio :");
                    precio = miScanner.nextDouble();
                    List<Producto> resultado = miRepoProductos.filtrarPreciosMenoresA(precio);
                    if (resultado.isEmpty()) {
                        System.out.println("No se encontraron productos menores a " + precio);
                    } else {
                        System.out.println("Productos menores a " + precio + ":");
                        for (Producto p : resultado) {
                            System.out.println("- " + p.getNombre() + " S/ " + p.getPrecio());
                        }
                    }
                    break;

            }
        }while(miVista != 0);
    }
}
