package tienda.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tienda.AppContext;
import tienda.base.Venta;

import java.util.List;

public class ReportesController {

    @FXML private Label lblTotalRecaudado;
    @FXML private Label lblTotalTransacciones;
    @FXML private Label lblTicketPromedio;

    @FXML private TableView<Venta> tblVentas;
    @FXML private TableColumn<Venta, String> colId;
    @FXML private TableColumn<Venta, String> colCliente;
    @FXML private TableColumn<Venta, String> colFecha; // Ajusta según el tipo en Venta
    @FXML private TableColumn<Venta, Double> colMonto;



    @FXML
    public void initialize() {
        // Enlazar columnas con los métodos de la clase Venta
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));

        actualizarInterfaz();
    }

    private void actualizarInterfaz() {
        // Obtenemos la lista directamente del repositorio oficial
        List<Venta> listaVentas = AppContext.repoVentas.devolverVentas();

        double recaudado = 0;
        for (Venta v : listaVentas) {
            recaudado += v.getValorTotal(); // Asegúrate de usar el getter correcto de Venta
        }

        int transacciones = listaVentas.size();
        double promedio = (transacciones > 0) ? (recaudado / transacciones) : 0.0;

        // Actualizar UI
        lblTotalRecaudado.setText(String.format("$%.2f", recaudado));
        lblTotalTransacciones.setText(String.valueOf(transacciones));
        lblTicketPromedio.setText(String.format("$%.2f", promedio));

        // Convertimos a ObservableList para la tabla
        tblVentas.setItems(FXCollections.observableArrayList(listaVentas));
    }
}