package view.components.accion;

import controller.ObjGraficosService;
import controller.RecursosService;
import models.Accion;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccionTemplate extends JPanel {
  private static final long serialVersionUID = 1L;
  
  // Declaración de servicios y objetos
  private ObjGraficosService sObjGraficos;
  private RecursosService sRecursos;
  private AccionComponent accionComponent;

  // Declaración Objetos Gráficos
  private JLabel lImagen, lTitulo, lParrafo;

  // Declaración Objetos Decoradores
  private ImageIcon iDimAux;

  public AccionTemplate(AccionComponent accionComponent, Accion accion) {
    this.sObjGraficos = ObjGraficosService.getService();
    this.sRecursos = RecursosService.getService();
    this.accionComponent = accionComponent;
    this.accionComponent.getClass();

    iDimAux = new ImageIcon(
      accion.getImagenAccion().getImage()
        .getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)
    );
    this.lImagen = sObjGraficos.construirJLabel(
      null,
      (250 - 45) / 2, 15, 45, 45,
      null,
      iDimAux,
      null, null, null, null,
      "c"
    );
    this.add(lImagen);

    this.lTitulo = sObjGraficos.construirJLabel(
      accion.getNombreAccion(),
      (250 - 220) / 2, 60, 220, 30,
      null, null,
      sRecursos.getFontTitulo(),
      null,
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    this.add(lTitulo);

    this.lParrafo = sObjGraficos.construirJLabel(
      "<html><div align='center'>" + accion.getDescripcionAccion() + "</div></html>",
      (250 - 230) / 2, 85, 230, 50,
      null, null,
      sRecursos.getFontLigera(),
      null,
      sRecursos.getColorGrisOscuro(),
      null,
      "c"
    );
    this.add(lParrafo);

    this.setSize(250, 135);
    this.setBackground(Color.WHITE);
    this.setBorder(sRecursos.getBDifuminado());
    this.setLayout(null);
    this.setVisible(true);
  }
}