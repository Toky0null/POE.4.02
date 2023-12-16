package view.components.products;

import controller.RecursosService;
import logicService.ArticleStService;

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
  private ArticleStService sProducts;
  private String[] placeholders = {
    "Nombre", "Precio", "Proveedor", "Telefono", "Email", "Filtrar...",
  };
  private Products products;

  public ProductsComponent() {
    sProducts = ArticleStService.getService();
    productsTemplate = new ProductTemplate(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == productsTemplate.getBMostrar()) mostrarRegistrosTabla();
    if (e.getSource() == productsTemplate.getBInsert()) insertarRegistroTabla();
    if (e.getSource() == productsTemplate.getBChange()) modificarRegistroTabla();
    if (e.getSource() == productsTemplate.getRemove()) eliminarRegistroTabla();
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
      products = sProducts.getItem(fila);
      productsTemplate.getLIdValor().setText(products.getId() + "");
      productsTemplate.getTNameP().setText(products.getName());
      productsTemplate.getTSupplier().setText(products.getSupplier());
      productsTemplate.getTOnll().setText(products.getOnll());
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
    productsTemplate.getLIdValor().setText(sProducts.returnAmountItems() + "");
    productsTemplate.getTNameP().setText(placeholders[0]);
    productsTemplate.getTSupplier().setText(placeholders[1]);
    productsTemplate.getTOnll().setText(placeholders[2]);
    productsTemplate.getTPrice().setText(placeholders[3]);
    productsTemplate.getTEmail().setText(placeholders[4]);
    productsTemplate.getTabla().setSelectionMode(0);
  }

  public void mostrarRegistrosTabla() {
    for (int i = 0; i < sProducts.returnAmountItems(); i++) {
      products = sProducts.getItem(i);
      this.agregarRegistro(products);
    }
    productsTemplate.getLIdValor().setText(sProducts.returnAmountItems() + "");
    productsTemplate.getBMostrar().setEnabled(false);
  }

  public void insertarRegistroTabla() {
    products = new Products();
    products.setId(sProducts.returnAmountItems());
    products.setName(productsTemplate.getTNameP().getText());
    products.setSupplier(productsTemplate.getTSupplier().getText());
    products.setOnll(productsTemplate.getTOnll().getText());
    products.setTelefono(productsTemplate.getTPrice().getText());
    products.setEmail(productsTemplate.getTEmail().getText());
    this.agregarRegistro(products);
    sProducts.addItemA(products);
    restaurarValores();
  }

  public void modificarRegistroTabla() {
    int fSeleccionada = productsTemplate.getTabla().getSelectedRow();
    if (fSeleccionada != -1) {
      productsTemplate.getModelo()
        .setValueAt(productsTemplate.getTNameP().getText(), fSeleccionada, 1);
      productsTemplate.getModelo()
        .setValueAt(productsTemplate.getTPrice().getText(), fSeleccionada, 2);
      productsTemplate.getModelo()
        .setValueAt(productsTemplate.getTEmail().getText(), fSeleccionada, 3);
      products = sProducts.getItem(fSeleccionada);
      products.setName(productsTemplate.getTNameP().getText());
      products.setSupplier(productsTemplate.getTSupplier().getText());
      products.setOnll(productsTemplate.getTOnll().getText());
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
          products.getName(),
          products.getPrice(),
          products.getEmail(),
        }
      );
  }
}