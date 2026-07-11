package tienda.controladores;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import javax.imageio.IIOException;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class AdministradorController {


    @FXML
    private Button btnSalir;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnVentas;
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnPedidos;
    @FXML
    private Button btnProductos;

    @FXML
    private StackPane contenedorPrincipal;
    //Falta agregar los demas botones
    private void cargarVista(String fxml){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));

            Parent vista = loader.load();

            contenedorPrincipal.getChildren().clear();
            contenedorPrincipal.getChildren().add(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        //Vista Inicial

        btnDashboard.setOnAction(event -> {
            cargarVista("/fxml/dashboard.fxml");
        });
//
        btnClientes.setOnAction(event -> {
            cargarVista("/fxml/clientes.fxml");
        });
//
        btnVentas.setOnAction(event -> {
            cargarVista("/fxml/ventasReporte.fxml");
        });
//
//        btnPedidos.setOnAction(event -> {
//            cargarVista("/fxml/pedidos.fxml");
//        });
//
        btnProductos.setOnAction(event -> {
            cargarVista("/fxml/productos.fxml");
        });

        cargarVista("/fxml/dashboard.fxml");


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
    }


}
