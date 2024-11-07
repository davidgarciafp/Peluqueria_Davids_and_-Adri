package vista.productos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.ControladorProductos;
import modelo.Productos;
import modelo.Trabajadores;

public class EditarProductos extends JFrame {
    private Integer idProducto;
    private JLabel codigoBarrasLabel;
    private JTextField codigoBarrasField;
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
    private JLabel productoGastadoLabel;
    private JTextField productoGastadoField;
    private JLabel productoActivoLabel;
    private JCheckBox productoActivoCheckBox;
    private JLabel missatgeLabel;
    private JButton guardarProductoButton;
    private JButton volverButton;
    private ControladorProductos controladorProductos;

    private Trabajadores trabajadores;
    private Productos productos;

    public EditarProductos(Trabajadores trabajadores, Integer idProducto) {
        this.trabajadores = trabajadores;
        this.productos = new Productos();
        this.idProducto = idProducto;
        
        controladorProductos = new ControladorProductos(); // Inicializar el controlador.
        setTitle("Peluqueria"); // Pon un titulo a la pagina.
        setSize(800, 600); // Configuracion del tamaño de la pantalla.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Indica que la aplicación se cerrará completamente.
        setLocationRelativeTo(null); // Centra la ventana en la pantalla.

        // Creamos un panel para agregar los componetes que quieras.
        JPanel panel = new JPanel();
        add(panel);
        posicioBotons(panel, productos, idProducto);

        setVisible(true); 
    }

