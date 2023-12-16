package logicService;

import java.util.ArrayList;

import controller.ControllerItems;

import models.Products;

public class ArticleStService {
  private static ArticleStService servicio;
  private ControllerItems cProducts;
  private ArrayList<Products> product;

  public ArticleStService() {
   cProducts = new ControllerItems();
   product = cProducts.getItem();
  }

  public Products getItem(int posicion) {
    try {
      return product.get(posicion);
    } catch (Exception e) {
      return null;
    }
  }

  public void addItemA(Products products) {
    cProducts.addItem(products);
  }

  public int returnAmountItems() {
    return product.size();
  }

  public static ArticleStService getService() {
    if (servicio == null) servicio = new ArticleStService();
    return servicio;
  }
  
  
  public void removeItem (int id){  
  cProducts.removeItem(id);
  }
  
//  continuar con los metodos del CRUD
}
