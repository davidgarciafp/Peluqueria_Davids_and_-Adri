package org.example.perruqueria.perruqueriadreams.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.example.perruqueria.perruqueriadreams.Models.Servicios;
import org.example.perruqueria.perruqueriadreams.Models.ServiciosDAO;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorServicios implements Initializable {
    private ServiciosDAO serviciosDAO = new ServiciosDAO();
    private ControladorVentana vista = new ControladorVentana();

    @FXML
    private ChoiceBox<String> usuario;
    @FXML private PasswordField password;
    @FXML private Button acceder;
    @FXML private Label mensaje;
    @FXML private Button btnServicios;
    @FXML private ImageView volverTabla;
    @FXML private ImageView iconoCerrarSesion;
    @FXML private TableView<Servicios> tablaServicios;
    @FXML private TableColumn<Servicios, BigDecimal> columnaPrecio;
    @FXML private TableColumn<Servicios, String> columnaDescripcion;
    @FXML private Button anadir;
    @FXML private Button crear;
    @FXML private Button editar;
    @FXML private TextField campoPrecio;
    @FXML private TextArea campoDescripcion;
    @FXML private CheckBox checkboxActivo;
    @FXML private Label mensajeError;
    @FXML private Button mostrarInhabilitados;
    @FXML private Label nombreAdmin;

    private static Servicios servicioSeleccionado;
    private static boolean tablaActivos;

    public static boolean isTablaActivos() {
        return tablaActivos;
    }

    public static void setTablaActivos(boolean tablaActivos) {
        ControladorServicios.tablaActivos = tablaActivos;
    }

    public ControladorServicios() {
        //Constructor
    }
    
    public void mostrarServicios() {
        List<Servicios> servicios = this.serviciosDAO.mostrarServicios();
        ObservableList<Servicios> datosServicios = FXCollections.observableList(servicios);

        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionServicio"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precioBase"));
        tablaServicios.setItems(datosServicios);
    }

    public void mostrarServiciosInhabilitados() {
        List<Servicios> servicios = this.serviciosDAO.mostrarServiciosEliminados();
        ObservableList<Servicios> datosServicios = FXCollections.observableList(servicios);

        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionServicio"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precioBase"));
        tablaServicios.setItems(datosServicios);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (tablaServicios != null) {
            mostrarServicios();
            anadir.setVisible(false);
            editar.setVisible(false);
            checkboxActivo.setVisible(false);
            try {
                tablaServicios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        servicioSeleccionado = newSelection;

                        anadir.setVisible(true);
                        anadir.setOnAction(event -> {
                            vista.redirigir("Servicios");
                        });
                        crear.setVisible(false);
                        editar.setVisible(true);
                        checkboxActivo.setVisible(true);

                        campoDescripcion.setText(servicioSeleccionado.getDescripcionServicio());
                        BigDecimal precioServicio;
                        if (servicioSeleccionado.getPrecioBase() == null) {
                            precioServicio = new BigDecimal(0);
                        }
                        else {
                            precioServicio = servicioSeleccionado.getPrecioBase();
                        }
                        campoPrecio.setText(String.valueOf(precioServicio));
                        checkboxActivo.setSelected(servicioSeleccionado.isServicio_activo());
                    }
                });
            } catch (NullPointerException error) {
                System.out.println("Error: " + error.getMessage());
            }
            volverTabla.setOnMouseClicked((MouseEvent event) -> {
                vista.redirigir("MenuAdmin");
            });
            crear.setOnAction(event -> {
                if (campoDescripcion.getText().isBlank() || campoPrecio.getText().isBlank()) {
                    mensajeError.setText("*Ni el precio ni la descripción pueden estar vacíos*");
                    mensajeError.setVisible(true);
                }
                else {
                    try {
                        boolean insercionExitosa = serviciosDAO.agregarServicios(new Servicios(new BigDecimal(campoPrecio.getText()), campoDescripcion.getText(), true));                    
                        if(insercionExitosa){
                            System.out.println("Servicio creado con éxito");
                            vista.redirigir("Servicios");
                        }else{
                            System.out.println("Error al insertar el servicio");
                        }
                    
                    }catch(NumberFormatException e) {
                        mensajeError.setText("El precio debe ser un número válido");
                        mensajeError.setVisible(true);
                    }catch (Exception e) {
                        mensajeError.setText("Se produjo un error: " + e.getMessage());
                        mensajeError.setVisible(true);
                    }
                }
            
            });
            editar.setOnAction(event -> {
                if (campoDescripcion.getText().isBlank() || campoPrecio.getText().isBlank()) {
                    mensajeError.setVisible(true);
                }
                else {
                    boolean edicionExitosa = serviciosDAO.actualizarServicios(servicioSeleccionado.getIdServicio(), new BigDecimal(campoPrecio.getText()), campoDescripcion.getText(), checkboxActivo.isSelected());
                    if (edicionExitosa) {
                        System.out.println("Servicio editado con éxito");
                        vista.redirigir("Servicios");
                    }
                    else {
                        System.out.println("Error al editar el servicio");
                    }
                }
            });

            setTablaActivos(true);
            mostrarInhabilitados.setOnAction(event -> {
                if (isTablaActivos()) {
                    mostrarServiciosInhabilitados();
                    mostrarInhabilitados.setText("Mostrar habilitados");
                    setTablaActivos(false);
                }
                else {
                    mostrarServicios();
                    mostrarInhabilitados.setText("Mostrar inhabilitados");
                    setTablaActivos(true);
                }
            });
        }

    }
}
