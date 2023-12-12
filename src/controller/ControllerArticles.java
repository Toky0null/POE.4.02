package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import models.Products;

public class ControllerArticles {
  private ArrayList<Products> amigos;

  public ControllerArticles() {
    amigos = new ArrayList<Products>();
    cargarDatos();
  }

  public void cargarDatos() {
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    try {
      archivo = new File("src/archives/items.txt");
      fr = new FileReader(archivo);
      br = new BufferedReader(fr);
      String linea;
      while ((linea = br.readLine()) != null) {
        String[] atributos = linea.split(",");
        Products amigo = new Products();
        amigo.setId(Integer.parseInt(atributos[0]));
        amigo.setNombre(atributos[1]);
        amigo.setEdad(atributos[2]);
        amigo.setOficio(atributos[3]);
        amigo.setTelefono(atributos[4]);
        amigo.setEmail(atributos[5]);
        amigos.add(amigo);
      }
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<Products> getAmigos() { return amigos; }
}