package vista.clientes;

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

import controlador.ControladorClientes;
import modelo.Productos;
import modelo.Trabajadores;
import vista.Menu;
import modelo.Servicios;
import modelo.Clientes;

public class GestionClientes extends JFrame {
    private JLabel volverLabel;
    private JTable tablaCliente;
    private JButton agregarButton;
    private ControladorClientes controladorClientes;
    private Trabajadores trabajadores;
    private Productos productos;
    private Servicios servicios;
    private Clientes clientes;
    private JLabel titulo;

    public GestionClientes(Trabajadores trabajadores, Clientes clientes) {
        this.trabajadores = trabajadores;
        this.clientes = clientes;
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

        titulo = new JLabel("Gestión de Clientes");
        titulo.setBounds(10, 50, 400, 25);
        titulo.setFont(nFont18);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo);

        JScrollPane scrollPane = mostrarTablaClientes();
        panel.add(scrollPane);

        agregarButton = new JButton("Agregar Cliente");
        agregarButton.setBounds(250, 500, 200, 40);
        agregarButton.setHorizontalAlignment(SwingConstants.CENTER);
        agregarButton.setBackground(new Color(206, 123, 86));
        agregarButton.setFont(nFont18);
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
            }
        });
        panel.getRootPane().setDefaultButton(agregarButton); // Para poderlo pulsar con la tecla "INTRO".
        panel.add(agregarButton);
    }
    
    // Metodo
    private void volverAtras() {
        new Menu(trabajadores, servicios, productos, clientes).setVisible(true);
        dispose();
    }

    private JScrollPane mostrarTablaClientes() {
        List<Clientes> listaClientes = controladorClientes.mostrarClientes(); // Obté la llista de trabajadores del controlador.
        String[] columnas = {"Nombre", "Apellido", "Correo", "Teléfono", "Protección de Datos", "Descripción", "Editar"}; // Defineix les columnes de la taula.
        Object[][] datos = new Object[listaClientes.size()][8]; // Crar un array de los trabajadores.

        // Omple l'array de dades amb la informació de cada llibre.
        for (int i = 0; i < listaClientes.size(); i++) {

            Clientes cliente = listaClientes.get(i);
            
            datos[i] = new Object[]{
                cliente.getNombre_cliente(),
                cliente.getApellido_cliente(), 
                cliente.getCorreo_cliente(),
                cliente.getTelefono_cliente(),
                cliente.isProteccion_datos(),
                cliente.getDescripcion_cliente(),
                "Editar"
            };
        }

        // Crea un modelo de tabla personalizado
        DefaultTableModel opcionesTabla = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7; // Solo editable la columna "Editar"
            }
        };

        tablaCliente = new JTable(opcionesTabla);
    
        // Añadir un MouseListener para manejar el clic en la columna "Editar"
        tablaCliente.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaCliente.rowAtPoint(e.getPoint());
                int column = tablaCliente.columnAtPoint(e.getPoint());
                System.out.println("Clicked row: " + row + ", column: " + column);

                if (column == 6) { // Si se clicó en la columna "Editar"
                    Clientes clienteSeleccionado = listaClientes.get(row);
                    int idCliente = clienteSeleccionado.getId_cliente(); // Obtener el DNI
                    System.out.println("Opening EditarClientes for client ID: " + idCliente);

                    try {
                        EditarClientes editarClientes = new EditarClientes(trabajadores, idCliente);
                        editarClientes.setVisible(true);
                        dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.err.println("Error al abrir EditarClientes: " + ex.getMessage());
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaCliente); // Crea un JScrollPane que contindrà la taula de llibres.
        scrollPane.setBounds(50, 100, 600, 300); // Ajusteu la posició i la mida del JScrollPane.
    
        return scrollPane; // Retorna el JScrollPane amb la taula de llibres.
    }

    private void agregarCliente() {
        new CrearClientes(trabajadores).setVisible(true);
        dispose();
    }
}