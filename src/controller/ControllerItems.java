package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import models.Products;

public class ControllerItems {
  private ArrayList<Products> items;

  public ControllerItems() {
    items = new ArrayList<Products>();
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
        Products item = new Products();
        item.setId(Integer.parseInt(atributos[0]));
        item.setNombre(atributos[1]);
        item.setEdad(atributos[2]);
        item.setOficio(atributos[3]);
        item.setTelefono(atributos[4]);
        item.setEmail(atributos[5]);
        items.add(item);
      }
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<Products> getItem() { return items; }
}