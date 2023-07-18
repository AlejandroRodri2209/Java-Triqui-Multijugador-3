/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package triqui.juegotriqui.Modelo;

public class Jugador {
   private String usuario;
   private String nombre;
   private String apellido;

   public Jugador(String usuario, String nombre, String apellido) {
      this.usuario = usuario;
      this.nombre = nombre;
      this.apellido = apellido;
   }

   public String getUsuario() {
      return usuario;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getApellido() {
      return apellido;
   }

   public void setApellido(String apellido) {
      this.apellido = apellido;
   }
}