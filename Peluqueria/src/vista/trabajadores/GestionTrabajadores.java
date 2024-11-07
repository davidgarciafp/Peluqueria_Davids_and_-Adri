package vista.trabajadores;

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
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import javax.swing.JPanel;

import controlador.ControladorTrabajadores;
import modelo.Servicios;
import modelo.Clientes;
import modelo.Productos;
import modelo.Trabajadores;
import vista.Menu;

public class GestionTrabajadores extends JFrame {
    //private JLabel mensaje;
    private JLabel volverLabel;
    private JTable tablaTrabajador;
    private JButton agregarButton;
    private JButton eliminadosButton;
    private ControladorTrabajadores controladorTrabajadores;
    private Trabajadores trabajadores;
    private Servicios servicios;
    private Productos productos;
    private Clientes clientes;
    private boolean mostrandoDeshabilitados = false;
    private JLabel titulo;


    public GestionTrabajadores(Trabajadores trabajadores) {
        this.trabajadores = trabajadores;

        controladorTrabajadores = new ControladorTrabajadores(); // Inicializar el controlador.
        setTitle("Peluqueria"); // Pon un titulo a la pagina.
        setSize(800, 600); // Configuracion del tamaño de la pantalla.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Indica que la aplicación se cerrará completamente.
        setLocationRelativeTo(null); // Centra la ventana en la pantalla.

        // Creamos un panel para agregar los componetes que quieras.
        JPanel panel = new JPanel();
        add(panel);
        posicioBotons(panel);

        setVisible(true); 
    }
    private void posicioBotons(JPanel panel) {

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
                volverAtras();
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

        titulo = new JLabel("Gestión de Trabajadores");
        titulo.setBounds(10, 50, 400, 25);
        titulo.setFont(nFont18);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo);

        JScrollPane scrollPane = mostrarTablaTrabajadores((Trabajadores) trabajadores);
        panel.add(scrollPane);

        agregarButton = new JButton("Agregar Trabajador");
        agregarButton.setBounds(150, 500, 200, 40);
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

        eliminadosButton = new JButton("Trabajadores deshabilitados");
        eliminadosButton.setBounds(400, 500, 300, 40);
        eliminadosButton.setHorizontalAlignment(SwingConstants.CENTER);
        eliminadosButton.setBackground(new Color(206, 123, 86));
        eliminadosButton.setFont(nFont18);
        eliminadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mostrandoDeshabilitados) {
                    mostrarTrabajadoresDeshabilitados();
                    eliminadosButton.setText("Mostrar Trabajadores Activos");
                    mostrandoDeshabilitados = true;
                } else {
                    actualizarTabla(controladorTrabajadores.mostrarTrabajadores());
                    eliminadosButton.setText("Trabajadores deshabilitados");
                    mostrandoDeshabilitados = false;
                }
            }
        });
        panel.add(eliminadosButton);
    }
    
    // Metodo
    private void volverAtras() {
        new Menu(this.trabajadores, this.servicios, productos, clientes).setVisible(true);
        dispose();
    }

    private JScrollPane mostrarTablaTrabajadores(Trabajadores trabajadores) {
        String[] columnas = {"Nombre", "Apellido", "Correo", "Telèfon", "Rol", "Alta", "Comisión Producto", "Comisión Servicio", "Editar"}; // Defineix les columnes de la taula.
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8;
            }
        };
        tablaTrabajador = new JTable(modelo);
        
        // Añadir el MouseListener para el botón editar
        tablaTrabajador.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaTrabajador.rowAtPoint(e.getPoint());
                int column = tablaTrabajador.columnAtPoint(e.getPoint());
                if (column == 8) {
                    List<Trabajadores> listaProductos = mostrandoDeshabilitados ? 
                    controladorTrabajadores.mostrarTrabajadoresEliminados() : 
                        controladorTrabajadores.mostrarTrabajadores();
                    Trabajadores productoSeleccionado = listaProductos.get(row);
                    int idTrabajador = productoSeleccionado.getIdTrabajador();
                    new EditarTrabajadores(trabajadores, idTrabajador).setVisible(true);
                    dispose();
                }
            }
        });
    
        // Mostrar los productos activos inicialmente
        actualizarTabla(controladorTrabajadores.mostrarTrabajadores());
    
        JScrollPane scrollPane = new JScrollPane(tablaTrabajador);
        scrollPane.setBounds(50, 100, 600, 300);
        
        return scrollPane;
    }

    private void agregarTrabajador(Trabajadores trabajadores) {
        new CrearTrabajadores(trabajadores).setVisible(true);
        dispose();
    }

    private void mostrarTrabajadoresDeshabilitados(){
        List<Trabajadores> listaTrabajadores = controladorTrabajadores.mostrarTrabajadoresEliminados(); // Obté la llista de trabajadores del controlador.
        actualizarTabla(listaTrabajadores);
    }

    private void actualizarTabla(List<Trabajadores> listaTrabajadores) {
        DefaultTableModel modelo = (DefaultTableModel) tablaTrabajador.getModel();
        modelo.setRowCount(0); // Limpia la tabla
    
        for (Trabajadores trabajador : listaTrabajadores) {
            modelo.addRow(new Object[]{
                trabajador.getNombreTrabajador(),
                trabajador.getApellidoTrabajador(), 
                trabajador.getCorreoTrabajador(),
                trabajador.getTelefonoTrabajador(),
                trabajador.isTipoTrabajador(),
                trabajador.isTrabajadorActivo(),
                trabajador.getComisionProducto(),
                trabajador.getComisionServicio(),
                "Editar"
            });
        }
    }
}