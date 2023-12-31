package view.login;

import view.menu.MenuComponent;
import logicService.UserService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import models.User;

public class LoginComponent extends MouseAdapter implements ActionListener {
  private LoginTemplate loginTemplate;
  private MenuComponent vistaPrincipal;
  private UserService sUsuario;
  private JButton boton;
  private JTextField text;
  private JLabel label;
  private String[] placeholders = { "Nombre Usuario", "Clave Usuario" };
  private int estado = 1, estadoAnterior = 0, direccion = -1;
  private Timer timer;

  public LoginComponent() {
     sUsuario = UserService.getService();
    this.timer = new Timer(1, this);
    this.loginTemplate = new LoginTemplate(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof JButton) {
      this.estadoAnterior = estado;
      loginTemplate.getBOpcion(estadoAnterior).setIcon(loginTemplate.getIPunto1());
      if (e.getSource() == loginTemplate.getBOpcion(1)) {
        this.estado = 1;
        this.direccion = -1;
      }
      if (e.getSource() == loginTemplate.getBOpcion(2)) 
        this.estado = 2;
      if (e.getSource() == loginTemplate.getBOpcion(3)) {
        this.estado = 3;
        this.direccion = 1;
      }
      loginTemplate.getBOpcion(estado).setIcon(loginTemplate.getIPunto2());
      timer.start();
    }
    moverImagenes();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getSource() == loginTemplate.getBEntrar()) this.enviarDatosUsuario();
    if (e.getSource() == loginTemplate.getBCerrar()) System.exit(0);
    if (e.getSource() == loginTemplate.getBRegistrarse()){
        String nombreUsuario = loginTemplate.getTNombreUsuario().getText();
        String claveUsuario = new String(loginTemplate.getTClaveUsuario().getPassword());
        String tipoUsuario = ((String) loginTemplate.getCbTipoUsuario().getSelectedItem());

    if (!nombreUsuario.equals(placeholders[0]) && !claveUsuario.equals(placeholders[1])) {
            // Crea un nuevo usuario con los datos recogidos
            User nuevoUsuario = new User();
            nuevoUsuario.setNombreUsuario(nombreUsuario);
            nuevoUsuario.setClaveUsuario(claveUsuario);
            nuevoUsuario.setTipoUsuario(tipoUsuario);
            nuevoUsuario.setImagenUsuario(new ImageIcon(getClass().getResource("/view/img/perfiles/perfiles.png")));
            // Llama al método para agregar el nuevo usuario
            sUsuario.addUser(nuevoUsuario);
            JOptionPane.showMessageDialog(null, "Usuario Registrado", "Felicidades!!", 1);
        } else {
            // Muestra un mensaje de error si los campos tienen los placeholders
            JOptionPane.showMessageDialog(null, "Los campos no pueden contener valores predeterminados.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
      
    }
    if (e.getSource() instanceof JTextField) {
      text = ((JTextField) e.getSource());
      label = loginTemplate.getLabels(text);
      label.setIcon(loginTemplate.getIAzul(label));
      text.setForeground(loginTemplate.getRecursosService().getColorPrincipal());
      text.setBorder(loginTemplate.getRecursosService().getBInferiorAzul());
      if (
        text.getText().equals(placeholders[0]) || 
        text.getText().equals(placeholders[1])
      ) 
        text.setText("");
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (e.getSource() instanceof JButton) {
      boton = ((JButton) e.getSource());
      boton.setBackground(loginTemplate.getRecursosService().getColorPrincipalOscuro());
    }
    if (e.getSource() instanceof JLabel) {
      label = ((JLabel) e.getSource());
      label.setIcon(loginTemplate.getINaranja(label));
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {
    if (e.getSource() instanceof JButton) {
      boton = ((JButton) e.getSource());
      boton.setBackground(loginTemplate.getRecursosService().getColorPrincipal());
    }
    if (e.getSource() instanceof JLabel) {
      label = ((JLabel) e.getSource());
      label.setIcon(loginTemplate.getIBlanca(label));
    }
  }

  public void enviarDatosUsuario() {
    String nombreUsuario = loginTemplate.getTNombreUsuario().getText();
    String claveUsuario = new String(loginTemplate.getTClaveUsuario().getPassword());
    String tipoUsuario = ((String) loginTemplate.getCbTipoUsuario().getSelectedItem());
    if(!nombreUsuario.isBlank() && !claveUsuario.isBlank()) {
      if (sUsuario.checkUserS(nombreUsuario, claveUsuario, tipoUsuario)) {
        JOptionPane.showMessageDialog(null, "Ingreso Exitoso", "Advertencia", 1);
        entrar(tipoUsuario);
      } else
        JOptionPane.showMessageDialog(null, "Algo quedo mal", "Advertencia", 2);
    } else
      JOptionPane.showMessageDialog(null, "No puede dejar un campo vacio", "Advertencia", 2);
  }

  public void entrar(String type) {
    if (vistaPrincipal == null) {
      this.vistaPrincipal = new MenuComponent(this); 
      this.vistaPrincipal.hidenB(type);
    }
    else {
      this.vistaPrincipal.restaurarValores();
      this.vistaPrincipal.getVistaPrincipalTemplate().setVisible(true);
    }
    loginTemplate.setVisible(false);
  }

  public void restaurarValores() {
    this.getLoginTemplate().getTNombreUsuario().setText("Nombre Usuario");
    this.getLoginTemplate().getTNombreUsuario()
      .setBorder(this.getLoginTemplate().getRecursosService().getBInferiorGris());
    this.getLoginTemplate().getTNombreUsuario()
      .setForeground(this.getLoginTemplate().getRecursosService().getColorGrisOscuro());
    this.getLoginTemplate().getTClaveUsuario().setText("Clave Usuario");
    this.getLoginTemplate().getTClaveUsuario()
      .setBorder(this.getLoginTemplate().getRecursosService().getBInferiorGris());
    this.getLoginTemplate().getTClaveUsuario()
      .setForeground(this.getLoginTemplate().getRecursosService().getColorGrisOscuro());
    this.getLoginTemplate().getCbTipoUsuario().setSelectedIndex(0);
  }

  private void moverImagenes() {
    switch (estado) {
      case 1:
        if (loginTemplate.getPSvg().getX() == 100) timer.stop(); 
        else 
          loginTemplate.getPSvg().setLocation(
            loginTemplate.getPSvg().getX() + 1,
            loginTemplate.getPSvg().getY()
          );
        break;
      case 2:
        if (loginTemplate.getPSvg().getX() == -500) timer.stop();
        else 
          loginTemplate.getPSvg().setLocation(
            loginTemplate.getPSvg().getX() + direccion,
            loginTemplate.getPSvg().getY()
          );
        break;
      case 3:
        if (loginTemplate.getPSvg().getX() == -1100) timer.stop();
        else 
          loginTemplate.getPSvg().setLocation(
            loginTemplate.getPSvg().getX() - 1,
            loginTemplate.getPSvg().getY()
          );
        break;
    }
    loginTemplate.repaint(0, 80, 620, 375);
  }

  public LoginTemplate getLoginTemplate() { return this.loginTemplate; }
}