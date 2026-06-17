package tienda;

import tienda.modelo.Admin;
import tienda.modelo.Producto;
import tienda.repositorio.ProductosRepositorio;
import tienda.servicios.OrdenadorServicio;
import tienda.servicios.StockServicio;

import tienda.modelo.Venta;
import tienda.servicios.ActualizarCarrito;
import tienda.vista.MenuAdministrador;
import tienda.vista.MenuPrincipal;
import tienda.vista.MenuVenta;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Admin admin = new Admin();
        ProductosRepositorio miRepoProductos = new ProductosRepositorio();
        OrdenadorServicio ordenadorServicio = new OrdenadorServicio(miRepoProductos);
        StockServicio stockServicio = new StockServicio(miRepoProductos);
        MenuAdministrador menuAdministrador = new MenuAdministrador(admin,miRepoProductos,ordenadorServicio,stockServicio);
        MenuVenta menuVenta = new MenuVenta();
        MenuPrincipal menuPrincipal = new MenuPrincipal(menuAdministrador,menuVenta);
        menuPrincipal.iniciar();

    }
}