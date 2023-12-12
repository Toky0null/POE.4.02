package view.components.productStore;

import models.ProductStore;

public class ProductStoreComponent {
  private ProductStoreTemplate productoTemplate;

  public ProductStoreComponent(ProductStore producto) {
    productoTemplate = new ProductStoreTemplate(this, producto);
  }

  public ProductStoreTemplate getProductoTemplate() {
    return productoTemplate;
  }
}
