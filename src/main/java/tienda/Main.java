package tienda;
import tienda.modelo.Producto;
import tienda.repositorio.ProductosRepositorio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //    Test
        Scanner miScanner = new Scanner(System.in);

//    Datos de entrada
        String nombre;double precio;int stock;
        List<Producto> misProductos = new ArrayList<>();
        ProductosRepositorio miInventario = new ProductosRepositorio(misProductos);
        int miEntrada = -1;
//    Inicio del bucle
        do {
            System.out.println("0)Salir");
            System.out.println("1)Añadir Producto");
            System.out.println("2)Listar Inventario");
            System.out.println("3)Buscar Producto por nombre");
            System.out.println("4)Buscar por id");
            System.out.println("5)Ordenar por nombre");
            System.out.println("6)Ordernar por precio");
            try{
                System.out.print("Ingrese su opcion :");
                miEntrada = miScanner.nextInt();
                switch (miEntrada){
                    case 1 :
                        System.out.print("Ingrese el nombre del producto :");
                        nombre = miScanner.next();
                        miScanner.nextLine();
                        System.out.print("\nIngrese el precio del producto :");
                        precio = miScanner.nextDouble();
                        miScanner.nextLine();
                        System.out.print("\nIngrese el stock del producto :");
                        stock = miScanner.nextInt();
                        miScanner.nextLine();
                        Producto miProductos= new Producto(nombre,precio,stock);
                        miInventario.guardar(miProductos);
                        System.out.println("Producto guardado exitosamente");
                        break;
                    case 2 :
                        System.out.println(miInventario.listarProductos());
                        break;
                    case 3 :
                        break;
                    case 0 :
                        System.out.println("Saliendo");
                        break;
                    default :
                        System.out.println("Valor Invalido");
                }
                //Captura el error de miEntrada y asigna -1 para seguir en el bucle
            } catch (InputMismatchException e) {
                System.out.println("Error Debes ingreser un numero Error :"+e.toString());
                miScanner.nextLine();
            }
        }while (miEntrada != 0);
        miScanner.close();
        System.out.println("Gracias");
    }
}
