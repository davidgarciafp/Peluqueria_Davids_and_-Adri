package org.example.perruqueria.perruqueriadreams;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.perruqueria.perruqueriadreams.Controllers.ControladorTrabajadores;
import org.example.perruqueria.perruqueriadreams.Controllers.DatosGlobales;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FXML/Login.fxml"));
        DatosGlobales.setMystage(stage);
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        ControladorTrabajadores trabajadores = fxmlLoader.getController();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}