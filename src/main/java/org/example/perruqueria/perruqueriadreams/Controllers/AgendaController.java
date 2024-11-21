//package org.example.perruqueria.perruqueriadreams.Controllers;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import javafx.fxml.Initializable;
//
//import com.calendarfx.model.Calendar;
//import com.calendarfx.model.CalendarSource;
//import com.calendarfx.view.CalendarView;
//import javafx.application.Platform;
//import javafx.fxml.FXML;
//import javafx.scene.layout.Pane;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//public class AgendaController {
//
//    @FXML
//    private Pane panelAgenda;
//
//    public void initialize() {
//        if(panelAgenda != null){
//            System.out.println("hola");
//            // Crear el CalendarView
//            CalendarView calendarView = new CalendarView();
//
//            // Configurar calendarios
//            Calendar birthdays = new Calendar("Birthdays");
//            Calendar holidays = new Calendar("Holidays");
//
//            birthdays.setStyle(Calendar.Style.STYLE1);
//            holidays.setStyle(Calendar.Style.STYLE2);
//
//            // Crear una fuente de calendarios
//            CalendarSource calendarSource = new CalendarSource("My Calendars");
//            calendarSource.getCalendars().addAll(birthdays, holidays);
//
//            calendarView.getCalendarSources().add(calendarSource);
//            calendarView.setRequestedTime(LocalTime.now());
//
//            // Actualizar la hora en segundo plano
//            Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
//                @Override
//                public void run() {
//                    while (true) {
//                        Platform.runLater(() -> {
//                            calendarView.setToday(LocalDate.now());
//                            calendarView.setTime(LocalTime.now());
//                        });
//
//                        try {
//                            Thread.sleep(10000); // Actualizar cada 10 segundos
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            };
//
//            updateTimeThread.setPriority(Thread.MIN_PRIORITY);
//            updateTimeThread.setDaemon(true);
//            updateTimeThread.start();
//
//            // Agregar el CalendarView al panelAgenda
//            panelAgenda.getChildren().add(calendarView);
//
//
//        }
//    }
//}
