package org.example.perruqueria.perruqueriadreams.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.perruqueria.perruqueriadreams.Models.Agenda;
import org.example.perruqueria.perruqueriadreams.Models.AgendaDAO;
import org.example.perruqueria.perruqueriadreams.Models.Trabajadores;
import org.example.perruqueria.perruqueriadreams.Models.TrabajadoresDAO;

import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ControladorAgenda implements Initializable {
    private AgendaDAO agendaDAO = new AgendaDAO();
    private TrabajadoresDAO trabajadoresDAO = new TrabajadoresDAO();
    private ControladorVentana vista = new ControladorVentana();
    private HashMap<String, String> agendaMap = new HashMap<String, String>();
    @FXML private HBox agenda;
    @FXML private DatePicker calendario;
    @FXML private TableView<String> tabla;
    @FXML private ImageView siguienteDia;
    @FXML private ImageView anteriorDia;
    @FXML private Button btnClientes;
    @FXML private Button btnCobros;

    public HashMap<String, String> getAgendaMap() {
        return agendaMap;
    }

    public void setAgendaMap(HashMap<String, String> agendaMap) {
        this.agendaMap = agendaMap;
    }

    public void mostrarTabla() {
        TableColumn<String, String> columna1 = new TableColumn<>("Columna1");
        TableColumn<String, String> columna2 = new TableColumn<>("Columna2");
        TableColumn<String, String> columna3 = new TableColumn<>("Columna3");
        tabla.getColumns().addAll(columna1, columna2, columna3);
        int[] valoresColumna1 = new int[5];
        int suma = 0;
        for (int i = 0; i < valoresColumna1.length; i++) {
            suma += 1;
            valoresColumna1[i] = suma;
        }
        for (int valor : valoresColumna1) {
            System.out.println(valor);
        }
        columna1.setCellValueFactory(new PropertyValueFactory<>(""));
    }

    public void generarAgenda() {

        String[] horas = {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00"};

        List<Trabajadores> trabajadores = trabajadoresDAO.mostrarTrabajadores();

        VBox columnaHoras = new VBox();
        columnaHoras.setPrefWidth(100);
        columnaHoras.setStyle("-fx-font-size: 17px");
        columnaHoras.getStyleClass().add("negrita");
        columnaHoras.setAlignment(Pos.TOP_CENTER);
        columnaHoras.setSpacing(13);
        columnaHoras.setPadding(new Insets(45, 0, 0, 0));
        for (String hora : horas) {
            Label labelHora = new Label(hora);
            columnaHoras.getChildren().add(labelHora);
        }
        agenda.getChildren().add(columnaHoras);
        for (Trabajadores trabajador : trabajadores) {
            Label nombreTrabajador = new Label(trabajador.getNombreTrabajador());
            nombreTrabajador.getStyleClass().add("negrita");
            nombreTrabajador.setStyle("-fx-font-size: 20px");
            nombreTrabajador.setPadding(new Insets(0, 0, 8, 0));
            VBox columnaNueva = new VBox();
            columnaNueva.getChildren().add(nombreTrabajador);
            for (String hora : horas) {
                TextArea inputTexto = new TextArea();
                inputTexto.setId(calendario.getValue() + "/" + hora + "/" + trabajador.getIdTrabajador());
                String[] datosId = inputTexto.getId().split("/");
                for (String dato : datosId) {
                    System.out.println(dato);
                }
                String fecha = datosId[0];
                String horaId = datosId[1];
                Integer idTrabajador = Integer.parseInt(datosId[2]);
                String texto = agendaDAO.mostrarAgenda(fecha, horaId, idTrabajador); //Obtenemos el texto de la cita para ese dia a esa hora y para ese trabajador
                if (texto != null) { //Comprobamos que la consulta haya obtenido un registro, ya que puede ser que el registro para ese campo aún no esté creado
                    inputTexto.setText(texto);
                    if (inputTexto.getText().isEmpty()) { //Si está vacío el campo, le ponemos un fondo verde, si no, rojo
                        inputTexto.getStyleClass().add("fondoVerde");
                    }
                    else {
                        inputTexto.getStyleClass().add("fondoRojo");
                    }
                }
                inputTexto.focusedProperty().addListener((observable, antiguoValor, nuevoValor) -> {
                    if (antiguoValor && !inputTexto.getText().isEmpty()) { //Si se han hecho cambios y el contenido no se ha dejado vacío
                        inputTexto.getStyleClass().remove("fondoVerde");
                        inputTexto.getStyleClass().add("fondoRojo");
                        System.out.println(inputTexto.getText() + " " + "ID: " + inputTexto.getId());
                        boolean existeRegistro = agendaDAO.encontrarDia(fecha, horaId, idTrabajador); //Verificar si ya existe el registro para ese dia, hora y trabajador
                        if (existeRegistro) {
                            boolean edicionExitosa = agendaDAO.actualizarAgenda(fecha, horaId, idTrabajador, inputTexto.getText());
                        }
                        else {
                            boolean insercionExitosa = agendaDAO.agregarAgenda(new Agenda(0, fecha, horaId, inputTexto.getText(), idTrabajador));
                        }
                    } else if (antiguoValor && inputTexto.getText().isEmpty()) { //Si se han hecho cambios pero se ha dejado el campo vacío
                        inputTexto.getStyleClass().remove("fondoRojo");
                        inputTexto.getStyleClass().add("fondoVerde");
                        boolean eliminacionExitosa = agendaDAO.eliminarAgenda(fecha, horaId, idTrabajador);
                    }

                });
                agendaMap.put(inputTexto.getId(), inputTexto.getText());
                inputTexto.setPrefWidth(200);
                columnaNueva.getChildren().add(inputTexto); //Metemos ese input a la columna del trabajador actual
            }
            columnaNueva.setAlignment(Pos.CENTER);
            agenda.getChildren().add(columnaNueva);
        }

        agendaMap.forEach((clave, valor) -> {
            System.out.println("Clave: " + clave + " " + "Valor: " + valor);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (agenda != null) {
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            calendario.setValue(fechaActual);
            calendario.setOnAction(event -> {
                LocalDate fechaSeleccionada = calendario.getValue();
                calendario.setValue(fechaSeleccionada);
                agenda.getChildren().clear(); //Si se ha cambiado el dia en el calendario, borramos todos los elementos de la agenda y la volvemos a generar
                generarAgenda();
            });
            siguienteDia.setOnMouseClicked((MouseEvent event) -> {
                calendario.setValue(calendario.getValue().plusDays(1));
            });
            anteriorDia.setOnMouseClicked((MouseEvent event) -> {
                calendario.setValue(calendario.getValue().minusDays(1));
            });
            btnClientes.setOnAction(event -> {
                vista.redirigir("Clientes");
            });
            btnCobros.setOnAction(event -> {
                vista.redirigir("Cobros");
            });
            generarAgenda(); //La generamos cuando se haya cargado la vista
        }
    }
}
