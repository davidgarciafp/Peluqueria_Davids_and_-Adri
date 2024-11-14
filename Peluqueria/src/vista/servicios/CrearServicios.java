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

    public CrearServicios(Trabajadores trabajadores) {
        controladorServicios = new ControladorServicios(); // Inicializar el controlador.
        setTitle("Peluqueria"); // Pon un titulo a la pagina.
        setSize(800, 600); // Configuracion del tamaño de la pantalla.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Indica que la aplicación se cerrará completamente.
        setLocationRelativeTo(null); // Centra la ventana en la pantalla.

        // Creamos un panel para agregar los componetes que quieras.
        JPanel panel = new JPanel();
        add(panel);
        posicioBotons(panel, trabajadores);

        setVisible(true); 
    }

    private void posicioBotons(JPanel panel, Trabajadores trabajadores) {
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
        agregarServicioButton.setBounds(150, 450, 200, 40);
        agregarServicioButton.setHorizontalAlignment(SwingConstants.CENTER);
        agregarServicioButton.setBackground(new Color(218, 247, 166));
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
        volverButton.setBounds(150, 500, 200, 40);
        volverButton.setHorizontalAlignment(SwingConstants.CENTER);
        volverButton.setBackground(new Color(240, 128, 128));
        volverButton.setFont(nFont18);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAtras((Trabajadores) trabajadores);
            }
        });
        panel.add(volverButton);
    }

    // Metodos
    private void volverAtras(Trabajadores trabajadores) {
        new GestionServicios(trabajadores).setVisible(true);
        dispose();
    }

    private void crearServicio() {
        String precioBaseText = precioBaseField.getText();
        String descripcionServicio = descripcionServicioField.getText();

        String missatge = "";
        Color colorMissatge = Color.BLUE;
        
        if (descripcionServicio.isEmpty()) {
            missatge = "Tienes que rellenar todos los campos";
        } else if (!precioBaseText.matches("^-?\\d+(\\.\\d+)?$")) {
            missatge = "El precio base tiene que ser X.XX";
            colorMissatge = new Color(240, 128, 128);
        } else {
            BigDecimal precioBase = new BigDecimal(precioBaseText);

            Servicios servicio = new Servicios();
            servicio.setPrecio_base(precioBase);
            servicio.setDescripcion_servicio(descripcionServicio);
            servicio.setServicio_activo(true);

            try {
                boolean resultat = controladorServicios.anyadirServicios(servicio);

                if (resultat) {
                    missatge = "Servicio Creado!";
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
        }
        missatgeLabel.setText(missatge);
        missatgeLabel.setForeground(colorMissatge);
    }
}