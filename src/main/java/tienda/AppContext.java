package tienda;

import tienda.modelo.Admin;
import tienda.repositorio.ClientesRepositorio;
import tienda.repositorio.ProductosRepositorio;
import tienda.repositorio.RepositorioPedidos;
import tienda.repositorio.RepositorioVentas;
import tienda.servicios.OrdenadorServicio;
import tienda.servicios.StockServicio;
import tienda.servicios.VentaServicio;

public class AppContext {
    public static Admin admin;
    public static RepositorioVentas repoVentas;
    public static RepositorioPedidos repoPedidos;
    public static ClientesRepositorio repoClientes;
    public static ProductosRepositorio repoProductos;
    public static OrdenadorServicio ordenadorServicio;
    public static StockServicio stockServicio;
    public static VentaServicio ventaServicio;

}