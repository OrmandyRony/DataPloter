
package Clases;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Esta clase contiene el metodo para leer el archivo y pasarlo a una matriz.
 * 
 * @author Rony
 */
public class LeerArchivo {
    
    /**
     * Metodo para verificar que la ruta del archivo enviado, contenga una ruta
     * y no se encuentre vacio.
     * 
     * @param archivo
     * @return un valor booleano
     */
    
    public boolean existeArchivo(String archivo){   
        if (archivo != null) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodo para leer el archivo y convertirlo en una matriz de String[]
     * 
     * @param ventana
     * @param archivo
     * @return una matriz de String[]
     */
      
    public String[] leer(JFrame ventana, String archivo){
        String texto = "";
        try{            
                if(archivo != null){
                FileReader lector = new FileReader(archivo);
                BufferedReader leer = new BufferedReader(lector);
                String auxiliar = "";
                while((auxiliar = leer.readLine())!= null){
                    texto += auxiliar + ",";
                    
                }
                leer.close();
                }
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(ventana,"No se encontro el archivo, intentelo"
                    + "de nuevo.");
        }
      
        String[] datos = texto.split(",");
     
        return datos;
    }    
    
}
