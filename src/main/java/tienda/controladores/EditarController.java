package tienda.controladores;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tienda.AppContext;
import tienda.modelo.Producto;
import tienda.modelo.TipoCategoria;


public class EditarController {
    private Producto productoEditar;
    private boolean modoEdicion = false;

    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtPrecio;
    @FXML
    private ChoiceBox<TipoCategoria> chbCategoria;
    @FXML
    private Spinner<Integer> spnStock;

    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;

    public void cargarProducto(Producto producto){

        this.productoEditar = producto;
        this.modoEdicion = true;

        txtNombres.setText(producto.getNombre());
        txtPrecio.setText(String.valueOf(producto.getPrecio()));
        chbCategoria.setValue(producto.getTipo());

        spnStock.getValueFactory().setValue(
                producto.getStock()
        );

        btnConfirmar.setText("Actualizar");
    }

    public boolean campoVacios(){
        if (txtNombres.getText().isEmpty() || txtPrecio.getText().isEmpty()){
            return true;
        }else {
            return false;
        }
    }



    public void rellenarCategorias(){
        chbCategoria.setItems(FXCollections.observableArrayList(
                TipoCategoria.values()
        ));
    }
    private void cerrarVentana(){

        Stage stage =
                (Stage) btnCancelar.getScene().getWindow();

        stage.close();
    }

    public void initialize(){

        rellenarCategorias();

        spnStock.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        0,
                        100,1
                ) {
                }
        );



        btnConfirmar.setOnAction(event -> {

            if (campoVacios()) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Rellene todos los campos");
                alert.showAndWait();
                return;
            }

            String nombre = txtNombres.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            TipoCategoria tipo = chbCategoria.getValue();
            int stock = spnStock.getValue();

            if (modoEdicion) {

                productoEditar.setNombre(nombre);
                productoEditar.setPrecio(precio);
                productoEditar.setTipo(tipo);
                productoEditar.setStock(stock);

            } else {

                Producto producto =
                        new Producto(nombre,tipo,precio,stock);

                AppContext.repoProductos.guardar(producto);
            }

            cerrarVentana();
        });

        btnCancelar.setOnAction(event -> {
            cerrarVentana();
        });
    }



}
