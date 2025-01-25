package org.example.perruqueria.perruqueriadreams.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.example.perruqueria.perruqueriadreams.Models.*;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControladorCobros implements Initializable {
    @FXML private VBox camposCobros;
    @FXML private VBox camposServicios;
    @FXML private VBox camposProductos;
    @FXML private ImageView volverTabla;
    @FXML private ChoiceBox<Servicios> selectServicio;
    @FXML private ChoiceBox<Trabajadores> trabajadorServicio;
    @FXML private TextField precioServicio;
    @FXML private TextArea comentarioServicio;
    @FXML private ImageView anadirServicio;
    @FXML private VBox serviciosAnadidos;
    @FXML private ChoiceBox<Productos> selectProducto;
    @FXML private ChoiceBox<Trabajadores> trabajadorProducto;
    @FXML private TextField precioProducto;
    @FXML private TextField cantidadProducto;
    @FXML private TextArea descripcionVenta;
    @FXML private ImageView anadirProducto;
    @FXML private VBox productosAnadidos;
    @FXML private Text fechaHoy;
    @FXML private Text nombreCliente;
    @FXML private ChoiceBox<Trabajadores> selectTrabajadores;
    @FXML private TextField campoImporte;
    @FXML private TextField campoPagoPendiente;
    @FXML private TextField campoEfectivo;
    @FXML private TextField campoTarjeta;
    @FXML private TextField campoBizum;
    @FXML private Button btnRealizar;
    @FXML private Button btnImprimir;
    @FXML private TableView<Cobros> tablaCobros;
    @FXML private TableColumn<Cobros, String> colDiaCobro;
    @FXML private TableColumn<Cobros, String> colCliente;
    @FXML private TableColumn<Cobros, BigDecimal> colImporte;
    @FXML private TableColumn<Cobros, BigDecimal> colDeudas;
    @FXML private TableColumn<Cobros, Boolean> colPagado;
    @FXML private TextField campoFecha;
    @FXML private TextField campoCliente;
    @FXML private TextField campoImporteEditar;
    @FXML private TextField campoDeudas;
    @FXML private CheckBox checkPagado;
    @FXML private Button btnGuardarCambios;

private Cobros cobroSeleccionado;
    private TrabajadoresDAO modeloTrabajadores = new TrabajadoresDAO();
    private ServiciosDAO modeloServicios = new ServiciosDAO();
    private ProductosDAO modeloProductos = new ProductosDAO();
    private ServiciosRealizadosDAO modeloServiciosRealizados = new ServiciosRealizadosDAO();
    private VentasDAO modeloVentas = new VentasDAO();
    private CobrosDAO modeloCobros = new CobrosDAO();

    private List<Servicios> listaServicios = modeloServicios.mostrarServicios();
    private List<Productos> listaProductos = modeloProductos.mostrarProductos();
    private List<Trabajadores> listaTrabajadores = modeloTrabajadores.mostrarTrabajadores();

    private ArrayList<ServiciosRealizados> serviciosRealizados = new ArrayList<>();
    private ArrayList<Ventas> productosVendidos = new ArrayList<>();

    private ControladorVentana vista = new ControladorVentana();

    public List<Servicios> getListaServicios() {
        return listaServicios;
    }

    public List<Productos> getListaProductos() {
        return listaProductos;
    }

    public List<Trabajadores> getListaTrabajadores() {
        return listaTrabajadores;
    }

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

    public void mostrarValoresProductos(ChoiceBox<Productos> select) {
        select.setConverter(new StringConverter<>() {
            @Override
            public String toString(Productos producto) {
                return producto != null ? producto.getNombreProducto() : "";
            }

            @Override
            public Productos fromString(String string) {
                return null;
            }
        });
    }

    public void cargarSelectTrabajador(ChoiceBox<Trabajadores> select) {
        ObservableList<Trabajadores> trabajadores = FXCollections.observableList(getListaTrabajadores());
        select.setItems(trabajadores);
        mostrarValoresTrabajadores(select);
    }

    public void cargarSelectServicio(ChoiceBox<Servicios> select) {
        ObservableList<Servicios> servicios = FXCollections.observableList(getListaServicios());
        select.setItems(servicios);
        mostrarValoresServicios(select);
    }

    public void cargarSelectProducto(ChoiceBox<Productos> select) {
        ObservableList<Productos> productos = FXCollections.observableList(getListaProductos());
        select.setItems(productos);
        mostrarValoresProductos(select);
    }

    public void sumarImporte(BigDecimal precio) {
        BigDecimal precioImporte = new BigDecimal(campoImporte.getText());
        precioImporte = precioImporte.add(precio);
        campoImporte.setText(String.valueOf(precioImporte));
    }

    public void restarImporte(BigDecimal precio) {
        BigDecimal precioImporte = new BigDecimal(campoImporte.getText());
        precioImporte = precioImporte.subtract(precio);
        campoImporte.setText(String.valueOf(precioImporte));
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

    public void anadirServicios(Servicios servicio, Clientes cliente, Trabajadores trabajador, BigDecimal precio, String comentario) {
        HBox servicioAnadido = new HBox();
        servicioAnadido.setAlignment(Pos.TOP_CENTER);
        servicioAnadido.setSpacing(20);

        ChoiceBox<Servicios> selectServicio = new ChoiceBox<>();
        cargarSelectServicio(selectServicio);
        selectServicio.setValue(servicio);
        ChoiceBox<Trabajadores> selectTrabajador = new ChoiceBox<>();
        cargarSelectTrabajador(selectTrabajador);
        selectTrabajador.setValue(trabajador);
        sumarImporte(precio);
        ServiciosRealizados servicioRealizado = new ServiciosRealizados(servicio.getIdServicio(), cliente.getIdCliente(), trabajador.getIdTrabajador(), LocalDate.now().toString(), precio, comentario);
        serviciosRealizados.add(servicioRealizado);

        //StackPane iconoEditar = crearIcono("/org/example/perruqueria/perruqueriadreams/Images/editar.png", String.valueOf(servicio.getIdServicio()));
        StackPane iconoEliminar = crearIcono("/org/example/perruqueria/perruqueriadreams/Images/eliminar.png", String.valueOf(servicio.getIdServicio()));

        servicioAnadido.getChildren().addAll(selectServicio, selectTrabajador, iconoEliminar);
        serviciosAnadidos.getChildren().add(servicioAnadido);

        /*iconoEditar.setOnMouseClicked((MouseEvent event) -> {
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
        });*/

        iconoEliminar.setOnMouseClicked((MouseEvent event) -> {
            restarImporte(precio);
            Integer idIcono = Integer.parseInt(iconoEliminar.getId());
            serviciosRealizados.removeIf(servicioReal -> servicioReal.getIdServicio().equals(idIcono));
            Node padre = iconoEliminar.getParent();
            serviciosAnadidos.getChildren().remove(padre);
        });
    }

    public void anadirProductos(Productos producto, Trabajadores trabajador, BigDecimal precio, Integer cantidad, String descripcion) {
        HBox productoAnadido = new HBox();
        productoAnadido.setAlignment(Pos.TOP_CENTER);
        productoAnadido.setSpacing(20);

        ChoiceBox<Productos> selectProducto = new ChoiceBox<>();
        cargarSelectProducto(selectProducto);
        selectProducto.setValue(producto);
        ChoiceBox<Trabajadores> selectTrabajador = new ChoiceBox<>();
        cargarSelectTrabajador(selectTrabajador);
        selectTrabajador.setValue(trabajador);

        BigDecimal precioBase = producto.getPrecioProducto();
        BigDecimal precioSumar = precioBase.multiply(new BigDecimal(cantidad));
        sumarImporte(precioSumar);

        Ventas venta = new Ventas(trabajador.getIdTrabajador(), producto.getIdProducto(), ControladorClientes.getClienteSeleccionado().getIdCliente(), LocalDate.now().toString(), cantidad, descripcion);
        productosVendidos.add(venta);

        StackPane iconoEliminar = crearIcono("/org/example/perruqueria/perruqueriadreams/Images/eliminar.png", String.valueOf(producto.getIdProducto()));

        productoAnadido.getChildren().addAll(selectProducto, selectTrabajador, iconoEliminar);
        productosAnadidos.getChildren().add(productoAnadido);

        iconoEliminar.setOnMouseClicked((MouseEvent event) -> {
            BigDecimal precioRestar = precio.multiply(new BigDecimal(cantidad));
            restarImporte(precioRestar);
            Integer idIcono = Integer.parseInt(iconoEliminar.getId());
            productosVendidos.removeIf(productoVendido -> productoVendido.getIdProducto().equals(idIcono));
            Node padre = iconoEliminar.getParent();
            productosAnadidos.getChildren().remove(padre);
        });
    }

    public boolean realizarCobro(ArrayList<ServiciosRealizados> serviciosRealizados, ArrayList<Ventas> ventas, Trabajadores trabajador, Clientes cliente, BigDecimal importe, BigDecimal efectivo, BigDecimal tarjeta, BigDecimal bizum, BigDecimal deudas) {
        for (ServiciosRealizados serviciosRealizado : serviciosRealizados) {
            modeloServiciosRealizados.agregarServicioRealizado(serviciosRealizado);
        }
        for (Ventas venta : ventas) {
            modeloVentas.agregarVenta(venta);
        }
        Boolean pagado = true;
        if (deudas.compareTo(BigDecimal.ZERO) > 0) { //Comprobar si el valor de las deudas es mayor a 0.
            pagado = false;
        }
        Cobros cobro = new Cobros(trabajador.getIdTrabajador(), cliente.getIdCliente(), LocalDate.now().toString(), importe, efectivo, tarjeta, bizum, deudas, pagado);
        boolean realizado = modeloCobros.agregarCobro(cobro);
        return realizado;
    }
    private void cargarCobrosEnTabla() {
        // Obtener los cobros desde el DAO
        List<Cobros> listaCobros = modeloCobros.obtenerCobros();
    
        // Convertir la lista en ObservableList para usar con JavaFX
        ObservableList<Cobros> datosCobros = FXCollections.observableArrayList(listaCobros);
    
        // Cargar los datos en la tabla
        tablaCobros.setItems(datosCobros);
    }

    private void cargarDatosEnFormulario(Cobros cobro) {
        cobroSeleccionado = cobro;
    
        // Cargar los datos en los campos
        campoFecha.setText(cobro.getDiaCobro());
        campoCliente.setText(cobro.getNombreClienteTemporal()); // Solo para mostrar, no se modifica
        campoImporteEditar.setText(cobro.getImporte().toString());
        campoDeudas.setText(cobro.getDeudas().toString());
        checkPagado.setSelected(cobro.isPagado());
    }
    
    private void guardarCambiosCobro() {
        if (cobroSeleccionado != null) {
            // Actualizar los datos del cobroSeleccionado con los valores del formulario
            cobroSeleccionado.setDiaCobro(campoFecha.getText());
            cobroSeleccionado.setImporte(new BigDecimal(campoImporteEditar.getText()));
            cobroSeleccionado.setDeudas(new BigDecimal(campoDeudas.getText()));
            cobroSeleccionado.setPagado(checkPagado.isSelected());
    
            // Actualizar en la base de datos
            boolean exito = modeloCobros.modificarCobro(cobroSeleccionado);
    
            if (exito) {
                mostrarMensaje("El cobro se ha modificado con éxito.");
                cargarCobrosEnTabla(); // Recargar la tabla
            } else {
                mostrarMensaje("No se pudo modificar el cobro.");
            }
        } else {
            mostrarMensaje("No hay un cobro seleccionado.");
        }
    }
    
    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         // Configurar columnas de la tabla
        colDiaCobro.setCellValueFactory(new PropertyValueFactory<>("diaCobro"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("nombreClienteTemporal"));
        colImporte.setCellValueFactory(new PropertyValueFactory<>("importe"));
        colDeudas.setCellValueFactory(new PropertyValueFactory<>("deudas"));

        // Cargar los datos
        cargarCobrosEnTabla();
        
        volverTabla.setOnMouseClicked((MouseEvent event) -> {
            vista.redirigir("MenuAdmin");
        });
        tablaCobros.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarDatosEnFormulario(newValue);
            }
        });
    
        // Acción para guardar cambios
        btnGuardarCambios.setOnAction(event -> guardarCambiosCobro());
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
            cargarSelectProducto(selectProducto);
            cargarSelectTrabajador(trabajadorProducto);

            selectServicio.setOnAction(event -> {
                BigDecimal precio = selectServicio.getValue().getPrecioBase();
                precioServicio.setText(String.valueOf(precio));
            });
            selectProducto.setOnAction(event -> {
                BigDecimal precio = selectProducto.getValue().getPrecioProducto();
                precioProducto.setText(String.valueOf(precio));
                cantidadProducto.setText("1");
            });
            volverTabla.setOnMouseClicked((MouseEvent event) -> {
                vista.redirigir("FichaCliente");
            });
            anadirServicio.setOnMouseClicked((MouseEvent event) -> {
                Servicios servicio = selectServicio.getValue();
                Trabajadores trabajador = trabajadorServicio.getValue();
                BigDecimal precio = (precioServicio.getText().isBlank()) ? servicio.getPrecioBase() : new BigDecimal(precioServicio.getText());
                String comentario = comentarioServicio.getText();
                anadirServicios(servicio, ControladorClientes.getClienteSeleccionado(), trabajador, precio, comentario);
            });

            anadirProducto.setOnMouseClicked((MouseEvent event) -> {
                Productos producto = selectProducto.getValue();
                Trabajadores trabajador = trabajadorProducto.getValue();
                BigDecimal precio = (precioProducto.getText().isBlank()) ? producto.getPrecioProducto() : new BigDecimal(precioProducto.getText());
                Integer cantidad = Integer.parseInt(cantidadProducto.getText());
                String descripcion = descripcionVenta.getText();
                anadirProductos(producto, trabajador, precio, cantidad, descripcion);
            });

            btnRealizar.setOnAction(event -> {
                if (!serviciosAnadidos.getChildren().isEmpty() || !productosAnadidos.getChildren().isEmpty()) {
                    BigDecimal importe = new BigDecimal(campoImporte.getText());
                    BigDecimal efectivo = (campoEfectivo.getText().isBlank()) ? new BigDecimal("0") : new BigDecimal(campoEfectivo.getText());
                    BigDecimal tarjeta = (campoTarjeta.getText().isBlank()) ? new BigDecimal("0") : new BigDecimal(campoTarjeta.getText());
                    BigDecimal bizum = (campoBizum.getText().isBlank()) ? new BigDecimal("0") : new BigDecimal(campoBizum.getText());
                    BigDecimal deudas = (campoPagoPendiente.getText().isBlank()) ? new BigDecimal("0") : new BigDecimal(campoPagoPendiente.getText());
                    boolean exitoso = realizarCobro(serviciosRealizados, productosVendidos, selectTrabajadores.getValue(), ControladorClientes.getClienteSeleccionado(), importe, efectivo, tarjeta, bizum, deudas);
                    if (exitoso) {
                        boolean confirmado = Global.mostrarAlertaExitosa("COBRO REALIZADO CON ÉXITO.");
                        if (confirmado) {
                            vista.redirigir("Agenda");
                        }
                    }
                }
            });
        }
    }

}
