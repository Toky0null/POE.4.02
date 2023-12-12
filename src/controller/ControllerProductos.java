package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import models.ProductStore;

public class ControllerProductos {
  private ArrayList<ProductStore> productos;

  public ControllerProductos() {
    productos = new ArrayList<ProductStore>();
    cargarDatos();
  }

  public void cargarDatos() {
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    try {
      archivo = new File("src/archives/productos.txt");
      fr = new FileReader(archivo);
      br = new BufferedReader(fr);
      String linea;
      while ((linea = br.readLine()) != null) {
        String[] atributos = linea.split(",");
        ProductStore producto = new ProductStore();
        producto.setId(Integer.parseInt(atributos[0]));
        producto.setNombreProducto(atributos[1]);
        producto.setDescripcion(atributos[2]);
        producto.setCampo(atributos[3]);
        producto.setPuntuacion(atributos[4]);
        producto.setImagen(new ImageIcon(atributos[5]));
        productos.add(producto);
      }
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<ProductStore> getProductos() { return productos; }
}