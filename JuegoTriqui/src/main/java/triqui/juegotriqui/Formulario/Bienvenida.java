package triqui.juegotriqui.Formulario;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BoxLayout;

public class Bienvenida extends JFrame implements ActionListener {
    private JButton btnIniciar;
    private JLabel lblTitulo;

    public Bienvenida() {
        // Configuración de la ventana de bienvenida
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bienvenido al juego de Triqui");

        // Creación del panel principal con BoxLayout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        getContentPane().add(panel);

        // Creación del botón para iniciar el juego
        btnIniciar = new JButton("Iniciar Juego");
        btnIniciar.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(btnIniciar);

        // Creación del espacio vertical entre el botón y el texto
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Creación del título
        lblTitulo = new JLabel("¡Bienvenido al juego de Triqui!");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblTitulo);

        // Agregar el ActionListener al botón de inicio
        btnIniciar.addActionListener(this);

        // Centrar la ventana en la pantalla
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((pantalla.width - getWidth()) / 2, (pantalla.height - getHeight()) / 2);

        // Ajustar el tamaño de la ventana y mostrarla
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciar) {
            // Acción para el botón "Iniciar Juego"
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        Bienvenida bienvenida = new Bienvenida();
    }
}
