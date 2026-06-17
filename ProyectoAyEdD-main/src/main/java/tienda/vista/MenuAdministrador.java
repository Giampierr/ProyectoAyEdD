package tienda.vista;

import tienda.modelo.Admin;
import tienda.modelo.Producto;
import tienda.repositorio.ProductosRepositorio;
import tienda.servicios.OrdenadorServicio;
import tienda.servicios.StockServicio;

import java.security.PrivateKey;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAdministrador {
    private Admin admin;
    private ProductosRepositorio miInventario;
    private StockServicio stockServicio;
    private OrdenadorServicio ordenarServicio;

    public MenuAdministrador(Admin admin, ProductosRepositorio miInventario, OrdenadorServicio ordenarServicio, StockServicio stockServicio) {
        this.admin = admin;
        this.miInventario = miInventario;
        this.ordenarServicio = ordenarServicio;
        this.stockServicio = stockServicio;
    }

    void inicar(){
        int miEntrada = -1,id,stock;
        String nombre;
        double precio;

        Scanner miScanner = new Scanner(System.in);
        System.out.println("Ingresa la contraseña");
        String password = miScanner.next();
        miScanner.nextLine();
        if (admin.validarPassword(password) == true) {
            do {
                System.out.println(stockServicio .alertar());
                System.out.println("\n0) Salir");
                System.out.println("1) Añadir Producto");
                System.out.println("2) Listar Inventario");
                System.out.println("3) Buscar por nombre");
                System.out.println("4) Buscar por id");
                System.out.println("5) Ordenar por nombre");
                System.out.println("6) Ordenar por precio");
                try {
                    System.out.print("Ingrese opción: ");
                    miEntrada = miScanner.nextInt();
                    miScanner.nextLine();
                    switch (miEntrada) {
                        case 1:
                            try {
                                System.out.print("Nombre: ");
                                nombre = miScanner.nextLine();
                                System.out.print("Stock: ");
                                stock = miScanner.nextInt();
                                miScanner.nextLine();
                                if (stockServicio.actualizarStock(stock, nombre)) {
                                    System.out.println("Producto encontrado, stock actualizado.");
                                } else {
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
                            System.out.println(miInventario.listarProductos());
                            break;

                        case 3:
                            System.out.print("Nombre a buscar: ");
                            nombre = miScanner.nextLine();
                            System.out.println(
                                    miInventario.busquedaLineal(nombre)
                            );
                            break;

                        case 4:

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

                        case 5:
                            System.out.println(ordenarServicio.ordenarPorNombre());
                            break;

                        case 6:
                            System.out.println(ordenarServicio.ordenarPorPrecio());
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
