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

import controlador.ControladorTrabajadores;
import modelo.Trabajadores;

public class EditarTrabajadores extends JFrame {
    private Integer idTrabajador;
    private JLabel nombre_trabajadorLabel;
    private JTextField nombre_trabajadorField;
    private JLabel apellido_trabajadorLabel;
    private JTextField apellido_trabajadorField;
    private JLabel correo_trabajadorLabel;
    private JTextField correo_trabajadorField;
    private JLabel telefono_trabajadorLabel;
    private JTextField telefono_trabajadorField;
    private JLabel contrasenaLabel;
    private JTextField contrasenaField;
    private JLabel trabajador_activoLabel;
    private JCheckBox trabajador_activoCheckBox;
    private JLabel tipo_trabajadorLabel;
    private JCheckBox tipo_trabajadorCheckBox;
    private JLabel comisionProductoLabel;
    private JTextField comisionProductoField;
    private JLabel comisionServicioLabel;
    private JTextField comisionServicioField;
    private JLabel missatgeLabel;
    private JButton guardarTrabajadorButton;
    private JButton volverButton;
    private ControladorTrabajadores controladorTrabajadores;
    private Trabajadores trabajadores;

    public EditarTrabajadores(Trabajadores trabajadores,Integer idTrabajador) {
        this.trabajadores = trabajadores;
        this.idTrabajador = idTrabajador;
        controladorTrabajadores = new ControladorTrabajadores(); // Inicializar el controlador.
        setTitle("Peluqueria"); // Pon un titulo a la pagina.
        setSize(800, 600); // Configuracion del tamaño de la pantalla.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Indica que la aplicación se cerrará completamente.
        setLocationRelativeTo(null); // Centra la ventana en la pantalla.

        // Creamos un panel para agregar los componetes que quieras.
        JPanel panel = new JPanel();
        add(panel);
        posicioBotons(panel, trabajadores, idTrabajador);

        setVisible(true); 
    }

