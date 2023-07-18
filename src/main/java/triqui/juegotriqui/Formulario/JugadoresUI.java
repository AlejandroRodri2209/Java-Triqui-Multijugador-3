package triqui.juegotriqui.Formulario;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import triqui.juegotriqui.Conexion.ConexionBD;
import triqui.juegotriqui.Dao.JugadorDAO;
import triqui.juegotriqui.Modelo.Jugador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;

public class JugadoresUI extends JFrame {
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTable table;

    private JugadorDAO jugadorDAO;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JugadoresUI frame = new JugadoresUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JugadoresUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Jugadores");
        setSize(500, 400);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((pantalla.width - getWidth()) / 2, (pantalla.height - getHeight()) / 2);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(10, 11, 60, 14);
        contentPane.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(80, 8, 150, 20);
        contentPane.add(txtUsuario);
        txtUsuario.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 36, 60, 14);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(80, 33, 150, 20);
        contentPane.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(10, 61, 60, 14);
        contentPane.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(80, 58, 150, 20);
        contentPane.add(txtApellido);
        txtApellido.setColumns(10);

        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtUsuario.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(JugadoresUI.this, "Ingrese usuario, nombre y apellido", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String usuario = txtUsuario.getText();
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                Jugador jugadorExistente = jugadorDAO.obtenerPorUsuario(usuario);
                if (jugadorExistente != null) {
                    JOptionPane.showMessageDialog(JugadoresUI.this, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Jugador jugador = new Jugador(usuario, nombre, apellido);
                jugadorDAO.insertar(jugador);
                actualizarTabla();
            }
        });
        btnInsertar.setBounds(10, 86, 89, 23);
        contentPane.add(btnInsertar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtUsuario.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(JugadoresUI.this, "Ingrese usuario, nombre y apellido", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(JugadoresUI.this, "Seleccione una fila para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String usuario = txtUsuario.getText();
                    String nombre = txtNombre.getText();
                    String apellido = txtApellido.getText();
                    Jugador jugador = new Jugador(usuario, nombre, apellido);
                    jugadorDAO.actualizar(jugador);
                    actualizarTabla();
                }
            }
        });
        btnActualizar.setBounds(109, 86, 89, 23);
        contentPane.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(JugadoresUI.this, "Seleccione una fila para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String usuario = table.getValueAt(filaSeleccionada, 0).toString();
                    jugadorDAO.eliminar(usuario);
                    actualizarTabla();
                }
            }
        });
        btnEliminar.setBounds(208, 86, 89, 23);
        contentPane.add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtUsuario.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
            }
        });
        btnLimpiar.setBounds(307, 86, 89, 23);
        contentPane.add(btnLimpiar);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Usuario", "Nombre", "Apellido" }));
        table.setBounds(10, 120, 464, 200);
        contentPane.add(table);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                setVisible(false);
            }
        });
        btnRegresar.setBounds(10, 330, 89, 23);
        contentPane.add(btnRegresar);

        jugadorDAO = new JugadorDAO(ConexionBD.getConnection());
        actualizarTabla();
    }

    private void actualizarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);
        List<Jugador> jugadores = jugadorDAO.obtenerTodos();
        for (Jugador jugador : jugadores) {
            Object[] fila = new Object[3];
            fila[0] = jugador.getUsuario();
            fila[1] = jugador.getNombre();
            fila[2] = jugador.getApellido();
            modelo.addRow(fila);
        }
    }
}
