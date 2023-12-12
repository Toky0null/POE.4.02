package view.components.products;

import controller.RecursosService;
import logicService.ArticleService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import models.Products;

public class ProductsComponent extends MouseAdapter implements ActionListener, FocusListener {
  private ProductTemplate productsTemplate;
  private ArticleService sProducts;
  private String[] placeholders = {
    "Nombre", "Precio", "Proveedor", "Telefono", "Email", "Filtrar...",
  };
  private Products products;

  public ProductsComponent() {
    sProducts = ArticleService.getService();
    productsTemplate = new ProductTemplate(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == productsTemplate.getBMostrar()) mostrarRegistrosTabla();
    if (e.getSource() == productsTemplate.getBInsertar()) insertarRegistroTabla();
    if (e.getSource() == productsTemplate.getBModificar()) modificarRegistroTabla();
    if (e.getSource() == productsTemplate.getBEliminar()) eliminarRegistroTabla();
    if (e.getSource() == productsTemplate.getBFiltrar()) filtrarRegistrosTabla();
  }

  public ProductTemplate getAmigosTemplate() {
    return productsTemplate;
  }

  @Override
  public void focusGained(FocusEvent e) {
    JTextField textField = ((JTextField) e.getSource());
    textField.setBorder(RecursosService.getService().getBAzul());
    if (
      textField.getText().equals(placeholders[0]) ||
      textField.getText().equals(placeholders[1]) ||
      textField.getText().equals(placeholders[2]) ||
      textField.getText().equals(placeholders[3]) ||
      textField.getText().equals(placeholders[4]) ||
      textField.getText().equals(placeholders[5])
    ) textField.setText("");
  }

  @Override
  public void focusLost(FocusEvent e) {
    JTextField textField = ((JTextField) e.getSource());
    textField.setBorder(null);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getSource() instanceof JTable) {
      int fila = productsTemplate.getTabla().getSelectedRow();
      products = sProducts.devolverAmigo(fila);
      productsTemplate.getLIdValor().setText(products.getId() + "");
      productsTemplate.getTNombreP().setText(products.getNombre());
      productsTemplate.getTSupplier().setText(products.getEdad());
      productsTemplate.getTOficio().setText(products.getOnll());
      productsTemplate.getTPrice().setText(products.getPrice());
      productsTemplate.getTEmail().setText(products.getEmail());
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (e.getSource() instanceof JButton) {
      JButton boton = ((JButton) e.getSource());
      boton.setBackground(RecursosService.getService().getColorPrincipalOscuro());
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {
    if (e.getSource() instanceof JButton) {
      JButton boton = ((JButton) e.getSource());
      boton.setBackground(RecursosService.getService().getColorPrincipal());
    }
  }

  public void restaurarValores() {
    productsTemplate.getLIdValor().setText(sProducts.devolverCantidadAmigos() + "");
    productsTemplate.getTNombreP().setText(placeholders[0]);
    productsTemplate.getTSupplier().setText(placeholders[1]);
    productsTemplate.getTOficio().setText(placeholders[2]);
    productsTemplate.getTPrice().setText(placeholders[3]);
    productsTemplate.getTEmail().setText(placeholders[4]);
    productsTemplate.getTabla().setSelectionMode(0);
  }

  public void mostrarRegistrosTabla() {
    for (int i = 0; i < sProducts.devolverCantidadAmigos(); i++) {
      products = sProducts.devolverAmigo(i);
      this.agregarRegistro(products);
    }
    productsTemplate.getLIdValor().setText(sProducts.devolverCantidadAmigos() + "");
    productsTemplate.getBMostrar().setEnabled(false);
  }

  public void insertarRegistroTabla() {
    products = new Products();
    products.setId(sProducts.devolverCantidadAmigos());
    products.setNombre(productsTemplate.getTNombreP().getText());
    products.setEdad(productsTemplate.getTSupplier().getText());
    products.setOficio(productsTemplate.getTOficio().getText());
    products.setTelefono(productsTemplate.getTPrice().getText());
    products.setEmail(productsTemplate.getTEmail().getText());
    this.agregarRegistro(products);
    sProducts.agregarAmigo(products);
    restaurarValores();
  }

  public void modificarRegistroTabla() {
    int fSeleccionada = productsTemplate.getTabla().getSelectedRow();
    if (fSeleccionada != -1) {
      productsTemplate.getModelo()
        .setValueAt(productsTemplate.getTNombreP().getText(), fSeleccionada, 1);
      productsTemplate.getModelo()
        .setValueAt(productsTemplate.getTPrice().getText(), fSeleccionada, 2);
      productsTemplate.getModelo()
        .setValueAt(productsTemplate.getTEmail().getText(), fSeleccionada, 3);
      products = sProducts.devolverAmigo(fSeleccionada);
      products.setNombre(productsTemplate.getTNombreP().getText());
      products.setEdad(productsTemplate.getTSupplier().getText());
      products.setOficio(productsTemplate.getTOficio().getText());
      products.setTelefono(productsTemplate.getTPrice().getText());
      products.setEmail(productsTemplate.getTEmail().getText());
      restaurarValores();
    } else JOptionPane.showMessageDialog(
      null,
      "seleccione una fila",
      "Error",
      JOptionPane.ERROR_MESSAGE
    );
  }

  public void eliminarRegistroTabla() {
    int fSeleccionada = productsTemplate.getTabla().getSelectedRow();
    if (fSeleccionada != -1) 
      productsTemplate.getModelo().removeRow(fSeleccionada); 
    else 
      JOptionPane.showMessageDialog(
        null,
        "seleccione una fila",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
  }

  public void filtrarRegistrosTabla() {
    TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(
      productsTemplate.getModelo()
    );
    productsTemplate.getTabla().setRowSorter(trs);
    trs.setRowFilter(RowFilter.regexFilter(productsTemplate.getTConsulta().getText())
    );
  }

  public void agregarRegistro(Products products) {
    productsTemplate
      .getModelo()
      .addRow(new Object[] {
          products.getId(),
          products.getNombre(),
          products.getPrice(),
          products.getEmail(),
        }
      );
  }
}