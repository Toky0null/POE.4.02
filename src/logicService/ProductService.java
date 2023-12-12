package logicService;

import java.util.ArrayList;
import controller.ControllerProductos;
import models.ProductStore;

public class ProductService {
  private static ProductService servicio;
  private ControllerProductos cProductos;
  private ArrayList<ProductStore> productos;

  public ProductService() {
    cProductos = new ControllerProductos();
    productos = cProductos.getProductos();
  }

  public ProductStore devolverProducto(int posicion) {
    try {
      return productos.get(posicion);
    } catch (Exception e) {
      return null;
    }
  }

  public static ProductService getService() {
    if (servicio == null) servicio = new ProductService();
    return servicio;
  }
}
