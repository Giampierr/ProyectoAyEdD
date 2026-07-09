package tienda.controladores;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tienda.base.Venta;
import tienda.modelo.Cliente;
import tienda.modelo.Producto;
import tienda.repositorio.ClientesRepositorio;
import tienda.repositorio.ProductosRepositorio;
import tienda.repositorio.RepositorioPedidos;
import tienda.repositorio.RepositorioVentas;
import tienda.AppContext;
import tienda.servicios.StockServicio;
import javafx.beans.property.SimpleStringProperty;

import java.time.format.DateTimeFormatter;

public class DashboardController {
    private RepositorioVentas miRepoVentas;
    private RepositorioPedidos miRepoPedidos;
    private ClientesRepositorio miRepoClientes;
    private ProductosRepositorio miRepoProductos;
    private StockServicio stockServicio;

    @FXML
    private Label lblVentas;
    @FXML
    private Label lblPedidos;
    @FXML
    private Label lblClientes;
    @FXML
    private Label lblProductos;


    @FXML
    private TableView tblProductos;
    @FXML
    private TableColumn<Producto,Integer> colIdProducto;
    @FXML
    private TableColumn<Producto,String> colNombre;
    @FXML
    private TableColumn<Producto,Double> colPrecio;
    @FXML
    private TableColumn<Producto,Integer> colStock;

    @FXML
    private TableView tblVentas;
    @FXML
    private TableColumn<Venta,Integer> colId;
    @FXML
    private TableColumn<Venta, String> colCliente;
    @FXML
    private TableColumn<Venta,String> colFecha;
    @FXML
    private TableColumn<Venta,Double> colTotal;

    @FXML
    public void initialize() {

        double totalVenta = 0;

        for(Venta venta : AppContext.repoVentas.devolverVentas()){
            totalVenta += venta.obtenerValorTotal();
        }

        lblVentas.setText(String.valueOf(totalVenta));
        lblPedidos.setText(
                String.valueOf(AppContext.repoPedidos.getColaPedidos().size())
        );

        lblClientes.setText(
                String.valueOf(AppContext.repoClientes.devolverClientes().size())
        );

        lblProductos.setText(
                String.valueOf(AppContext.repoProductos.listar().size())
        );

        colIdProducto.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tblProductos.setItems(FXCollections.observableArrayList(AppContext.stockServicio.devolverAlerta()));


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getCliente().getNombre())
                );
        colFecha.setCellValueFactory(cellDatas ->
                new SimpleStringProperty(
                        cellDatas.getValue().getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                ));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        tblVentas.setItems(FXCollections
                .observableArrayList(AppContext.repoVentas.devolverVentas()));
    }
}
