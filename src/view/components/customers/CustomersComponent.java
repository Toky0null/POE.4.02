package view.components.customers;

import controller.RecursosService;
import logicService.ArticleCustomersService;

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

import models.Customers;

public class CustomersComponent extends MouseAdapter implements ActionListener, FocusListener {
  private CustomersTemplate clienteTemplate;
  private ArticleCustomersService sClientes;
  private String[] placeholders = {
    "Nombre", "Edad", "Oficio", "Telefono", "Email", "Filtrar...",
  };
  private Customers cliente;

  public CustomersComponent() {
    sClientes = ArticleCustomersService.getService();
    clienteTemplate = new CustomersTemplate(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == clienteTemplate.getBMostrar()) mostrarRegistrosTabla();
    if (e.getSource() == clienteTemplate.getBInsertar()) insertarRegistroTabla();
    if (e.getSource() == clienteTemplate.getBModificar()) modificarRegistroTabla();
    if (e.getSource() == clienteTemplate.getBEliminar()) eliminarRegistroTabla();
    if (e.getSource() == clienteTemplate.getBFiltrar()) filtrarRegistrosTabla();
  }

  public CustomersTemplate getAmigosTemplate() {
    return clienteTemplate;
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
      int fila = clienteTemplate.getTabla().getSelectedRow();
      cliente = sClientes.returnCustomers(fila);
      clienteTemplate.getLIdValor().setText(cliente.getId() + "");
      clienteTemplate.getTNombre().setText(cliente.getNombre());
      clienteTemplate.getTEdad().setText(cliente.getEdad());
      clienteTemplate.getTOficio().setText(cliente.getOficio());
      clienteTemplate.getTTelefono().setText(cliente.getTelefono());
      clienteTemplate.getTEmail().setText(cliente.getEmail());
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
    clienteTemplate.getLIdValor().setText(sClientes.returnAmountC() + "");
    clienteTemplate.getTNombre().setText(placeholders[0]);
    clienteTemplate.getTEdad().setText(placeholders[1]);
    clienteTemplate.getTOficio().setText(placeholders[2]);
    clienteTemplate.getTTelefono().setText(placeholders[3]);
    clienteTemplate.getTEmail().setText(placeholders[4]);
    clienteTemplate.getTabla().setSelectionMode(0);
  }

  public void mostrarRegistrosTabla() {
    for (int i = 0; i < sClientes.returnAmountC(); i++) {
      cliente = sClientes.returnCustomers(i);
      this.agregarRegistro(cliente);
    }
    clienteTemplate.getLIdValor().setText(sClientes.returnAmountC() + "");
    clienteTemplate.getBMostrar().setEnabled(false);
  }

  public void insertarRegistroTabla() {
    cliente = new Customers();
    cliente.setId(sClientes.returnAmountC());
    cliente.setNombre(clienteTemplate.getTNombre().getText());
    cliente.setEdad(clienteTemplate.getTEdad().getText());
    cliente.setOficio(clienteTemplate.getTOficio().getText());
    cliente.setTelefono(clienteTemplate.getTTelefono().getText());
    cliente.setEmail(clienteTemplate.getTEmail().getText());
    this.agregarRegistro(cliente);
    sClientes.addCustomers(cliente);
    restaurarValores();
  }

  public void modificarRegistroTabla() {
    int fSeleccionada = clienteTemplate.getTabla().getSelectedRow();
    if (fSeleccionada != -1) {
      clienteTemplate.getModelo()
        .setValueAt(clienteTemplate.getTNombre().getText(), fSeleccionada, 1);
      clienteTemplate.getModelo()
        .setValueAt(clienteTemplate.getTTelefono().getText(), fSeleccionada, 2);
      clienteTemplate.getModelo()
        .setValueAt(clienteTemplate.getTEmail().getText(), fSeleccionada, 3);
      cliente = sClientes.returnCustomers(fSeleccionada);
      cliente.setNombre(clienteTemplate.getTNombre().getText());
      cliente.setEdad(clienteTemplate.getTEdad().getText());
      cliente.setOficio(clienteTemplate.getTOficio().getText());
      cliente.setTelefono(clienteTemplate.getTTelefono().getText());
      cliente.setEmail(clienteTemplate.getTEmail().getText());
      restaurarValores();
    } else JOptionPane.showMessageDialog(
      null,
      "seleccione una fila",
      "Error",
      JOptionPane.ERROR_MESSAGE
    );
  }

  public void eliminarRegistroTabla() {
    int fSeleccionada = clienteTemplate.getTabla().getSelectedRow();
    if (fSeleccionada != -1) 
      clienteTemplate.getModelo().removeRow(fSeleccionada); 
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
      clienteTemplate.getModelo()
    );
    clienteTemplate.getTabla().setRowSorter(trs);
    trs.setRowFilter(RowFilter.regexFilter(clienteTemplate.getTConsulta().getText())
    );
  }

  public void agregarRegistro(Customers cliente) {
    clienteTemplate
      .getModelo()
      .addRow(new Object[] {
          cliente.getId(),
          cliente.getNombre(),
          cliente.getTelefono(),
          cliente.getEmail(),
        }
      );
  }
}