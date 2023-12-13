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
   clientes = cCliente.getAmigos();
  }

  public Customers returnCustomers(int posicion) {
    try {
      return clientes.get(posicion);
    } catch (Exception e) {
      return null;
    }
  }

  public void addCustomers(Customers cliente) {
    this.clientes.add(cliente);
  }

  public int returnAmountC() {
    return clientes.size();
  }

  public static ArticleCustomersService getService() {
    if (servicio == null) servicio = new ArticleCustomersService();
    return servicio;
  }
}
