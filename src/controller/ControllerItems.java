package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import models.Products;

public class ControllerItems {
  private ArrayList<Products> items;

  public ControllerItems() {
    items = new ArrayList<Products>();
    loadDates();
  }

  public void loadDates() {
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
        item.setName(atributos[1]);
        item.setSupplier(atributos[2]);
        item.setOnll(atributos[3]);
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
  
    public Products returnProducts (int id){
        for (Products item : items) {
            if (item.getId() == id)
            return item;
        }
      return null;
    }
    
    
     public void updateProducts (Products itemActualizado) {
        for (int i = 0; i < items.size(); i++) {
            if (itemActualizado.getId()== items.get(i).getId()) {
                items.set(i, itemActualizado);
                break;
            }
        }
        writeUserFile();
    }

    private void writeUserFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/archives/items.txt"))) {
            for (Products item  : items ) {
                String linea = String.valueOf(item.getId()) + "," + item.getName() + "," + item.getSupplier() + "," + item.getOnll() + "," + item.getPrice() + "," + item.getEmail();
                writer.write(linea);
                writer.newLine();   
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    
    public void addItem(Products itemNew) {
        items.add(itemNew);
        writeUserFile();   
    }
   
    
    public void removeItem(int id) {
        Iterator<Products> iterator = items.iterator();
        while (iterator.hasNext()) {
            Products item = iterator.next();
            if (item.getId() == id) {
                iterator.remove();
                writeUserFile();
                break;
            }
        }
    }
   
}