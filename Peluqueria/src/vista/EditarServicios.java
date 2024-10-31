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
import controlador.ControladorTrabajadores;
import modelo.Servicios;
import modelo.Trabajadores;

public class EditarServicios extends JFrame {
    private JLabel idServicioLabel;
    private JTextField idServicioField;
    private JLabel precioBaseLabel;
    private JTextField precioBaseField;
    private JLabel descripcionServicioLabel;
    private JTextField descripcionServicioField;
    private JLabel servicioActivoLabel;
    private JCheckBox servicioActivoCheckBox;
    private JLabel missatgeLabel;
    private JButton guardarServicioButton;
    private JButton volverButton;
    private ControladorServicios controladorServicios;

    public EditarServicios(Servicios servicios, Integer idServicio) {
        controladorServicios = new ControladorServicios(); // Inicializar el controlador.
        setTitle("Peluqueria"); // Pon un titulo a la pagina.
        setSize(800, 600); // Configuracion del tamaño de la pantalla.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Indica que la aplicación se cerrará completamente.
        setLocationRelativeTo(null); // Centra la ventana en la pantalla.

        // Creamos un panel para agregar los componetes que quieras.
        JPanel panel = new JPanel();
        add(panel);
        posicioBotons(panel, servicios, idServicio);

        setVisible(true); 
    }

    private void posicioBotons(JPanel panel, Object servicios, Integer idServicio) {
        panel.setBackground(new Color(139, 137, 137)); // Canviar de color.
        panel.setLayout(null);


        Font nFont18 = new Font(null, Font.PLAIN, 18);


        idServicioLabel = new JLabel("ID Servicio: ");
        idServicioLabel.setBounds(50, 40, 200, 25);
        idServicioLabel.setFont(nFont18);
        panel.add(idServicioLabel);

        idServicioField = new JTextField(idServicio);
        idServicioField.setBounds(250, 40, 200, 25);
        idServicioField.setBackground(new Color(255, 255, 255)); 
        panel.add(idServicioField);


        precioBaseLabel = new JLabel("Precio Base: ");
        precioBaseLabel.setBounds(50, 80, 200, 25);
        precioBaseLabel.setFont(nFont18);
        panel.add(precioBaseLabel);

        precioBaseField = new JTextField(20);
        precioBaseField.setBounds(250, 80, 200, 25);
        precioBaseField.setBackground(new Color(255, 255, 255)); 
        panel.add(precioBaseField);


        descripcionServicioLabel = new JLabel("Descripcin del Servicio: ");
        descripcionServicioLabel.setBounds(50, 120, 200, 25);
        descripcionServicioLabel.setFont(nFont18);
        panel.add(descripcionServicioLabel);

        descripcionServicioField = new JTextField(20);
        descripcionServicioField.setBounds(250, 120, 200, 25);
        descripcionServicioField.setBackground(new Color(255, 255, 255)); 
        panel.add(descripcionServicioField);


        servicioActivoLabel = new JLabel("Habiltar / Deshabilitar: ");
        servicioActivoLabel.setBounds(50, 280, 200, 25);
        servicioActivoLabel.setFont(nFont18);
        panel.add(servicioActivoLabel);

        servicioActivoCheckBox = new JCheckBox();
        servicioActivoCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        servicioActivoCheckBox.setBounds(250, 280, 20, 25);
        servicioActivoCheckBox.setBackground(new Color(255, 255, 255));
        panel.add(servicioActivoCheckBox);


        missatgeLabel = new JLabel("");
        missatgeLabel.setBounds(50, 400, 400, 25);
        missatgeLabel.setFont(nFont18);
        missatgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(missatgeLabel);


        guardarServicioButton = new JButton("Guardar");
        guardarServicioButton.setBounds(150, 450, 200, 40);
        guardarServicioButton.setHorizontalAlignment(SwingConstants.CENTER);
        guardarServicioButton.setBackground(new Color(218, 247, 166));
        guardarServicioButton.setFont(nFont18);
        guardarServicioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarServicio();
            }
        });
        panel.getRootPane().setDefaultButton(guardarServicioButton); // Para poderlo pulsar con la tecla "INTRO".
        panel.add(guardarServicioButton);

        
        volverButton = new JButton("Volver");
        volverButton.setBounds(150, 500, 200, 40);
        volverButton.setHorizontalAlignment(SwingConstants.CENTER);
        volverButton.setBackground(new Color(240, 128, 128));
        volverButton.setFont(nFont18);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAtras((Servicios) servicios);
            }
        });
        panel.add(volverButton);


        mostrarDatosServicio(idServicio); // Para rellenar los campos que ya tiene el Trabajador.
    }

    // Metodos
    private void mostrarDatosServicio(Integer idServicio) {
        Servicios servicios = controladorServicios.buscarServicio(idServicio);

        try {
            if (servicios != null) {
                precioBaseField.setText(servicios.getPrecio_base());
                descripcionServicioField.setText(servicios.getDescripcion_servicio());
                servicioActivoCheckBox.setText(servicios.isServicio_activo());
            } else {
                missatgeLabel.setText("Trabajador no encontrado");
                missatgeLabel.setForeground(Color.BLACK);
            }
        } catch (RuntimeException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                missatgeLabel.setText("Base de datos no encontrada");
                missatgeLabel.setForeground(Color.BLACK);
            } else {
                ex.printStackTrace();
            }
        }
    }

    private void editarServicios() {
        Integer idServicio = idServicio.getText();
        String precioBaseText = precioBaseField.getText();
        BigDecimal precioBase = new BigDecimal(precioBaseText);
        String descripcionServicio = descripcionServicioField.getText();
        Boolean servicioActivo = servicioActivoCheckBox.isSelected();

        String missatge = "";
        Color colorMissatge = Color.BLUE;


        // Validar que el texto no esté vacío y sea un número válido
        if (precioBaseText != null && !precioBaseText.trim().isEmpty()) {
            try {
                // Intentar convertir el texto a BigDecimal
                precioBase = new BigDecimal(precioBaseText);
            } catch (NumberFormatException e) {
                // Si hay un error, comision ya está en 0.00

            }
        }

        
        if (idServicio.isEmpty() || precioBase.isEmpty() || descripcionServicio.isEmpty() || servicioActivo.isEmpty()) {
            missatge = "Tienes que rellenar todos los campos";
        } else {
            Servicios servicios = new Servicios();
            servicios.setId_servicio(idServicio);
            servicios.setPrecio_base(precioBase);
            servicios.setDescripcion_servicio(descripcionServicio);
            servicios.setServicio_activo(servicioActivo);


            try {
                boolean resultat = controladorServicios.modificarServicios(idServicio, precioBase, descripcionServicio, servicioActivo);

                
                if (resultat) {
                    missatge = "¡Servicio Actualizado!";
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

    private void volverAtras(Servicios servicios) {
        new GestionServicios(servicios).setVisible(true);
        dispose();
    }
}

