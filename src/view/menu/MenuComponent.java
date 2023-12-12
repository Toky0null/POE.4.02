package view.menu;

import view.components.products.ProductsComponent;
import view.components.header.HeaderComponent;
//import app.client.components.configuraciones.ConfiguracionesComponent;
import view.components.initial.InitialComponent;
import view.components.navigation.UserNavigationComponent;
import view.components.store.StoreComponent;
import view.login.LoginComponent;

import java.awt.Frame;

public class MenuComponent {
  private MenuTemplate vistaPrincipalTemplate;

  //Declaración Componentes
  private HeaderComponent barraTituloComponent;
  private UserNavigationComponent navegacionUsuarioComponent;
  private InitialComponent inicioComponent;
  private ProductsComponent articleComponent;
  private StoreComponent productosComponent;
//  private ConfiguracionesComponent configuracionesComponent;
  private LoginComponent loginComponent;

  public MenuComponent(LoginComponent loginComponent) {
    this.loginComponent = loginComponent;
    this.vistaPrincipalTemplate = new MenuTemplate(this);
    this.barraTituloComponent = new HeaderComponent(this);
    this.navegacionUsuarioComponent = new UserNavigationComponent(this);
    this.inicioComponent = new InitialComponent();

    vistaPrincipalTemplate.getPNavegacion()
      .add(navegacionUsuarioComponent.getNavegacionUsuarioTemplate());
    vistaPrincipalTemplate.getPBarra()
      .add(barraTituloComponent.getBarraTituloTemplate());
    vistaPrincipalTemplate.getPPrincipal()
      .add(inicioComponent.getInicioTemplate());
  }

  public MenuTemplate getVistaPrincipalTemplate() {
    return this.vistaPrincipalTemplate;
  }

  public void mostrarComponente(String comando) {
    vistaPrincipalTemplate.getPPrincipal().removeAll();
    switch (comando) {
      case "Inicio":
        vistaPrincipalTemplate.getPPrincipal()
          .add(inicioComponent.getInicioTemplate());
        break;
      case "Articulos":
        if (this.articleComponent == null) 
          this.articleComponent = new ProductsComponent();
        vistaPrincipalTemplate.getPPrincipal()
          .add(articleComponent.getAmigosTemplate());
        break;
      case "Clientes":
        break;
      case "Productos":
        if (this.productosComponent == null) 
          this.productosComponent = new StoreComponent();
        vistaPrincipalTemplate.crearContenidoProductos(
          productosComponent.getProductosTemplate()
        );
        break;
      case "Configuraciones":
//        if (this.configuracionesComponent == null) 
//          this.configuracionesComponent = new ConfiguracionesComponent();
//        vistaPrincipalTemplate.getPPrincipal()
//          .add(configuracionesComponent.getConfiguracionesTemplate());
        break;
      case "Cerrar Sesión":
        this.loginComponent.restaurarValores();
        this.loginComponent.getLoginTemplate().setVisible(true);
        this.vistaPrincipalTemplate.setVisible(false);
        break;
    }
    vistaPrincipalTemplate.repaint();
  }

  public void restaurarValores() {
    this.vistaPrincipalTemplate.getPPrincipal()
      .add(inicioComponent.getInicioTemplate());
    this.navegacionUsuarioComponent.actualizarValores();
  }
  
  public void moverVentana(int posicionX, int posicionY) {
    this.vistaPrincipalTemplate.setLocation(posicionX, posicionY);
  }
  
  public void cerrar() {
    System.exit(0);
  }

  public void minimizar() {
    this.vistaPrincipalTemplate.setExtendedState(Frame.ICONIFIED);
  }
}