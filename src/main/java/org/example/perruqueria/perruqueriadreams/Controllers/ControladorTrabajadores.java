package org.example.perruqueria.perruqueriadreams.Controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.example.perruqueria.perruqueriadreams.Models.Trabajadores;
import org.example.perruqueria.perruqueriadreams.Models.TrabajadoresDAO;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;

public class ControladorTrabajadores implements Initializable{
    private TrabajadoresDAO trabajadoresDAO = new TrabajadoresDAO();
    private ControladorVentana vista = new ControladorVentana();

    @FXML private PasswordField password;
    @FXML private Button acceder;
    @FXML private ChoiceBox<Trabajadores> selectTrabajador;
    @FXML private PasswordField contrasena;
    @FXML private Button entrar;
    @FXML private ImageView volverAgenda;
    @FXML private ImageView iconoCerrarSesionMenu;
    @FXML private VBox estadisticas;
    @FXML private Text nombreTrabajador;
    @FXML private Button btnTrabajadores;
    @FXML private Button btnServicios;
    @FXML private Button btnProductos;
    @FXML private Button btnClientes;
    @FXML private ImageView volverTabla;
    @FXML private ImageView iconoCerrarSesion;
    @FXML private TableView<Trabajadores> tablaTrabajadores;
    @FXML private TableColumn<Trabajadores, String> columnaNombre;
    @FXML private TableColumn<Trabajadores, String> columnaApellido;
    @FXML private TableColumn<Trabajadores, String> columnaCorreo;
    @FXML private TableColumn<Trabajadores, String> columnaTelefono;
    @FXML private TableColumn<Trabajadores, Boolean> columnaRol;
    @FXML private TableColumn<Trabajadores, BigDecimal> columnaComisionServicio;
    @FXML private TableColumn<Trabajadores, BigDecimal> columnaComisionProducto;
    @FXML private Button anadir;
    @FXML private Button crear;
    @FXML private Button editar;
    @FXML private TextField campoNombre;
    @FXML private TextField campoApellido;
    @FXML private TextField campoContrasena;
    @FXML private TextField campoCorreo;
    @FXML private TextField campoTelefono;
    @FXML private CheckBox checkboxActivo;
    @FXML private CheckBox checkboxAdmin;
    @FXML private Label etiquetaComisionServicio;
    @FXML private Label etiquetaComisionProducto;
    @FXML private TextField campoComisionServicio;
    @FXML private TextField campoComisionProducto;
    @FXML private Label mensajeError;
    @FXML private Button mostrarInhabilitados;
    @FXML private Label nombreAdmin;

    private static Trabajadores trabajadorValidado;
    private static Trabajadores trabajadorSeleccionado;
    private static boolean tablaActivos;

    public static Trabajadores getTrabajadorValidado() {
        return trabajadorValidado;
    }

    public static void setTrabajadorValidado(Trabajadores trabajadorValidado) {
        ControladorTrabajadores.trabajadorValidado = trabajadorValidado;
    }

    public static boolean isTablaActivos() {
        return tablaActivos;
    }

    public static void setTablaActivos(boolean tablaActivos) {
        ControladorTrabajadores.tablaActivos = tablaActivos;
    }

    public ControladorTrabajadores() {
        //Constructor
    }

    public void validarLoginPrincipal() {
        String inputPassword = this.password.getText();
        Trabajadores trabajador = this.trabajadoresDAO.identificarDreams(inputPassword);
        if (trabajador != null) {
            vista.redirigir("Agenda");
        }
        else {
            Global.mostrarAlertaError("CONTRASEÑA INCORRECTA");
            this.password.setText("");
        }
    }

