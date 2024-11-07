package vista.servicios;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.ControladorServicios;
import modelo.Servicios;
import modelo.Trabajadores;

public class CrearServicios extends JFrame {
    private JLabel precioBaseLabel;
    private JTextField precioBaseField;
    private JLabel descripcionServicioLabel;
    private JTextField descripcionServicioField;
    private JButton agregarServicioButton;
    private JLabel missatgeLabel;
    private JButton volverButton;
    private ControladorServicios controladorServicios;

    private Trabajadores trabajadores;
    private Servicios servicios;

    public CrearServicios(Trabajadores trabajadores) {
        this.trabajadores =  trabajadores;
        this.servicios = new Servicios();

        controladorServicios = new ControladorServicios(); // Inicializar el controlador.
        setTitle("Peluqueria"); // Pon un titulo a la pagina.
        setSize(800, 600); // Configuracion del tamaño de la pantalla.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Indica que la aplicación se cerrará completamente.
        setLocationRelativeTo(null); // Centra la ventana en la pantalla.

        // Creamos un panel para agregar los componetes que quieras.
        JPanel panel = new JPanel();
        add(panel);
        posicioBotons(panel, servicios);

        setVisible(true); 
    }

    private void posicioBotons(JPanel panel, Object servicios) {
        panel.setBackground(new Color(139, 137, 137)); // Canviar de color.
        panel.setLayout(null);


        Font nFont18 = new Font(null, Font.PLAIN, 18);


        precioBaseLabel = new JLabel("Precio Base: ");
        precioBaseLabel.setBounds(300, 80, 200, 25);
        precioBaseLabel.setFont(nFont18);
        panel.add(precioBaseLabel);

        precioBaseField = new JTextField(20);
        precioBaseField.setBounds(500, 80, 200, 25);
        precioBaseField.setBackground(new Color(255, 255, 255)); 
        panel.add(precioBaseField);


        descripcionServicioLabel = new JLabel("Descripción del Servicio: ");
        descripcionServicioLabel.setBounds(300, 120, 200, 25);
        descripcionServicioLabel.setFont(nFont18);
        panel.add(descripcionServicioLabel);

        descripcionServicioField = new JTextField(20);
        descripcionServicioField.setBounds(500, 120, 200, 25);
        descripcionServicioField.setBackground(new Color(255, 255, 255)); 
        panel.add(descripcionServicioField);



        missatgeLabel = new JLabel("");
        missatgeLabel.setBounds(500, 400, 200, 25);
        missatgeLabel.setFont(nFont18);
        missatgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(missatgeLabel);

        agregarServicioButton = new JButton("Crear Servicio");
        agregarServicioButton.setBounds(500, 500, 200, 40);
        agregarServicioButton.setHorizontalAlignment(SwingConstants.CENTER);
        agregarServicioButton.setBackground(new Color(206, 63, 86));
        agregarServicioButton.setFont(nFont18);
        agregarServicioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearServicio();
            }
        });
        panel.getRootPane().setDefaultButton(agregarServicioButton); // Para poderlo pulsar con la tecla "INTRO".
        panel.add(agregarServicioButton);

        
        volverButton = new JButton("Volver");
        volverButton.setBounds(50, 20, 200, 40);
        volverButton.setHorizontalAlignment(SwingConstants.CENTER);
        volverButton.setBackground(new Color(105, 123, 86));
        volverButton.setFont(nFont18);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAtras((Servicios) servicios);
            }
        });
        panel.add(volverButton);
    }

    // Metodos
    private  void volverAtras(Servicios servicios) {
        new GestionServicios(trabajadores, servicios).setVisible(true);
        dispose();
    }

    private void crearServicio() {
        BigDecimal precioBase = new BigDecimal(precioBaseField.getText());
        String descripcionServicio = descripcionServicioField.getText();

        String missatge = "";
        Color colorMissatge = Color.BLUE;
        
        if ( precioBaseField.getText().isEmpty() || descripcionServicioField.getText().isEmpty()) {
            missatge = "Tienes que rellenar todos los campos";
        } else {
            Servicios servicios = new Servicios(
            null,
            precioBase,
            descripcionServicio,
            true);

            try {
                boolean resultat = controladorServicios.anyadirServicios(servicios);
                
                if (resultat) {
                    missatge = "¡Servicio Creado!";
                    colorMissatge = Color.GREEN;
                }
            } catch (RuntimeException ex) {
                if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                    missatge = "Base de datos no encontrada";
                    colorMissatge = Color.BLACK;
                } else {
                    ex.printStackTrace();
                }
            }
        }if (missatgeLabel != null) {
            missatgeLabel.setText(missatge);
            missatgeLabel.setForeground(colorMissatge);
        } else {
            System.out.println("Error: missatgeLabel es nulo");
        }
    }
}
