package view.components.store;

import logicService.ProductService;
import models.ProductStore;

public class StoreComponent {
  private StoreTemplate productosTemplate;
  private ProductService sProducto;

  public StoreComponent() {
    sProducto = ProductService.getService();
    productosTemplate = new StoreTemplate(this);
  }

  public ProductStore devolverProducto(int posicion) {
    return this.sProducto.devolverProducto(posicion);
  }

  public StoreTemplate getProductosTemplate() {
    return productosTemplate;
  }
}
