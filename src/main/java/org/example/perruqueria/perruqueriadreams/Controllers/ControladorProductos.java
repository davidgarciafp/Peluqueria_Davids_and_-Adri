package org.example.perruqueria.perruqueriadreams.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.example.perruqueria.perruqueriadreams.Models.Productos;
import org.example.perruqueria.perruqueriadreams.Models.ProductosDAO;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorProductos implements Initializable {
    private ProductosDAO productosDAO = new ProductosDAO();
    private ControladorVentana vista = new ControladorVentana();

    @FXML private ImageView volverTabla;
    @FXML private ImageView iconoCerrarSesion;
    @FXML private TableView<Productos> tablaProductos;
    @FXML private TableColumn<Productos, String> columnaCodigoBarras;
    @FXML private TableColumn<Productos, String> columnaNombre;
    @FXML private TableColumn<Productos, String> columnaMarca;
    @FXML private TableColumn<Productos, BigDecimal> columnaPrecio;
    @FXML private TableColumn<Productos, String> columnaDescripcion;
    @FXML private TableColumn<Productos, Integer> columnaCantidad;
    @FXML private TableColumn<Productos, Integer> columnaGastados;

    @FXML private Button anadir;
    @FXML private Button crear;
    @FXML private Button editar;
    @FXML private TextField campoCodigoBarras;
    @FXML private TextField campoNombre;
    @FXML private TextField campoMarca;
    @FXML private TextArea campoDescripcion;
    @FXML private TextField campoPrecio;
    @FXML private TextField campoCantidad;
    @FXML private Label etiquetaGastados;
    @FXML private ChoiceBox<String> campoGastados;
    @FXML private CheckBox checkboxActivo;
    @FXML private Button mostrarInactivos;

    private static Productos productoSeleccionado;
    private static boolean tablaActivos;

    public static boolean isTablaActivos() {
        return tablaActivos;
    }

    public static void setTablaActivos(boolean tablaActivos) {
        ControladorProductos.tablaActivos = tablaActivos;
    }

    public ControladorProductos() {
        //Constructor
    }

    public static Productos getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public static void setProductoSeleccionado(Productos productoSeleccionado) {
        ControladorProductos.productoSeleccionado = productoSeleccionado;
    }

    public void mostrarProductos() {
        List<Productos> productos = this.productosDAO.mostrarProductos();
        ObservableList<Productos> datosProductos = FXCollections.observableList(productos);

        columnaCodigoBarras.setCellValueFactory(new PropertyValueFactory<>("codigoBarras"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        columnaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precioProducto"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionProducto"));
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadDisponible"));
        columnaGastados.setCellValueFactory(new PropertyValueFactory<>("productoGastado"));
        tablaProductos.setItems(datosProductos);
    }

    public void mostrarProductosInactivos() {
        List<Productos> productos = this.productosDAO.mostrarProductosEliminados();
        ObservableList<Productos> datosProductos = FXCollections.observableList(productos);

        columnaCodigoBarras.setCellValueFactory(new PropertyValueFactory<>("codigoBarras"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        columnaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precioProducto"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionProducto"));
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadDisponible"));
        columnaGastados.setCellValueFactory(new PropertyValueFactory<>("productoGastado"));
        tablaProductos.setItems(datosProductos);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (tablaProductos != null) {
            mostrarProductos();
            volverTabla.setOnMouseClicked((MouseEvent event) -> {
                vista.redirigir("MenuAdmin");
            });
            anadir.setVisible(false);
            editar.setVisible(false);
            etiquetaGastados.setVisible(false);
            campoGastados.setVisible(false);
            checkboxActivo.setVisible(false);

            tablaProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    setProductoSeleccionado(newSelection);
                    anadir.setVisible(true);
                    anadir.setOnAction(event -> {
                        vista.redirigir("Productos");
                    });
                    crear.setVisible(false);
                    editar.setVisible(true);

                    ArrayList<String> nombresCampos = new ArrayList<>();
                    nombresCampos.add("Disponible");
                    nombresCampos.add("Gastado");
                    nombresCampos.add("Vendido");
                    campoGastados.setItems(FXCollections.observableList(nombresCampos));

                    campoCodigoBarras.setText(getProductoSeleccionado().getCodigoBarras());
                    campoNombre.setText(getProductoSeleccionado().getNombreProducto());
                    campoMarca.setText(getProductoSeleccionado().getMarca());
                    campoDescripcion.setText(getProductoSeleccionado().getDescripcionProducto());
                    campoPrecio.setText(String.valueOf(getProductoSeleccionado().getPrecioProducto()));
                    campoCantidad.setText(String.valueOf(getProductoSeleccionado().getCantidadDisponible()));
                    switch (getProductoSeleccionado().getProductoGastado()) {
                        case 0:
                            campoGastados.setValue("Disponible");
                            break;
                        case 1:
                            campoGastados.setValue("Gastado");
                            break;
                        case 2:
                            campoGastados.setValue("Vendido");
                            break;
                    }
                    etiquetaGastados.setVisible(true);
                    campoGastados.setVisible(true);
                    checkboxActivo.setVisible(true);
                    checkboxActivo.setSelected(getProductoSeleccionado().isProductoActivo());
                }
            });

            crear.setOnAction(event -> {
                if (campoCodigoBarras.getText().isBlank() || campoPrecio.getText().isBlank()) {
                    Global.mostrarAlertaAdvertencia("Ni el código de barras ni el precio pueden estar vacíos.");
                }
                else {
                    boolean insercionExitosa = productosDAO.agregarProductos(new Productos(1, campoCodigoBarras.getText(), campoNombre.getText(), campoMarca.getText(), new BigDecimal(campoPrecio.getText()), campoDescripcion.getText(), Integer.parseInt(campoCantidad.getText()), 0, true));
                    if (insercionExitosa) {
                        boolean confirmado = Global.mostrarAlertaExitosa("Producto agregado con éxito.");
                        if (confirmado) {
                            vista.redirigir("Productos");
                        }
                    }
                    else {
                        Global.mostrarAlertaError("Error al dar de alta el producto.");
                    }
                }
            });
            editar.setOnAction(event -> {
                if (campoCodigoBarras.getText().isBlank() || campoPrecio.getText().isBlank()) {
                    Global.mostrarAlertaAdvertencia("Ni el código de barras ni el precio pueden estar vacíos.");
                }
                else {
                    int numGastado = -1;
                    switch (campoGastados.getValue()) {
                        case "Disponible":
                            numGastado = 0;
                            break;
                        case "Gastado":
                            numGastado = 1;
                        case "Vendido":
                            numGastado = 2;
                    }
                    boolean edicionExitosa = productosDAO.actualizarProductos(productoSeleccionado.getIdProducto(), campoCodigoBarras.getText(), campoNombre.getText(), campoMarca.getText(), new BigDecimal(campoPrecio.getText()), campoDescripcion.getText(), Integer.parseInt(campoCantidad.getText()), numGastado, checkboxActivo.isSelected());
                    if (edicionExitosa) {
                        boolean confirmado = Global.mostrarAlertaExitosa("Producto editado con éxito.");
                        if (confirmado) {
                            vista.redirigir("Productos");
                        }
                    }
                    else {
                        Global.mostrarAlertaError("Error al editar el producto.");
                    }
                }
            });

            setTablaActivos(true);
            mostrarInactivos.setOnAction(event -> {
                if (isTablaActivos()) {
                    mostrarProductosInactivos();
                    mostrarInactivos.setText("MOSTRAR ACTIVOS");
                    setTablaActivos(false);
                }
                else {
                    mostrarProductos();
                    mostrarInactivos.setText("MOSTRAR INACTIVOS");
                    setTablaActivos(true);
                }
            });
        }
    }
}
