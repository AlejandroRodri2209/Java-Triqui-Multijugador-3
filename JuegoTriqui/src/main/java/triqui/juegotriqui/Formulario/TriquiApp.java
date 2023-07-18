/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package triqui.juegotriqui.Formulario;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import triqui.juegotriqui.Modelo.Jugador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TriquiApp extends JFrame {

    private static final long serialVersionUID = 1L;
    private char[][] tablero = new char[5][4];
    private static char[] simbolos = {'X', 'O', 'Y'}; // Simbolos de los jugadores
    private int jugadorActual = 0;
    private JButton[][] buttons = new JButton[5][4];
    private JLabel statusLabel = new JLabel();
    private JLabel turnoLabel = new JLabel(); // Etiqueta para mostrar el jugador que tiene el turno
    private JPanel jugadoresPanel; // Panel para mostrar los nombres de los jugadores
    private List<Jugador> jugadoresSeleccionados;
    private MenuPrincipal menuPrincipal;

    public TriquiApp(List<Jugador> jugadoresSeleccionados, MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        this.jugadoresSeleccionados = jugadoresSeleccionados;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Triqui");
        setPreferredSize(new Dimension(400, 500));
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana al iniciar la aplicación

        for (int i = 0; i < 5; i++) { // Inicializar el tablero con celdas vacías
            for (int j = 0; j < 4; j++) {
                tablero[i][j] = '-';
            }
        }

        // Crear la cuadrícula de botones para el tablero de juego
        JPanel gridPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        gridPanel.setBackground(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                JButton button = new JButton("-");
                button.setFont(new Font("Arial", Font.BOLD, 40));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        movimiento(button);
                    }
                });
                buttons[i][j] = button;
                gridPanel.add(button);
            }
        }

        // Crear el panel de estado
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(Color.WHITE);

        // Etiqueta para mostrar el jugador que tiene el turno
        turnoLabel.setHorizontalAlignment(JLabel.CENTER);
        turnoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusPanel.add(turnoLabel, BorderLayout.EAST);

        // Etiqueta para mostrar el estado del juego
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusPanel.add(statusLabel, BorderLayout.CENTER);

        // Crear el panel de los jugadores
        jugadoresPanel = new JPanel(new GridLayout(1, 3));
        jugadoresPanel.setBackground(Color.WHITE);
        jugadoresPanel.setPreferredSize(new Dimension(400, 50));

        for (Jugador jugador : jugadoresSeleccionados) {
            JLabel jugadorLabel = new JLabel(jugador.getUsuario());
            jugadorLabel.setHorizontalAlignment(JLabel.CENTER);
            jugadorLabel.setFont(new Font("Arial", Font.BOLD, 16));
            jugadoresPanel.add(jugadorLabel);
        }

        // Crear el botón de regresar
        JButton btnRegresar = new JButton("Regresar al Menú");
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenu();
            }
        });

        // Añadir los componentes a la ventana del juego
        add(jugadoresPanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
        add(btnRegresar, BorderLayout.PAGE_END);

        setVisible(true); // Mostrar la ventana del juego

        // Mostrar el jugador que tiene el turno al inicio del juego
        turnoLabel.setText("Turno del jugador " + jugadoresSeleccionados.get(jugadorActual).getUsuario());
    }

    private void movimiento(JButton button) {
        int fila = -1;
        int col = -1;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (button == buttons[i][j]) {
                    fila = i;
                    col = j;
                    break;
                }
            }
        }

        if (tablero[fila][col] == '-') {
            tablero[fila][col] = simbolos[jugadorActual];
            button.setText(String.valueOf(tablero[fila][col]));

            if (hayGanador(simbolos[jugadorActual])) {
                // Mostrar el nombre del jugador que ganó
                String nombreGanador = jugadoresSeleccionados.get(jugadorActual).getUsuario();
                statusLabel.setText("¡El jugador " + nombreGanador + " ha ganado!");
                desactivarBotones();
            } else if (tableroLleno()) {
                statusLabel.setText("¡Empate!");
                desactivarBotones();
            } else {
                jugadorActual = (jugadorActual + 1) % 3;
                String nombreTurno = jugadoresSeleccionados.get(jugadorActual).getUsuario();
                turnoLabel.setText("Turno del jugador " + nombreTurno); // Actualizar la etiqueta de turno
            }

            // Ocultar el nombre de los jugadores que no están en turno
            for (int i = 0; i < 3; i++) {
                JLabel jugadorLabel = (JLabel) jugadoresPanel.getComponent(i);
                if (i == jugadorActual) {
                    jugadorLabel.setVisible(true);
                } else {
                    jugadorLabel.setVisible(false);
                }
            }
        }
    }

    private boolean hayGanador(char simbolo) {
        int i, j, contador;

        for (i = 0; i < 5; i++) {
            contador = 0;
            for (j = 0; j < 4; j++) {
                if (tablero[i][j] == simbolo) {
                    contador++;
                    if (contador == 3) {
                        return true;
                    }
                } else {
                    contador = 0;
                }
            }
        }

        for (j = 0; j < 4; j++) {
            contador = 0;
            for (i = 0; i < 5; i++) {
                if (tablero[i][j] == simbolo) {
                    contador++;
                    if (contador == 3) {
                        return true;
                    }
                } else {
                    contador = 0;
                }
            }
        }

        for (i = 0; i < 5 - 2; i++) {
            for (j = 0; j < 4 - 2; j++) {
                if (tablero[i][j] == simbolo && tablero[i + 1][j + 1] == simbolo && tablero[i + 2][j + 2] == simbolo) {
                    return true;
                }
                if (tablero[i + 2][j] == simbolo && tablero[i + 1][j + 1] == simbolo && tablero[i][j + 2] == simbolo) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean tableroLleno() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (tablero[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private void desactivarBotones() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void regresarAlMenu() {
        setVisible(false);
        menuPrincipal.setVisible(true);
    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(new Runnable() {
        //    public void run() {
        //        List<Jugador> jugadoresSeleccionados = seleccionarJugadores(); // Lógica para seleccionar los jugadores
        //        new TriquiApp(jugadoresSeleccionados, new MenuPrincipal());
        //    }
        //});
    }
}
