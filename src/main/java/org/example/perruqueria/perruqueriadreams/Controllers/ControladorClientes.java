package org.example.perruqueria.perruqueriadreams.Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.example.perruqueria.perruqueriadreams.Models.Clientes;
import org.example.perruqueria.perruqueriadreams.Models.ClientesDAO;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;

public class ControladorClientes implements Initializable{
    private ClientesDAO clientesDAO = new ClientesDAO();
    private ControladorVentana vista = new ControladorVentana();

    @FXML private ImageView volverTabla;
    @FXML private ImageView iconoCerrarSesion;
    @FXML private TableView<Clientes> tablaClientes;
    @FXML private TableColumn<Clientes, String> columnaNombre;
    @FXML private TableColumn<Clientes, String> columnaApellido;
    @FXML private TableColumn<Clientes, String> columnaCorreo;
    @FXML private TableColumn<Clientes, String> columnaTelefono;
    @FXML private TableColumn<Clientes, String> columnaDescripcion;
    @FXML private TableColumn<Clientes, Boolean> columnaProteccionDatos;
    @FXML private Button anadir;
    @FXML private Button crear;
    @FXML private Button editar;
    @FXML private TextField campoNombre;
    @FXML private TextField campoApellido;
    @FXML private TextField campoCorreo;
    @FXML private TextField campoTelefono;
    @FXML private CheckBox checkboxProteccionDatos;
    @FXML private TextArea campoDescripcion;
    @FXML private Label mensajeError;

    private static Clientes clienteSeleccionado;

    public ControladorClientes() {
        //Constructor
    }

    public void mostrarClientes() {
        List<Clientes> clientes = this.clientesDAO.mostrarClientes();
        ObservableList<Clientes> datosClientes = FXCollections.observableList(clientes);
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellidoCliente"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correoCliente"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoCliente"));
        columnaProteccionDatos.setCellValueFactory(new PropertyValueFactory<>("proteccionDatos"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionCliente"));
        tablaClientes.setItems(datosClientes);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (tablaClientes != null) {
            mostrarClientes();
            anadir.setVisible(false);
            editar.setVisible(false);

            checkboxProteccionDatos.setVisible(true);
            try {
                tablaClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        clienteSeleccionado = newSelection;

                        anadir.setVisible(true);
                        anadir.setOnAction(event -> {
                            vista.redirigir("Clientes");
                        });
                        crear.setVisible(false);
                        editar.setVisible(true);
                        checkboxProteccionDatos.setVisible(true);

                        campoNombre.setText(clienteSeleccionado.getNombreCliente());
                        campoApellido.setText(clienteSeleccionado.getApellidoCliente());
                        campoCorreo.setText(clienteSeleccionado.getCorreoCliente());
                        campoTelefono.setText(clienteSeleccionado.getTelefonoCliente());
                        checkboxProteccionDatos.setSelected(clienteSeleccionado.isProteccionDatos());
                        campoDescripcion.setText(clienteSeleccionado.getDescripcionCliente());
                    }
                });
            } catch (NullPointerException error) {
                System.out.println("Error: " + error.getMessage());
            }
            volverTabla.setOnMouseClicked((MouseEvent event) -> {
                vista.redirigir("MenuAdmin");
            });
            crear.setOnAction(event -> {
                if (campoNombre.getText().isBlank() || campoTelefono.getText().isBlank()) {
                    mensajeError.setText("*Ni el nombre ni el teléfono pueden estar vacíos*");
                    mensajeError.setVisible(true);
                }
                else {
                    System.out.println( "El campo es : XXXXXXXXXXX"+checkboxProteccionDatos.isSelected());
                    boolean insercionExitosa = clientesDAO.agregarClientes(new Clientes(campoNombre.getText(), campoApellido.getText(), campoCorreo.getText(), campoTelefono.getText(), checkboxProteccionDatos.isSelected(), campoDescripcion.getText()));
                    if (insercionExitosa) {
                        System.out.println("Cliente creado con éxito");
                        vista.redirigir("Clientes");
                    }
                    else {
                        System.out.println("Error al insertar el cliente");
                    }
                }
            });
            editar.setOnAction(event -> {
                if (campoNombre.getText().isBlank() || campoTelefono.getText().isBlank()) {
                    mensajeError.setText("*Ni el nombre ni el teléfono pueden estar vacíos*");
                    mensajeError.setVisible(true);
                }
                else {
                    boolean edicionExitosa = clientesDAO.actualizarClientes(clienteSeleccionado.getIdCliente(), campoNombre.getText(), campoApellido.getText(), campoCorreo.getText(), campoTelefono.getText(), checkboxProteccionDatos.isSelected(), campoDescripcion.getText());
                    if (edicionExitosa) {
                        System.out.println("cliente editado con éxito");
                        vista.redirigir("Clientes");
                    }
                    else {
                        System.out.println("Error al editar el cliente");
                    }
                }
            });
        }
    }
}