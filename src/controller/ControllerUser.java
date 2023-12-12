package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import models.User;

public class ControllerUser {
  private ArrayList<User> usuarios;

  public ControllerUser() {
    usuarios = new ArrayList<User>();
    cargarDatos();
  }

  public void cargarDatos() {
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    try {
      archivo = new File("src/archives/usuarios.txt");
      fr = new FileReader(archivo);
      br = new BufferedReader(fr);

      String linea;
      while ((linea = br.readLine()) != null) {
        String[] atributos = linea.split(",");
        User usuario = new User();
        usuario.setNombreUsuario(atributos[0]);
        usuario.setClaveUsuario(atributos[1]);
        usuario.setTipoUsuario(atributos[2]);
        usuario.setImagenUsuario(new ImageIcon(atributos[3]));
        usuarios.add(usuario);
      }
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean verificarUsuario(
    String nombreUsuario, String claveUsuario, String tipoUsuario
  ) {
    for (User usuario : usuarios) {
      if (usuario.getNombreUsuario().equals(nombreUsuario)) 
        if (usuario.getClaveUsuario().equals(claveUsuario)) 
          if (usuario.getTipoUsuario().equals(tipoUsuario)) 
            return true;
    }
    return false;
  }

  public User devolverUsuario(String nombreUsuario) {
    for (User usuario : usuarios) {
      if (usuario.getNombreUsuario().equals(nombreUsuario)) 
        return usuario;
    }
    return null;
  }
}