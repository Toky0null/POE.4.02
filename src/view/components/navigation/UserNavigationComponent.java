package view.components.navigation;

import  view.menu.MenuComponent;

import controller.RecursosService;
import logicService.UserService;

import models.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class UserNavigationComponent extends MouseAdapter implements ActionListener {
  private UserNavigationTemplate navegacionUsuarioTemplate;
  private MenuComponent vistaPrincipalComponent;
  private UserService sUsuario;
  private User usuarioConectado;

  public UserNavigationComponent(MenuComponent vistaPrincipalComponent) {
    this.vistaPrincipalComponent = vistaPrincipalComponent;
    this.sUsuario = UserService.getService();
    this.usuarioConectado = sUsuario.getUsuarioConectado();
    this.navegacionUsuarioTemplate = new UserNavigationTemplate(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.vistaPrincipalComponent.mostrarComponente(e.getActionCommand().trim());
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    JButton boton = ((JButton) e.getSource());
    boton.setContentAreaFilled(true);
    boton.setBackground(RecursosService.getService().getColorPrincipalOscuro());
  }

  @Override
  public void mouseExited(MouseEvent e) {
    JButton boton = ((JButton) e.getSource());
    boton.setContentAreaFilled(false);
  }

  public void actualizarValores() {
    this.usuarioConectado = sUsuario.getUsuarioConectado();
    this.navegacionUsuarioTemplate.getPSuperior().removeAll();
    this.navegacionUsuarioTemplate.crearJLabels();
    this.navegacionUsuarioTemplate.repaint();
  }

  public User getUsuario() {
    return this.usuarioConectado;
  }

  public UserNavigationTemplate getNavegacionUsuarioTemplate() {
    return this.navegacionUsuarioTemplate;
  }
  
  public void hideBotton(String botton){
      switch (botton){
          case "Cliente":
              this.navegacionUsuarioTemplate.hideButtonClients();
              this.navegacionUsuarioTemplate.hideButtonP();
            break;
          case "Cajero":
              this.navegacionUsuarioTemplate.hideButtonP();         
              break;
    }
  
  }
}