package logicService;

import java.util.ArrayList;

import controller.ControllerCustomers;

import models.Customers;

public class ArticleCustomersService {
  private static ArticleCustomersService servicio;
  private ControllerCustomers cAmigos;
  private ArrayList<Customers> amigos;

  public ArticleCustomersService() {
   cAmigos = new ControllerCustomers();
   amigos = cAmigos.getAmigos();
  }

  public Customers devolverAmigo(int posicion) {
    try {
      return amigos.get(posicion);
    } catch (Exception e) {
      return null;
    }
  }

  public void agregarAmigo(Customers amigo) {
    this.amigos.add(amigo);
  }

  public int devolverCantidadAmigos() {
    return amigos.size();
  }

  public static ArticleCustomersService getService() {
    if (servicio == null) servicio = new ArticleCustomersService();
    return servicio;
  }
}