    private void posicioBotons(JPanel panel, Object trabajadores, Integer idTrabajador) {
        panel.setBackground(new Color(139, 137, 137)); // Canviar de color.
        panel.setLayout(null);


        Font nFont18 = new Font(null, Font.PLAIN, 18);

        nombre_trabajadorLabel = new JLabel("Nombre Trabajador: ");
        nombre_trabajadorLabel.setBounds(50, 80, 200, 25);
        nombre_trabajadorLabel.setFont(nFont18);
        panel.add(nombre_trabajadorLabel);

        nombre_trabajadorField = new JTextField(20);
        nombre_trabajadorField.setBounds(250, 80, 200, 25);
        nombre_trabajadorField.setBackground(new Color(255, 255, 255)); 
        panel.add(nombre_trabajadorField);


        apellido_trabajadorLabel = new JLabel("Apellido Trabajador: ");
        apellido_trabajadorLabel.setBounds(50, 120, 200, 25);
        apellido_trabajadorLabel.setFont(nFont18);
        panel.add(apellido_trabajadorLabel);

        apellido_trabajadorField = new JTextField(20);
        apellido_trabajadorField.setBounds(250, 120, 200, 25);
        apellido_trabajadorField.setBackground(new Color(255, 255, 255)); 
        panel.add(apellido_trabajadorField);


        correo_trabajadorLabel = new JLabel("Correo: ");
        correo_trabajadorLabel.setBounds(50, 160, 200, 25);
        correo_trabajadorLabel.setFont(nFont18);
        panel.add(correo_trabajadorLabel);

        correo_trabajadorField = new JTextField(20);
        correo_trabajadorField.setBounds(250, 160, 200, 25);
        correo_trabajadorField.setBackground(new Color(255, 255, 255)); 
        panel.add(correo_trabajadorField);


        telefono_trabajadorLabel = new JLabel("Telefono: ");
        telefono_trabajadorLabel.setBounds(50, 200, 200, 25);
        telefono_trabajadorLabel.setFont(nFont18);
        panel.add(telefono_trabajadorLabel);

        telefono_trabajadorField = new JTextField(20);
        telefono_trabajadorField.setBounds(250, 200, 200, 25);
        telefono_trabajadorField.setBackground(new Color(255, 255, 255)); 
        panel.add(telefono_trabajadorField);


        contrasenaLabel = new JLabel("Contraseña: ");
        contrasenaLabel.setBounds(50, 240, 200, 25);
        contrasenaLabel.setFont(nFont18);
        panel.add(contrasenaLabel);

        contrasenaField = new JTextField(20);
        contrasenaField.setBounds(250, 240, 200, 25);
        contrasenaField.setBackground(new Color(255, 255, 255)); 
        panel.add(contrasenaField);


        trabajador_activoLabel = new JLabel("Habiltar / Deshabilitar: ");
        trabajador_activoLabel.setBounds(50, 280, 200, 25);
        trabajador_activoLabel.setFont(nFont18);
        panel.add(trabajador_activoLabel);

        trabajador_activoCheckBox = new JCheckBox();
        trabajador_activoCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        trabajador_activoCheckBox.setBounds(250, 280, 20, 25);
        trabajador_activoCheckBox.setBackground(new Color(255, 255, 255));
        panel.add(trabajador_activoCheckBox);


        tipo_trabajadorLabel = new JLabel("Administrador: ");
        tipo_trabajadorLabel.setBounds(50, 320, 200, 25);
        tipo_trabajadorLabel.setFont(nFont18);
        panel.add(tipo_trabajadorLabel);

        tipo_trabajadorCheckBox = new JCheckBox();
        tipo_trabajadorCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        tipo_trabajadorCheckBox.setBounds(250, 320, 20, 25);
        tipo_trabajadorCheckBox.setBackground(new Color(255, 255, 255)); 
        panel.add(tipo_trabajadorCheckBox);


        comisionProductoLabel = new JLabel("Comisión Producto: ");
        comisionProductoLabel.setBounds(50, 360, 200, 25);
        comisionProductoLabel.setFont(nFont18);
        panel.add(comisionProductoLabel);

        comisionProductoField = new JTextField(20);
        comisionProductoField.setBounds(250, 360, 200, 25);
        comisionProductoField.setBackground(new Color(255, 255, 255)); 
        panel.add(comisionProductoField);

        comisionServicioLabel  = new JLabel("Comisión Servicio: ");
        comisionServicioLabel.setBounds(50, 400, 200, 25);
        comisionServicioLabel.setFont(nFont18);
        panel.add(comisionServicioLabel);

        comisionServicioField  = new JTextField(20);
        comisionServicioField.setBounds(250, 400, 200, 25);
        comisionServicioField.setBackground(new Color(255, 255, 255));
        panel.add(comisionServicioField);




        missatgeLabel = new JLabel("");
        missatgeLabel.setBounds(400, 400, 400, 25);
        missatgeLabel.setFont(nFont18);
        missatgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(missatgeLabel);


        guardarTrabajadorButton = new JButton("Guardar");
        guardarTrabajadorButton.setBounds(150, 450, 200, 40);
        guardarTrabajadorButton.setHorizontalAlignment(SwingConstants.CENTER);
        guardarTrabajadorButton.setBackground(new Color(218, 247, 166));
        guardarTrabajadorButton.setFont(nFont18);
        guardarTrabajadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarTrabajador();
            }
        });
        panel.getRootPane().setDefaultButton(guardarTrabajadorButton); // Para poderlo pulsar con la tecla "INTRO".
        panel.add(guardarTrabajadorButton);

        
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


        mostrarDatosTrabajador(idTrabajador); // Para rellenar los campos que ya tiene el Trabajador.
    }

    // Metodos
    private void mostrarDatosTrabajador(Integer idTrabajador) {
        Trabajadores trabajadores = controladorTrabajadores.buscarTrabajador(idTrabajador);

        try {
            if (trabajadores != null) {
                nombre_trabajadorField.setText(trabajadores.getNombreTrabajador());
                apellido_trabajadorField.setText(trabajadores.getApellidoTrabajador());
                correo_trabajadorField.setText(trabajadores.getCorreoTrabajador());
                telefono_trabajadorField.setText(trabajadores.getTelefonoTrabajador());
                contrasenaField.setText(trabajadores.getContrasena());
                trabajador_activoCheckBox.setSelected(trabajadores.isTrabajadorActivo());
                tipo_trabajadorCheckBox.setSelected(trabajadores.isTipoTrabajador());
                comisionProductoField.setText(String.valueOf(trabajadores.getComisionProducto()));
                comisionServicioField.setText(String.valueOf(trabajadores.getComisionServicio()));
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

    private void editarTrabajador() {
        String nombre = nombre_trabajadorField.getText();
        String apellido = apellido_trabajadorField.getText();
        String correo = correo_trabajadorField.getText();
        String telefono = telefono_trabajadorField.getText();
        String contrasena = contrasenaField.getText();
        Boolean  trabajadorActivo = trabajador_activoCheckBox.isSelected();
        Boolean trabajadorTipo = tipo_trabajadorCheckBox.isSelected(); // Jefe / Emlpeado;
        String comisionProductoText = comisionProductoField.getText();
        String comisionServicioText = comisionServicioField.getText();
        BigDecimal comision_producto = new BigDecimal(comisionProductoText);
        BigDecimal comision_servicio = new BigDecimal(comisionServicioText);


        String missatge = "";
        Color colorMissatge = Color.BLUE;


        // Validar que el texto no esté vacío y sea un número válido
        if (comisionProductoText != null && !comisionProductoText.trim().isEmpty()) {
            try {
                // Intentar convertir el texto a BigDecimal
                comision_producto = new BigDecimal(comisionProductoText);
            } catch (NumberFormatException e) {
                // Si hay un error, comision ya está en 0.00

            }
            
        }if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty() || contrasena.isEmpty()) {
            missatge = "Tienes que rellenar todos los campos";
        } else {
            try {
                boolean resultat = controladorTrabajadores.modificarTrabajadores(
                    this.idTrabajador,  // Usa this.idTrabajador aquí
                    nombre,
                    apellido,
                    correo,
                    telefono,
                    contrasena,
                    trabajadorActivo,
                    trabajadorTipo,
                    comision_producto,
                    comision_servicio
                );
    
                if (resultat) {
                    missatge = "¡Trabajador Actualizado!";
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
        new GestionTrabajadores(trabajadores).setVisible(true);
        dispose();
    }
}
