package logicService;

import java.util.ArrayList;

import controller.ControllerItems;
import models.Customers;

import models.Products;

public class ArticleStService {
  private static ArticleStService servicio;
  private ControllerItems cProducts;
  private ArrayList<Products> items;

  public ArticleStService() {
   cProducts = new ControllerItems();
   items = cProducts.getItem();
  }

  public Products getItem(int index) {
    try {
      return items.get(index);
    } catch (Exception e) {
      return null;
    }
  }

  public void addItemA(Products products) {
    cProducts.addItem(products);
  }

  public int returnAmountItems() {
    return items.size();
  }
  
  public int getLastCustomerId() {
        if (!items.isEmpty()) {
            return items.get(items.size() - 1).getId();
        } else {
            // Puedes elegir lanzar una excepción, retornar -1, o manejar esta situación de alguna otra manera
            System.out.println("La lista de clientes está vacía.");
            return -1;
        }
    }
  

  public static ArticleStService getService() {
    if (servicio == null) servicio = new ArticleStService();
    return servicio;
  }
  
  
  public void removeItem (int id){  
  cProducts.removeItem(id);
  }
  
  public void updateItems (Products itemUpdate){
   cProducts.updateProducts(itemUpdate);
  }
}
