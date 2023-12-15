package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import models.Customers;
import models.User;

public class ControllerCustomers {
  private ArrayList<Customers> clientes;

  public ControllerCustomers() {
    clientes = new ArrayList<Customers>();
    loadData();
  }

  public void loadData() {
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

  public ArrayList<Customers> getCustomers() { return clientes; }
  
  public Customers returnCustomer (int id){
  for (Customers cliente : clientes) {
        if (cliente.getId() == id)
          return cliente;
      }
      return null;
  }
  
  
    public void updateCutomer (Customers clienteActualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clienteActualizado.getId()== clientes.get(i).getId()) {
                clientes.set(i, clienteActualizado);
                break;
            }
        }
        writeUserFile();
   }

    private void writeUserFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/archives/clientes.txt"))) {
            for (Customers cliente  : clientes ) {
                String linea = String.valueOf(cliente.getId()) + "," + cliente.getNombre()+ "," + cliente.getEdad() + "," + cliente.getOficio() + "," + cliente.getTelefono() + "," + cliente.getEmail();
                writer.write(linea);
                writer.newLine();   
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    public void addUser(Customers nuevoCliente) {
        clientes.add(nuevoCliente);
        writeUserFile();   
    }
    
    public void removeCustomer(int id) {
        Iterator<Customers> iterator = clientes.iterator();
        while (iterator.hasNext()) {
            Customers cliente = iterator.next();
            if (cliente.getId() == id) {
                iterator.remove();
                writeUserFile();
                break;
            }
        }
    }
    
    
}