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

                        System.out.println("\nPRODUCTOS DISPONIBLES");
                        System.out.println(miInventario.listarProductos());

                        boolean comprando = true;
                        while (comprando) {
                            if (venta.estaVacio()) {
                                System.out.print("Ingrese ID del producto (P para pagar): ");
                            } else {
                                System.out.print("Ingrese ID del producto (P para pagar o D para devolver producto): ");
                            }
                            try {
                                String entrada = miScanner.nextLine().trim();

                                if (entrada.equalsIgnoreCase("P")) {
                                    if (venta.estaVacio()) {
                                        System.out.println("No seleccionaste ningún producto.");
                                    } else {
                                        actualizarCarrito.procesarVenta(venta);
                                        System.out.println(venta.mostrarResumen());
                                        System.out.println("¡Venta realizada con éxito!");
                                        comprando = false;
                                    }

                                } else if (entrada.equalsIgnoreCase("D") && !venta.estaVacio()) {
                                    System.out.println("\n--- Carrito actual ---");
                                    for (int i = 0; i < venta.getProductos().size(); i++) {
                                        Producto p = venta.getProductos().get(i);
                                        int cant = venta.getCantidades().get(i);
                                        System.out.printf("  ID: %d | %s x%d | Subtotal: S/%.2f%n",
                                                p.getId(), p.getNombre(), cant, p.getPrecio() * cant);
                                    }
                                    System.out.printf("Total acumulado: S/%.2f%n", venta.calcularTotal());
                                    System.out.println("----------------------");

                                    System.out.print("Ingrese ID del producto a devolver: ");
                                    try {
                                        int idDevolver = Integer.parseInt(miScanner.nextLine().trim());
                                        System.out.print("Ingrese cantidad a devolver: ");
                                        try {
                                            int cantDevolver = Integer.parseInt(miScanner.nextLine().trim());
                                            if (cantDevolver <= 0) {
                                                System.out.println("Cantidad inválida.");
                                            } else {
                                                System.out.println(actualizarCarrito.devolverProducto(venta, idDevolver, cantDevolver));
                                                System.out.println(venta.estaVacio() ? "Carrito vacío." : venta.mostrarCarritoActual());
                                                System.out.println("\nPRODUCTOS DISPONIBLES");
                                                System.out.println(miInventario.listarProductos());
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("Cantidad inválida.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("ID inválido.");
                                    }

                                } else {
                                    try {
                                        idSeleccionado = Integer.parseInt(entrada);
                                        Producto encontrado = miInventario.buscarPorId(idSeleccionado);
                                        if (encontrado == null) {
                                            System.out.println("Producto no encontrado.");
                                        } else if (encontrado.getStock() == 0) {
                                            System.out.println("Sin stock disponible para: " + encontrado.getNombre());
                                        } else {
                                            System.out.print("Cantidad: ");
                                            try {
                                                cantidad = Integer.parseInt(miScanner.nextLine().trim());
                                                if (cantidad <= 0) {
                                                    System.out.println("Cantidad inválida.");
                                                } else if (cantidad > encontrado.getStock()) {
                                                    System.out.println("Stock insuficiente. Disponible: " + encontrado.getStock());
                                                } else {
                                                    boolean agregado = actualizarCarrito.agregarAlCarrito(venta, encontrado, cantidad);
                                                    if (agregado) {
                                                        System.out.println("Agregado: " + encontrado.getNombre() + " x" + cantidad);
                                                        System.out.println(venta.mostrarCarritoActual());
                                                        System.out.println("\nPRODUCTOS DISPONIBLES");
                                                        System.out.println(miInventario.listarProductos());
                                                    } else {
                                                        System.out.println("Stock insuficiente.");
                                                    }
                                                }
                                            } catch (NumberFormatException e) {
                                                System.out.println("Cantidad inválida.");
                                            }
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Ingrese un ID válido, P o D.");
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Entrada inválida.");
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