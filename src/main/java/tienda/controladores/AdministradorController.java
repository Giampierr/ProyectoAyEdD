package tienda.controladores;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.awt.*;
import java.io.IOException;

public class AdministradorController {


    @FXML
    private Button btnSalir;
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
    @FXML
    private void mostrarDashboard() {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/dashboard.fxml")
        );

        try {
            Parent vista = loader.load();

            DashboardController controller = loader.getController();


            contenedorPrincipal.getChildren().setAll(vista);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
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
