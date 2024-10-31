package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.ControladorServicios;
import modelo.Servicios;

public class CrearServicios extends JFrame {
    private JLabel idServicioLabel;
    private JTextField idServicioField;
    private JLabel precioBaseLabel;
    private JTextField precioBaseField;
    private JLabel descripcionServicioLabel;
    private JTextField descripcionServicioField;
    private JLabel servicioActivoLabel;
    private JCheckBox servicioActivoCheckBox;
    private JButton agregarServicioButton;
    private JLabel missatgeLabel;
    private JButton volverButton;
    private ControladorServicios controladorServicios;

    public CrearServicios(Servicios servicios) {
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


        idServicioLabel = new JLabel("ID Servicio: ");
        idServicioLabel.setBounds(300, 40, 200, 25);
        idServicioLabel.setFont(nFont18);
        idServicioLabel.add(idServicioLabel);

        idServicioField = new JTextField(20);
        idServicioField.setBounds(500, 40, 200, 25);
        idServicioField.setBackground(new Color(255, 255, 255)); 
        panel.add(idServicioField);


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


        servicioActivoLabel = new JLabel("Servicio Activo: ");
        servicioActivoLabel.setBounds(300, 320, 200, 25);
        servicioActivoLabel.setFont(nFont18);
        panel.add(servicioActivoLabel);

        servicioActivoCheckBox = new JCheckBox();
        servicioActivoCheckBox.setBounds(500, 320, 200, 25);
        servicioActivoCheckBox.setBackground(new Color(255, 255, 255)); 
        panel.add(servicioActivoCheckBox);



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
        new GestionServicios(servicios).setVisible(true);
        dispose();
    }

    private void crearServicio() {
        Integer idServicio = idServicioField.getText();
        BigDecimal precioBase = precioBaseField.getText();
        String descripcionServicio = descripcionServicioField.getText();
        Boolean servicioActivo = servicioActivoCheckBox.isSelected(); // Jefe / Emlpeado;

        String missatge = "";
        Color colorMissatge = Color.BLUE;
        
        if (idServicio.isEmpty() || precioBase.isEmpty() || descripcionServicio.isEmpty()) {
            missatge = "Tienes que rellenar todos los campos";
        } else {
            Servicios servicios = new Servicios();
            servicios.setId_servicio(idServicio);
            servicios.setPrecio_base(precioBase);
            servicios.setDescripcion_servicio(descripcionServicio);
            servicios.setServicio_activo(servicioActivo);


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
        }
        missatgeLabel.setText(missatge);
        missatgeLabel.setForeground(colorMissatge);
    }
}
