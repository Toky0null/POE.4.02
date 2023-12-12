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

  public Products devolverItem(int posicion) {
    try {
      return product.get(posicion);
    } catch (Exception e) {
      return null;
    }
  }

  public void agregarItem(Products products) {
    this.product.add(products);
  }

  public int devolverCantidadItems() {
    return product.size();
  }

  public static ArticleStService getService() {
    if (servicio == null) servicio = new ArticleStService();
    return servicio;
  }
}
