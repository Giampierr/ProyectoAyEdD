package tienda;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import tienda.base.Venta;
import tienda.modelo.Admin;
import tienda.modelo.VentaDirecta;
import tienda.modelo.VentaPedido;
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

public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/inicio.fxml"));

        Scene scene = new Scene(loader.load());

        primaryStage.setTitle("Sistema de Ventas");
        primaryStage.setScene(scene);
        primaryStage.show();

        Admin admin = new Admin("admin123","angelo");
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

        //Asi no inicia
        //menuPrincipal.iniciar();


    }

    public static void main(String[] args) {
        launch(args);
    }
}