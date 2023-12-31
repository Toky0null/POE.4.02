package view.menu;

import controller.GraficosAvanzadosService;
import controller.ObjGraficosService;
import controller.RecursosService;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MenuTemplate extends JFrame {
  private static final long serialVersionUID = 1L;
  
  // Declaración Servicios y Objetos
  private ObjGraficosService sObjGraficos;
  private GraficosAvanzadosService sGraficosAvanzados;
  private RecursosService sRecursos;
  private MenuComponent vistaPrincipalComponent;

  // Declaración Objetos Gráficos
  private JPanel pNavegacion, pBarra, pPrincipal;
  private JScrollPane psProductos;

  public MenuTemplate(MenuComponent vistaPrincipalComponent) {
    this.vistaPrincipalComponent = vistaPrincipalComponent;
    this.vistaPrincipalComponent.getClass();
    sObjGraficos = ObjGraficosService.getService();
    sRecursos = RecursosService.getService();
    sGraficosAvanzados = GraficosAvanzadosService.getService();

    this.crearJPanels();

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(1100, 650);
    setLocationRelativeTo(this);
    setLayout(null);
    setUndecorated(true);
    setVisible(true);
  }

  public void crearJPanels() {
    pNavegacion = sObjGraficos.construirJPanel(0, 0, 250, 700, null, null);
    this.add(pNavegacion);

    pBarra = sObjGraficos.construirJPanel(250, 0, 850, 50, null, null);
    this.add(pBarra);

    pPrincipal = sObjGraficos.construirJPanel(250, 50, 850, 600, null, null);
    this.add(pPrincipal);
  }

  public void crearContenidoProductos(JPanel pProductos) {
    this.psProductos = sObjGraficos.construirPanelBarra(
      pProductos, 0, 0, 850, 600, null, null
    );
    this.psProductos.getVerticalScrollBar().setUI(
      sGraficosAvanzados.devolverScrollPersonalizado(
        7, 10,
        sRecursos.getColorGrisClaro(),
        sRecursos.getColorPrincipal(),
        sRecursos.getColorPrincipalOscuro()
      )
    );
    this.pPrincipal.add(psProductos);
    this.psProductos.revalidate();
  }

  public JPanel getPNavegacion() { return this.pNavegacion; }

  public JPanel getPPrincipal() { return this.pPrincipal; }

  public JPanel getPBarra() { return this.pBarra; }
}