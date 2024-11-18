package org.example.perruqueria.perruqueriadreams.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ControladorVentana {
    private static Stage stage;

    public ControladorVentana() {
        ControladorVentana.stage = DatosGlobales.getMystage();
    }

    public void redirigir(String vista) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/perruqueria/perruqueriadreams/FXML/" + vista + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
            stage.setTitle(vista);
            stage.setScene(scene);
        } catch (IOException error) {
            System.out.println("Error al cargar la vista: " + error.getMessage());
            error.printStackTrace();
        }
    }

}
