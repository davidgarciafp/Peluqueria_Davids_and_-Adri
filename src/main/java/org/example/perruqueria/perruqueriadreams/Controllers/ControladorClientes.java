package org.example.perruqueria.perruqueriadreams.Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.perruqueria.perruqueriadreams.Models.Clientes;
import org.example.perruqueria.perruqueriadreams.Models.ClientesDAO;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;

public class ControladorClientes implements Initializable{
    private ClientesDAO clientesDAO = new ClientesDAO();
    private ControladorVentana vista = new ControladorVentana();

    @FXML private ImageView volverTabla;
    @FXML private ImageView flechaVolver;
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
    @FXML private VBox seccionFicha;
    @FXML private Text tituloFicha;
    @FXML private ScrollPane listadoCobrosFicha;
    @FXML private TextArea descripcionFicha;
    @FXML private Button guardarFicha;
    @FXML private Button btnNuevaSesion;

    private static Clientes clienteSeleccionado;

    public static Clientes getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public static void setClienteSeleccionado(Clientes clienteSeleccionado) {
        ControladorClientes.clienteSeleccionado = clienteSeleccionado;
    }

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

    public void mostrarFichaCliente() {
        //List<String> ficha = clientesDAO.obtenerFicha();
        GridPane tabla = new GridPane();
        Text textoServicio = new Text("Servicio");
        Text textoVenta = new Text("Venta");
        tabla.add(textoServicio, 0, 0);
        tabla.add(textoVenta, 1, 0);
        /*for (String registro : ficha) {
            Text textoRegistro = new Text(registro);
            nuevoRegistro.getChildren().add(textoRegistro);

        }*/
        seccionFicha.getChildren().add(tabla);
    }

    public boolean actualizarDescripcionCliente(Integer id, String descripcion) {
        boolean exitoso = clientesDAO.actualizarDescripcionFicha(id, descripcion);
        return exitoso;
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
                tablaClientes.setOnMouseClicked((MouseEvent event) -> {
                    if (event.getClickCount() == 2) {
                        setClienteSeleccionado(tablaClientes.getSelectionModel().getSelectedItem());
                        vista.redirigir("FichaCliente");
                        System.out.println("Has pulsado en " + clienteSeleccionado.getNombreCliente());
                    }
                });
            } catch (NullPointerException error) {
                System.out.println("Error: " + error.getMessage());
            }
            volverTabla.setOnMouseClicked((MouseEvent event) -> {
                if (ControladorTrabajadores.getTrabajadorValidado() != null && ControladorTrabajadores.getTrabajadorValidado().isTipoTrabajador()) {
                    vista.redirigir("MenuAdmin");
                    System.out.println("Soy admin");
                }
                else {
                    vista.redirigir("Agenda");
                }
            });
            crear.setOnAction(event -> {
                if (campoNombre.getText().isBlank() || campoTelefono.getText().isBlank()) {
                    Global.mostrarAlertaAdvertencia("Ni el nombre ni el teléfono pueden estar vacíos");
                }
                else {
                    boolean insercionExitosa = clientesDAO.agregarClientes(new Clientes(campoNombre.getText(), campoApellido.getText(), campoCorreo.getText(), campoTelefono.getText(), checkboxProteccionDatos.isSelected(), campoDescripcion.getText()));
                    if (insercionExitosa) {
                        boolean confirmado = Global.mostrarAlertaExitosa("Cliente creado con éxito");
                        if (confirmado) {
                            vista.redirigir("Clientes");
                        }
                    }
                    else {
                        Global.mostrarAlertaError("Error al dar de alta al cliente.");
                    }
                }
            });
            editar.setOnAction(event -> {
                if (campoNombre.getText().isBlank() || campoTelefono.getText().isBlank()) {
                    Global.mostrarAlertaAdvertencia("Ni el nombre ni el teléfono pueden estar vacíos");
                }
                else {
                    boolean edicionExitosa = clientesDAO.actualizarClientes(clienteSeleccionado.getIdCliente(), campoNombre.getText(), campoApellido.getText(), campoCorreo.getText(), campoTelefono.getText(), checkboxProteccionDatos.isSelected(), campoDescripcion.getText());
                    if (edicionExitosa) {
                        boolean confirmado = Global.mostrarAlertaExitosa("Cliente editado con éxito");
                        if (confirmado) {
                            vista.redirigir("Clientes");
                        }
                    }
                    else {
                        Global.mostrarAlertaError("Error al editar el cliente");
                    }
                }
            });
        }
        if (seccionFicha != null) {
            btnNuevaSesion.setOnAction(event -> {
                vista.redirigir("Cobros");
            });
            flechaVolver.setOnMouseClicked((MouseEvent event) -> {
                vista.redirigir("Clientes");
            });
            tituloFicha.setText(tituloFicha.getText() + " " + getClienteSeleccionado().getNombreCliente());
            String descripcionInicial = clientesDAO.obtenerDescripcionCliente(getClienteSeleccionado().getIdCliente());
            descripcionFicha.setText(descripcionInicial);
            guardarFicha.setOnAction(event -> {
                if (descripcionFicha.getText() != descripcionInicial) {
                    boolean exitoso = actualizarDescripcionCliente(getClienteSeleccionado().getIdCliente(), descripcionFicha.getText());

                }
            });
        }
    }

}