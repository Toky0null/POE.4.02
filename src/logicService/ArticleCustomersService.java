package logicService;

import java.util.ArrayList;

import controller.ControllerCustomers;

import models.Customers;

public class ArticleCustomersService {
  private static ArticleCustomersService servicio;
  private ControllerCustomers cCliente;
  private ArrayList<Customers> clientes;

  public ArticleCustomersService() {
   cCliente = new ControllerCustomers();
   clientes = cCliente.getCustomers();
  }

  public Customers returnCustomers(int posicion) {
    try {
      return clientes.get(posicion);
    } catch (Exception e) {
      return null;
    }
  }

  public void addCustomers(Customers cliente) {
    cCliente.addUser(cliente);
  }

  public int returnAmountC() {
    return clientes.size();
  }

  public static ArticleCustomersService getService() {
    if (servicio == null) servicio = new ArticleCustomersService();
    return servicio;
  }
  
  
  public void removeCustomer (int id){  
  cCliente.removeCustomer(id);
  }
  
  public void updateCustomer(Customers clienteActualizado){
  cCliente.updateCutomer(clienteActualizado);
  }
  
  public int getLastCustomerId() {
        if (!clientes.isEmpty()) {
            return clientes.get(clientes.size() - 1).getId();
        } else {
            // Puedes elegir lanzar una excepción, retornar -1, o manejar esta situación de alguna otra manera
            System.out.println("La lista de clientes está vacía.");
            return -1;
        }
    }
}
