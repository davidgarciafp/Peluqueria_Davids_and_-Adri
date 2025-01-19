package org.example.perruqueria.perruqueriadreams;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.example.perruqueria.perruqueriadreams.Controllers.ControladorAgenda;
import org.example.perruqueria.perruqueriadreams.Controllers.ControladorTrabajadores;
import org.example.perruqueria.perruqueriadreams.Controllers.DatosGlobales;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML/Clientes.fxml"));
        DatosGlobales.setMystage(stage);
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        /*ControladorTrabajadores trabajadores = fxmlLoader.getController();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case ENTER -> trabajadores.identificarTrabajador();
                }
            }
        });*/
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}