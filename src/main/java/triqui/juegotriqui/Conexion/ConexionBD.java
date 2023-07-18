/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package triqui.juegotriqui.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
   private static Connection conn = null;

   public static Connection getConnection() {
      try {
         if (conn == null) {
            String url = "jdbc:sqlite:database.db";
            conn = DriverManager.getConnection(url);
         }
         return conn;
      } catch (SQLException e) {
         System.out.println(e.getMessage());
         return null;
      }
   }
}