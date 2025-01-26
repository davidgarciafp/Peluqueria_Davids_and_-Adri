package org.example.perruqueria.perruqueriadreams.Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.converter.LocalDateStringConverter;
import org.example.perruqueria.perruqueriadreams.Models.*;
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
    @FXML private HBox listadoCobrosFicha;
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

    public String formatearFecha(String fecha) {
        DateTimeFormatter formatoInicial = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate objetoFecha = LocalDate.parse(fecha, formatoInicial);
        DateTimeFormatter formatoFinal = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = objetoFecha.format(formatoFinal);
        return fechaFormateada;
    }

    public VBox crearContenidoCelda(String texto) {
        VBox vbox = new VBox();
        vbox.getStyleClass().add("celdas");
        vbox.getStyleClass().add("celdasTablas");
        vbox.setAlignment(Pos.CENTER);
        Text text = new Text(texto);
        vbox.getChildren().add(text);
        return vbox;
    }

    public VBox crearCabeceraTabla(String nombre) {
        VBox vbox = crearContenidoCelda(nombre);
        vbox.getStyleClass().add("negrita");
        return vbox;
    }

    public void mostrarFichaCliente(Integer id) {
        List<Object> serviciosFicha = clientesDAO.obtenerServiciosFicha(id);
        List <Servicios> listaServicios = (List<Servicios>) serviciosFicha.get(0);
        List<ServiciosRealizados> listaServiciosRealizados = (List<ServiciosRealizados>) serviciosFicha.get(1);

        List<Object> ventasFicha = clientesDAO.obtenerVentasFicha(id);
        List<Productos> listaProductos = (List<Productos>) ventasFicha.get(0);
        List<Ventas> listaVentas = (List<Ventas>) ventasFicha.get(1);

        GridPane tablaServicios = new GridPane();

        VBox vboxTextoServicio = crearCabeceraTabla("Servicio");
        VBox vboxTextoFecha = crearCabeceraTabla("Fecha");

        tablaServicios.add(vboxTextoServicio, 0, 0);
        tablaServicios.add(vboxTextoFecha, 1, 0);
        int fila = 1;

        for (ServiciosRealizados servicioRealizado : listaServiciosRealizados) {
            VBox vboxServicio = crearContenidoCelda(listaServicios.get(listaServiciosRealizados.indexOf(servicioRealizado)).getDescripcionServicio());
            tablaServicios.add(vboxServicio, 0, fila);

            String fecha = formatearFecha(servicioRealizado.getDiaServicioRealizados());
            VBox vboxFecha = crearContenidoCelda(fecha);
            tablaServicios.add(vboxFecha, 1, fila);
            fila++;
        }

        GridPane tablaVentas = new GridPane();
        VBox vboxTextoProducto = crearCabeceraTabla("Producto");
        VBox vboxTextoFechaVenta = crearCabeceraTabla("Fecha");

        tablaVentas.add(vboxTextoProducto, 0, 0);
        tablaVentas.add(vboxTextoFechaVenta, 1, 0);
        fila = 1;

        for (Ventas venta : listaVentas) {
            VBox vboxProducto = crearContenidoCelda(listaProductos.get(listaVentas.indexOf(venta)).getNombreProducto());
            tablaVentas.add(vboxProducto, 0, fila);

            String fecha = formatearFecha(venta.getDiaVenta());
            VBox vboxVenta = crearContenidoCelda(fecha);
            tablaVentas.add(vboxVenta, 1, fila);
            fila++;
        }
        listadoCobrosFicha.getChildren().add(tablaServicios);
        listadoCobrosFicha.getChildren().add(tablaVentas);
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
            mostrarFichaCliente(getClienteSeleccionado().getIdCliente());
            String descripcionInicial = clientesDAO.obtenerDescripcionCliente(getClienteSeleccionado().getIdCliente());
            descripcionFicha.setText(descripcionInicial);
            guardarFicha.setOnAction(event -> {
                if (descripcionFicha.getText() != descripcionInicial) {
                    boolean exitoso = actualizarDescripcionCliente(getClienteSeleccionado().getIdCliente(), descripcionFicha.getText());
                    if (exitoso) {
                        Global.mostrarAlertaExitosa("Descripción editada correctamente");
                    }
                    else {
                        Global.mostrarAlertaError("Error al editar la descripción");
                    }
                }
            });
        }
    }

}