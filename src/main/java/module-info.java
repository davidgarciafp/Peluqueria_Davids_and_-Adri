module org.example.perruqueria.perruqueriadreams {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens org.example.perruqueria.perruqueriadreams to javafx.fxml;
    opens org.example.perruqueria.perruqueriadreams.Models to javafx.base;
    opens org.example.perruqueria.perruqueriadreams.Controllers to javafx.fxml;
    exports org.example.perruqueria.perruqueriadreams;
}