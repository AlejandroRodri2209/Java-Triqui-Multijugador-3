/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package triqui.juegotriqui.Formulario;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.GridLayout;

public class MenuPrincipal extends JFrame implements ActionListener {
    private JButton btnCrudJugadores;
    private JButton btnSeleccionarJugadores;
    private JButton btnSalir;

    public MenuPrincipal() {
        // Configuración de la ventana principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Menú principal");

        // Creación del panel principal con un GridLayout
        JPanel panel = new JPanel(new GridLayout(3, 1));
        getContentPane().add(panel);

        // Creación de los botones de comando
        btnCrudJugadores = new JButton("CRUD Jugadores");
        btnSeleccionarJugadores = new JButton("Seleccionar Jugadores");
        btnSalir = new JButton("Salir");

        // Agregar los botones al panel principal
        panel.add(btnCrudJugadores);
        panel.add(btnSeleccionarJugadores);
        panel.add(btnSalir);

        // Agregar el ActionListener a los botones de comando
        btnCrudJugadores.addActionListener(this);
        btnSeleccionarJugadores.addActionListener(this);
        btnSalir.addActionListener(this);

        // Ajustar el tamaño de la ventana y ocultarla temporalmente
        setSize(400, 300);
        setVisible(false);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);

        // Mostrar la ventana después de centrarla
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == btnCrudJugadores) {
            // Acción para el botón "CRUD Jugadores"
            setVisible(false);
            JugadoresUI jugadoresUI = new JugadoresUI();
            jugadoresUI.setVisible(true);
        } else if (e.getSource() == btnSeleccionarJugadores) {
            // Acción para el botón "Seleccionar Jugadores"
            SeleccionarJugadoresUI seleccionarJugadoresUI = new SeleccionarJugadoresUI(this); // Pasar una instancia de MenuPrincipal
            seleccionarJugadoresUI.setVisible(true);
            setVisible(false); // Ocultar la ventana del menú principal
        } else if (e.getSource() == btnSalir) {
            // Acción para el botón "Salir"
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        // Crear una instancia de MenuPrincipal y mostrarla
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setSize(600, 400); // Cambiar el tamaño de la ventana "Bienvenido al juego"
    }
}

