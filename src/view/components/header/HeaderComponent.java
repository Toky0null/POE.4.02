package view.components.header;

import view.menu.MenuComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeaderComponent extends MouseAdapter implements ActionListener {
  private MenuComponent vistaPrincipalComponent;
  private HeaderTemplate barraTituloTemplate;
  private int posicionInicialX, posicionInicialY;

  public HeaderComponent(MenuComponent vistaPrincipalComponent) {
    this.vistaPrincipalComponent = vistaPrincipalComponent;
    this.barraTituloTemplate = new HeaderTemplate(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == barraTituloTemplate.getBMinimizar()) 
      vistaPrincipalComponent.minimizar();
    if (e.getSource() == barraTituloTemplate.getBCerrar()) 
      vistaPrincipalComponent.cerrar();
  }

  @Override
  public void mousePressed(MouseEvent e) {
    posicionInicialX = e.getX() + 250;
    posicionInicialY = e.getY();
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    this.vistaPrincipalComponent.moverVentana(
      e.getXOnScreen() - posicionInicialX,
      e.getYOnScreen() - posicionInicialY
    );
  }

  public HeaderTemplate getBarraTituloTemplate() {
    return this.barraTituloTemplate;
  }
}