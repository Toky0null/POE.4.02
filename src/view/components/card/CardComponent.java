package view.components.card;

import javax.swing.ImageIcon;

public class CardComponent {
  private CardTemplate tarjetaTemplate;

  public CardComponent(String titulo, ImageIcon iImagen, String parrafo) {
    tarjetaTemplate = new CardTemplate(this, titulo, iImagen, parrafo);
  }

  public CardTemplate getTarjetaTemplate() {
    return tarjetaTemplate;
  }
}
