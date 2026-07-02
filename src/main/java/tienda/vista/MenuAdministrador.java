package tienda.vista;

import tienda.modelo.Admin;
import tienda.modelo.Producto;
import tienda.repositorio.ProductosRepositorio;
import tienda.repositorio.RepositorioPedidos;
import tienda.repositorio.RepositorioVentas;
import tienda.servicios.OrdenadorServicio;
import tienda.servicios.StockServicio;
import tienda.servicios.VentaServicio;

import java.security.PrivateKey;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAdministrador {
    private Admin admin;
    private ProductosRepositorio miInventario;
    private StockServicio stockServicio;
    private VentaServicio ventaServicio;
    private OrdenadorServicio ordenarServicio;
    private RepositorioVentas repositorioVentas;
    private RepositorioPedidos repositorioPedido;

    public MenuAdministrador(Admin admin, ProductosRepositorio miInventario, OrdenadorServicio ordenarServicio, StockServicio stockServicio, RepositorioVentas repositorioVentas, RepositorioPedidos repositorioPedido,VentaServicio ventaServicio) {
        this.admin = admin;
        this.miInventario = miInventario;
        this.ordenarServicio = ordenarServicio;
        this.stockServicio = stockServicio;
        this.repositorioVentas = repositorioVentas;
        this.repositorioPedido = repositorioPedido;
        this.ventaServicio = ventaServicio;
    }

    void inicar(){
        int miEntrada = -1,id,stock,alertaCon = 0;
        String nombre;
        double precio;

        Scanner miScanner = new Scanner(System.in);
        System.out.println("Ingresa la contraseña");
        String password = miScanner.next();
        miScanner.nextLine();
        if (admin.validarPassword(password)) {
            do {
                if (alertaCon < 2) {
                    System.out.println(stockServicio .alertar());
                    alertaCon++;
                }
                System.out.println("\n0) salir");
                System.out.println("1) Añadir Producto");
                System.out.println("2) Actualizar Stock");
                System.out.println("3) Actualizar Precio");
                System.out.println("4) Listar Inventario");
                System.out.println("5) Buscar por nombre");
                System.out.println("6) Buscar por id");
                System.out.println("7) Ordenar por nombre");
                System.out.println("8) Ordenar por precio");
                System.out.println("9) Ver ventas");
                System.out.println("10) Ver pedidos");
                System.out.println("11) Atender Primer Pedido");
                System.out.println("12).Rechazar Pedido");
                try {
                    System.out.print("Ingrese opción: ");
                    miEntrada = miScanner.nextInt();
                    miScanner.nextLine();
                    switch (miEntrada) {
                        case 1:
                            try {
                                System.out.print("Nombre: ");
                                nombre = miScanner.nextLine();
                                miScanner.nextLine();

                                Producto producto3 = miInventario.busquedaNombre(nombre);

                                if (producto3 != null){
                                    System.out.println("Producto ya encontrado");
                                }else {
                                    System.out.print("Stock: ");
                                    stock = miScanner.nextInt();
                                    miScanner.nextLine();
                                    System.out.print("Precio: ");
                                    precio = miScanner.nextDouble();
                                    miScanner.nextLine();
                                    Producto producto = new Producto(nombre, precio, stock);
                                    miInventario.guardar(producto);
                                    System.out.println("Producto guardado.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Precio o stock inválido.");
                                miScanner.nextLine();
                            }
                            break;
                        case 2:
                            try {
                                System.out.println(miInventario.listarProductos());
                                System.out.print("Ingrese el id : ");
                                id = miScanner.nextInt();
                                miScanner.nextLine();

                                Producto producto = miInventario.buscarPorId(id);

                                if (producto != null) {
                                    System.out.print("Ingrese el stock nuevo:");
                                    stock = miScanner.nextInt();
                                    miScanner.nextLine();

                                    System.out.println(
                                            miInventario.actualizarStock(stock, id)
                                    );
                                } else {
                                    System.out.println("Id inválido");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Debe ingresar valores numéricos.");
                                miScanner.nextLine();
                            }
                            break;
                        case 3:
                            try {
                                System.out.println(miInventario.listarProductos());
                                System.out.print("Ingrese el id : ");
                                id = miScanner.nextInt();
                                miScanner.nextLine();

                                Producto producto1 = miInventario.buscarPorId(id);

                                if (producto1 != null) {
                                    System.out.print("Ingrese el precio nuevo:");
                                    precio = miScanner.nextDouble();
                                    miScanner.nextLine();

                                    System.out.println(
                                            miInventario.actualizarPrecio(precio, id)
                                    );
                                } else {
                                    System.out.println("Id inválido");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Debe ingresar valores numéricos.");
                                miScanner.nextLine();
                            }
                            break;
                        case 4:
                            System.out.println(miInventario.listarProductos());
                            break;
                        case 5:
                            System.out.print("Nombre a buscar: ");
                            nombre = miScanner.nextLine();
                            miScanner.nextLine();
                            Producto producto2 = miInventario.busquedaNombre(nombre);
                            if (producto2 != null) {
                                System.out.println(producto2.mostrar());
                            } else {
                                System.out.println("Producto no encontrado");
                            }
                            break;
                        case 6:
                            try {
                                System.out.print("ID a buscar: ");
                                id = miScanner.nextInt();
                                miScanner.nextLine();

                                System.out.println(
                                        miInventario.busquedaId(id)
                                );

                            } catch (InputMismatchException e) {
                                System.out.println("ID inválido.");
                                miScanner.nextLine();
                            }
                            break;
                        case 7:
                            System.out.println(ordenarServicio.ordenarPorNombre());
                            break;
                        case 8:
                            System.out.println(ordenarServicio.ordenarPorPrecio());
                            break;
                        case 9:
                            System.out.println(repositorioVentas.listar());
                            break;
                        case 10:
                            System.out.println(repositorioPedido.listarResumen());
                            break;
                        case 11:
                            System.out.println(ventaServicio.atenderPrimerPedido());
                            break;
                        case 12:
                            System.out.println(ventaServicio.rechazarPedido());
                            break;
                        case 0:
                            System.out.println("Saliendo...");
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Debes ingresar un número.");
                    miScanner.nextLine();
                }

            } while (miEntrada != 0);

        }else{
            System.out.println("Contraseña Incorrecta");
        }
    }
}
