package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import javax.swing.JPanel;

import controlador.ControladorTrabajadores;
import modelo.Trabajadores;

public class GestionTrabajadores extends JFrame {
    private JLabel mensaje;
    private JLabel volverLabel;
    private JTable tablaTrabajador;
    private JButton agregarButton;
    private ControladorTrabajadores controladorTrabajadores;

    public GestionTrabajadores(Trabajadores trabajadores) {
        controladorTrabajadores = new ControladorTrabajadores(); // Inicializar el controlador.
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
    private void posicioBotons(JPanel panel, Object trabajadores) {

        panel.setBackground(new Color(139, 137, 137)); // Canviar de color.
        panel.setLayout(null);

        // Fuentes
        Font nFont18 = new Font(null, Font.PLAIN, 18);

        mensaje = new JLabel(((Trabajadores) trabajadores).getNombreTrabajador() + " " + ((Trabajadores) trabajadores).getApellidoTrabajador());
        mensaje.setBounds(575, 110, 200, 40);
        mensaje.setFont(nFont18);
        mensaje.setForeground(new Color(255, 255, 255));
        panel.add(mensaje);

        ImageIcon originalIcon = new ImageIcon("C:\\xampp\\htdocs\\MP_12 Projecte_1\\Imagenes\\volver.png");
        Image imagenRedimensionada = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        volverLabel = new JLabel(new ImageIcon(imagenRedimensionada));
        volverLabel.setBounds(700, 25, 50, 50);
        volverLabel.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR)); // Cambia el cursor al pasar el mouse
        volverLabel.addMouseListener(new MouseAdapter() { // Agregar un MouseListener para manejar clics
            @Override
            public void mouseClicked(MouseEvent e) {
                volverAtras((Trabajadores) trabajadores);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                volverLabel.setOpaque(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                volverLabel.setOpaque(false);
            }
        });
        panel.add(volverLabel);

        JScrollPane scrollPane = mostrarTablaTrabajadores();
        panel.add(scrollPane);

        agregarButton = new JButton("Agregar Trabajador");
        agregarButton.setBounds(250, 500, 200, 40);
        agregarButton.setHorizontalAlignment(SwingConstants.CENTER);
        agregarButton.setBackground(new Color(206, 123, 86));
        agregarButton.setFont(nFont18);
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarTrabajador((Trabajadores) trabajadores);
            }
        });
        panel.getRootPane().setDefaultButton(agregarButton); // Para poderlo pulsar con la tecla "INTRO".
        panel.add(agregarButton);


    }

    
    // Metodo
    private void volverAtras(Trabajadores trabajadores) {
        new Menu(trabajadores).setVisible(true);
        dispose();
    }

    private JScrollPane mostrarTablaTrabajadores() {
        List<Trabajadores> listaTrabajadores = controladorTrabajadores.mostrarTrabajadores(); // Obté la llista de trabajadores del controlador.
        String[] columnas = {"DNI", "Nombre", "Apellido", "Correo", "Telèfon", "Alta", "Rol", "Comisión"}; // Defineix les columnes de la taula.
        Object[][] datos = new Object[listaTrabajadores.size()][8]; // Crar un array de los trabajadores.

        // Omple l'array de dades amb la informació de cada llibre.
        for (int i = 0; i < listaTrabajadores.size(); i++) {

            Trabajadores trabajador = listaTrabajadores.get(i);

            datos[i] = new Object[] {trabajador.getDni(), trabajador.getNombreTrabajador(), trabajador.getApellidoTrabajador(), trabajador.getCorreoTrabajador(), trabajador.getTelefonoTrabajador(), trabajador.isTrabajadorActivo(), trabajador.isTipoTrabajador(), trabajador.getComision()};
        }

        tablaTrabajador = new JTable(datos, columnas); // Crea la taula de llibres.

        JScrollPane scrollPane = new JScrollPane(tablaTrabajador); // Crea un JScrollPane que contindrà la taula de llibres.
        scrollPane.setBounds(50, 100, 200, 300); // Ajusteu la posició i la mida del JScrollPane.
    
        return scrollPane; // Retorna el JScrollPane amb la taula de llibres.
    }

    private void agregarTrabajador(Trabajadores trabajadores) {
        new CrearTrabajadores(trabajadores).setVisible(true);
        dispose();
    }
}