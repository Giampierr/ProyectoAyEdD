package tienda;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tienda.base.Venta;
import tienda.controladores.AdministradorController;
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
        AppContext.admin = new Admin("admin123","angelo");
        AppContext.repoVentas = new RepositorioVentas();
        AppContext.repoPedidos = new RepositorioPedidos();
        AppContext.repoClientes = new ClientesRepositorio();
        AppContext.repoProductos = new ProductosRepositorio();
        AppContext.ordenadorServicio = new OrdenadorServicio(AppContext.repoProductos);
        AppContext.stockServicio = new StockServicio(AppContext.repoProductos);
        AppContext.ventaServicio = new VentaServicio(AppContext.repoProductos,AppContext.repoPedidos,AppContext.repoVentas);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/inicio.fxml"));

        Scene scene = new Scene(loader.load());

        primaryStage.setTitle("Sistema de Ventas");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    public static void main(String[] args) {
        launch(args);
    }
}