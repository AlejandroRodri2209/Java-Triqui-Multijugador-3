/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package triqui.juegotriqui.Formulario;

import triqui.juegotriqui.Conexion.ConexionBD;
import triqui.juegotriqui.Dao.JugadorDAO;
import triqui.juegotriqui.Modelo.Jugador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SeleccionarJugadoresUI extends JFrame {

    private JPanel contentPane;
    private JList<String> jugadoresList;
    private DefaultListModel<String> listModel;
    private JButton btnSeleccionar;
    private JButton btnRegresar;
    private JButton btnIniciarJuego; // Botón para iniciar el juego
    private List<Jugador> jugadoresSeleccionados;

    private JugadorDAO jugadorDAO;
    private MenuPrincipal menuPrincipal;

    public SeleccionarJugadoresUI(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Seleccionar Jugadores");
        setSize(400, 300);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((pantalla.width - getWidth()) / 2, (pantalla.height - getHeight()) / 2);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        listModel = new DefaultListModel<>();
        jugadoresList = new JList<>(listModel);
        jugadoresList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPane.setViewportView(jugadoresList);

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        btnSeleccionar = new JButton("Seleccionar");
        buttonPanel.add(btnSeleccionar);

        btnRegresar = new JButton("Regresar");
        buttonPanel.add(btnRegresar);

        btnIniciarJuego = new JButton("Iniciar Juego"); // Botón para iniciar el juego
        buttonPanel.add(btnIniciarJuego); // Agregar el botón al panel

        jugadoresSeleccionados = new ArrayList<>();

        jugadorDAO = new JugadorDAO(ConexionBD.getConnection());
        List<Jugador> jugadores = jugadorDAO.obtenerTodos();
        for (Jugador jugador : jugadores) {
            listModel.addElement(jugador.getUsuario());
        }

        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = jugadoresList.getSelectedIndices();
                for (int i : selectedIndices) {
                    Jugador jugador = jugadores.get(i);
                    if (!jugadoresSeleccionados.contains(jugador)) {
                        jugadoresSeleccionados.add(jugador);
                    }
                }
                JOptionPane.showMessageDialog(SeleccionarJugadoresUI.this, "Jugadores seleccionados: " + jugadoresSeleccionados.size(), "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuPrincipal.setVisible(true);
                dispose();
            }
        });

        // Acción del botón para iniciar el juego
        btnIniciarJuego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jugadoresSeleccionados.size() == 3) {
                    TriquiApp triquiApp = new TriquiApp(jugadoresSeleccionados, menuPrincipal);
                    triquiApp.setVisible(true);
                    dispose(); // Cerrar la ventana actual
                } else {
                    JOptionPane.showMessageDialog(SeleccionarJugadoresUI.this, "Debe seleccionar exactamente 3 jugadores para iniciar el juego", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}