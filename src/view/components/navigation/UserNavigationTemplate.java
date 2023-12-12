package view.components.navigation;

import controller.ObjGraficosService;
import controller.RecursosService;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class UserNavigationTemplate extends JPanel {
  private static final long serialVersionUID = 1L;
  
  // Declaración Servicios y dependencias
  private UserNavigationComponent navegacionUsuarioComponent;
  private ObjGraficosService sObjGraficos;
  private RecursosService sRecursos;

  //Declaración objetos gráficos
  private JPanel pSuperior, pInferior;
  private JLabel lNombreUsuario, lEslogan, lImagenUsuario, lIconoUsuario;
  private JButton bInicio, bProducts, bStore, bConfiguracion, bCerrarSesion,bClientes;

  //Declaración Objetos Decoradores
  private ImageIcon iIconoUsuario, iInicio, iProducts, iStore, iClientes;
  private ImageIcon iConfiguracion, iCerrarSesion, iDimAux;
  private Border bVacio;

  public UserNavigationTemplate(UserNavigationComponent navegacionUsuarioComponent) {
    this.navegacionUsuarioComponent = navegacionUsuarioComponent;
    this.sObjGraficos = ObjGraficosService.getService();
    this.sRecursos = RecursosService.getService();

    this.crearObjetosDecoradores();
    this.crearJPanels();
    this.crearJLabels();
    this.crearJButtons();

    this.setSize(250, 700);
    this.setLayout(null);
    this.setVisible(true);
  }

  public void crearJPanels() {
    this.pSuperior = sObjGraficos.construirJPanel(
      0, 0, 250, 300,
      sRecursos.getColorPrincipal(),
      null
    );
    this.add(pSuperior);

    this.pInferior = sObjGraficos.construirJPanel(
      0, 300, 250, 400,
      sRecursos.getColorPrincipal(),
      null
    );
    this.add(pInferior);
  }

  public void crearObjetosDecoradores() {
    this.iIconoUsuario = new ImageIcon(
      getClass().getResource("/view/img/usuario_navegacion.png")
    );
    this.iInicio = new ImageIcon(getClass().getResource("/view/img/inicio.png"));
  this.iClientes = new ImageIcon(getClass().getResource("/view/img/clientes.png"));
    this.iProducts = new ImageIcon(getClass().getResource("/view/img/addP.png"));
    this.iStore = new ImageIcon(getClass().getResource("/view/img/tienda.png"));
    this.iConfiguracion = new ImageIcon(
      getClass().getResource("/view/img/configuracion.png")
    );
    this.iCerrarSesion = new ImageIcon(getClass().getResource("/view/img/salir.png"));
    this.bVacio = new EmptyBorder(2, 20, 2, 2);
  }

  public void crearJButtons() {
    // BOTÓN INICIO--------------------------------------------------------------------
    iDimAux = new ImageIcon(
      iInicio.getImage()
        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
    );
    this.bInicio = sObjGraficos.construirJButton(
      "      Inicio",
      30, 30, 200, 40,
      sRecursos.getCMano(),
      iDimAux,
      sRecursos.getFontMediana(),
      null,
      Color.WHITE,
      bVacio,
      "l",
      false
    );
    this.bInicio.addActionListener(navegacionUsuarioComponent);
    this.bInicio.addMouseListener(navegacionUsuarioComponent);
    this.pInferior.add(bInicio);

    // BOTÓN PERFIL--------------------------------------------------------------------
    iDimAux = new ImageIcon(
      iClientes.getImage()
        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
    );
    this.bClientes = sObjGraficos.construirJButton(
      "      Clientes",
      30, 80, 200, 40,
      sRecursos.getCMano(),
      iDimAux,
      sRecursos.getFontMediana(),
      null,
      Color.WHITE,
      bVacio,
      "l",
      false
    );
    this.bClientes.addActionListener(navegacionUsuarioComponent);
    this.bClientes.addMouseListener(navegacionUsuarioComponent);
    this.pInferior.add(bClientes);

    // BOTÓN AMIGOS--------------------------------------------------------------------
    iDimAux = new ImageIcon(
      iProducts.getImage()
        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
    );
    this.bProducts = sObjGraficos.construirJButton(
      "      Articulos",
      30, 130, 200, 40,
      sRecursos.getCMano(),
      iDimAux,
      sRecursos.getFontMediana(),
      null,
      Color.WHITE,
      bVacio,
      "l",
      false
    );
    this.bProducts.addActionListener(navegacionUsuarioComponent);
    this.bProducts.addMouseListener(navegacionUsuarioComponent);
    this.pInferior.add(bProducts);

    // BOTÓN TIENDA--------------------------------------------------------------------
    iDimAux = new ImageIcon(
      iStore.getImage()
        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
    );
    this.bStore = sObjGraficos.construirJButton(
      "      Tienda",
      30, 180, 200, 40,
      sRecursos.getCMano(),
      iDimAux,
      sRecursos.getFontMediana(),
      null,
      Color.WHITE,
      bVacio,
      "l",
      false
    );
    this.bStore.addActionListener(navegacionUsuarioComponent);
    this.bStore.addMouseListener(navegacionUsuarioComponent);
    this.pInferior.add(bStore);

    // BOTÓN CONFIGURACIÓN--------------------------------------------------------------------
    iDimAux = new ImageIcon(
      iConfiguracion.getImage()
        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
    );
    this.bConfiguracion = sObjGraficos.construirJButton(
      "      Configuraciones",
      30, 230, 200, 40,
      sRecursos.getCMano(),
      iDimAux,
      sRecursos.getFontMediana(),
      null,
      Color.WHITE,
      bVacio,
      "l",
      false
    );
    this.bConfiguracion.addActionListener(navegacionUsuarioComponent);
    this.bConfiguracion.addMouseListener(navegacionUsuarioComponent);
    this.pInferior.add(bConfiguracion);

    // BOTÓN CERRAR SESIÓN--------------------------------------------------------------------
    iDimAux = new ImageIcon(
      iCerrarSesion.getImage()
        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
    );
    this.bCerrarSesion = sObjGraficos.construirJButton(
      "      Cerrar Sesión",
      30, 280, 200, 40,
      sRecursos.getCMano(),
      iDimAux,
      sRecursos.getFontMediana(),
      null,
      Color.WHITE,
      bVacio,
      "l",
      false
    );
    this.bCerrarSesion.addActionListener(navegacionUsuarioComponent);
    this.bCerrarSesion.addMouseListener(navegacionUsuarioComponent);
    this.pInferior.add(bCerrarSesion);
  }

  public void crearJLabels() {
    // LABEL ICONO USUARIO--------------------------------------------------------------------
    iDimAux = new ImageIcon(
      iIconoUsuario.getImage()
        .getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)
    );
    this.lIconoUsuario = sObjGraficos.construirJLabel(
      null,
      10, 20, 30, 30,
      null,
      iDimAux,
      null, null, null, null,
      "c"
    );
    this.pSuperior.add(lIconoUsuario);

    // LABEL NOMBRE USUARIO--------------------------------------------------------------------
    this.lNombreUsuario = sObjGraficos.construirJLabel(
      navegacionUsuarioComponent.getUsuario().getNombreUsuario(),
      (this.pSuperior.getWidth() - 200) / 2, 20, 200, 30,
      null, null,
      sRecursos.getFontTitulo(),
      null,
      Color.WHITE,
      null,
      "c"
    );
    this.pSuperior.add(lNombreUsuario);

    // LABEL IMAGEN USUARIO--------------------------------------------------------------------
    iDimAux = new ImageIcon(
      navegacionUsuarioComponent
        .getUsuario()
        .getImagenUsuario()
        .getImage()
        .getScaledInstance(180, 180, Image.SCALE_AREA_AVERAGING)
    );
    this.lImagenUsuario = sObjGraficos.construirJLabel(
      null,
      (this.pSuperior.getWidth() - 180) / 2, 75, 180, 180,
      null,
      iDimAux,
      null, null, null, null,
      "c"
    );
    lImagenUsuario.setBorder(sRecursos.getBCircular());
    this.pSuperior.add(lImagenUsuario);

    // LABEL ESLOGAN--------------------------------------------------------------------
    this.lEslogan = sObjGraficos.construirJLabel(
      "<html><div align='center'> Nuestros clientes son <br/>lo mas importante</div></html>",
      (this.pSuperior.getWidth() - 180) / 2, 265, 180, 40,
      null, null,
      sRecursos.getFontLigera(),
      null,
      Color.WHITE,
      null,
      "c"
    );
    this.pSuperior.add(lEslogan);
  }

  public JPanel getPSuperior() { return pSuperior; }
}