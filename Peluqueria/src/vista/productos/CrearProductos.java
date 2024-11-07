package vista.productos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.ControladorProductos;
import modelo.Productos;
import modelo.Trabajadores;

public class CrearProductos extends JFrame {
    private JLabel nombreLabel;
    private JTextField nombreField;
    private JLabel marcaLabel;
    private JTextField marcaField;
    private JLabel precioLabel;
    private JTextField precioField;
    private JLabel descripcionLabel;
    private JTextField descripcionField;
    private JLabel cantidadLabel;
    private JTextField cantidadField;
    private JLabel missatgeLabel;
    private JButton agregarProductoButton;
    private JButton volverButton;
    private ControladorProductos controladorProductos;

    private Trabajadores trabajadores;
    private Productos productos;

    public CrearProductos(Trabajadores trabajadores) {
        this.trabajadores =  trabajadores;
        this.productos = new Productos();

        controladorProductos = new ControladorProductos(); // Inicializar el controlador.
        setTitle("Peluqueria"); // Pon un titulo a la pagina.
        setSize(800, 600); // Configuracion del tamaño de la pantalla.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Indica que la aplicación se cerrará completamente.
        setLocationRelativeTo(null); // Centra la ventana en la pantalla.

        // Creamos un panel para agregar los componetes que quieras.
        JPanel panel = new JPanel();
        add(panel);
        posicioBotons(panel, productos);

        setVisible(true); 
    }

    private void posicioBotons(JPanel panel, Object productos) {
        panel.setBackground(new Color(139, 137, 137)); // Canviar de color.
        panel.setLayout(null);


        Font nFont18 = new Font(null, Font.PLAIN, 18);




        nombreLabel = new JLabel("Nombre: ");
        nombreLabel.setBounds(300, 80, 200, 25);
        nombreLabel.setFont(nFont18);
        panel.add(nombreLabel);

        nombreField = new JTextField(20);
        nombreField.setBounds(500, 80, 200, 25);
        nombreField.setBackground(new Color(255, 255, 255)); 
        panel.add(nombreField);


        marcaLabel = new JLabel("Marca: ");
        marcaLabel.setBounds(300, 120, 200, 25);
        marcaLabel.setFont(nFont18);
        panel.add(marcaLabel);

        marcaField = new JTextField(20);
        marcaField.setBounds(500, 120, 200, 25);
        marcaField.setBackground(new Color(255, 255, 255)); 
        panel.add(marcaField);


        precioLabel = new JLabel("Precio: ");
        precioLabel.setBounds(300, 160, 200, 25);
        precioLabel.setFont(nFont18);
        panel.add(precioLabel);

        precioField  = new JTextField(20);
        precioField.setBounds(500, 160, 200, 25);
        precioField.setBackground(new Color(255, 255, 255)); 
        panel.add(precioField);

        descripcionLabel = new JLabel("Descripción: ");
        descripcionLabel.setBounds(300, 200, 200, 25);
        descripcionLabel.setFont(nFont18);
        panel.add(descripcionLabel);

        descripcionField = new JTextField(20);
        descripcionField.setBounds(500, 200, 200, 25);
        descripcionField.setBackground(new Color(255, 255, 255)); 
        panel.add(descripcionField);

        cantidadLabel = new JLabel("Cantidad Disponible: ");
        cantidadLabel.setBounds(300, 240, 200, 25);
        cantidadLabel.setFont(nFont18);
        panel.add(cantidadLabel);

        cantidadField = new JTextField(20);
        cantidadField.setBounds(500, 240, 200, 25);
        cantidadField.setBackground(new Color(255, 255, 255)); 
        panel.add(cantidadField);


        missatgeLabel = new JLabel("");
        missatgeLabel.setBounds(500, 400, 200, 25);
        missatgeLabel.setFont(nFont18);
        missatgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(missatgeLabel);

        agregarProductoButton = new JButton("Crear Producto");
        agregarProductoButton.setBounds(500, 500, 200, 40);
        agregarProductoButton.setHorizontalAlignment(SwingConstants.CENTER);
        agregarProductoButton.setBackground(new Color(206, 63, 86));
        agregarProductoButton.setFont(nFont18);
        agregarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearProducto();
            }
        });
        panel.getRootPane().setDefaultButton(agregarProductoButton); // Para poderlo pulsar con la tecla "INTRO".
        panel.add(agregarProductoButton);

        
        volverButton = new JButton("Volver");
        volverButton.setBounds(50, 20, 200, 40);
        volverButton.setHorizontalAlignment(SwingConstants.CENTER);
        volverButton.setBackground(new Color(105, 123, 86));
        volverButton.setFont(nFont18);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAtras((Productos) productos);
            }
        });
        panel.add(volverButton);
    }

    // Metodos
    private  void volverAtras(Productos productos) {
        new GestionProductos(trabajadores, productos).setVisible(true);
        dispose();
    }

    private void crearProducto() {
        String precioText = precioField.getText().trim();
        String cantidadText = cantidadField.getText().trim();
        String nombreProducto = nombreField.getText();
        String marca =  marcaField.getText();
        BigDecimal precioProducto = precioText.isEmpty() ? BigDecimal.ZERO : new BigDecimal(precioText);
        String descripcionProducto = descripcionField.getText();
        Integer cantidadDisponible = cantidadText.isEmpty() ? 0 : Integer.parseInt(cantidadText);
        Integer productoGastado = 0;
        Boolean productoActivo = true; // Jefe / Emlpeado;
        

        String missatge = "";
        Color colorMissatge = Color.BLUE;
        
        if (descripcionField.getText().isEmpty() || nombreField.getText().isEmpty() || marcaField.getText().isEmpty()) {

            missatge = "Tienes que rellenar todos los campos";
        } else {
            Productos productos = new Productos();
            productos.setNombre_producto(nombreProducto);
            productos.setMarca(marca);
            productos.setPrecio_producto(precioProducto);
            productos.setDescripcion_producto(descripcionProducto);
            productos.setCantidad_disponible(cantidadDisponible);
            productos.setProducto_gastado(productoGastado);
            productos.setProducto_activo(productoActivo);


            try {
                boolean resultat = controladorProductos.anyadirProductos(productos);
                
                if (resultat) {
                    missatge = "Producto Creado!";
                    colorMissatge = Color.GREEN;
                }
                else {
                    missatge = "Error al crear el producto";
                    colorMissatge = Color.RED;
                }
            } catch (RuntimeException ex) {
                if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                    missatge = "Base de datos no encontrada";
                    colorMissatge = Color.BLACK;
                } else {
                    ex.printStackTrace();
                }
            }
        }if (missatgeLabel != null) {
            missatgeLabel.setText(missatge);
            missatgeLabel.setForeground(colorMissatge);
        } else {
            System.out.println("Error: missatgeLabel es nulo");
        }
    }
}
