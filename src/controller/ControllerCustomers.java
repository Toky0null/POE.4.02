package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import models.Customers;

public class ControllerCustomers {
  private ArrayList<Customers> clientes;

  public ControllerCustomers() {
    clientes = new ArrayList<Customers>();
    cargarDatos();
  }

  public void cargarDatos() {
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    try {
      archivo = new File("src/archives/clientes.txt");
      fr = new FileReader(archivo);
      br = new BufferedReader(fr);
      String linea;
      while ((linea = br.readLine()) != null) {
        String[] atributos = linea.split(",");
        Customers cliente = new Customers();
        cliente.setId(Integer.parseInt(atributos[0]));
        cliente.setNombre(atributos[1]);
        cliente.setEdad(atributos[2]);
        cliente.setOficio(atributos[3]);
        cliente.setTelefono(atributos[4]);
        cliente.setEmail(atributos[5]);
        clientes.add(cliente);
      }
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<Customers> getAmigos() { return clientes; }
}