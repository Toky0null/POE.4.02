package logicService;

import controller.ControllerUser;
import models.User;

public class UserService {
  private static UserService servicio;
  private ControllerUser cUsuario;
  private User usuarioConectado;

  public UserService() {
    cUsuario = new ControllerUser();
  }

  public boolean checkUserS(
    String nombreUsuario, String claveUsuario, String tipoUsuario
  ) {
    if (cUsuario.checkUser(nombreUsuario, claveUsuario, tipoUsuario)) {
      this.usuarioConectado = cUsuario.returnUser(nombreUsuario);
      return true;
    }
    return false;
  }

  public User getUsuarioConectado() {
    return this.usuarioConectado;
  }

  public static UserService getService() {
    if (servicio == null) servicio = new UserService();
    return servicio;
  }
  
  public void addUser(User nuevoUser){
     cUsuario.addUser(nuevoUser);
  }
}