package tienda;

import tienda.modelo.Admin;
import tienda.modelo.Cliente;
import tienda.repositorio.*;
import tienda.servicios.*;
import tienda.vista.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin();
        ProductosRepositorio miRepoProductos = new ProductosRepositorio();
        ClientesRepositorio miRepoClientes = new ClientesRepositorio(new ArrayList<Cliente>());
        RepositorioPedidos miRepoPedidos = new RepositorioPedidos();
        RepositorioVentas miRepoVentas = new RepositorioVentas();
        OrdenadorServicio ordenadorServicio = new OrdenadorServicio(miRepoProductos);
        StockServicio stockServicio = new StockServicio(miRepoProductos);
        VentaServicio ventaServicio = new VentaServicio(miRepoProductos, miRepoPedidos, miRepoVentas);
        MenuAdministrador menuAdministrador = new MenuAdministrador(admin, miRepoProductos, ordenadorServicio, stockServicio);
        MenuVenta menuVenta = new MenuVenta(miRepoClientes, miRepoProductos, ventaServicio);
        MenuPrincipal menuPrincipal = new MenuPrincipal(menuAdministrador, menuVenta);
        menuPrincipal.iniciar();
    }
}