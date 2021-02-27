package usacdataplotter;
import GUI.Interfaz;
import javax.swing.JFrame;

/**
 * USAC Data Plotter
 *
 * @author Rony Ortiz ortizormandy@gmail.com
 * @version 0.0
 * @since 2020-09-20
 */

public class USACDataPlotter {

    /**
     * USAC Data Plotter
     * 
     * Esta aplicacion tiene como función de presentar datos de una forma grafica,
     * introduciendole la ruta de un archivo con formato csv.
     * 
     * Esta es nuestra clase principal
     * 
     * @param args 
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Interfaz ventana = new Interfaz();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 800);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.repaint();

        
    }
    
}
