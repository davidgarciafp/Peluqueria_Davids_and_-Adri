package org.example.perruqueria.perruqueriadreams.Controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Global {
    private static Stage mystage;
    private static String nombreUsuario;

    public static Stage getMystage() {
        return mystage;
    }

    public static void setMystage(Stage mystage) {
        Global.mystage = mystage;
    }

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static void setNombreUsuario(String nombreUsuario) {
        Global.nombreUsuario = nombreUsuario;
    }

    public static boolean mostrarAlertaExitosa(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(mensaje);
        alerta.showAndWait();
        ButtonType botonSeleccionado = alerta.getResult();
        boolean confirmado = botonSeleccionado.getText().equals("Aceptar");
        return  confirmado;
    }
}
