package tienda.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tienda.base.Venta;
import tienda.estructuras.ListaDobleVentas;
import tienda.estructuras.NodoVenta;
import java.time.LocalDateTime;

public class ReportesController {

    @FXML private Label lblTotalRecaudado;
    @FXML private Label lblTotalTransacciones;
    @FXML private Label lblTicketPromedio;

    @FXML private TableView<Venta> tblVentas;
    @FXML private TableColumn<Venta, String> colId;
    @FXML private TableColumn<Venta, String> colCliente;
    @FXML private TableColumn<Venta, LocalDateTime> colFecha;
    @FXML private TableColumn<Venta, Double> colMonto;

    private ListaDobleVentas historialDeVentas;

    @FXML
    public void initialize() {
        // Enlazar columnas con los métodos de la clase Venta
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente")); // Asegúrate que Venta tenga getCliente()
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("valorTotal")); // Usamos valorTotal

        historialDeVentas = new ListaDobleVentas();
        
        // Aquí podrías cargar tus datos reales. 
        // Si tienes una base de datos o lista global, cárgala aquí.
        
        actualizarInterfaz();
    }

    private void actualizarInterfaz() {
        double recaudado = historialDeVentas.calcularSumaTotal();
        int transacciones = historialDeVentas.getTamaño();
        double promedio = (transacciones > 0) ? (recaudado / transacciones) : 0.0;

        lblTotalRecaudado.setText(String.format("$%.2f", recaudado));
        lblTotalTransacciones.setText(String.valueOf(transacciones));
        lblTicketPromedio.setText(String.format("$%.2f", promedio));

        ObservableList<Venta> datosTabla = FXCollections.observableArrayList();
        NodoVenta actual = historialDeVentas.getCabeza();
        
        while (actual != null) {
            datosTabla.add(actual.dato);
            actual = actual.siguiente;
        }

        tblVentas.setItems(datosTabla);
    }
}