    public void cargarSelectTrabajadores() {
        List<Trabajadores> trabajadores = this.trabajadoresDAO.mostrarTrabajadores();
        ObservableList<Trabajadores> listaTrabajadores = FXCollections.observableList(trabajadores);
        this.selectTrabajador.setItems(listaTrabajadores);
        this.selectTrabajador.setConverter(new StringConverter<>() {
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

    public void validarLoginTrabajadores(Trabajadores trabajador) {
        Trabajadores trabajadorValidado = this.trabajadoresDAO.identificarTrabajador(trabajador.getIdTrabajador(), trabajador.getContrasena());
        if (trabajadorValidado != null) {
            setTrabajadorValidado(trabajadorValidado);
            if (trabajadorValidado.isTipoTrabajador()) {
                vista.redirigir("MenuAdmin");
            }
            else {
                vista.redirigir("Menu");
            }
        }
        else {
            Global.mostrarAlertaError("CREDENCIALES INCORRECTAS");
            this.password.setText("");
        }
    }

    public void mostrarTrabajadores() {
        List<Trabajadores> trabajadores = this.trabajadoresDAO.mostrarTrabajadores();
        ObservableList<Trabajadores> datosTrabajadores = FXCollections.observableList(trabajadores);

        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreTrabajador"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellidoTrabajador"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correoTrabajador"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoTrabajador"));
        columnaRol.setCellValueFactory(new PropertyValueFactory<>("tipoTrabajador"));
        columnaRol.setCellFactory(new Callback<TableColumn<Trabajadores, Boolean>, TableCell<Trabajadores, Boolean>>() {
            @Override
            public TableCell<Trabajadores, Boolean> call(TableColumn<Trabajadores, Boolean> param) {
                return new TableCell<Trabajadores, Boolean>() {
                    @Override
                    protected void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item ? "Administrador" : "Técnico");
                        }
                    }
                };
            }
        });
        columnaComisionServicio.setCellValueFactory(new PropertyValueFactory<>("comisionServicio"));
        columnaComisionProducto.setCellValueFactory(new PropertyValueFactory<>("comisionProducto"));
        tablaTrabajadores.setItems(datosTrabajadores);
    }

    public void mostrarTrabajadoresInhabilitados() {
        List<Trabajadores> trabajadores = this.trabajadoresDAO.mostrarTrabajadoresEliminados();
        ObservableList<Trabajadores> datosTrabajadores = FXCollections.observableList(trabajadores);

        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreTrabajador"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellidoTrabajador"));
        columnaCorreo.setCellValueFactory(new PropertyValueFactory<>("correoTrabajador"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoTrabajador"));
        columnaRol.setCellValueFactory(new PropertyValueFactory<>("tipoTrabajador"));
        columnaRol.setCellFactory(new Callback<TableColumn<Trabajadores, Boolean>, TableCell<Trabajadores, Boolean>>() {
            @Override
            public TableCell<Trabajadores, Boolean> call(TableColumn<Trabajadores, Boolean> param) {
                return new TableCell<Trabajadores, Boolean>() {
                    @Override
                    protected void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item ? "Administrador" : "Técnico");
                        }
                    }
                };
            }
        });
        columnaComisionServicio.setCellValueFactory(new PropertyValueFactory<>("comisionServicio"));
        columnaComisionProducto.setCellValueFactory(new PropertyValueFactory<>("comisionProducto"));
        tablaTrabajadores.setItems(datosTrabajadores);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(acceder != null){
            try {
                acceder.setOnAction(event -> {
                    validarLoginPrincipal();
                });
            } catch (Exception error) {
                throw new RuntimeException("Error" + error.getMessage());
            }
        }
        if (entrar != null) {
            cargarSelectTrabajadores();
            entrar.setOnAction(event -> {
                Trabajadores trabajador = this.selectTrabajador.getValue();
                trabajador.setContrasena(contrasena.getText());
                validarLoginTrabajadores(trabajador);
            });
            volverAgenda.setOnMouseClicked((MouseEvent event) -> {
                vista.redirigir("Agenda");
            });
        }
        if (estadisticas != null) {
            String apellido = (getTrabajadorValidado().getApellidoTrabajador() != null) ? getTrabajadorValidado().getApellidoTrabajador() : "";
            nombreTrabajador.setText(getTrabajadorValidado().getNombreTrabajador() + " " + apellido);
            iconoCerrarSesionMenu.setOnMouseClicked((MouseEvent event ) -> {
                vista.redirigir("LoginTrabajadores");
            });
        }
        if (btnTrabajadores != null) {
            iconoCerrarSesion.setOnMouseClicked((MouseEvent event) -> {
                vista.redirigir("LoginTrabajadores");
            });
            try {
                btnTrabajadores.setOnAction(event -> {
                    vista.redirigir("Trabajadores");
                });
            } catch (Exception error) {
                throw new RuntimeException("Error" + error.getMessage());
            }
        }
        if (btnServicios != null) {
            iconoCerrarSesion.setOnMouseClicked((MouseEvent event) -> {
                vista.redirigir("LoginTrabajadores");
            });
            try {
                btnServicios.setOnAction(event -> {
                    vista.redirigir("Servicios");
                });
            } catch (Exception error) {
                throw new RuntimeException("Error" + error.getMessage());
            }
        }
        if (btnProductos != null) {
            iconoCerrarSesion.setOnMouseClicked((MouseEvent event) -> {
                vista.redirigir("LoginTrabajadores");
            });
            try {
                btnProductos.setOnAction(event -> {
                    vista.redirigir("Productos");
                });
            } catch (Exception error) {
                throw new RuntimeException("Error" + error.getMessage());
            }
        }
        if (btnClientes != null) {
            iconoCerrarSesion.setOnMouseClicked((MouseEvent event) -> {
                vista.redirigir("LoginTrabajadores");
            });
            try {
                btnClientes.setOnAction(event -> {
                    vista.redirigir("Clientes");
                });
            } catch (Exception error) {
                throw new RuntimeException("Error" + error.getMessage());
            }
        }

        if (tablaTrabajadores != null) {
            mostrarTrabajadores();
            anadir.setVisible(false);
            editar.setVisible(false);
            checkboxActivo.setVisible(false);
            checkboxAdmin.setVisible(false);
            etiquetaComisionServicio.setVisible(false);
            campoComisionServicio.setVisible(false);
            etiquetaComisionProducto.setVisible(false);
            campoComisionProducto.setVisible(false);
            try {
                tablaTrabajadores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        trabajadorSeleccionado = newSelection;

                        anadir.setVisible(true);
                        anadir.setOnAction(event -> {
                            vista.redirigir("Trabajadores");
                        });
                        crear.setVisible(false);
                        editar.setVisible(true);
                        checkboxActivo.setVisible(true);
                        checkboxAdmin.setVisible(true);
                        etiquetaComisionServicio.setVisible(true);
                        campoComisionServicio.setVisible(true);
                        etiquetaComisionProducto.setVisible(true);
                        campoComisionProducto.setVisible(true);

                        campoNombre.setText(trabajadorSeleccionado.getNombreTrabajador());
                        campoApellido.setText(trabajadorSeleccionado.getApellidoTrabajador());
                        campoContrasena.setText(trabajadorSeleccionado.getContrasena());
                        campoCorreo.setText(trabajadorSeleccionado.getCorreoTrabajador());
                        campoTelefono.setText(trabajadorSeleccionado.getTelefonoTrabajador());
                        BigDecimal comisionServicio;
                        if (trabajadorSeleccionado.getComisionServicio() == null) {
                            comisionServicio = new BigDecimal(0);
                        }
                        else {
                            comisionServicio = trabajadorSeleccionado.getComisionServicio();
                        }
                        campoComisionServicio.setText(String.valueOf(comisionServicio));
                        BigDecimal comisionProducto;
                        if (trabajadorSeleccionado.getComisionProducto() == null) {
                            comisionProducto = new BigDecimal(0);
                        }
                        else {
                            comisionProducto = trabajadorSeleccionado.getComisionProducto();
                        }
                        campoComisionProducto.setText(String.valueOf(comisionProducto));
                        checkboxActivo.setSelected(trabajadorSeleccionado.isTrabajadorActivo());
                        checkboxAdmin.setSelected(trabajadorSeleccionado.isTipoTrabajador());
                    }
                });
            } catch (NullPointerException error) {
                System.out.println("Error: " + error.getMessage());
            }
            volverTabla.setOnMouseClicked((MouseEvent event) -> {
                vista.redirigir("MenuAdmin");
            });
            crear.setOnAction(event -> {
                if (campoNombre.getText().isBlank() || campoContrasena.getText().isBlank()) {
                    mensajeError.setText("*Ni el nombre ni la contraseña pueden estar vacíos*");
                    mensajeError.setVisible(true);
                }
                else {
                    boolean insercionExitosa = trabajadoresDAO.agregarTrabajadores(new Trabajadores(campoNombre.getText(), campoApellido.getText(), campoCorreo.getText(), campoTelefono.getText(), campoContrasena.getText(), true, false));
                    if (insercionExitosa) {
                        System.out.println("Trabajador creado con éxito");
                        vista.redirigir("Trabajadores");
                    }
                    else {
                        System.out.println("Error al insertar el trabajador");
                    }
                }
            });
            editar.setOnAction(event -> {
                if (campoNombre.getText().isBlank() || campoContrasena.getText().isBlank()) {
                    mensajeError.setVisible(true);
                }
                else {
                    boolean edicionExitosa = trabajadoresDAO.actualizarTrabajadores(trabajadorSeleccionado.getIdTrabajador(), campoNombre.getText(), campoApellido.getText(), campoCorreo.getText(), campoTelefono.getText(), campoContrasena.getText(), checkboxActivo.isSelected(), checkboxAdmin.isSelected(), new BigDecimal(campoComisionServicio.getText()), new BigDecimal(campoComisionProducto.getText()));
                    if (edicionExitosa) {
                        System.out.println("Trabajador editado con éxito");
                        vista.redirigir("Trabajadores");
                    }
                    else {
                        System.out.println("Error al editar el trabajador");
                    }
                }
            });

            setTablaActivos(true);
            mostrarInhabilitados.setOnAction(event -> {
                if (isTablaActivos()) {
                    mostrarTrabajadoresInhabilitados();
                    mostrarInhabilitados.setText("Mostrar habilitados");
                    setTablaActivos(false);
                }
                else {
                    mostrarTrabajadores();
                    mostrarInhabilitados.setText("Mostrar inhabilitados");
                    setTablaActivos(true);
                }
            });
        }

    }

}