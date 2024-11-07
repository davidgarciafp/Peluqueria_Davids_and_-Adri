package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controlador.ControladorTrabajadores;
import modelo.Clientes;
import modelo.Productos;
import modelo.Servicios;
import modelo.Trabajadores;

public class IniciarSesion extends JFrame {
    private JLabel imagenLabel;
    private JLabel contrasenaUsuarioLabel;
    private JPasswordField contrasenaUsuarioField;
    private JLabel missatgeLabel;
    private JButton loginButton;
    private ControladorTrabajadores controladorTrabajadores;

    public IniciarSesion() {
        controladorTrabajadores = new ControladorTrabajadores();
        setTitle("Peluqueria");
        setSize(800, 600);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        posicioBotons(panel);

        ajustarComponentes(panel);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarComponentes(panel);
            }
        });

        setVisible(true);
    }

    private void posicioBotons(JPanel panel) {
        panel.setBackground(new Color(220, 207, 201));
        panel.setLayout(null);
    
        Font nFont16 = new Font(null, Font.PLAIN, 16);
        Font nFont20 = new Font(null, Font.PLAIN, 20);
    
        ImageIcon imagen = new ImageIcon("C:\\xampp\\htdocs\\MP_12 Projecte_1\\Imagenes\\Dreams_Logo.jpg");
        imagenLabel = new JLabel(imagen);
        imagenLabel.setBounds(75, 50, 650, 166);
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(imagenLabel);
    
        contrasenaUsuarioLabel = new JLabel("Contraseña:");
        contrasenaUsuarioLabel.setBounds(300, 270, 200, 25);  // Bajado a 320
        contrasenaUsuarioLabel.setFont(nFont16);
        contrasenaUsuarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(contrasenaUsuarioLabel);
    
        contrasenaUsuarioField = new JPasswordField(20);
        contrasenaUsuarioField.setBounds(300, 300, 200, 25);  // Bajado a 350
        contrasenaUsuarioField.setBackground(new Color(235, 235, 235)); 
        panel.add(contrasenaUsuarioField);
    
        missatgeLabel = new JLabel("");
        missatgeLabel.setBounds(300, 380, 200, 25);  // Ajustado a 380
        missatgeLabel.setFont(nFont16);
        missatgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(missatgeLabel);
    
        loginButton = new JButton("Iniciar Sessió");
        loginButton.setBounds(300, 410, 200, 40);  // Ajustado a 410
        loginButton.setFont(nFont20);
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        loginButton.setBackground(new Color(235, 235, 235)); 
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                identificarInicio();
            }
        });
        panel.getRootPane().setDefaultButton(loginButton);
        panel.add(loginButton);
    }
    
    private void ajustarComponentes(JPanel panel) {
        Dimension size = panel.getParent().getSize();
        double scaleX = size.width / 800.0;
        double scaleY = size.height / 600.0;
    
        imagenLabel.setBounds((int)(75 * scaleX), (int)(50 * scaleY), (int)(650 * scaleX), (int)(166 * scaleY));
        contrasenaUsuarioLabel.setBounds((int)(300 * scaleX), (int)(270 * scaleY), (int)(200 * scaleX), (int)(25 * scaleY));
        contrasenaUsuarioField.setBounds((int)(300 * scaleX), (int)(300 * scaleY), (int)(200 * scaleX), (int)(25 * scaleY));
        missatgeLabel.setBounds((int)(300 * scaleX), (int)(380 * scaleY), (int)(200 * scaleX), (int)(25 * scaleY));
        loginButton.setBounds((int)(300 * scaleX), (int)(410 * scaleY), (int)(200 * scaleX), (int)(40 * scaleY));
    
        ajustarFuentes(scaleX, scaleY);
    }

    private void ajustarFuentes(double scaleX, double scaleY) {
        contrasenaUsuarioLabel.setFont(contrasenaUsuarioLabel.getFont().deriveFont((float)(16 * scaleY)));
        contrasenaUsuarioField.setFont(contrasenaUsuarioField.getFont().deriveFont((float)(14 * scaleY)));
        missatgeLabel.setFont(missatgeLabel.getFont().deriveFont((float)(14 * scaleY)));
        loginButton.setFont(loginButton.getFont().deriveFont((float)(20 * scaleY)));
    }

    private void identificarInicio() {
        String contrasena = new String(contrasenaUsuarioField.getPassword());
    
        try {
            Trabajadores trabajador = controladorTrabajadores.identificarInicio(contrasena);
            if (trabajador != null) {
                // La contraseña es correcta y corresponde al usuario con ID 1
                new Menu(trabajador, new Servicios(), new Productos(), new Clientes()).setVisible(true);
                dispose(); // Cierra la ventana actual de inicio de sesión
            } else {
                missatgeLabel.setText("La contraseña no es correcta");
                missatgeLabel.setForeground(Color.RED);
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
}