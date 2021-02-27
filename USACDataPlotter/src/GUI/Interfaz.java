package GUI;

import Clases.LeerArchivo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Esta clase contienen la ventana principal que vera el usuario,
 * donde ingresara la ruta del archivo en formato csv y le dara un nombre a la 
 * grafica.
 * @author Rony
 */
public class Interfaz extends JFrame implements ActionListener {
    private JLabel etiqueta1;
    private final JTextField campoTexto1;
    private JTextField campoTexto2;
    private JButton btnBuscar;
    private JButton btnAceptar;
    private String ruta;
    JPanel panelGrafica;
    Container cp = getContentPane();
    String[] datos;
    String[] nombreEjes;
    double[] valoresNumericos;
    String[] nombre;
    
    public Interfaz() {
        super("USAC Processing Data"); 
        
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(null);
        
        etiqueta1 = new JLabel("Ruta del archivo:");
        etiqueta1.setBounds(10, 10, 100, 20);
        panelDatos.add(etiqueta1);
        
        campoTexto1 = new JTextField("");
        campoTexto1.setBounds(10, 40, 645, 30);
        panelDatos.add(campoTexto1);
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(670, 40, 100, 30);
        btnBuscar.addActionListener(this);
        panelDatos.add(btnBuscar);
        
        etiqueta1 = new JLabel("Titulo para la gráfica:");
        etiqueta1.setBounds(10, 70, 200, 20);
        panelDatos.add(etiqueta1);
        
        campoTexto2 = new JTextField("");
        campoTexto2.setBounds(10, 100, 765, 30);
        panelDatos.add(campoTexto2);
        
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(670, 140, 100, 30);
        btnAceptar.addActionListener(this);
        panelDatos.add(btnAceptar);
         
        
        cp.add(panelDatos, BorderLayout.CENTER);
        
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (btnBuscar == evento.getSource()) {
            
            JFileChooser fc = new JFileChooser();
            int op = fc.showOpenDialog(this);
            if (op == JFileChooser.APPROVE_OPTION){
                campoTexto1.setText(fc.getSelectedFile().toString());
                ruta = fc.getSelectedFile().toString();
            } 
               
        }
        
        if (btnAceptar == evento.getSource()) {
             ruta = campoTexto1.getText();
            LeerArchivo archivo = new LeerArchivo();
            if (archivo.existeArchivo(ruta)) {
                this.repaint();
                datos = archivo.leer(this, ruta);
                ordenarDatos();
                 try {
                     Grafica();
                 } catch (IOException ex) {
                     Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                 }
                Opciones opciones = new Opciones(this, campoTexto2.getText() ,nombreEjes, 
                        valoresNumericos, nombre);
                this.validate();
            } else {
                JOptionPane.showMessageDialog(this, "Los parametros estan vacios"
                        + " o el archivo no existe"
                        , "Error ", JOptionPane.ERROR_MESSAGE);
            }     
        }
    }
    
    /**
     * Metodo para dividir los datos ingresados en 3, el primero contiene los 
     * nombres de los ejes de la grafica, el segundo solo los valores numericos 
     * y el tercero los nombres de los valores numericos.
     */
    
    private void ordenarDatos(){
        this.nombreEjes = new String[2];
        this.nombreEjes[0] = datos[0];
        this.nombreEjes[1] = datos[1];
        
        int tamano = (datos.length-2)/2;
        
        this.nombre = new String[tamano];
        this.valoresNumericos = new double[tamano];
        
        for (int i = 1; i <= tamano; i++) {
            this.nombre[i-1] = datos[2*i];
        }
        
        for (int i = 1; i <= tamano; i++) {
            this.valoresNumericos[i-1] = Double.parseDouble(datos[2*i+1]);
        }
        
        
    }
    
    /**
     * Metodo para generar la grafica de barras.
     * @return 
     */
    
    private void Grafica() throws IOException {
        panelGrafica = new JPanel();
     
        // Fuente de Datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       
        for (int i = 0; i < valoresNumericos.length; i++) {
            dataset.setValue(valoresNumericos[i], "", nombre[i]);
        }
        
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createBarChart3D
        (campoTexto2.getText(), nombreEjes[0], nombreEjes[1], 
        dataset, PlotOrientation.VERTICAL, true,true, false);
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.black); 
        CategoryPlot p = chart.getCategoryPlot(); 
        p.setRangeGridlinePaint(Color.blue); 
        BarRenderer render = (BarRenderer)p.getRenderer();
        render.setSeriesPaint(0, Color.green);
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        ChartUtilities.saveChartAsPNG(new File("graficaDesordenada.png"), chart, 800, 800 );
                
        panelGrafica.add(chartPanel);
        cp.add(panelGrafica, BorderLayout.SOUTH);
    }
}
