package org.example.perruqueria.perruqueriadreams.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private HashMap<String, String> agendaMap = new HashMap<String, String>();
    @FXML private HBox agenda;
    @FXML private VBox columnaHoras;
    @FXML private HBox columnasTrabajadores;
    @FXML private DatePicker calendario;
    @FXML private TableView<String> tabla;

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
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        calendario.setValue(fechaActual);
        calendario.setOnAction(event -> {
            LocalDate fechaSeleccionada = calendario.getValue();
            calendario.setValue(fechaSeleccionada);
            /*agendaMap.forEach((clave, valor) -> {
                String[] valoresId = clave.split("|");
                String fecha = valoresId[0];
                agendaMap.remove(clave);
                agendaMap.put(calendario.getValue() + valoresId[1] + valoresId[2], "");
            });
            agendaMap.forEach((clave, valor) -> {
                System.out.println("Clave: " + clave + " " + "Valor: " + valor);
            });*/

        });

        String[] horas = {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00"};

        List<Trabajadores> trabajadores = trabajadoresDAO.mostrarTrabajadores();


        for (String hora : horas) {
            Label labelHora = new Label(hora);
            columnaHoras.getChildren().add(labelHora);
        }
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
                inputTexto.focusedProperty().addListener((observable, antiguoValor, nuevoValor) -> {
                    if (!inputTexto.getText().isEmpty()) {
                        if (antiguoValor) {
                            String nuevoTexto = inputTexto.getText();
                            System.out.println(nuevoTexto + " " + "ID: " + inputTexto.getId());
                            String[] datosId = inputTexto.getId().split("/");
                            for (String dato : datosId) {
                                System.out.println(dato);
                            }
                            String fecha = datosId[0];
                            String horaId = datosId[1];
                            Integer idTrabajador = Integer.parseInt(datosId[2]);
                            boolean existeRegistro = agendaDAO.encontrarDia(fecha, horaId, idTrabajador);
                            if (existeRegistro) {
                                boolean edicionExitosa = agendaDAO.actualizarAgenda(fecha, horaId, idTrabajador, inputTexto.getText());
                            }
                            else {
                                boolean insercionExitosa = agendaDAO.agregarAgenda(new Agenda(0, fecha, horaId, inputTexto.getText(), idTrabajador));
                            }
                        }
                    }
                });
                agendaMap.put(inputTexto.getId(), inputTexto.getText());
                inputTexto.setPrefWidth(200);
                columnaNueva.getChildren().add(inputTexto);
            }
            columnaNueva.setAlignment(Pos.CENTER);
            agenda.getChildren().add(columnaNueva);
        }

        agendaMap.forEach((clave, valor) -> {
            System.out.println("Clave: " + clave + " " + "Valor: " + valor);
        });
    }

    public void mostrarHoras() {
        /*String[] horas = {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00"};
        for (String hora : horas) {
            Label labelHora = new Label();
            labelHora.setText(hora);
        }

        TrabajadoresDAO trabajadoresDAO = new TrabajadoresDAO();
        List<Trabajadores> trabajadores = trabajadoresDAO.mostrarTrabajadores();
        int numeroTrabajadores = trabajadores.size();
        List<Agenda> agendas = agendaDAO.mostrarAgenda();

        ObservableList<Agenda> datosAgenda = FXCollections.observableList(agendas);
        for (Agenda agenda : datosAgenda) {
            TableColumn<Agenda, Integer> columnaTrabajador = new TableColumn<>("idTrabajador");
            columnaTrabajador.setCellValueFactory(new PropertyValueFactory<>("texto"));
            Integer id = agenda.getIdTrabajador();
            Trabajadores trabajador = trabajadoresDAO.encontrarTrabajador(id);
            columnaTrabajador.setText(trabajador.getNombreTrabajador());
            tablaAgenda.getColumns().add(columnaTrabajador);
        }
        hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        tablaAgenda.setItems(datosAgenda);*/
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (agenda != null) {
            generarAgenda();
        }
    }
}
