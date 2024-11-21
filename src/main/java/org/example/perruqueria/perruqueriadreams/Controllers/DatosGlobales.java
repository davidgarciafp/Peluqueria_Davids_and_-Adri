package org.example.perruqueria.perruqueriadreams.Controllers;

import javafx.stage.Stage;

public class DatosGlobales {
    private static Stage mystage;
    private static String nombreUsuario;

    public static Stage getMystage() {
        return mystage;
    }

    public static void setMystage(Stage mystage) {
        DatosGlobales.mystage = mystage;
    }

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static void setNombreUsuario(String nombreUsuario) {
        DatosGlobales.nombreUsuario = nombreUsuario;
    }
}
