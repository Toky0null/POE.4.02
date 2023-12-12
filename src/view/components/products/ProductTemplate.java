package view.components.products;

import controller.GraficosAvanzadosService;
import controller.ObjGraficosService;
import controller.RecursosService;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ProductTemplate extends JPanel {
  private static final long serialVersionUID = 1L;
  
  // Declaración servicios y objetos
  private ProductsComponent ProductsComponent;
  private ObjGraficosService sObjGraficos;
  private GraficosAvanzadosService sGraficosAvanzados;
  private RecursosService sRecursos;

  // Declaración objetos gráficos
  private JPanel pOpciones, pDatos;
  private JButton bMostrar, bInsertar, bFiltrar, bModificar, bEliminar;
  private JTextField tConsulta;
  private JLabel lTitulo, lInstrucciones, lEslogan;
  private JLabel lId, lIdValor, lNombreP, lSupplier, lOnll, lPrice, lEmail;
  private JTextField tNombreP, tSupplier, tOnll, tPrice, tEmail;

  // Declaración objetos decoradores
  private Color colorGris;

  // Declaración objetos para JTable
  private JScrollPane pTabla;
  private JPanel pCorner;
  private JTable tabla;
  private JTableHeader header;
  private DefaultTableModel modelo;
  private String[] cabecera = { "id", "Nombre","Precio","Proveedor" };

  public ProductTemplate(ProductsComponent productsComponent) {
    this.ProductsComponent = productsComponent;
    this.sObjGraficos = ObjGraficosService.getService();
    this.sRecursos = RecursosService.getService();
    this.sGraficosAvanzados = GraficosAvanzadosService.getService();

    this.colorGris = new Color(235, 235, 235);

    this.crearJPanels();
    this.crearJTable();
    this.crearContenidoPOpciones();
    this.crearContenidoPDatos();

    this.setSize(850, 600);
    this.setBackground(sRecursos.getColorGrisClaro());
    this.setLayout(null);
    this.setVisible(true);
  }

  public void crearJPanels() {
    pOpciones = sObjGraficos.construirJPanel(10, 10, 580, 200, Color.WHITE, null);
    this.add(pOpciones);

    pDatos = sObjGraficos.construirJPanel(600, 10, 240, 580, Color.WHITE, null);
    this.add(pDatos);
  }

  public void crearJTable() {
    modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(cabecera);

    tabla = new JTable();
    tabla.setModel(modelo);
    tabla.addMouseListener(ProductsComponent);

    tabla.setRowHeight(40);
    tabla.setShowHorizontalLines(false);
    tabla.setShowVerticalLines(false);

    header = tabla.getTableHeader();
    header.setPreferredSize(new Dimension(580, 30));

    pTabla = sObjGraficos.construirPanelBarra(
      tabla,
      10, 220, 580, 370,
      Color.WHITE,
      null
    );

    header.setDefaultRenderer(
      sGraficosAvanzados.devolverTablaPersonalizada(
        sRecursos.getColorPrincipal(),
        null, null,
        Color.WHITE,
        sRecursos.getFontLigera()
      )
    );

    tabla.setDefaultRenderer(
      Object.class,
      sGraficosAvanzados.devolverTablaPersonalizada(
        Color.WHITE,
        sRecursos.getColorGrisClaro(),
        sRecursos.getColorPrincipalOscuro(),
        sRecursos.getColorGrisOscuro(),
        sRecursos.getFontLigera()
      )
    );

    pTabla.getVerticalScrollBar().setUI(
      sGraficosAvanzados.devolverScrollPersonalizado(
        7, 10,
        Color.WHITE,
        Color.GRAY,
        sRecursos.getColorGrisOscuro()
      )
    );

    pCorner = new JPanel();
    pCorner.setBackground(sRecursos.getColorPrincipal());
    pTabla.setCorner(JScrollPane.UPPER_RIGHT_CORNER, pCorner);
    this.add(pTabla);
  }

  public void crearContenidoPOpciones() {
    // LABEL TITULO--------------------------------------------------------------------
    lTitulo = sObjGraficos.construirJLabel(
      "Edición de Productos",
      20, 10, 200, 30,
      null, null,
      sRecursos.getFontTitulo(),
      null,
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    pOpciones.add(lTitulo);

    // TEXTFIELD CONSULTA--------------------------------------------------------------------
    tConsulta = sObjGraficos.construirJTextField(
      "Filtrar...",
      30, 60, 380, 40,
      sRecursos.getFontMediana(),
      colorGris,
      sRecursos.getColorGrisOscuro(),
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    tConsulta.addFocusListener(ProductsComponent);
    pOpciones.add(tConsulta);

    // BOTÓN FILTRAR--------------------------------------------------------------------
    bFiltrar = sObjGraficos.construirJButton(
      "Filtrar",
      430, 65, 120, 35,
      sRecursos.getCMano(),
      null,
      sRecursos.getFontMediana(),
      sRecursos.getColorPrincipal(),
      Color.WHITE,
      null,
      "c",
      true
    );
    bFiltrar.addMouseListener(ProductsComponent);
    bFiltrar.addActionListener(ProductsComponent);
    pOpciones.add(bFiltrar);

    // BOTÓN MOSTRAR--------------------------------------------------------------------
    bMostrar = sObjGraficos.construirJButton(
      "Mostrar",
      20, 145, 120, 35,
      sRecursos.getCMano(),
      null,
      sRecursos.getFontMediana(),
      sRecursos.getColorPrincipal(),
      Color.WHITE,
      null,
      "c",
      true
    );
    bMostrar.addMouseListener(ProductsComponent);
    bMostrar.addActionListener(ProductsComponent);
    pOpciones.add(bMostrar);

    // BOTÓN INSERTAR--------------------------------------------------------------------
    bInsertar = sObjGraficos.construirJButton(
      "Insertar",
      160, 145, 120, 35,
      sRecursos.getCMano(),
      null,
      sRecursos.getFontMediana(),
      sRecursos.getColorPrincipal(),
      Color.WHITE,
      null,
      "c",
      true
    );
    bInsertar.addMouseListener(ProductsComponent);
    bInsertar.addActionListener(ProductsComponent);
    pOpciones.add(bInsertar);

    // BOTÓN MODIFICAR--------------------------------------------------------------------
    bModificar = sObjGraficos.construirJButton(
      "Modificar",
      300, 145, 120, 35,
      sRecursos.getCMano(),
      null,
      sRecursos.getFontMediana(),
      sRecursos.getColorPrincipal(),
      Color.WHITE,
      null,
      "c",
      true
    );
    bModificar.addMouseListener(ProductsComponent);
    bModificar.addActionListener(ProductsComponent);
    pOpciones.add(bModificar);

    // BOTÓN ELIMINAR--------------------------------------------------------------------
    bEliminar = sObjGraficos.construirJButton(
      "Eliminar",
      440, 145, 120, 35,
      sRecursos.getCMano(),
      null,
      sRecursos.getFontMediana(),
      sRecursos.getColorPrincipal(),
      Color.WHITE,
      null,
      "c",
      true
    );
    bEliminar.addMouseListener(ProductsComponent);
    bEliminar.addActionListener(ProductsComponent);
    pOpciones.add(bEliminar);
  }

  public void crearContenidoPDatos() {
    // LABEL INSTRUCCIONES ----------------------------------------------------------------
    lInstrucciones = sObjGraficos.construirJLabel(
      "<html>Productos<html>",
      20, 10, 120, 50,
      null, null,
      sRecursos.getFontTitulo(),
      null,
      sRecursos.getColorGrisOscuro(),
      null,
      "l"
    );
    pDatos.add(lInstrucciones);

    // LABEL ESLOGAN ----------------------------------------------------------------
    lEslogan = sObjGraficos.construirJLabel(
      "<html>A continuación puede ver y editar la información de los productos<html>",
      20, 50, 180, 90,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorGrisOscuro(),
      null,
      "l"
    );
    pDatos.add(lEslogan);

    // LABEL ID ----------------------------------------------------------------
    lId = sObjGraficos.construirJLabel(
      "Id Producto:",
      20, 140, 160, 30,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lId);

    // LABEL ID CONTENIDO ----------------------------------------------------------
    lIdValor = sObjGraficos.construirJLabel(
      "0",
      120, 140, 160, 30,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lIdValor);

    // LABEL PRODUCTO----------------------------------------------------------------
    lNombreP = sObjGraficos.construirJLabel(
      "Nombre Producto:",
      20, 180, 160, 30,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lNombreP);

    // TEXTFIELD PRODUCTO ----------------------------------------------------------------
    tNombreP = sObjGraficos.construirJTextField(
      "Item",
      30, 215, 180, 30,
      sRecursos.getFontLigera(),
      colorGris,
      sRecursos.getColorGrisOscuro(),
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    tNombreP.addFocusListener(ProductsComponent);
    pDatos.add(tNombreP);

    // LABEL PROVEEDOR ----------------------------------------------------------------
    lSupplier = sObjGraficos.construirJLabel(
      "Proveedor Item:",
      20, 265, 160, 30,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lSupplier);

    // TEXTFIELD PROVEEDOR ----------------------------------------------------------------
    tSupplier = sObjGraficos.construirJTextField(
      "Proveedor",
      30, 300, 180, 30,
      sRecursos.getFontLigera(),
      colorGris,
      sRecursos.getColorGrisOscuro(),
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    tSupplier.addFocusListener(ProductsComponent);
    pDatos.add(tSupplier);

    // LABEL NULL ----------------------------------------------------------------
    lOnll = sObjGraficos.construirJLabel(
      "NULL:",
      20, 350, 160, 30,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lOnll);

    // TEXTFIELD NULL ----------------------------------------------------------------
    tOnll = sObjGraficos.construirJTextField(
      "NULL",
      30, 385, 180, 30,
      sRecursos.getFontLigera(),
      colorGris,
      sRecursos.getColorGrisOscuro(),
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    tOnll.addFocusListener(ProductsComponent);
    pDatos.add(tOnll);

    // LABEL TELEFONO ----------------------------------------------------------------
    lPrice = sObjGraficos.construirJLabel(
      "Precio Item:",
      20, 425, 160, 30,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lPrice);

    // TEXTFIELD PRECIO----------------------------------------------------------------
    tPrice = sObjGraficos.construirJTextField(
      "Precio",
      30, 460, 180, 30,
      sRecursos.getFontLigera(),
      colorGris,
      sRecursos.getColorGrisOscuro(),
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    tPrice.addFocusListener(ProductsComponent);
    pDatos.add(tPrice);

    // LABEL EMAIL ----------------------------------------------------------------
    lEmail = sObjGraficos.construirJLabel(
      "Email Contacto:",
      20, 510, 160, 30,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorPrincipalOscuro(),
      null,
      "l"
    );
    pDatos.add(lEmail);

    // TEXTFIELD EMAIL ----------------------------------------------------------------
    tEmail = sObjGraficos.construirJTextField(
      "Email",
      30, 545, 180, 30,
      sRecursos.getFontLigera(),
      colorGris,
      sRecursos.getColorGrisOscuro(),
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    tEmail.addFocusListener(ProductsComponent);
    pDatos.add(tEmail);
  }

  public JTable getTabla() { return tabla; }

  public DefaultTableModel getModelo() { return modelo; }

  public JButton getBMostrar() { return bMostrar; }

  public JButton getBInsertar() { return bInsertar; }

  public JButton getBModificar() { return bModificar; }

  public JButton getBEliminar() { return bEliminar; }

  public JButton getBFiltrar() { return bFiltrar; }

  public JLabel getLIdValor() { return lIdValor; }

  public JTextField getTNombreP() { return tNombreP; }

  public JTextField getTSupplier() { return tSupplier; }

  public JTextField getTOficio() { return tOnll; }

  public JTextField getTPrice() { return tPrice; }

  public JTextField getTEmail() { return tEmail; }

  public JTextField getTConsulta() { return tConsulta; }
}