package tienda.controladores;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import static tienda.AppContext.admin;


public class MenuController {
    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField psfPassword;

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnVenta;

    @FXML
    public void initialize() {
        btnAdmin.setOnAction(e -> {

            String correo = txtCorreo.getText().toLowerCase().trim();
            String password =psfPassword.getText();

            if (correo.isEmpty() || password.isEmpty()){
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error de acceso");
                alerta.setContentText("Rellene ambos campos");
                alerta.showAndWait();
            }
            else if( admin.validarPassword(password,correo)){

                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/administrador.fxml"));

                    Parent root = loader.load();

                    Stage stage = (Stage) btnAdmin.getScene().getWindow();

                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de acceso");
                alerta.setContentText("El usuario o contraseña son inválidas");
                alerta.showAndWait();
                txtCorreo.clear();
                psfPassword.clear();
            }
        });

        btnVenta.setOnAction(event -> {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/venta.fxml"));

                Parent root = loader.load();

                Stage stage = (Stage) btnVenta.getScene().getWindow();

                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
