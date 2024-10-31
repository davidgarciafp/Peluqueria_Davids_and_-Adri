package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import basedatos.ConexionBaseDatos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controlador.ControladorTrabajadores;
import modelo.Trabajadores;

public class IniciarSesion extends JFrame {
    private JLabel imagenLabel;
    private JLabel nombreUsuarioLabel;
    private JComboBox<String> nombreUsuarioComboBox;
    private Map<String, Integer> id_trabajadorMap = new HashMap<>(); // Mapa para almacenar id_trabajador
    private JLabel contrasenaUsuarioLabel;
    private JPasswordField contrasenaUsuarioField;
    private JLabel missatgeErrorLabel;
    private JButton loginButton;
    private ControladorTrabajadores controladorTrabajadores;

    public IniciarSesion() {
        controladorTrabajadores = new ControladorTrabajadores();  // Inicializar el controlador.
        setTitle("Peluqueria"); // Pon un titulo a la pagina.
        setSize(800, 600); // Configuracion del tamaño de la pantalla.
        setMinimumSize(new Dimension(800, 600)); // Tamaño mínimo de 800x600 píxeles
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Indica que la aplicación se cerrará completamente.
        setLocationRelativeTo(null); // Centra la ventana en la pantalla.

        // Creamos un panel para agregar los componetes que quieras.
        JPanel panel = new JPanel();
        add(panel);
        posicioBotons(panel);

        if (comprovarBD()) {
            llenarComboBox(); // Llenar el JComboBox con los nombres de trabajadores
        }
        
        // Ajustar componentes al tamaño inicial
        ajustarComponentes(panel);

        // Escuchar cambios de tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarComponentes(panel);
            }
        });

        setVisible(true); // PAra que se vea
    }

    private void posicioBotons(JPanel panel) {
        panel.setBackground(new Color(220, 207, 201)); // Canviar de color.
        panel.setLayout(null);


        // Fuentes
        Font nFont14 = new Font(null, Font.PLAIN, 14);
        Font nFont16 = new Font(null, Font.PLAIN, 16);
        Font nFont20 = new Font(null, Font.PLAIN, 20);


        // Cargar la imagen
        ImageIcon imagen = new ImageIcon("C:\\xampp\\htdocs\\MP_12 Projecte_1\\Imagenes\\Dreams_Logo.jpg"); // Cambia la ruta a la ubicación de tu imagen
        imagenLabel = new JLabel(imagen);
        imagenLabel.setBounds(75, 50, 650, 166); // Ajusta las coordenadas y el tamaño según sea necesario
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(imagenLabel);


        nombreUsuarioLabel = new JLabel("Nombre de Usuario:");
        nombreUsuarioLabel.setBounds(300, 270, 200, 25);
        nombreUsuarioLabel.setFont(nFont16);
        nombreUsuarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(nombreUsuarioLabel);


        // Crear un JComboBox con opciones
        nombreUsuarioComboBox = new JComboBox<>();
        nombreUsuarioComboBox.setBounds(300, 300, 200, 25);
        nombreUsuarioComboBox.setBackground(new Color(235, 235, 235));
        panel.add(nombreUsuarioComboBox);


        contrasenaUsuarioLabel = new JLabel("Contraseña:");
        contrasenaUsuarioLabel.setBounds(300, 340, 200, 25);
        contrasenaUsuarioLabel.setFont(nFont16);
        contrasenaUsuarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(contrasenaUsuarioLabel);

        contrasenaUsuarioField = new JPasswordField(20);
        contrasenaUsuarioField.setBounds(300, 370, 200, 25);
        contrasenaUsuarioField.setBackground(new Color(235, 235, 235)); 
        panel.add(contrasenaUsuarioField);


        missatgeErrorLabel = new JLabel("");
        missatgeErrorLabel.setBounds(300, 410, 200, 25);
        missatgeErrorLabel.setFont(nFont14);
        missatgeErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(missatgeErrorLabel);


        loginButton = new JButton("Iniciar Sessió");
        loginButton.setBounds(300, 450, 200, 40);
        loginButton.setFont(nFont20);
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        loginButton.setBackground(new Color(235, 235, 235)); 
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                identificarUsuario();
            }
        });
        panel.getRootPane().setDefaultButton(loginButton); // Para poderlo pulsar con la tecla "INTRO".
        panel.add(loginButton);
    }

    // Ajustar las fuentes según la escala de la pantalla
    private void ajustarFuentes(double scaleX, double scaleY) {
        nombreUsuarioLabel.setFont(nombreUsuarioLabel.getFont().deriveFont((float)(16 * scaleY)));
        nombreUsuarioComboBox.setFont(nombreUsuarioComboBox.getFont().deriveFont((float)(14 * scaleY)));
        contrasenaUsuarioLabel.setFont(contrasenaUsuarioLabel.getFont().deriveFont((float)(16 * scaleY)));
        contrasenaUsuarioField.setFont(contrasenaUsuarioField.getFont().deriveFont((float)(14 * scaleY)));
        missatgeErrorLabel.setFont(missatgeErrorLabel.getFont().deriveFont((float)(14 * scaleY)));
        loginButton.setFont(loginButton.getFont().deriveFont((float)(20 * scaleY)));
        
    }

    // Adaptar el tamaño de cada elemeto segun el tamaño de la pantalla.
    private void ajustarComponentes(JPanel panel) {
        Dimension size = panel.getParent().getSize();
        double scaleX = size.width / 800.0; // Escala en base a 800
        double scaleY = size.height / 600.0; // Escala en base a 600
    
        // Redimensionar y reposicionar componentes
        imagenLabel.setBounds((int)(75 * scaleX), (int)(50 * scaleY), (int)(650 * scaleX), (int)(166 * scaleY));
        nombreUsuarioLabel.setBounds((int)(300 * scaleX), (int)(270 * scaleY), (int)(200 * scaleX), (int)(25 * scaleY));
        nombreUsuarioComboBox.setBounds((int)(300 * scaleX), (int)(300 * scaleY), (int)(200 * scaleX), (int)(25 * scaleY));
        contrasenaUsuarioLabel.setBounds((int)(300 * scaleX), (int)(340 * scaleY), (int)(200 * scaleX), (int)(25 * scaleY));
        contrasenaUsuarioField.setBounds((int)(300 * scaleX), (int)(370 * scaleY), (int)(200 * scaleX), (int)(25 * scaleY));
        missatgeErrorLabel.setBounds((int)(300 * scaleX), (int)(410 * scaleY), (int)(200 * scaleX), (int)(25 * scaleY));
        loginButton.setBounds((int)(300 * scaleX), (int)(450 * scaleY), (int)(200 * scaleX), (int)(40 * scaleY));

        // Ajustar las fuentes
        ajustarFuentes(scaleX, scaleY);
    }


    // Metodos
    private boolean comprovarBD() {
        Boolean resultado = false;
        try {
            // Aquí deberías intentar establecer una conexión a la base de datos.
            // Por ejemplo:
            Connection connection = ConexionBaseDatos.getConexion(); // Intenta obtener la conexión
            if (connection != null) {
                resultado = true; // Conexión exitosa
            }
        } catch (SQLException ex) {
            // Manejo de la excepción si no se puede conectar
            if (ex.getMessage().contains("BaseDatos NO encontrada")) {
                missatgeErrorLabel.setText("Base de datos no encontrada");
                missatgeErrorLabel.setForeground(Color.BLACK);
            } else {
                ex.printStackTrace(); // Imprimir otras excepciones
            }
        }
        return resultado; // En caso de error false
    }

    private void llenarComboBox() {
        try {
            List<Trabajadores> trabajadoresList = controladorTrabajadores.mostrarTrabajadores();
            nombreUsuarioComboBox.addItem("--Selecionar--"); // Agregar opción por defecto
            for (Trabajadores trabajador : trabajadoresList) {
                String displayName = trabajador.getNombreTrabajador() + " " + trabajador.getApellidoTrabajador();

                id_trabajadorMap.put(displayName, trabajador.getId_trabajador()); // Almacenar el id_trabajador en el mapa
                nombreUsuarioComboBox.addItem(displayName); // Solo mostrar nombre y apellido
            }
        } catch (RuntimeException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                missatgeErrorLabel.setText("Base de datos no encontrada");
                missatgeErrorLabel.setForeground(Color.BLACK);
            } else {
                ex.printStackTrace();
            }
        }
    }
    
    private void identificarUsuario() {
        String selecionarNombre = (String) nombreUsuarioComboBox.getSelectedItem(); // Obtener nombre seleccionado
        // Verificar si no se ha seleccionado un usuario
        if (selecionarNombre.equals("--Selecionar--")) {
            missatgeErrorLabel.setText("Elige un usuario");
            missatgeErrorLabel.setForeground(Color.BLUE);
        } else {
            int id_trabajador = id_trabajadorMap.get(selecionarNombre); // Obtener el id_trabajador correspondiente
            String contrasena = new String(contrasenaUsuarioField.getPassword());
            
            try {
                Trabajadores trabajador = controladorTrabajadores.identificarTrabajador(id_trabajador, contrasena);
                if (trabajador != null) {
                    if (trabajador.isTipoTrabajador()) {
                        // Trabajador és Jefe
                        new Menu(trabajador).setVisible(true);
                    } else {
                        // Trabajador és Empleado
                        new Menu(trabajador).setVisible(true);
                    }
                    dispose(); // Per tancar la pestanya actual
                } else {
                    missatgeErrorLabel.setText("La contraseña no es correcta");
                    missatgeErrorLabel.setForeground(Color.RED);
                }
            } catch (RuntimeException ex) {
                if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                    missatgeErrorLabel.setText("Base de datos no encontrada");
                    missatgeErrorLabel.setForeground(Color.BLACK);
                } else {
                    ex.printStackTrace();
                }
            }
        }
    }
}