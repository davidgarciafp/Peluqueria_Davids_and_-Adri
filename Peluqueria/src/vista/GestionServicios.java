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
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.util.Base64Decoder.IntWrapper;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import javax.swing.JPanel;

import controlador.ControladorServicios;
import modelo.Productos;
import modelo.Servicios;

public class GestionServicios extends JFrame {
    private JLabel volverLabel;
    private JTable tablaServicio;
    private JButton agregarButton;
    private ControladorServicios controladorServicios;

    public GestionServicios(Servicios servicios) {
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

        // Fuentes
        Font nFont18 = new Font(null, Font.PLAIN, 18);


        ImageIcon originalIcon = new ImageIcon("C:\\xampp\\htdocs\\MP_12 Projecte_1\\Imagenes\\volver.png");
        Image imagenRedimensionada = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        volverLabel = new JLabel(new ImageIcon(imagenRedimensionada));
        volverLabel.setBounds(700, 25, 50, 50);
        volverLabel.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR)); // Cambia el cursor al pasar el mouse
        volverLabel.addMouseListener(new MouseAdapter() { // Agregar un MouseListener para manejar clics
            @Override
            public void mouseClicked(MouseEvent e) {
                volverAtras((Servicios) servicios);
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

        JScrollPane scrollPane = mostrarTablaServicios((Servicios) servicios);
        panel.add(scrollPane);

        agregarButton = new JButton("Agregar Servicio");
        agregarButton.setBounds(250, 500, 200, 40);
        agregarButton.setHorizontalAlignment(SwingConstants.CENTER);
        agregarButton.setBackground(new Color(206, 123, 86));
        agregarButton.setFont(nFont18);
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarServicio((Servicios) servicios);
            }
        });
        panel.getRootPane().setDefaultButton(agregarButton); // Para poderlo pulsar con la tecla "INTRO".
        panel.add(agregarButton);
    }
    
    // Metodo
    private void volverAtras(Servicios servicios) {
        new Menu(servicios).setVisible(true);
        dispose();
    }

    private JScrollPane mostrarTablaServicios(Servicios servicios) {
        List<Servicios> listaServicios = controladorServicios.mostrarServicios(); // Obté la llista de trabajadores del controlador.
        String[] columnas = {"ID Servicio", "Precio Base de Servicio", "Descripción de Servicio", "Servicio Activo"}; // Defineix les columnes de la taula.
        Object[][] datos = new Object[listaServicios.size()][5]; // Crar un array de los trabajadores.

        // Omple l'array de dades amb la informació de cada llibre.
        for (int i = 0; i < listaServicios.size(); i++) {

            Servicios servicio = listaServicios.get(i);
            
            datos[i] = new Object[]{
                servicio.getId_servicio(),
                servicio.getPrecio_base(),
                servicio.getDescripcion_servicio(),
                servicio.isServicio_activo(),
                "Editar"
            };
        }

        // Crea un modelo de tabla personalizado
        DefaultTableModel opcionesTabla = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Solo editable la columna "Editar"
            }
        };

        tablaServicio = new JTable(opcionesTabla);
    
        // Añadir un MouseListener para manejar el clic en la columna "Editar"
        tablaServicio.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaServicio.rowAtPoint(e.getPoint());
                int column = tablaServicio.columnAtPoint(e.getPoint());
                if (column == 4) { // Si se clicó en la columna "Editar"
                    String isServicio = (Int) tablaServicio.getValueAt(row, 0); // Obtener el DNI
                    new EditarServicios(tablaServicio, idServicio).setVisible(true);
                    dispose();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaServicio); // Crea un JScrollPane que contindrà la taula de llibres.
        scrollPane.setBounds(50, 100, 600, 300); // Ajusteu la posició i la mida del JScrollPane.
    
        return scrollPane; // Retorna el JScrollPane amb la taula de llibres.
    }

    private void agregarServicio(Servicios servicios) {
        new CrearServicio(servicios).setVisible(true);
        dispose();
    }
}