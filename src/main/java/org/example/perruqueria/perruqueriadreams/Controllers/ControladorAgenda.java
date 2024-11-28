package org.example.perruqueria.perruqueriadreams.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.perruqueria.perruqueriadreams.Models.Agenda;
import org.example.perruqueria.perruqueriadreams.Models.AgendaDAO;
import org.example.perruqueria.perruqueriadreams.Models.Trabajadores;
import org.example.perruqueria.perruqueriadreams.Models.TrabajadoresDAO;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorAgenda implements Initializable {
    private AgendaDAO agendaDAO = new AgendaDAO();
    @FXML private TableView<Agenda> tablaAgenda;
    @FXML private TableColumn<Agenda, String> hora;
    @FXML private Label dia;
    @FXML private TableView<String> tabla;

    public void mostrarHoras() {
        /*String[] horas = {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00"};
        for (String hora : horas) {
            Label labelHora = new Label();
            labelHora.setText(hora);
        }*/

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
        tablaAgenda.setItems(datosAgenda);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (tablaAgenda != null) {
            mostrarHoras();
        }
    }
}
