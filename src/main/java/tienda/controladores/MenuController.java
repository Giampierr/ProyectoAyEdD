package tienda.controladores;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.*;
import tienda.modelo.Admin;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.util.Locale;

public class MenuController {
    Admin admin = new Admin("admin123","angelo");
    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField psfPassword;

    @FXML
    private Button btnAdmin;


    @FXML
    public void initialize() {
        btnAdmin.setOnAction(e -> {

            String correo = txtCorreo.getText().toLowerCase().trim();
            String password =psfPassword.getText();



            if( admin.validarPassword("admin123","angelo")){

            }else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de acceso");
                alerta.setHeaderText("Credenciales incorrectas");
                alerta.setContentText("El usuario o contraseña son inválidas");
                alerta.showAndWait();
            }
        });
    }
}
