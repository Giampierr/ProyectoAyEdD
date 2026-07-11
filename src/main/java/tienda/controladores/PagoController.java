package tienda.controladores;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tienda.modelo.Cliente;
import tienda.modelo.Item;
import tienda.AppContext;
public class PagoController {
    private Cliente clienteSeleccionado;
    @FXML
    private TextField txtCliente;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblNombres;
    @FXML
    private Label lblDni;
    @FXML
    private Label lblTelefono;
    @FXML
    private Label lblCorreo;

    @FXML
    private TableView<Item> tblCarrito;
    @FXML
    private TableColumn<Item,String> colProducto;
    @FXML
    private TableColumn<Item,Integer> colCantidad;
    @FXML
    private TableColumn<Item,Double> colPrecioUni;
    @FXML
    private TableColumn<Item,Double> colSubtotal;

    @FXML
    private Label lblTotal;
    @FXML
    private Label lblTotal2;
    @FXML
    private ComboBox<String> cbxTipoDePago;
    @FXML
    private Button btnComprar;
    @FXML
    private Button btnPedido;

    private void limpiarCampos(){

        lblNombres.setText("");
        lblDni.setText("");
        lblTelefono.setText("");
        lblCorreo.setText("");
    }

    public void initialize(){
        System.out.println(lblNombres);
        System.out.println(lblDni);
        System.out.println(lblTelefono);
        System.out.println(lblCorreo);

        //Buscar cliente
        btnBuscar.setOnAction(e->{
            String dni = txtCliente.getText();
            clienteSeleccionado = AppContext.repoClientes.busquedaDni(dni);

            if(clienteSeleccionado == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Cliente no encontrado");
                alert.showAndWait();
                return;
            }

            limpiarCampos();
            System.out.println("DNI buscado: " + dni);
            System.out.println(clienteSeleccionado.getNombre());
            lblNombres.setText(clienteSeleccionado.getNombre());
            lblDni.setText(clienteSeleccionado.getDni());
            lblTelefono.setText(clienteSeleccionado.getTelefono());
            lblCorreo.setText(clienteSeleccionado.getEmail());

        });
        //Tabla carrito
        colProducto.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getProducto().getNombre()));
        colPrecioUni.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getProducto().getPrecio()).asObject());
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        tblCarrito.setItems(FXCollections.observableArrayList(AppContext.ventaServicio.devolver()));
        //Label totales
        double subtotal = AppContext.ventaServicio.calcularSubtotal();
        lblTotal.setText(String.format("S/. %.2f",subtotal));
        lblTotal2.setText(String.format("S/. %.2f",subtotal));

        //Boton Limpiar
        btnLimpiar.setOnAction(event -> {
            limpiarCampos();
            txtCliente.clear();
            clienteSeleccionado = null;
        });

        //Boton Cancelar
        btnCancelar.setOnAction(event -> {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/venta.fxml"));

                Parent root = loader.load();

                Stage stage = (Stage) btnComprar.getScene().getWindow();

                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        //Comprar
        btnComprar.setOnAction(event -> {
            if (clienteSeleccionado == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cliente inválido");
                alert.setContentText("Debe seleccionar un cliente");
                alert.showAndWait();
                return;
            }

            AppContext.ventaServicio.procesarVenta(clienteSeleccionado);
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/venta.fxml"));

                Parent root = loader.load();

                Stage stage = (Stage) btnComprar.getScene().getWindow();

                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //Pedido
        btnPedido.setOnAction(event -> {
            if (clienteSeleccionado == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cliente inválido");
                alert.setContentText("Debe seleccionar un cliente");
                alert.showAndWait();
                return;
            }
            AppContext.ventaServicio.procesarPedido(clienteSeleccionado);
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/venta.fxml"));

                Parent root = loader.load();

                Stage stage = (Stage) btnPedido.getScene().getWindow();

                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}
