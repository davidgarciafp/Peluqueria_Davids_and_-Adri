package vista;

import java.awt.Color;
//import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Trabajadores;


public class GestionServicios extends JFrame {

    private JLabel volverLabel;

    public GestionServicios(Trabajadores trabajadores) {
        // Inicializar el controlador.
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

        panel.setBackground(new Color(0, 207, 201)); // Canviar de color.
        panel.setLayout(null);

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
    }

    
    private void volverAtras(Trabajadores trabajadores) {
        new Menu(trabajadores).setVisible(true);
        dispose();
    }
}