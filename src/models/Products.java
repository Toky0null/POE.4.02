package models;

public class Products {
  private int id;
  private String nombre, supplier, onll, price, email;

  public int getId() { return id; }

  public String getNombre() { return nombre; }

  public String getEdad() { return supplier; }

  public String getOnll() { return onll; }

  public String getPrice() { return price; }

  public String getEmail() { return email; }

  public void setId(int id) {
    this.id = id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setEdad(String supplier) {
    this.supplier = supplier;
  }

  public void setOficio(String onll) {
    this.onll = onll;
  }

  public void setTelefono(String price) {
    this.price = price;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
