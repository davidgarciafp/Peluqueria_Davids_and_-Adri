package vista.servicios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorServicios;
import modelo.Servicios;
import modelo.Trabajadores;
import vista.Menu;

public class GestionServicios extends JFrame {
    private JLabel volverLabel;
    private JTable tablaServicio;
    private JButton agregarButton;
    private JButton eliminadosButton;
    private ControladorServicios controladorServicios;
    private boolean mostrandoDeshabilitados = false;
    private JLabel titulo;

    public GestionServicios(Trabajadores trabajadores) {
        controladorServicios = new ControladorServicios(); // Inicializar el controlador.
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
    private void posicioBotons(JPanel panel, Trabajadores trabajadores) {

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

        titulo = new JLabel("Gestión de Servicios");
        titulo.setBounds(10, 50, 400, 25);
        titulo.setFont(nFont18);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo);

        JScrollPane scrollPane = mostrarTablaServicios((Trabajadores) trabajadores);
        panel.add(scrollPane);

        agregarButton = new JButton("Agregar Servicio");
        agregarButton.setBounds(150, 500, 200, 40);
        agregarButton.setHorizontalAlignment(SwingConstants.CENTER);
        agregarButton.setBackground(new Color(206, 123, 86));
        agregarButton.setFont(nFont18);
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarServicio((Trabajadores) trabajadores);
            }
        });
        panel.getRootPane().setDefaultButton(agregarButton); // Para poderlo pulsar con la tecla "INTRO".
        panel.add(agregarButton);

        eliminadosButton = new JButton("Servicios deshabilitados");
        eliminadosButton.setBounds(400, 500, 300, 40);
        eliminadosButton.setHorizontalAlignment(SwingConstants.CENTER);
        eliminadosButton.setBackground(new Color(206, 123, 86));
        eliminadosButton.setFont(nFont18);
        eliminadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mostrandoDeshabilitados) {
                    mostrarServiciosDeshabilitados();
                    eliminadosButton.setText("Mostrar Servicios Activos");
                    mostrandoDeshabilitados = true;
                } else {
                    actualizarTabla(controladorServicios.mostrarServicios());
                    eliminadosButton.setText("Servicios deshabilitados");
                    mostrandoDeshabilitados = false;
                }
            }
        });
        panel.add(eliminadosButton);
    }
    
    // Metodo
    private void volverAtras(Trabajadores trabajadores) {
        new Menu(trabajadores).setVisible(true);
        dispose();
    }

    private JScrollPane mostrarTablaServicios(Trabajadores trabajadores) {
        List<Servicios> listaServicios = controladorServicios.mostrarServicios();
        String[] columnas = {"ID", "Precio Base", "Descripción", "Activo", "Editar"}; // Defineix les columnes de la taula.
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
        
        
        DefaultTableModel opcionesTabla = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };

        tablaServicio = new JTable(opcionesTabla);

        // Añadir el MouseListener para el botón editar
        tablaServicio.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaServicio.rowAtPoint(e.getPoint());
                int column = tablaServicio.columnAtPoint(e.getPoint());
                if (column == 4) { // Si se clicó en la columna "Editar"
                    int idServicio = (int) tablaServicio.getValueAt(row, 0); // Obtener el Id trabajador
                    new EditarServicios(trabajadores, idServicio).setVisible(true);
                    dispose();
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tablaServicio);
        scrollPane.setBounds(50, 100, 600, 300);
        
        return scrollPane;
    }

    private void agregarServicio(Trabajadores trabajadores) {
        new CrearServicios(trabajadores).setVisible(true);
        dispose();
    }

    private void mostrarServiciosDeshabilitados(){
        List<Servicios> listaServicios = controladorServicios.mostrarServiciosEliminados(); // Obté la llista de trabajadores del controlador.
        actualizarTabla(listaServicios);
    }

    private void actualizarTabla(List<Servicios> listaServicios) {
        DefaultTableModel modelo = (DefaultTableModel) tablaServicio.getModel();
        modelo.setRowCount(0); // Limpia la tabla
    
        for (Servicios servicio : listaServicios) {
            modelo.addRow(new Object[]{
                servicio.getId_servicio(),
                servicio.getPrecio_base(),
                servicio.getDescripcion_servicio(), 
                servicio.isServicio_activo(),
                "Editar"
            });
        }
    }
}