package tienda.controladores;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tienda.modelo.Item;
import tienda.modelo.Producto;
import tienda.AppContext;
import tienda.modelo.TipoCategoria;

public class VentaController {
    @FXML
    private Button btnSalir ;
    @FXML
    private Button btnVaciar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnPagar;
    @FXML
    private Label lblSubtotal;
    @FXML
    private Label lblDescuento;
    @FXML
    private Label lblTotal;
    @FXML
    private TextField txtBusqueda;
    @FXML
    private ChoiceBox<TipoCategoria> chsCategorias;

    @FXML
    private TableView<Producto> tblProductos;
    @FXML
    private TableColumn<Producto,Integer> colId;
    @FXML
    private TableColumn<Producto,String> colProducto;
    @FXML
    private TableColumn<Producto, String> colCategoria;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, Void> colAccion;

    @FXML
    private TableView<Item> tblCarrito;
    @FXML
    private TableColumn<Item,String> colItem;
    @FXML
    private TableColumn<Item,Double> colPrecioUni;
    @FXML
    private TableColumn<Item,Integer> colCantidad;
    @FXML
    private TableColumn<Item,Double> colSubtotal;
    @FXML
    private TableColumn<Item,Void> colEliminar;


    //Metodos
    private void actualizarResumen(){
        double subtotal = AppContext.ventaServicio.calcularSubtotal();

        lblSubtotal.setText(String.format("S/. %.2f",subtotal));

        lblDescuento.setText("S/. 0.00");

        lblTotal.setText(String.format("S/. %.2f",subtotal));
    }

    public void initialize(){
        //Busqueda
        txtBusqueda.textProperty().addListener((obs, oldValue, newValue) -> {

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
                );
                tblProductos.refresh();

            }

        });

        //Selector
        chsCategorias.setItems(
                FXCollections.observableArrayList(
                        TipoCategoria.values()
                )
        );


       //Tabla Productos
       colId.setCellValueFactory(new PropertyValueFactory<>("id"));
       colProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
       colCategoria.setCellValueFactory(cellData ->
           new SimpleStringProperty(
                   cellData.getValue().getTipo().toString().toLowerCase()
           ));
       colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

       colAccion.setCellFactory(param -> new TableCell<>(){
           private final Button btnAgregar= new Button("Agregar");
           {
               btnAgregar.getStyleClass().add("btn-add");
           }

           {
               btnAgregar.setOnAction(e->{

                   Producto producto = getTableView().getItems().get(getIndex());
                   int idProducto = producto.getId();

                   AppContext.ventaServicio.agregar(idProducto);
                   tblCarrito.setItems(FXCollections.observableArrayList(
                           AppContext.ventaServicio.devolver()
                   ));
                   tblCarrito.refresh();
                   actualizarResumen();
               });
           }
           @Override
           protected void updateItem(Void item,boolean empty){
               super.updateItem(item,empty);

               if (empty){
                   setGraphic(null);
               }else {
                   setGraphic(btnAgregar);
               }
           }
       });
        tblProductos.setItems(FXCollections.observableArrayList(AppContext.repoProductos.listar()));

    //Tabla Carrito
        colItem.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getProducto().getNombre()));
        colPrecioUni.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getProducto().getPrecio()).asObject());
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        colEliminar.setCellFactory( param -> new TableCell<>(){
            private final Button btnEliminar = new Button("X");

            {
                btnEliminar.setOnAction(e->{
                    {
                        btnEliminar.getStyleClass().add("btn-remove");
                    }

                    Item item = getTableView().getItems().get(getIndex());
                    int idProducto = item.getProducto().getId();

                    AppContext.ventaServicio.eliminarPorId(idProducto);
                    tblCarrito.setItems(FXCollections.observableArrayList(
                            AppContext.ventaServicio.devolver()
                    ));
                    actualizarResumen();
                });
            }
            @Override
            protected void updateItem(Void item,boolean empty){
                super.updateItem(item,empty);

                if (empty){
                    setGraphic(null);
                }else {
                    setGraphic(btnEliminar);
                }
            }
        });
        tblCarrito.setItems(FXCollections.observableArrayList(AppContext.ventaServicio.devolver()));


        //Boton Salir
       btnSalir.setOnAction(event -> {
           try{
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/inicio.fxml"));

               Parent root = loader.load();

               Stage stage = (Stage) btnSalir.getScene().getWindow();

               stage.setScene(new Scene(root));
               stage.show();
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       });

       //Boton Vaciar
        btnVaciar.setOnAction(event -> {
            AppContext.ventaServicio.vaciarCarrito();
            tblCarrito.setItems(FXCollections.observableArrayList(
                    AppContext.ventaServicio.devolver()
            ));
        });

        //Boton Procesar
        btnPagar.setOnAction(event -> {

            if (AppContext.ventaServicio.devolver().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Carrito Vacío");
                alert.setContentText("El carrito esta vacío");
                alert.showAndWait();
                return;
            }

            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pago.fxml"));

                Parent root = loader.load();

                Stage stage = (Stage) btnPagar.getScene().getWindow();

                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
   }
}
