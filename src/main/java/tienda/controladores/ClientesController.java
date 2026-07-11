package tienda.controladores;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tienda.AppContext;
import tienda.modelo.Cliente;

public class ClientesController {

    @FXML private TableView<Cliente> tblClientes;

    // Solo dejamos las 4 columnas que quedaron en el FXML
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colDni;
    @FXML private TableColumn<Cliente, String> colEmail;
    @FXML private TableColumn<Cliente, String> colTelefono;


    @FXML
    public void initialize() {
        // Enlazamos las columnas con los atributos del modelo Cliente
        // Asegúrate de que los nombres ("nombre", "dni", etc.) coincidan con los getters en Cliente.java
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        cargarDatos();
    }

    private void cargarDatos() {
        tblClientes.setItems(FXCollections.observableArrayList(AppContext.repoClientes.devolverClientes()));
    }
}