package logicService;

import java.util.ArrayList;

import controller.ControllerArticles;

import models.Products;

public class ArticleService {
  private static ArticleService servicio;
  private ControllerArticles cProducts;
  private ArrayList<Products> product;

  public ArticleService() {
   cProducts = new ControllerArticles();
   product = cProducts.getAmigos();
  }

  public Products devolverAmigo(int posicion) {
    try {
      return product.get(posicion);
    } catch (Exception e) {
      return null;
    }
  }

  public void agregarAmigo(Products products) {
    this.product.add(products);
  }

  public int devolverCantidadAmigos() {
    return product.size();
  }

  public static ArticleService getService() {
    if (servicio == null) servicio = new ArticleService();
    return servicio;
  }
}
