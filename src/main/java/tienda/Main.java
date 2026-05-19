package tienda;

import tienda.modelo.Admin;
import tienda.modelo.Producto;
import tienda.repositorio.ProductosRepositorio;
import tienda.servicios.OrdenadorServicio;
import tienda.servicios.StockServicio;

import tienda.modelo.Venta;
import tienda.servicios.ActualizarCarrito;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner miScanner = new Scanner(System.in);
        String nombre;
        double precio;
        int stock;
        int id;
        Admin admin= new Admin();
        List<Producto> misProductos = new ArrayList<>();
        ProductosRepositorio miInventario =
                new ProductosRepositorio(misProductos);
        StockServicio stockServicio  = new StockServicio(miInventario);
        OrdenadorServicio ordenarServicio = new OrdenadorServicio(miInventario);
        int miEntrada = -1;
        int miVista = -1;

        do {
            System.out.println("Ingrese  a la vista ");
            System.out.println("0)  Salir");
            System.out.println("1)  Inventariado");
            System.out.println("2)  Venta");
            try{

                miVista = miScanner.nextInt();
                miScanner.nextLine();
                switch (miVista){
                    case 1:
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

                            break;
                        }else{
                            System.out.println("Contraseña Incorrecta");
                            break;
                        }
                    case 2:
                        Venta venta = new Venta();
                        ActualizarCarrito actualizarCarrito = new ActualizarCarrito(miInventario);
                        int idSeleccionado;
                        int cantidad;

                        System.out.println("\n===== PRODUCTOS DISPONIBLES =====");
                        System.out.println(miInventario.listarProductos());

                        boolean comprando = true;
                        while (comprando) {
                            System.out.print("Ingrese ID del producto (0 para pagar): ");
                            try {
                                idSeleccionado = miScanner.nextInt();
                                miScanner.nextLine();

                                if (idSeleccionado == 0) {
                                    if (venta.estaVacio()) {
                                        System.out.println("No seleccionaste ningún producto.");
                                    } else {
                                        actualizarCarrito.procesarVenta(venta);
                                        System.out.println(venta.mostrarResumen());
                                        System.out.println("¡Venta realizada con éxito!");
                                    }
                                    comprando = false;

                                } else {
                                    Producto encontrado = miInventario.buscarPorId(idSeleccionado);
                                    if (encontrado == null) {
                                        System.out.println("Producto no encontrado.");
                                    } else if (encontrado.getStock() == 0) {
                                        System.out.println("Sin stock disponible para: " + encontrado.getNombre());
                                    } else {
                                        System.out.print("Cantidad: ");
                                        try {
                                            cantidad = miScanner.nextInt();
                                            miScanner.nextLine();
                                            if (cantidad <= 0) {
                                                System.out.println("Cantidad inválida.");
                                            } else if (cantidad > encontrado.getStock()) {
                                                System.out.println("Stock insuficiente. Disponible: " + encontrado.getStock());
                                            } else {
                                                boolean agregado = actualizarCarrito.agregarAlCarrito(venta, encontrado, cantidad);
                                                if (agregado) {
                                                    System.out.println("Agregado: " + encontrado.getNombre() + " x" + cantidad);
                                                    System.out.println(venta.mostrarCarritoActual());
                                                    System.out.println("\n===== PRODUCTOS DISPONIBLES =====");
                                                    System.out.println(miInventario.listarProductos());
                                                } else {
                                                    System.out.println("Stock insuficiente.");
                                                }
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Cantidad inválida.");
                                            miScanner.nextLine();
                                        }
                                    }
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ingrese un número válido.");
                                miScanner.nextLine();
                            }
                        }
                        break;
                }
            }catch (InputMismatchException e){
                System.out.print("Ingrese un valor valido");
            }
        }while(miVista !=0);
        miScanner.close();
        System.out.println("Saliendo.");
    }
}