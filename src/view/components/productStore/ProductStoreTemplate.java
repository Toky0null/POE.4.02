package view.components.productStore;

import controller.ObjGraficosService;
import controller.RecursosService;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.ProductStore;

public class ProductStoreTemplate extends JPanel {
  private static final long serialVersionUID = 1L;
  
  // Declaración Servicios y dependencias
  private ProductStoreComponent productoComponent;
  private ObjGraficosService sObjGraficos;
  private RecursosService sRecursos;
  private ProductStore producto;

  // Declaración LayoutManager
  private GridBagLayout lGrid;
  private GridBagConstraints gbc;

  // Declaración Objetos Gráficos
  private JLabel lTitulo, lImagen, lParrafo, lEstrella, lPuntuacion, lCampo;

  // Declaración Objetos Decoradores
  private ImageIcon iEstrella, iDimAux;

  public ProductStoreTemplate(ProductStoreComponent productoComponent, ProductStore producto) {
    this.productoComponent = productoComponent;
    this.productoComponent.getClass();
    this.sObjGraficos = ObjGraficosService.getService();
    this.sRecursos = RecursosService.getService();
    this.producto = producto;

    lGrid = new GridBagLayout();
    gbc = new GridBagConstraints();
    this.setLayout(lGrid);

    this.crearJLabels();

    this.setPreferredSize(new Dimension(262, 330));
    this.setBorder(sRecursos.getBRedondeado());
    this.setBackground(Color.WHITE);
    this.setVisible(true);
  }

  public void crearJLabels() {
    lTitulo = sObjGraficos.construirJLabel(
      producto.getNombreProducto(),
      0, 0, 0, 0,
      null, null,
      sRecursos.getFontTitulo(),
      null,
      sRecursos.getColorPrincipal(),
      null,
      "l"
    );
    modificarGbc(0, 0, 3, 1, 0, 10, 10, 0, 0, 0, 0, 0, 0, 0);
    this.add(lTitulo, gbc);

    iDimAux = new ImageIcon(
      producto.getImagen().getImage()
        .getScaledInstance(250, 148, Image.SCALE_AREA_AVERAGING)
    );
    lImagen = sObjGraficos.construirJLabel(
      null,
      0, 0, 0, 0,
      sRecursos.getCMano(),
      iDimAux,
      null, null, null, null,
      "c"
    );
    lImagen.setBorder(sRecursos.getBRedondeado());
    modificarGbc(0, 1, 3, 1, 2, 10, 10, 3, 10, 3, 0, 0, 0, 0);
    this.add(lImagen, gbc);

    lParrafo = sObjGraficos.construirJLabel(
      "<html><div align='justify'>" + producto.getDescripcion() + "</div></html>",
      0, 0, 0, 0,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorGrisOscuro(),
      null,
      "l"
    );
    modificarGbc(0, 2, 3, 1, 2, 10, 10, 15, 10, 15, 0, 0, 0, 0);
    this.add(lParrafo, gbc);

    lCampo = sObjGraficos.construirJLabel(
      producto.getCampo(),
      0, 0, 0, 0,
      null, null,
      sRecursos.getFontMediana(),
      sRecursos.getColorPrincipalClaro(),
      sRecursos.getColorPrincipalMarino(),
      null,
      "c"
    );
    modificarGbc(0, 3, 1, 1, 0, 10, 10, 5, 15, 5, 10, 10, 0, 0);
    this.add(lCampo, gbc);

    iEstrella = new ImageIcon(getClass().getResource("/view/img/estrella.png"));
    iDimAux = new ImageIcon(
      iEstrella.getImage()
        .getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING)
    );
    lEstrella = sObjGraficos.construirJLabel(
      null,
      0, 0, 0, 0,
      null,
      iDimAux,
      null, null, null, null,
      "c"
    );
    modificarGbc(1, 3, 1, 1, 0, 13, 10, 5, 15, 5, 0, 0, 1, 0);
    this.add(lEstrella, gbc);

    lPuntuacion = sObjGraficos.construirJLabel(
      producto.getPuntuacion() + "/ 5",
      0, 0, 0, 0,
      null, null,
      sRecursos.getFontMediana(),
      null,
      sRecursos.getColorPrincipalMarino(),
      null,
      "l"
    );
    modificarGbc(2, 3, 1, 1, 0, 16, 10, 10, 15, 0, 0, 0, 0, 0);
    this.add(lPuntuacion, gbc);
  }

  public void modificarGbc(
    int posColumna, int posFila,
    int numColumnas, int numFilas,
    int tipoEstirado,
    int posicionInterna,
    int marginArriba, int marginDerecha, int marginAbajo, int marginIzquierda,
    int paddingX, int paddingY,
    int estiramientoColumna, int estiramientoFila
  ) {
    gbc.gridx = posColumna; 
    gbc.gridy = posFila;
    gbc.gridwidth = numColumnas;
    gbc.gridheight = numFilas;
    gbc.fill = tipoEstirado;
    gbc.anchor = posicionInterna;
    gbc.insets.top = marginArriba;
    gbc.insets.right = marginDerecha;
    gbc.insets.bottom = marginAbajo;
    gbc.insets.left = marginIzquierda;
    gbc.ipadx = paddingX;
    gbc.ipady = paddingY;
    gbc.weightx = estiramientoColumna;
    gbc.weighty = estiramientoFila;
  }
}