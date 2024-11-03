package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.ControladorClientes;
import modelo.Clientes;
import modelo.Trabajadores;

public class CrearClientes extends JFrame {
    private JLabel nombreLabel;
    private JTextField nombreField;
    private JLabel apellidoLabel;
    private JTextField apellidoField;
    private JLabel correoLabel;
    private JTextField correoField;
    private JLabel telefonoLabel;
    private JTextField telefonoField;
    private JLabel descripcionLabel;
    private JLabel proteccionDatosLabel;
    private JCheckBox proteccionDatosCheckBox;
    private JTextField descripcionField;
    private JLabel missatgeLabel;
    private JButton agregarClienteButton;
    private JButton volverButton;
    private ControladorClientes controladorClientes;

    private Trabajadores trabajadores;
    private Clientes clientes;

    public CrearClientes(Trabajadores trabajadores) {
        this.trabajadores =  trabajadores;
        this.clientes = new Clientes();

        controladorClientes = new ControladorClientes(); // Inicializar el controlador.
        setTitle("Peluqueria"); // Pon un titulo a la pagina.
        setSize(800, 600); // Configuracion del tamaño de la pantalla.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Indica que la aplicación se cerrará completamente.
        setLocationRelativeTo(null); // Centra la ventana en la pantalla.

        // Creamos un panel para agregar los componetes que quieras.
        JPanel panel = new JPanel();
        add(panel);
        posicioBotons(panel, clientes);

        setVisible(true); 
    }

    private void posicioBotons(JPanel panel, Object clientes) {
        panel.setBackground(new Color(139, 137, 137)); // Canviar de color.
        panel.setLayout(null);


        Font nFont18 = new Font(null, Font.PLAIN, 18);




        nombreLabel = new JLabel("Nombre: ");
        nombreLabel.setBounds(300, 40, 200, 25);
        nombreLabel.setFont(nFont18);
        panel.add(nombreLabel);

        nombreField = new JTextField(20);
        nombreField.setBounds(500, 40, 200, 25);
        nombreField.setBackground(new Color(255, 255, 255)); 
        panel.add(nombreField);


        apellidoLabel = new JLabel("Apellido: ");
        apellidoLabel.setBounds(300, 80, 200, 25);
        apellidoLabel.setFont(nFont18);
        panel.add(apellidoLabel);

        apellidoField = new JTextField(20);
        apellidoField.setBounds(500, 80, 200, 25);
        apellidoField.setBackground(new Color(255, 255, 255)); 
        panel.add(apellidoField);


        correoLabel = new JLabel("Correo: ");
        correoLabel.setBounds(300, 120, 200, 25);
        correoLabel.setFont(nFont18);
        panel.add(correoLabel);

        correoField = new JTextField(20);
        correoField.setBounds(500, 120, 200, 25);
        correoField.setBackground(new Color(255, 255, 255)); 
        panel.add(correoField);


        telefonoLabel = new JLabel("Teléfono: ");
        telefonoLabel.setBounds(300, 160, 200, 25);
        telefonoLabel.setFont(nFont18);
        panel.add(telefonoLabel);

        telefonoField  = new JTextField(20);
        telefonoField.setBounds(500, 160, 200, 25);
        telefonoField.setBackground(new Color(255, 255, 255)); 
        panel.add(telefonoField);

        descripcionLabel = new JLabel("Descripción: ");
        descripcionLabel.setBounds(300, 200, 200, 25);
        descripcionLabel.setFont(nFont18);
        panel.add(descripcionLabel);

        descripcionField = new JTextField(20);
        descripcionField.setBounds(500, 200, 200, 25);
        descripcionField.setBackground(new Color(255, 255, 255)); 
        panel.add(descripcionField);

        proteccionDatosLabel = new JLabel("Protección de Datos: ");
        proteccionDatosLabel.setBounds(300, 240, 200, 25);
        proteccionDatosLabel.setFont(nFont18);
        panel.add(proteccionDatosLabel);

        proteccionDatosCheckBox = new JCheckBox();
        proteccionDatosCheckBox.setBounds(500, 240, 200, 25);
        proteccionDatosCheckBox.setBackground(new Color(255, 255, 255)); 
        panel.add(proteccionDatosCheckBox);


        missatgeLabel = new JLabel("");
        missatgeLabel.setBounds(500, 400, 200, 25);
        missatgeLabel.setFont(nFont18);
        missatgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(missatgeLabel);

        agregarClienteButton = new JButton("Crear Cliente");
        agregarClienteButton.setBounds(500, 500, 200, 40);
        agregarClienteButton.setHorizontalAlignment(SwingConstants.CENTER);
        agregarClienteButton.setBackground(new Color(206, 63, 86));
        agregarClienteButton.setFont(nFont18);
        agregarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCliente();
            }
        });
        panel.getRootPane().setDefaultButton(agregarClienteButton); // Para poderlo pulsar con la tecla "INTRO".
        panel.add(agregarClienteButton);

        
        volverButton = new JButton("Volver");
        volverButton.setBounds(50, 20, 200, 40);
        volverButton.setHorizontalAlignment(SwingConstants.CENTER);
        volverButton.setBackground(new Color(105, 123, 86));
        volverButton.setFont(nFont18);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAtras((Clientes) clientes);
            }
        });
        panel.add(volverButton);
    }

    // Metodos
    private  void volverAtras(Clientes clientes) {
        new GestionClientes(trabajadores, clientes).setVisible(true);
        dispose();
    }

    private void crearCliente() {
        String nombreCliente = nombreField.getText();
        String apellidoCliente =  apellidoField.getText();
        String correoCliente = correoField.getText();
        String telefonoCliente = telefonoField.getText();
        String descripcionCliente = descripcionField.getText();
        Boolean proteccionDatos = proteccionDatosCheckBox.isSelected();
        

        String missatge = "";
        Color colorMissatge = Color.BLUE;
        
        if (nombreField.getText().isEmpty() || telefonoField.getText().isEmpty()) {


            missatge = "Tienes que rellenar todos los campos";
        } else {
            Clientes clientes = new Clientes();
            clientes.setNombre_cliente(nombreCliente);
            clientes.setApellido_cliente(apellidoCliente);
            clientes.setCorreo_cliente(correoCliente);
            clientes.setTelefono_cliente(telefonoCliente);
            clientes.setDescripcion_cliente(descripcionCliente);
            clientes.setProteccion_datos(proteccionDatos);


            try {
                boolean resultat = controladorClientes.anyadirClientes(clientes);
                
                if (resultat) {
                    missatge = "Cliente Creado!";
                    colorMissatge = Color.GREEN;
                }
                else {
                    missatge = "Error al crear el cliente";
                    colorMissatge = Color.RED;
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

