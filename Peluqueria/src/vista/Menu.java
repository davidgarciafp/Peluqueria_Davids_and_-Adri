package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modelo.Trabajadores;
import modelo.Servicios;

public class Menu extends JFrame {
    private JPanel menuBotones;
    private JLabel cerrarSesionLabel;
    private JLabel usuariLabel;
    private JLabel trabajadorLabel;
    private JButton TrabajadoresButton;
    private JButton serviciosButton;
    private JButton productosButton;
    private JButton clientesButton;
    private JLayeredPane layeredPane; // Declarar aquí


    public Menu(Object trabajadores) {
        // Inicializar el controlador.
        setTitle("Peluqueria"); // Pon un titulo a la pagina.
        setSize(800, 600); // Configuracion del tamaño de la pantalla.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Indica que la aplicación se cerrará completamente.
        setMinimumSize(new Dimension(800, 600)); // Tamaño mínimo de 800x600 píxeles
        setLocationRelativeTo(null); // Centra la ventana en la pantalla.

        // Creamos un panel para agregar los componetes que quieras.
        JPanel panel = new JPanel();
        add(panel);
        posicioBotons(panel, trabajadores);


        // Ajustar componentes al tamaño inicial
        ajustarComponentes(panel, layeredPane);

        // Escuchar cambios de tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarComponentes(panel, layeredPane);
            }
        });


        setVisible(true); 
    }

    private void posicioBotons(JPanel panel, Object trabajadores) {
        panel.setBackground(new Color(220, 207, 201)); // Canviar de color.
        panel.setLayout(null);


        // Fuentes
        Font nFont18 = new Font(null, Font.PLAIN, 18);


        // Crear un JLayeredPane
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 800, 600); // Ajusta el tamaño según sea necesario
        panel.add(layeredPane);


        // Crear un panel para la columna de botones
        menuBotones = new JPanel();
        menuBotones.setBackground(new Color(200, 200, 200)); // Color de fondo blanco
        menuBotones.setLayout(null);
        menuBotones.setBounds(550, 0, 250, 600); // Ajusta el tamaño y la posición según sea necesario
        layeredPane.add(menuBotones, Integer.valueOf(0)); // Añadir a la capa inferior


        ImageIcon imgOriginalCerrarSession = new ImageIcon("C:\\xampp\\htdocs\\MP_12 Projecte_1\\Imagenes\\Cerrar_Sesion.png");
        Image imagenRedimensionadaCerrarSession = imgOriginalCerrarSession.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        cerrarSesionLabel = new JLabel(new ImageIcon(imagenRedimensionadaCerrarSession));
        cerrarSesionLabel.setBounds(700, 25, 50, 50);
        cerrarSesionLabel.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR)); // Cambia el cursor al pasar el mouse
        cerrarSesionLabel.addMouseListener(new MouseAdapter() { // Agregar un MouseListener para manejar clics
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarSesion();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                cerrarSesionLabel.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                cerrarSesionLabel.setOpaque(false);
            }
        });
        layeredPane.add(cerrarSesionLabel, Integer.valueOf(1)); // Añadir a la capa superior


        ImageIcon imgOriginalUsuario = new ImageIcon("C:\\xampp\\htdocs\\MP_12 Projecte_1\\Imagenes\\Avatar.png");
        Image imagenRedimensionadaUsuario = imgOriginalUsuario.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        usuariLabel = new JLabel(new ImageIcon(imagenRedimensionadaUsuario));
        usuariLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usuariLabel.setBounds(550, 80, 250, 75);
        layeredPane.add(usuariLabel, Integer.valueOf(1)); // Añadir a la capa superior


        trabajadorLabel = new JLabel(((Trabajadores) trabajadores).getNombreTrabajador() + " " + ((Trabajadores) trabajadores).getApellidoTrabajador());
        trabajadorLabel.setBounds(575, 145, 200, 40);
        trabajadorLabel.setFont(nFont18);
        trabajadorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        trabajadorLabel.setForeground(new Color(90, 90, 90));
        layeredPane.add(trabajadorLabel, Integer.valueOf(1)); // Añadir a la capa superior



        serviciosButton = new JButton("Servicios");
        serviciosButton.setBounds(600, 205, 150, 40);
        serviciosButton.setFont(nFont18);
        serviciosButton.setHorizontalAlignment(SwingConstants.CENTER);
        serviciosButton.setBackground(new Color(139, 137, 137)); 
        serviciosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paginaGestionServicios((Servicios) servicios);
            }
        });
        layeredPane.add(serviciosButton, Integer.valueOf(1)); // Añadir a la capa superior


        clientesButton = new JButton("Clientes");
        clientesButton.setBounds(600, 265, 150, 40);
        clientesButton.setFont(nFont18);
        clientesButton.setHorizontalAlignment(SwingConstants.CENTER);
        clientesButton.setBackground(new Color(139, 137, 137)); 
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paginaClientesVista((Trabajadores) trabajadores);
            }
        });
        layeredPane.add(clientesButton, Integer.valueOf(1)); // Añadir a la capa superior


        productosButton = new JButton("Productos");
        productosButton.setBounds(600, 330, 150, 40);
        productosButton.setFont(nFont18);
        productosButton.setHorizontalAlignment(SwingConstants.CENTER);
        productosButton.setBackground(new Color(139, 137, 137)); 
        productosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paginaProductosVista((Trabajadores) trabajadores);
            }
        });
        layeredPane.add(productosButton, Integer.valueOf(1)); // Añadir a la capa superior


        TrabajadoresButton = new JButton("Trabajadores");
        TrabajadoresButton.setBounds(600, 395, 150, 40);
        TrabajadoresButton.setFont(nFont18);
        TrabajadoresButton.setHorizontalAlignment(SwingConstants.CENTER);
        TrabajadoresButton.setBackground(new Color(139, 137, 137)); 
        TrabajadoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paginaGestionTrabajadores((Trabajadores) trabajadores);
            }
        });
        layeredPane.add(TrabajadoresButton, Integer.valueOf(1)); // Añadir a la capa superior
    }

    // Ajustar las fuentes según la escala de la pantalla
    private void ajustarFuentes(double scaleX, double scaleY) {
        trabajadorLabel.setFont(trabajadorLabel.getFont().deriveFont((float)(18 * scaleY)));
        serviciosButton.setFont(serviciosButton.getFont().deriveFont((float)(18 * scaleY)));
        clientesButton.setFont(clientesButton.getFont().deriveFont((float)(18 * scaleY)));
        productosButton.setFont(productosButton.getFont().deriveFont((float)(18 * scaleY)));
        TrabajadoresButton.setFont(TrabajadoresButton.getFont().deriveFont((float)(18 * scaleY)));
    }
    
    // Adaptar el tamaño de cada elemeto segun el tamaño de la pantalla.
    private void ajustarComponentes(JPanel panel, JLayeredPane layeredPane) {
        Dimension size = panel.getParent().getSize();
        double scaleX = size.width / 800.0; // Escala en base a 800
        double scaleY = size.height / 600.0; // Escala en base a 600

            // Redimensionar y reposicionar el JLayeredPane
        layeredPane.setBounds(0, 0, (int)(size.width), (int)(size.height));
    
        // Redimensionar y reposicionar componentes
        menuBotones.setBounds((int)(550 * scaleX), (int)(0 * scaleY), (int)(250 * scaleX), (int)(600 * scaleY));
        cerrarSesionLabel.setBounds((int)(700 * scaleX), (int)(25 * scaleY), (int)(50 * scaleX), (int)(50 * scaleY));
        usuariLabel.setBounds((int)(550 * scaleX), (int)(80 * scaleY), (int)(250 * scaleX), (int)(75 * scaleY));
        trabajadorLabel.setBounds((int)(575 * scaleX), (int)(145 * scaleY), (int)(200 * scaleX), (int)(40 * scaleY));
        // Botones
        serviciosButton.setBounds((int)(600 * scaleX), (int)(205 * scaleY), (int)(150 * scaleX), (int)(40 * scaleY));
        clientesButton.setBounds((int)(600 * scaleX), (int)(265 * scaleY), (int)(150 * scaleX), (int)(40 * scaleY));
        productosButton.setBounds((int)(600 * scaleX), (int)(330 * scaleY), (int)(150 * scaleX), (int)(40 * scaleY));
        TrabajadoresButton.setBounds((int)(600 * scaleX), (int)(395 * scaleY), (int)(150 * scaleX), (int)(40 * scaleY));

        // Ajustar las fuentes
        ajustarFuentes(scaleX, scaleY);
    }


    // Metodos
    private void cerrarSesion() {
        new IniciarSesion().setVisible(true);
        dispose();
    }

    private void paginaGestionTrabajadores(Trabajadores trabajadores) {
        try {
            new GestionTrabajadores(trabajadores).setVisible(true);
            dispose();
        } catch (RuntimeException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                new IniciarSesion().setVisible(true);
                dispose();
            } else {
                ex.printStackTrace();
            }
        }
    }
    
    private void paginaClientesVista(Trabajadores trabajadores) {
        try {
            new GestionClientes(trabajadores).setVisible(true);
            dispose();
        } catch (RuntimeException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                new IniciarSesion().setVisible(true);
                dispose();
            } else {
                ex.printStackTrace();
            }
        }
    }

    private void paginaGestionServicios(Servicios servicios) {
        try {
            new GestionServicios(servicios).setVisible(true);
            dispose();
        } catch (RuntimeException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                new IniciarSesion().setVisible(true);
                dispose();
            } else {
                ex.printStackTrace();
            }
        }
    }

    private void paginaProductosVista(Trabajadores trabajadores) {
        try {
            new GestionProductos(trabajadores).setVisible(true);
            dispose();
        } catch (RuntimeException ex) {
            if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                new IniciarSesion().setVisible(true);
                dispose();
            } else {
                ex.printStackTrace();
            }
        }
    }
}
