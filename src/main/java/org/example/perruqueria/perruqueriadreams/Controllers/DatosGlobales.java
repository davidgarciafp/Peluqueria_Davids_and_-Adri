package org.example.perruqueria.perruqueriadreams.Controllers;

import javafx.stage.Stage;

public class DatosGlobales {
    private static Stage mystage;

    public static Stage getMystage() {
        return mystage;
    }

    public static void setMystage(Stage mystage) {
        DatosGlobales.mystage = mystage;
    }
}
