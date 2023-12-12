package logicService;

import javax.swing.ImageIcon; // Importa ImageIcon para manejar imágenes.
import models.Accion; // Importa la clase Accion del paquete models.
import java.io.*; // Importa clases para manejar archivos y flujos de entrada/salida.
import java.util.ArrayList; // Importa ArrayList para usar listas dinámicas.

public class AccionService {
  private static AccionService servicio; // Variable estática para implementar el patrón Singleton.
  private ArrayList<Accion> acciones; // Lista para almacenar objetos de tipo Accion.

  // Constructor de la clase.
  public AccionService() {
    acciones = new ArrayList<Accion>(); // Inicializa la lista de acciones.
    cargarDatos(); // Llama al método cargarDatos para llenar la lista.
  }

  // Método para cargar datos de un archivo de texto.
  public void cargarDatos() {
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    try {
      archivo = new File("src/archives/aciones.txt"); // Especifica el archivo de texto.
      fr = new FileReader(archivo); // Crea un FileReader para leer el archivo.
      br = new BufferedReader(fr); // Usa BufferedReader para leer líneas del archivo.

      String linea;
      while ((linea = br.readLine()) != null) { // Lee cada línea del archivo.
        String[] atributos = linea.split(","); // Separa la línea por comas.
        Accion accion = new Accion(); // Crea un nuevo objeto Accion.
        accion.setNombreAccion(atributos[0]); // Establece el nombre de la acción.
        accion.setDescripcionAccion(atributos[1]); // Establece la descripción.
        accion.setImagenAccion(new ImageIcon(atributos[2])); // Establece la imagen.
        acciones.add(accion); // Añade el objeto Accion a la lista.
      }
      fr.close(); // Cierra el FileReader.
    } catch (Exception e) {
      e.printStackTrace(); // Imprime errores si los hay.
    }
  }

  // Método para devolver una acción específica según su posición en la lista.
  public Accion devolverAccion(int posicion) {
    try {
      return acciones.get(posicion); // Devuelve la acción en la posición especificada.
    } catch (Exception e) {
      return null; // Devuelve null si hay un error (por ejemplo, posición inválida).
    }
  }

  // Método estático para obtener la instancia del servicio (patrón Singleton).
  public static AccionService getService() {
    if (servicio == null) servicio = new AccionService(); // Crea la instancia si aún no existe.
    return servicio; // Devuelve la instancia del servicio.
  }
}
