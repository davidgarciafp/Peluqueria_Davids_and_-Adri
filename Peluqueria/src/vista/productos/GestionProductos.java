package vista.productos;

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

import controlador.ControladorProductos;
import modelo.Productos;
import modelo.Trabajadores;
import vista.Menu;

public class GestionProductos extends JFrame {
    private JLabel volverLabel;
    private JTable tablaProducto;
    private JButton agregarButton;
    private JButton eliminadosButton;
    private ControladorProductos controladorProductos;
    private boolean mostrandoDeshabilitados = false;
    private JLabel titulo;

    public GestionProductos(Trabajadores trabajadores) {
        controladorProductos = new ControladorProductos(); // Inicializar el controlador.
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

        titulo = new JLabel("Gestión de Productos");
        titulo.setBounds(10, 50, 400, 25);
        titulo.setFont(nFont18);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo);

        JScrollPane scrollPane = mostrarTablaProductos((Trabajadores) trabajadores);
        panel.add(scrollPane);

        agregarButton = new JButton("Agregar Producto");
        agregarButton.setBounds(150, 500, 200, 40);
        agregarButton.setHorizontalAlignment(SwingConstants.CENTER);
        agregarButton.setBackground(new Color(206, 123, 86));
        agregarButton.setFont(nFont18);
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto((Trabajadores) trabajadores);
            }
        });
        panel.getRootPane().setDefaultButton(agregarButton); // Para poderlo pulsar con la tecla "INTRO".
        panel.add(agregarButton);
        

        eliminadosButton = new JButton("Productos deshabilitados");
        eliminadosButton.setBounds(400, 500, 300, 40);
        eliminadosButton.setHorizontalAlignment(SwingConstants.CENTER);
        eliminadosButton.setBackground(new Color(206, 123, 86));
        eliminadosButton.setFont(nFont18);
        eliminadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mostrandoDeshabilitados) {
                    mostrarProductosDeshabilitados();
                    eliminadosButton.setText("Mostrar Productos Activos");
                    mostrandoDeshabilitados = true;
                } else {
                    actualizarTabla(controladorProductos.mostrarProductos());
                    eliminadosButton.setText("Productos deshabilitados");
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

    private JScrollPane mostrarTablaProductos(Trabajadores trabajadores) {
        List<Productos> listaProductos = controladorProductos.mostrarProductos();
        String[] columnas = {"ID", "Código de Barras", "Nombre", "Marca", "Precio", "Descripción", "Cantidad Disponible", "Producto Activo", "Editar"};
        Object[][] datos = new Object[listaProductos.size()][9]; // Crar un array de los trabajadores.

        // Omple l'array de dades amb la informació de cada llibre.
        for (int i = 0; i < listaProductos.size(); i++) {

            Productos producto = listaProductos.get(i);
            
            datos[i] = new Object[]{
                producto.getId_producto(),
                producto.getCodigo_barras(),
                producto.getNombre_producto(),
                producto.getMarca(),
                producto.getPrecio_producto(),
                producto.getDescripcion_producto(),
                producto.getCantidad_disponible(),
                producto.isProducto_activo(),
                "Editar"
            };
        }
        
        
        
        DefaultTableModel opcionesTabla = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8;
            }
        };
        
        tablaProducto = new JTable(opcionesTabla);

        // Añadir el MouseListener para el botón editar
        tablaProducto.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaProducto.rowAtPoint(e.getPoint());
                int column = tablaProducto.columnAtPoint(e.getPoint());
                if (column == 8) { // Si se clicó en la columna "Editar"
                    int idProducto = (int) tablaProducto.getValueAt(row, 0); // Obtener el Id trabajador
                    new EditarProductos(trabajadores, idProducto).setVisible(true);
                    dispose();
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tablaProducto);
        scrollPane.setBounds(50, 100, 600, 300);
        
        return scrollPane;
    }

    private void agregarProducto(Trabajadores trabajadores) {
        new CrearProductos(trabajadores).setVisible(true);
        dispose();
    }
    
    private void mostrarProductosDeshabilitados() {
        List<Productos> listaProductos = controladorProductos.mostrarProductosEliminados(); // Obté la llista de trabajadores del controlador.
        actualizarTabla(listaProductos);
    }

    private void actualizarTabla(List<Productos> listaProductos) {
        DefaultTableModel modelo = (DefaultTableModel) tablaProducto.getModel();
        modelo.setRowCount(0); // Limpia la tabla
    
        for (Productos producto : listaProductos) {
            modelo.addRow(new Object[]{
                producto.getId_producto(),
                producto.getCodigo_barras(),
                producto.getNombre_producto(),
                producto.getMarca(), 
                producto.getPrecio_producto(),
                producto.getDescripcion_producto(),
                producto.getCantidad_disponible(),
                producto.isProducto_activo(),
                "Editar"
            });
        }
    }
}