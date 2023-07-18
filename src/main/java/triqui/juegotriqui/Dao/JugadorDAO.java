/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package triqui.juegotriqui.Dao;

import triqui.juegotriqui.Modelo.Jugador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAO {
   private Connection conexion;

   public JugadorDAO(Connection conexion) {
      this.conexion = conexion;
   }

   public void insertar(Jugador jugador) {
      String sql = "INSERT INTO jugadores (usuario, nombre, apellido) VALUES (?, ?, ?)";
      try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
         pstmt.setString(1, jugador.getUsuario());
         pstmt.setString(2, jugador.getNombre());
         pstmt.setString(3, jugador.getApellido());
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void actualizar(Jugador jugador) {
      String sql = "UPDATE jugadores SET nombre = ?, apellido = ? WHERE usuario = ?";
      try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
         pstmt.setString(1, jugador.getNombre());
         pstmt.setString(2, jugador.getApellido());
         pstmt.setString(3, jugador.getUsuario());
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void eliminar(String usuario) {
      String sql = "DELETE FROM jugadores WHERE usuario = ?";
      try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
         pstmt.setString(1, usuario);
         pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public List<Jugador> obtenerTodos() {
      String sql = "SELECT usuario, nombre, apellido FROM jugadores";
      List<Jugador> jugadores = new ArrayList<>();
      try (PreparedStatement pstmt = conexion.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
         while (rs.next()) {
            String usuario = rs.getString("usuario");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            Jugador jugador = new Jugador(usuario, nombre, apellido);
            jugadores.add(jugador);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return jugadores;
   }

   public Jugador obtenerPorUsuario(String usuario) {
      String sql = "SELECT usuario, nombre, apellido FROM jugadores WHERE usuario = ?";
      try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
         pstmt.setString(1, usuario);
         ResultSet rs = pstmt.executeQuery();
         if (rs.next()) {
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            Jugador jugador = new Jugador(usuario, nombre, apellido);
            return jugador;
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return null;
   }
}