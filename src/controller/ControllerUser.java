package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

    public User returnUser(String nombreUsuario) {
      for (User usuario : usuarios) {
        if (usuario.getNombreUsuario().equals(nombreUsuario)) 
          return usuario;
      }
      return null;
    }
    
    public void addUser(User nuevoUsuario) {
        usuarios.add(nuevoUsuario);
        writeUserFile();
        
    }

    private void writeUserFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/archives/usuarios.txt"))) {
            for (User usuario : usuarios) {
                String linea = usuario.getNombreUsuario() + "," + usuario.getClaveUsuario() + "," + usuario.getTipoUsuario() + "," + "src/view/img/perfiles/perfiles.png";
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    
//    public void updateUser (User usuarioActualizado) {
//        for (int i = 0; i < usuarios.size(); i++) {
//            if (usuarios.get(i).getNombreUsuario().equals(usuarioActualizado.getNombreUsuario())) {
//                usuarios.set(i, usuarioActualizado);
//                break;
//            }
//        }
//         writeUserFile();
//   }
  
}