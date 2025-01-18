package org.example.perruqueria.perruqueriadreams.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.example.perruqueria.perruqueriadreams.Models.Servicios;
import org.example.perruqueria.perruqueriadreams.Models.ServiciosDAO;
import org.example.perruqueria.perruqueriadreams.Models.Trabajadores;
import org.example.perruqueria.perruqueriadreams.Models.TrabajadoresDAO;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorCobros implements Initializable {
    @FXML private Text nombreCliente;
    @FXML private VBox camposCobros;
    @FXML private VBox camposServicios;
    @FXML private ChoiceBox<Servicios> selectServicio;
    @FXML private ChoiceBox<Trabajadores> trabajadorServicio;
    @FXML private TextField precioServicio;
    @FXML private TextArea comentarioServicio;
    @FXML private CheckBox servicioPagado;
    @FXML private ImageView anadirServicio;
    @FXML private VBox serviciosAnadidos;
    @FXML private VBox camposProductos;
    @FXML private ChoiceBox<Trabajadores> selectTrabajadores;
    private TrabajadoresDAO modeloTrabajadores = new TrabajadoresDAO();
    private ServiciosDAO modeloServicios = new ServiciosDAO();

    public void cargarSelectTrabajador() {
        List<Trabajadores> trabajadores = modeloTrabajadores.mostrarTrabajadores();
        ObservableList<Trabajadores> listaTrabajadores = FXCollections.observableList(trabajadores);
        selectTrabajadores.setItems(listaTrabajadores);
        selectTrabajadores.setConverter(new StringConverter<>() {
            @Override
            public String toString(Trabajadores trabajador) {
                return trabajador != null ? trabajador.getNombreTrabajador() : "";
            }

            @Override
            public Trabajadores fromString(String string) {
                return null;
            }
        });
    }

    public void anadirServicios(Servicios servicio, Trabajadores trabajador, BigDecimal precio, String comentario, boolean pagado) {
        VBox camposServicioAnadido = new VBox();
        ChoiceBox<Servicios> selectServicio = new ChoiceBox<>();
        selectServicio.setValue(servicio);
        ChoiceBox<Trabajadores> selectTrabajador = new ChoiceBox<>();
        selectTrabajador.setValue(trabajador);
        TextField campoPrecio = new TextField(String.valueOf(precio));
        TextArea campoComentario = new TextArea(comentario);
        CheckBox campoPagado = new CheckBox("Pagado: ");
        campoPagado.setSelected(pagado);
        camposServicioAnadido.getChildren().addAll(selectServicio, selectTrabajador, campoPrecio, campoComentario, campoPagado);
        serviciosAnadidos.getChildren().add(camposServicioAnadido);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (camposCobros != null) {
            String cliente = ControladorClientes.getClienteSeleccionado().getNombreCliente();
            nombreCliente.setText(cliente);
            System.out.println(cliente);
            cargarSelectTrabajador();
            anadirServicio.setOnMouseClicked((MouseEvent event) -> {
                Servicios servicio = selectServicio.getValue();
                Trabajadores trabajador = trabajadorServicio.getValue();
                BigDecimal precio = (!precioServicio.getText().isEmpty()) ? new BigDecimal(precioServicio.getText()) : servicio.getPrecioBase();
                String comentario = comentarioServicio.getText();
                Boolean pagado = servicioPagado.isSelected();
                anadirServicios(servicio, trabajador, precio, comentario, pagado);
            });

        }
    }
}
