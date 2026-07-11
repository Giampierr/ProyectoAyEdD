package tienda.controladores;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import tienda.AppContext;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import tienda.modelo.Producto;
import tienda.modelo.TipoCategoria;

import java.util.Map;
import java.util.Optional;

public class ProductosController {
    @FXML
    private TextField txtBuscar;
    @FXML
    private Label lblStock;
    @FXML
    private Label lblTotalCategorias;
    @FXML
    private Label lblTotalProductos;

    @FXML
    private LineChart<String,Number> lineProductos;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @FXML
    private TableView<Producto> tblProductosBajoStock;
    @FXML
    private TableColumn<Producto,Integer> colIdBajo;
    @FXML
    private TableColumn<Producto,String> colNombreBajo;
    @FXML
    private TableColumn<Producto,Integer> colStockBajo;

    @FXML
    private TableView<Producto> tblProductos;
    @FXML
    private TableColumn<Producto,Integer> colId;
    @FXML
    private TableColumn<Producto,String> colNombre;
    @FXML
    private TableColumn<Producto,Integer> colStock;
    @FXML
    private TableColumn<Producto,Double> colPrecio;
    @FXML
    private TableColumn<Producto,String> colCategoria;

    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEditar;

    public String calcularStockTotal(){
        int totalProductos = 0;


        for (Producto producto : AppContext.repoProductos.listar()) {
            totalProductos += producto.getStock();
        }

        return Integer.toString(totalProductos);
    }

    public String calcularProductosTotal(){
        return Integer.toString(AppContext.repoProductos.listar().size());
    }

    public String calcularCategoriasTotal(){
        return Integer.toString(TipoCategoria.values().length);
    }

    public void cargarGrafico(){
        lineProductos.getData().clear();

        XYChart.Series<String, Number> serie =
                new XYChart.Series<>();

        serie.setName("Productos Por Categoria");

        Map<TipoCategoria, Integer> productos =
                AppContext.repoProductos.productosPorCategorias();

        productos.forEach((categorias, total) -> {
            serie.getData().add(
                    new XYChart.Data<>(categorias.name(), total)
            );
        });

        lineProductos.getData().add(serie);

        xAxis.setTickLabelRotation(0);
    }
    private void cargarTablaProductos() {
        tblProductos.setItems(
                FXCollections.observableArrayList(
                        AppContext.repoProductos.listar()
                )
        );
    }

    public void initialize(){

        txtBuscar.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    if (newValue == null || newValue.trim().isEmpty()) {

                        tblProductos.setItems(
                                FXCollections.observableArrayList(
                                        AppContext.repoProductos.listar()
                                )
                        );

                    } else {

                        tblProductos.setItems(
                                FXCollections.observableArrayList(
                                        AppContext.repoProductos.buscarPorNombre(newValue)
                                )
                        );}
                        tblProductos.refresh();
                    });

        //Labels
        lblStock.setText(calcularStockTotal());
        lblTotalProductos.setText(calcularProductosTotal());
        lblTotalCategorias.setText(calcularCategoriasTotal());

        //Grafico

        cargarGrafico();

        //Tablas

        colIdBajo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreBajo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colStockBajo.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblProductosBajoStock.setItems(FXCollections.observableArrayList(AppContext.stockServicio.devolverAlerta()));

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCategoria.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getTipo().toString()
                ));
        tblProductos.setItems(FXCollections.observableArrayList(AppContext.repoProductos.listar()));

        btnAgregar.setOnAction(event -> {

            try{

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                                "/fxml/editarProducto.fxml"
                        )
                );

                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();

                cargarTablaProductos();

            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        btnEditar.setOnAction(event -> {

            Producto producto =
                    tblProductos.getSelectionModel()
                            .getSelectedItem();

            if(producto == null){

                Alert alert = new Alert(
                        Alert.AlertType.WARNING
                );

                alert.setContentText(
                        "Seleccione un producto"
                );

                alert.showAndWait();
                return;
            }

            try{

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                                "/fxml/editarProducto.fxml"
                        )
                );

                Parent root = loader.load();

                EditarController controller =
                        loader.getController();

                controller.cargarProducto(producto);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();

                cargarTablaProductos();
                tblProductos.refresh();

            } catch (Exception ex){
                ex.printStackTrace();
            }
        });


        //Para arreglar
        btnEliminar.setOnAction(event -> {

            Producto productoSeleccionado =
                    tblProductos.getSelectionModel().getSelectedItem();

            if (productoSeleccionado == null) {

                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Eliminar Producto");
                alerta.setHeaderText(null);
                alerta.setContentText("Seleccione un producto primero.");
                alerta.showAndWait();

                return;
            }

            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);

            confirmacion.setTitle("Confirmar eliminación");
            confirmacion.setHeaderText("Eliminar producto");

            confirmacion.setContentText(
                    "¿Desea eliminar el producto '" +
                            productoSeleccionado.getNombre() +
                            "'?"
            );

            Optional<ButtonType> resultado =
                    confirmacion.showAndWait();

            if (resultado.isPresent() &&
                    resultado.get() == ButtonType.OK) {

                AppContext.repoProductos.eliminar(
                        productoSeleccionado.getId()
                );

                cargarTablaProductos();

                Alert exito = new Alert(Alert.AlertType.INFORMATION);
                exito.setTitle("Producto eliminado");
                exito.setHeaderText(null);
                exito.setContentText("Producto eliminado correctamente.");
                exito.showAndWait();
            }
        });

    }

}
