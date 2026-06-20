package tienda;

import tienda.modelo.Admin;
import tienda.modelo.VentaDirecta;
import tienda.repositorio.ClientesRepositorio;
import tienda.repositorio.ProductosRepositorio;
import tienda.repositorio.RepositorioPedidos;
import tienda.repositorio.RepositorioVentas;
import tienda.servicios.OrdenadorServicio;
import tienda.servicios.StockServicio;

import tienda.servicios.VentaServicio;
import tienda.vista.MenuAdministrador;
import tienda.vista.MenuPrincipal;
import tienda.vista.MenuVenta;

public class Main {

    public static void main(String[] args) {
        Admin admin = new Admin("admin");

        ProductosRepositorio miRepoProductos = new ProductosRepositorio();
        ClientesRepositorio miRepoClientes = new ClientesRepositorio();
        RepositorioVentas miRepoVentas = new RepositorioVentas();
        RepositorioPedidos miRepoPedidos = new RepositorioPedidos();

        OrdenadorServicio ordenadorServicio = new OrdenadorServicio(miRepoProductos);
        StockServicio stockServicio = new StockServicio(miRepoProductos);
        VentaServicio ventaServicio = new VentaServicio(miRepoProductos,miRepoPedidos,miRepoVentas);


        MenuAdministrador menuAdministrador = new MenuAdministrador(admin,miRepoProductos,ordenadorServicio,stockServicio,miRepoVentas,miRepoPedidos,ventaServicio);
        MenuVenta menuVenta = new MenuVenta(miRepoClientes,miRepoProductos,ventaServicio);
        MenuPrincipal menuPrincipal = new MenuPrincipal(menuAdministrador,menuVenta);


        menuPrincipal.iniciar();

    }
}