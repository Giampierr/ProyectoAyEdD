package tienda.controladores;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
public class MenuController {
    @FXML
    private BorderPane rootPane;

    @FXML
    public void initialize() {
        BackgroundImage fondo = new BackgroundImage(
                new javafx.scene.image.Image(getClass().getResource("/img/menu-fondo-png")).toExternalForm()
        ),
                Back
    }
}