    private void posicioBotons(JPanel panel, Object productos, Integer idProducto) {
        panel.setBackground(new Color(139, 137, 137)); // Canviar de color.
        panel.setLayout(null);


        Font nFont18 = new Font(null, Font.PLAIN, 18);

        codigoBarrasLabel = new JLabel("Codigo de Barras: ");
        codigoBarrasLabel.setBounds(50, 40, 200, 25);
        codigoBarrasLabel.setFont(nFont18);
        panel.add(codigoBarrasLabel);

        codigoBarrasField = new JTextField(20);
        codigoBarrasField.setBounds(250, 40, 200, 25);
        codigoBarrasField.setBackground(new Color(255, 255, 255)); 
        panel.add(codigoBarrasField);


        nombreLabel = new JLabel("Nombre: ");
        nombreLabel.setBounds(50, 80, 200, 25);
        nombreLabel.setFont(nFont18);
        panel.add(nombreLabel);

        nombreField = new JTextField(20);
        nombreField.setBounds(250, 80, 200, 25);
        nombreField.setBackground(new Color(255, 255, 255)); 
        panel.add(nombreField);


        marcaLabel = new JLabel("marca: ");
        marcaLabel.setBounds(50, 120, 200, 25);
        marcaLabel.setFont(nFont18);
        panel.add(marcaLabel);

        marcaField = new JTextField(20);
        marcaField.setBounds(250, 120, 200, 25);
        marcaField.setBackground(new Color(255, 255, 255)); 
        panel.add(marcaField);

        precioLabel = new JLabel("Precio: ");
        precioLabel.setBounds(50, 160, 200, 25);
        precioLabel.setFont(nFont18);
        panel.add(precioLabel);

        precioField = new JTextField(20);
        precioField.setBounds(250, 160, 200, 25);
        precioField.setBackground(new Color(255, 255, 255)); 
        panel.add(precioField);


        descripcionLabel = new JLabel("Descripción: ");
        descripcionLabel.setBounds(50, 200, 200, 25);
        descripcionLabel.setFont(nFont18);
        panel.add(descripcionLabel);

        descripcionField = new JTextField(20);
        descripcionField.setBounds(250, 200, 200, 25);
        descripcionField.setBackground(new Color(255, 255, 255)); 
        panel.add(descripcionField);


        cantidadLabel = new JLabel("Cantidad: ");
        cantidadLabel.setBounds(50, 240, 200, 25);
        cantidadLabel.setFont(nFont18);
        panel.add(cantidadLabel);

        cantidadField = new JTextField(20);
        cantidadField.setBounds(250, 240, 200, 25);
        cantidadField.setBackground(new Color(255, 255, 255)); 
        panel.add(cantidadField);

        productoGastadoLabel = new JLabel("Vendido(1)/Gastado(2): ");
        productoGastadoLabel.setBounds(50, 280, 200, 25);
        productoGastadoLabel.setFont(nFont18);
        panel.add(productoGastadoLabel);

        productoGastadoField = new JTextField(20);
        productoGastadoField.setBounds(250, 280, 200, 25);
        productoGastadoField.setBackground(new Color(255, 255, 255)); 
        panel.add(productoGastadoField);


        productoActivoLabel = new JLabel("Habiltar / Deshabilitar: ");
        productoActivoLabel.setBounds(50, 320, 200, 25);
        productoActivoLabel.setFont(nFont18);
        panel.add(productoActivoLabel);

        productoActivoCheckBox = new JCheckBox();
        productoActivoCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        productoActivoCheckBox.setBounds(250, 320, 20, 25);
        productoActivoCheckBox.setBackground(new Color(255, 255, 255));
        panel.add(productoActivoCheckBox);


        missatgeLabel = new JLabel("");
        missatgeLabel.setBounds(50, 400, 400, 25);
        missatgeLabel.setFont(nFont18);
        missatgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(missatgeLabel);


        guardarProductoButton = new JButton("Guardar");
        guardarProductoButton.setBounds(150, 450, 200, 40);
        guardarProductoButton.setHorizontalAlignment(SwingConstants.CENTER);
        guardarProductoButton.setBackground(new Color(218, 247, 166));
        guardarProductoButton.setFont(nFont18);
        guardarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarProductos();
            }
        });
        panel.getRootPane().setDefaultButton(guardarProductoButton); // Para poderlo pulsar con la tecla "INTRO".
        panel.add(guardarProductoButton);

        
        volverButton = new JButton("Volver");
        volverButton.setBounds(150, 500, 200, 40);
        volverButton.setHorizontalAlignment(SwingConstants.CENTER);
        volverButton.setBackground(new Color(240, 128, 128));
        volverButton.setFont(nFont18);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAtras();
            }
        });
        panel.add(volverButton);


        mostrarDatosProducto(idProducto); // Para rellenar los campos que ya tiene el Trabajador.
    }

    // Metodos
    private void mostrarDatosProducto(Integer idProducto) {
        Productos productos = controladorProductos.buscarProducto(idProducto);

        try {
            if (productos != null) {
                codigoBarrasField.setText(productos.getCodigo_barras());
                nombreField.setText(productos.getNombre_producto());
                marcaField.setText(productos.getMarca());
                precioField.setText(productos.getPrecio_producto().toString());
                descripcionField.setText(productos.getDescripcion_producto());
                cantidadField.setText(productos.getCantidad_disponible().toString());
                productoGastadoField.setText(productos.getProducto_gastado().toString());
                productoActivoCheckBox.setSelected(productos.isProducto_activo());
            } else {
                missatgeLabel.setText("Producto no encontrado");
                missatgeLabel.setForeground(Color.BLACK);
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

    private void editarProductos() {
        String  codigoBarras = codigoBarrasField.getText();
        String nombreProducto =  nombreField.getText();
        String  marca = marcaField.getText();
        String precioText = precioField.getText();
        BigDecimal precioProducto = new BigDecimal(precioText);
        String descripcionProducto = descripcionField.getText();
        String cantidadText = cantidadField.getText();
        int cantidadDisponible = Integer.parseInt(cantidadText);
        String productoGastadoText = productoGastadoField.getText();
        int productoGastado ;//= Integer.parseInt(productoGastadoText);
        Boolean productoActivo = productoActivoCheckBox.isSelected();

        try {
            productoGastado = Integer.parseInt(productoGastadoText);
            if (productoGastado < 0 || productoGastado > 2) {
                missatgeLabel.setText("El valor de Producto Gastado debe ser 0, 1 o 2");
                missatgeLabel.setForeground(Color.RED);
                return; // Salir del método si el valor no es válido
            }
        } catch (NumberFormatException e) {
            missatgeLabel.setText("El valor de Producto Gastado debe ser un número");
            missatgeLabel.setForeground(Color.RED);
            return; // Salir del método si el valor no es un número
        }

        String missatge = "";
        Color colorMissatge = Color.BLUE;


        // Validar que el texto no esté vacío y sea un número válido
        if (precioText != null && !precioText.trim().isEmpty()) {
            try {
                // Intentar convertir el texto a BigDecimal
                precioProducto = new BigDecimal(precioText);
            } catch (NumberFormatException e) {
                // Si hay un error, comision ya está en 0.00

            }
        }

        
        if (nombreProducto.isEmpty() || marca.isEmpty() || precioText.isEmpty() || descripcionProducto.isEmpty() ||  cantidadText.isEmpty()) {

            missatge = "Tienes que rellenar todos los campos";
        } else {


            try {
                boolean resultat = controladorProductos.modificarProductos(
                    this.idProducto,
                    codigoBarras,
                    nombreProducto,
                    marca,
                    precioProducto,
                    descripcionProducto,
                    cantidadDisponible,
                    productoGastado,
                    productoActivo
                );
                
                if (resultat) {
                    missatge = "¡Producto Actualizado!";
                    colorMissatge = Color.GREEN;
                }
            } catch (RuntimeException ex) {
                if (ex.getMessage().equals("BaseDatos NO encontrada")) {
                    missatge = "Base de datos no encontrada";
                    colorMissatge = Color.BLACK;
                } else {
                    ex.printStackTrace();
                }
            }
        }
        missatgeLabel.setText(missatge);
        missatgeLabel.setForeground(colorMissatge);
    }

    private void volverAtras() {
        new GestionProductos(trabajadores, productos).setVisible(true);
        dispose();
    }
}


