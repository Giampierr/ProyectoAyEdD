package tienda.vista;

import tienda.modelo.Producto;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {
    MenuAdministrador menuAdministrador;
    MenuVenta menuVenta;

    public MenuPrincipal(MenuAdministrador menuAdministrador, MenuVenta menuVenta) {
        this.menuAdministrador = menuAdministrador;
        this.menuVenta = menuVenta;
    }

    public void iniciar(){
        Scanner miScanner = new Scanner(System.in);
        int miEntrada = -1;
        int miVista = -1;

        do {
            System.out.println("Ingrese a la vista ");
            System.out.println("0)  Salir");
            System.out.println("1)  Inventariado");
            System.out.println("2)  Venta");
            try{

                miVista = miScanner.nextInt();
                miScanner.nextLine();
                switch (miVista){
                    case 1:
                        menuAdministrador.inicar();
                        break;
                    case 2:
                        menuVenta.iniciar();
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("Ingrese un valor valido");
            }
        }while(miVista !=0);
        miScanner.close();
        System.out.println("Saliendo.");
    }
}
