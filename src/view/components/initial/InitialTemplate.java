package view.components.initial;

import view.components.accion.AccionComponent;
import view.components.accion.AccionTemplate;
import view.components.card.CardComponent;

import controller.ObjGraficosService;
import controller.RecursosService;

import models.Accion;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InitialTemplate extends JPanel {
  private static final long serialVersionUID = 1L;
  
  // Declaración Servicios y dependencias
  private InitialComponent inicioComponent;
  private ObjGraficosService sObjGraficos;
  private RecursosService sRecursos;

  // Declaración Objetos Gráficos
  private JPanel pTarjetas, pMision, pVision, pNosotros, pUsuarios, pDesarrollo, pGrupo, pAcciones;
  private JLabel lAcciones;
  private JButton bDerecha, bIzquierda;

  // Declaración Objetos Decoradores
  private ImageIcon iTarjeta1, iTarjeta2, iTarjeta3, iTarjeta4, iTarjeta5, iTarjeta6;
  private ImageIcon iDerecha, iIzquierda, iDimAux;

  public InitialTemplate(InitialComponent inicioComponent) {
    this.inicioComponent = inicioComponent;
    this.inicioComponent.getClass();
    sObjGraficos = ObjGraficosService.getService();
    sRecursos = RecursosService.getService();

    this.crearObjetosDecoradores();
    this.crearJButtons();
    this.crearJPanels();
    this.crearContenidoPMision();
    this.crearContenidoPVision();
    this.crearContenidoPNosotros();
    this.crearContenidoPUsuarios();
    this.crearContenidoPDesarrollo();
    this.crearContenidoPGrupo();
    this.crearContenidoPAcciones();

    this.setSize(850, 600);
    this.setBackground(sRecursos.getColorGrisClaro());
    this.setLayout(null);
    this.setVisible(true);
  }

  public void crearJPanels() {
    this.pTarjetas = sObjGraficos.construirJPanel(
      0, 0, 2000, 245, sRecursos.getColorTransparente(), null
    );
    this.add(pTarjetas);

    this.pMision = sObjGraficos.construirJPanel(20, 15, 256, 230, Color.WHITE, null);
    pTarjetas.add(pMision);

    this.pVision = sObjGraficos.construirJPanel(296, 15, 256, 230, Color.WHITE, null);
    pTarjetas.add(pVision);

    this.pNosotros = sObjGraficos.construirJPanel(572, 15, 256, 230, Color.WHITE, null);
    pTarjetas.add(pNosotros);

    this.pUsuarios = sObjGraficos.construirJPanel(848, 15, 256, 230, Color.WHITE, null);
    pTarjetas.add(pUsuarios);

    this.pDesarrollo = sObjGraficos.construirJPanel(1124, 15, 256, 230, Color.WHITE, null);
    pTarjetas.add(pDesarrollo);

    this.pGrupo = sObjGraficos.construirJPanel(1400, 15, 256, 230, Color.WHITE, null);
    pTarjetas.add(pGrupo);

    this.pAcciones = sObjGraficos.construirJPanel(20, 260, 810, 330, Color.WHITE, null);
    this.add(pAcciones);
  }

  public void crearObjetosDecoradores() {
    this.iTarjeta1 = new ImageIcon(
  getClass().getResource("/view/img/tarjetas/tarjeta1.jpg")
    );
    this.iTarjeta2 = new ImageIcon(
      getClass().getResource("/view/img/tarjetas/tarjeta2.jpg")
    );
    this.iTarjeta3 = new ImageIcon(
      getClass().getResource("/view/img/tarjetas/tarjeta3.jpg")
    );
    this.iTarjeta4 = new ImageIcon(
      getClass().getResource("/view/img/tarjetas/tarjeta4.jpg")
    );
    this.iTarjeta5 = new ImageIcon(
      getClass().getResource("/view/img/tarjetas/tarjeta5.jpg")
    );
    this.iTarjeta6 = new ImageIcon(
       getClass().getResource("/view/img/tarjetas/tarjeta5.jpg")
    );
    this.iDerecha = new ImageIcon(
          getClass().getResource("/view/img/derecha.png")
    );

    this.iIzquierda = new ImageIcon(
      getClass().getResource("/view/img/izquierda.png")
    );
  }

  public void crearJButtons() {
    iDimAux = new ImageIcon( iIzquierda.getImage()
      .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
    );

    bIzquierda = sObjGraficos.construirJButton(
      null,
      0, 125, 20, 20,
      sRecursos.getCMano(),
      iDimAux,
      null, null, null, null,
      "c",
      false
    );
    bIzquierda.addActionListener(inicioComponent);
    this.add(bIzquierda);

    iDimAux = new ImageIcon( iDerecha.getImage()
        .getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)
    );

    bDerecha = sObjGraficos.construirJButton(
      null,
      830, 125, 20, 20,
      sRecursos.getCMano(),
      iDimAux,
      null, null, null, null,
      "c",
      false
    );
    bDerecha.addActionListener(inicioComponent);
    this.add(bDerecha);
  }

  public void crearContenidoPMision() {
    this.pMision.add( 
      new CardComponent(
        "Nuestra Misión",
        iTarjeta1,
        "Brindar cursos a la comunidad académica para" +
        "complementar habilidades fuera del pensum común."
      ).getTarjetaTemplate()
    );
  }

  public void crearContenidoPVision() {
    this.pVision.add(
      new CardComponent(
        "Nuestra Visión",
        iTarjeta2,
        "Brindar cursos académicos al 80% de los" +
        "estudiantes de ingeniería de Sistemas."
      ).getTarjetaTemplate()
    );
  }

  public void crearContenidoPNosotros() {
    this.pNosotros.add(
      new CardComponent(
        "Sobre Nosotros",
        iTarjeta3,
        "Somos un grupo de trabajo de la Universidad" +
        "distrital Francisco jose de Caldas."
      ).getTarjetaTemplate()
    );
  }

  public void crearContenidoPUsuarios() {
    this.pUsuarios.add(
      new CardComponent(
        "Nuestros Usuarios",
        iTarjeta4,
        "Creamos experiencias de aprendizaje acordes a nuestros usuarios."
      ).getTarjetaTemplate()
    );
  }

  public void crearContenidoPDesarrollo() {
    this.pDesarrollo.add(
      new CardComponent(
        "Desarrollo",
        iTarjeta5,
        "Nuestro Enfoque principal esta en la creación de Software."
      ).getTarjetaTemplate()
    );
  }

  public void crearContenidoPGrupo() {
    this.pGrupo.add(
      new CardComponent(
        "El grupo",
        iTarjeta6,
        "Nuestra comunidad y el aspecto social es lo mas importante."
      ).getTarjetaTemplate()
    );
  }

  public void crearContenidoPAcciones() {
    this.lAcciones = sObjGraficos.construirJLabel(
      "Nuestros Servicios",
      10, 10, 160, 30,
      null, null,
      sRecursos.getFontTitulo(),
      null,
      sRecursos.getColorPrincipal(),
      null,
      "c"
    );
    this.pAcciones.add(lAcciones);

    int numeroAccion = 0, fila = 0;
    Accion accion = inicioComponent.obtenerAccion(numeroAccion);
    while (accion != null) {
      AccionTemplate pAccion = 
        new AccionComponent(accion).getAccionTemplate();
      pAccion.setLocation(
        15 + ((pAccion.getWidth() + 15) * (numeroAccion % 3)),
        50 + ((pAccion.getHeight() + 15) * fila)
      );
      if (numeroAccion % 3 == 2) fila++;
      this.pAcciones.add(pAccion);
      numeroAccion++;
      accion = inicioComponent.obtenerAccion(numeroAccion);
    }
  }

  public JButton getBDerecha() { return bDerecha; }

  public JButton getBIzquierda() { return bIzquierda; }

  public JPanel getPTarjetas() { return pTarjetas; }
}