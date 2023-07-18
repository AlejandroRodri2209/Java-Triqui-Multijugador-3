package triqui.juegotriqui.Formulario;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.UIManager;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Bienvenida extends JFrame implements ActionListener {
    private JButton btnIniciar;
    private JLabel lblTitulo;

    public Bienvenida() {
        try {
            // Establecer el aspecto NimbusLookAndFeel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configuración de la ventana de bienvenida
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bienvenido Triqui Evolution Infinite");

        // Creación del panel principal con BorderLayout
        BackgroundPanel panel = new BackgroundPanel("C:\\\\Users\\\\Aleja\\\\Documents\\\\NetBeansProjects\\\\JuegoTriqui\\\\src\\\\main\\\\java\\\\triqui\\\\juegotriqui\\\\Imagen\\\\Fondo.jpeg"); // Reemplaza "fondo.jpg" con la ruta de tu archivo de imagen de fondo en formato JPEG
        panel.setLayout(new BorderLayout());
        setContentPane(panel);

        // Creación del botón para iniciar el juego
        btnIniciar = new JButton("¡Vamos a Jugar!");
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 24));
        btnIniciar.setFocusPainted(false);
        panel.add(btnIniciar, BorderLayout.SOUTH);

        // Creación del espacio vertical entre el botón y el título
        panel.add(Box.createRigidArea(new Dimension(0, 30)), BorderLayout.NORTH);

        // Creación del título
        lblTitulo = new JLabel("¡Bienvenido al Triqui Evolution Infinite!");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        panel.add(lblTitulo, BorderLayout.CENTER);

        // Agregar el ActionListener al botón de inicio
        btnIniciar.addActionListener(this);

        // Ajustar el tamaño de la ventana y mostrarla
        setSize(800, 600); // Cambia el tamaño según el tamaño de tu imagen de fondo
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciar) {
            // Acción para el botón "¡Vamos a Jugar!"
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        Bienvenida bienvenida = new Bienvenida();
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

// Dentro de la clase BackgroundPanel, en el método paintComponent:

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (backgroundImage != null) {
        int width = getWidth();
        int height = getHeight();
        int imgWidth = backgroundImage.getWidth(this);
        int imgHeight = backgroundImage.getHeight(this);

        // Escalar la imagen para que se ajuste manteniendo la relación de aspecto
        int newWidth, newHeight;
        double imgAspectRatio = (double) imgWidth / imgHeight;
        double panelAspectRatio = (double) width / height;

        if (imgAspectRatio > panelAspectRatio) {
            // La imagen es más ancha que el panel
            newWidth = width;
            newHeight = (int) (width / imgAspectRatio);
        } else {
            // La imagen es más alta que el panel o tiene la misma relación de aspecto
            newHeight = height;
            newWidth = (int) (height * imgAspectRatio);
        }

        int x = (width - newWidth) / 2;
        int y = (height - newHeight) / 2;

        g.drawImage(backgroundImage, x, y, newWidth, newHeight, this);
    }
}
}
