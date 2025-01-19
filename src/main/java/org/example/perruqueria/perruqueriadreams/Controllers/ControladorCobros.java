package org.example.perruqueria.perruqueriadreams.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.example.perruqueria.perruqueriadreams.Models.*;

import java.awt.*;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class ControladorCobros implements Initializable {
    @FXML private VBox camposCobros;
    @FXML private VBox camposServicios;
    @FXML private VBox camposProductos;
    @FXML private ChoiceBox<Servicios> selectServicio;
    @FXML private ChoiceBox<Trabajadores> trabajadorServicio;
    @FXML private TextField precioServicio;
    @FXML private TextArea comentarioServicio;
    @FXML private ImageView anadirServicio;
    @FXML private VBox serviciosAnadidos;
    @FXML private Text fechaHoy;
    @FXML private Text nombreCliente;
    @FXML private ChoiceBox<Trabajadores> selectTrabajadores;
    @FXML private TextField campoImporte;
    private TrabajadoresDAO modeloTrabajadores = new TrabajadoresDAO();
    private ServiciosDAO modeloServicios = new ServiciosDAO();
    private ArrayList<ServiciosRealizados> serviciosRealizados = new ArrayList<>();

    public void mostrarValoresTrabajadores(ChoiceBox<Trabajadores> select) {
        select.setConverter(new StringConverter<>() {
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

    public void mostrarValoresServicios(ChoiceBox<Servicios> select) {
        select.setConverter(new StringConverter<>() {
            @Override
            public String toString(Servicios servicio) {
                return servicio != null ? servicio.getDescripcionServicio() : "";
            }

            @Override
            public Servicios fromString(String string) {
                return null;
            }
        });
    }

    public void cargarSelectTrabajador(ChoiceBox<Trabajadores> select) {
        List<Trabajadores> trabajadores = modeloTrabajadores.mostrarTrabajadores();
        ObservableList<Trabajadores> listaTrabajadores = FXCollections.observableList(trabajadores);
        select.setItems(listaTrabajadores);
        mostrarValoresTrabajadores(select);
    }

    public void cargarSelectServicio(ChoiceBox<Servicios> select) {
        List<Servicios> servicios = modeloServicios.mostrarServicios();
        ObservableList<Servicios> listaServicios = FXCollections.observableList(servicios);
        select.setItems(listaServicios);
        mostrarValoresServicios(select);
    }

    public StackPane crearIcono(String ruta, String id) {
        Image imagen = new Image(getClass().getResource(ruta).toExternalForm());
        ImageView icono = new ImageView(imagen);
        icono.setFitWidth(25);
        icono.setFitHeight(25);
        StackPane contenedorImagen = new StackPane();
        contenedorImagen.setId(id);
        contenedorImagen.getChildren().add(icono);
        contenedorImagen.setPrefWidth(icono.getFitWidth());
        contenedorImagen.setPrefHeight(icono.getFitHeight());

        return contenedorImagen;
    }

    public void anadirServicios(Servicios servicio, Trabajadores trabajador, BigDecimal precio, String comentario) {
        HBox servicioAnadido = new HBox();
        servicioAnadido.setAlignment(Pos.TOP_CENTER);
        servicioAnadido.setSpacing(20);

        ChoiceBox<Servicios> selectServicio = new ChoiceBox<>();
        cargarSelectServicio(selectServicio);
        selectServicio.setValue(servicio);
        ChoiceBox<Trabajadores> selectTrabajador = new ChoiceBox<>();
        cargarSelectTrabajador(selectTrabajador);
        selectTrabajador.setValue(trabajador);
        BigDecimal precioImporte = new BigDecimal(campoImporte.getText());
        precioImporte = precioImporte.add(precio);
        campoImporte.setText(String.valueOf(precioImporte));
        ServiciosRealizados servicioRealizado = new ServiciosRealizados(servicio.getIdServicio(), trabajador.getIdTrabajador(), LocalDate.now(), precio, comentario);
        serviciosRealizados.add(servicioRealizado);

        StackPane iconoEditar = crearIcono("/org/example/perruqueria/perruqueriadreams/Images/editar.png", String.valueOf(servicio.getIdServicio()));
        StackPane iconoEliminar = crearIcono("/org/example/perruqueria/perruqueriadreams/Images/eliminar.png", String.valueOf(servicio.getIdServicio()));

        servicioAnadido.getChildren().addAll(selectServicio, selectTrabajador, iconoEditar, iconoEliminar);
        serviciosAnadidos.getChildren().add(servicioAnadido);

        iconoEditar.setOnMouseClicked((MouseEvent event) -> {
            Integer idIcono = Integer.parseInt(iconoEditar.getId());
            for (ServiciosRealizados serviciosReal : serviciosRealizados) {
                if (serviciosReal.getIdServicio().equals(idIcono)) {
                    List<Servicios> servicios = modeloServicios.mostrarServicios();
                    List<Trabajadores> trabajadores = modeloTrabajadores.mostrarTrabajadores();
                    for (Servicios ser : servicios) {
                        if (ser.getIdServicio().equals(idIcono)) {
                            this.selectServicio.setValue(ser);
                        }
                    }
                    for (Trabajadores tr : trabajadores) {
                        if (serviciosReal.getIdTrabajador().equals(tr.getIdTrabajador())) {
                            this.trabajadorServicio.setValue(tr);
                        }
                    }
                    precioServicio.setText(String.valueOf(serviciosReal.getPrecioFinal()));
                    comentarioServicio.setText(serviciosReal.getComentario());
                }
            }
        });

        iconoEliminar.setOnMouseClicked((MouseEvent event) -> {
            BigDecimal precioTotal = new BigDecimal(campoImporte.getText());
            precioTotal = precioTotal.subtract(precio);
            campoImporte.setText(String.valueOf(precioTotal));
            Integer idIcono = Integer.parseInt(iconoEliminar.getId());
            serviciosRealizados.removeIf(servicioReal -> servicioReal.getIdServicio().equals(idIcono));
            Node padre = iconoEliminar.getParent();
            serviciosAnadidos.getChildren().remove(padre);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (camposCobros != null) {
            String cliente = ControladorClientes.getClienteSeleccionado().getNombreCliente();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String diaActual = LocalDate.now().format(formatoFecha);
            fechaHoy.setText(diaActual);
            nombreCliente.setText(cliente);
            campoImporte.setText("0.00");
            cargarSelectTrabajador(selectTrabajadores);
            cargarSelectServicio(selectServicio);
            cargarSelectTrabajador(trabajadorServicio);
            selectServicio.setOnAction(event -> {
                BigDecimal precio = selectServicio.getValue().getPrecioBase();
                precioServicio.setText(String.valueOf(precio));
            });
            anadirServicio.setOnMouseClicked((MouseEvent event) -> {
                Servicios servicio = selectServicio.getValue();
                Trabajadores trabajador = trabajadorServicio.getValue();
                BigDecimal precio = (precioServicio.getText().isBlank()) ? servicio.getPrecioBase() : new BigDecimal(precioServicio.getText());
                String comentario = comentarioServicio.getText();
                anadirServicios(servicio, trabajador, precio, comentario);
            });
        }
    }
}
