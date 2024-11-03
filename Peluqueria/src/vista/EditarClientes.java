package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.ControladorClientes;
import modelo.Clientes;
import modelo.Trabajadores;

public class EditarClientes extends JFrame {
    private Integer idCliente;
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
    private JButton guardarClienteButton;
    private JButton volverButton;
    private ControladorClientes controladorClientes;

    private Trabajadores trabajadores;
    private Clientes clientes;


    public EditarClientes(Trabajadores trabajadores, Integer idCliente) {
        this.trabajadores = trabajadores;
        this.clientes = new Clientes();
        this.idCliente = idCliente;
        
        controladorClientes = new ControladorClientes(); // Inicializar el controlador.
        setTitle("Peluqueria"); // Pon un titulo a la pagina.
        setSize(800, 600); // Configuracion del tamaño de la pantalla.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Indica que la aplicación se cerrará completamente.
        setLocationRelativeTo(null); // Centra la ventana en la pantalla.

        // Creamos un panel para agregar los componetes que quieras.
        JPanel panel = new JPanel();
        add(panel);
        posicioBotons(panel, clientes, idCliente);

        setVisible(true); 
    }

    private void posicioBotons(JPanel panel, Object clientes, Integer idCliente) {
        panel.setBackground(new Color(139, 137, 137)); // Canviar de color.
        panel.setLayout(null);


        Font nFont18 = new Font(null, Font.PLAIN, 18);


        nombreLabel = new JLabel("Nombre: ");
        nombreLabel.setBounds(50, 80, 200, 25);
        nombreLabel.setFont(nFont18);
        panel.add(nombreLabel);

        nombreField = new JTextField(20);
        nombreField.setBounds(250, 80, 200, 25);
        nombreField.setBackground(new Color(255, 255, 255)); 
        panel.add(nombreField);


        apellidoLabel = new JLabel("Apellido: ");
        apellidoLabel.setBounds(50, 120, 200, 25);
        apellidoLabel.setFont(nFont18);
        panel.add(apellidoLabel);

        apellidoField = new JTextField(20);
        apellidoField.setBounds(250, 120, 200, 25);
        apellidoField.setBackground(new Color(255, 255, 255)); 
        panel.add(apellidoField);

        correoLabel = new JLabel("Correo: ");
        correoLabel.setBounds(50, 160, 200, 25);
        correoLabel.setFont(nFont18);
        panel.add(correoLabel);

        correoField = new JTextField(20);
        correoField.setBounds(250, 160, 200, 25);
        correoField.setBackground(new Color(255, 255, 255)); 
        panel.add(correoField);
        

        telefonoLabel = new JLabel("Teléfono: ");
        telefonoLabel.setBounds(50, 200, 200, 25);
        telefonoLabel.setFont(nFont18);
        panel.add(telefonoLabel);

        telefonoField = new JTextField(20);
        telefonoField.setBounds(250, 200, 200, 25);
        telefonoField.setBackground(new Color(255, 255, 255)); 
        panel.add(telefonoField);


        descripcionLabel = new JLabel("Descripción: ");
        descripcionLabel.setBounds(50, 240, 200, 25);
        descripcionLabel.setFont(nFont18);
        panel.add(descripcionLabel);

        descripcionField = new JTextField(20);
        descripcionField.setBounds(250, 240, 200, 25);
        descripcionField.setBackground(new Color(255, 255, 255)); 
        panel.add(descripcionField);


        proteccionDatosLabel = new JLabel("Protección de datos: ");
        proteccionDatosLabel.setBounds(50, 280, 200, 25);
        proteccionDatosLabel.setFont(nFont18);
        panel.add(proteccionDatosLabel);

        proteccionDatosCheckBox = new JCheckBox();
        proteccionDatosCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        proteccionDatosCheckBox.setBounds(250, 280, 20, 25);
        proteccionDatosCheckBox.setBackground(new Color(255, 255, 255));
        panel.add(proteccionDatosCheckBox);


        missatgeLabel = new JLabel("");
        missatgeLabel.setBounds(50, 400, 400, 25);
        missatgeLabel.setFont(nFont18);
        missatgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(missatgeLabel);


        guardarClienteButton = new JButton("Guardar");
        guardarClienteButton.setBounds(150, 450, 200, 40);
        guardarClienteButton.setHorizontalAlignment(SwingConstants.CENTER);
        guardarClienteButton.setBackground(new Color(218, 247, 166));
        guardarClienteButton.setFont(nFont18);
        guardarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarClientes();
            }
        });
        panel.getRootPane().setDefaultButton(guardarClienteButton); // Para poderlo pulsar con la tecla "INTRO".
        panel.add(guardarClienteButton);

        
        volverButton = new JButton("Volver");
        volverButton.setBounds(150, 500, 200, 40);
        volverButton.setHorizontalAlignment(SwingConstants.CENTER);
        volverButton.setBackground(new Color(240, 128, 128));
        volverButton.setFont(nFont18);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAtras();
            }
        });
        panel.add(volverButton);


        mostrarDatosCliente(idCliente); // Para rellenar los campos que ya tiene el Trabajador.
    }

    // Metodos
    private void mostrarDatosCliente(Integer idCliente) {
        Clientes clientes = controladorClientes.buscarCliente(idCliente);

        try {
            if (clientes != null) {
                nombreField.setText(clientes.getNombre_cliente());
                apellidoField.setText(clientes.getApellido_cliente());
                correoField.setText(clientes.getCorreo_cliente());
                telefonoField.setText(clientes.getTelefono_cliente());
                proteccionDatosCheckBox.setSelected(clientes.isProteccion_datos());
                descripcionField.setText(clientes.getDescripcion_cliente());
            } else {
                missatgeLabel.setText("Cliente no encontrado");
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

    private void editarClientes() {
        String nombreCliente =  nombreField.getText();
        String apellidoCliente = apellidoField.getText();
        String correoCliente = correoField.getText();
        String telefonoCliente = telefonoField.getText();
        Boolean proteccionDatos = proteccionDatosCheckBox.isSelected();
        String descripcionCliente = descripcionField.getText();

        String missatge = "";
        Color colorMissatge = Color.BLUE;



        
        if (nombreCliente.isEmpty() || telefonoCliente.isEmpty()) {

            missatge = "Tienes que rellenar todos los campos";
        } else {


            try {
                boolean resultat = controladorClientes.modificarClientes(
                    this.idCliente,
                    nombreCliente,
                    apellidoCliente,
                    correoCliente,
                    telefonoCliente,
                    proteccionDatos,
                    descripcionCliente
                    
                );
                
                if (resultat) {
                    missatge = "Cliente Actualizado!";
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

    private void volverAtras() {
        new GestionClientes(trabajadores, clientes).setVisible(true);
        dispose();
    }
}






