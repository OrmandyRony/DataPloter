/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Esta clase contiene la ventana en la que se mostrara la grafica, y los cambios 
 * que se vallan haciendo al realizarse el ordenamiento.
 * 
 * @author Rony
 */
public class ProcesoOrdenamiento extends JFrame {
    
    private JLabel etiqueta1;
    private JLabel etiqueta2;
    private JLabel etiqueta3;
    private JLabel etiqueta4;
    private JLabel etiqueta5;
    String nombreGrafica;
    String[] nombreEjes;
    double[] valoresNumericos;
    String[] nombre;
    String algoritmo;
    String tiempo;
    String velocidad;
    int pasos;
    boolean orden;
    JPanel panelDatos;
    JPanel panelGrafica;
    Container cp = getContentPane();
    Thread finalizar;
    public int hilo;
    String tablaDatos;
    
    /**
     * Metodo parametizado
     * @param nombreGrafica
     * @param nombreEjes
     * @param valoresNumericos
     * @param nombre
     * @param algoritmo
     * @param velocidad
     * @param orden 
     */
    
    public ProcesoOrdenamiento(String nombreGrafica, String[] nombreEjes, 
            double[] valoresNumericos,String[] nombre,String algoritmo, String velocidad, 
            boolean orden) {
        
        super("Proceso de Ordenamiento");
        panelDatos = new JPanel();
        panelDatos.setLayout(null);
        setVisible(true);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.algoritmo = algoritmo;
        this.velocidad = velocidad;
        this.orden = orden;
        this.nombreGrafica = nombreGrafica;
        this.nombreEjes = nombreEjes;
        this.valoresNumericos = valoresNumericos;
        this.nombre = nombre;
        this.algoritmo = algoritmo;
        this.orden = orden;
        this.tablaDatos = "";
        
        etiqueta1 = new JLabel("Algoritmo: " + this.algoritmo);
        etiqueta1.setBounds(100, 500, 200, 20);
        panelDatos.add(etiqueta1);
          
        etiqueta2 = new JLabel("Tiempo: " + this.tiempo);
        etiqueta2.setBounds(570, 500, 100, 20);
        panelDatos.add(etiqueta2);
        
        etiqueta5 = new JLabel("Velocidad: " + this.velocidad);
        etiqueta5.setBounds(100, 530, 200, 20);
        panelDatos.add(etiqueta5);
        
        etiqueta3= new JLabel("Pasos: "+ this.pasos);
        etiqueta3.setBounds(570, 530, 100, 20);
        panelDatos.add(etiqueta3);
        
        etiqueta4 = new JLabel("Orden: " + ordena(this.orden));
        etiqueta4.setBounds(100, 560, 200, 20);
        panelDatos.add(etiqueta4);
        
        
        cp.add(panelDatos, BorderLayout.CENTER);
        Grafica();
       

      
    }
    
    /**
     * Este metodo le muestra la primera grafica al usuario en el tiempo 0.
     */
    
    private void Grafica() {
        panelGrafica = new JPanel();
     
        // Fuente de Datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       
        for (int i = 0; i < valoresNumericos.length; i++) {
            dataset.setValue(valoresNumericos[i], "", nombre[i]);
        }
        
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createBarChart3D
        (nombreGrafica, nombreEjes[0], nombreEjes[1], 
        dataset, PlotOrientation.VERTICAL, true,true, false);
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.black); 
        CategoryPlot p = chart.getCategoryPlot(); 
        p.setRangeGridlinePaint(Color.blue); 
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer render = (BarRenderer)plot.getRenderer();
        render.setSeriesPaint(0, Color.green);
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
               
        panelGrafica.add(chartPanel);
        cp.add(panelGrafica, BorderLayout.SOUTH);
    }
    
    /**
     * Metodo que le asigna ascendente o descenten a la variavle ordenl, para 
     * mostrarle al usuario el orden elegido.
     * @param orde
     * @return 
     */
    
    public String ordena(boolean orde){
        String orden1;
        if (orde) {
            orden1 = "Ascendente";
        } else{
            orden1 = "Descendente";
        }
        return orden1;
    }
    
    /**
     * Metodo que actualiza la grafica, el numero de pasos y el tiempo segun vallan 
     * ordenando los datos, los algoritmos de ordenamiento.
     * 
     * @throws IOException 
     */
    
    private void refrescarGrafica() throws IOException {
        
        
        etiqueta3.removeAll();
        etiqueta3.setText("Pasos: " + this.pasos);
      
        etiqueta2.removeAll();
        etiqueta2.setText("Tiempo: " + this.tiempo);

        panelGrafica.removeAll();
        panelGrafica.revalidate();
        
        
        
        // Fuente de Datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (int i = 0; i < nombre.length; i++) {
            dataset.setValue(valoresNumericos[i], "", nombre[i]);
        }
        
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createBarChart3D
        (nombreGrafica, nombreEjes[0], nombreEjes[1], 
        dataset, PlotOrientation.VERTICAL, true,true, false);
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setPaint(Color.black); 
        CategoryPlot p = chart.getCategoryPlot(); 
        p.setRangeGridlinePaint(Color.blue); 
        BarRenderer render = (BarRenderer)p.getRenderer();
        render.setSeriesPaint(0, Color.green);
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
 
        ChartUtilities.saveChartAsPNG(new File("graficaOrdenada.png"), chart, 800, 800);
        
        etiqueta1.repaint();
        etiqueta4.repaint();
        etiqueta5.repaint();
        etiqueta3.repaint();
        etiqueta2.repaint();
        panelGrafica.add(chartPanel);
        cp.add(panelGrafica);
        cp.validate();
        
    }
    
    /**
     * Metodo que es invocado en cada algoritmo de ordenamiento, para enviarle 
     * los datos que valla ordenando, este actualiza el orden de los valores numericos 
     * los nombres, pasos y tiempo.
     * 
     * @param actualizarValores
     * @param actualizarNombres
     * @param pasos
     * @param tiempo
     * @throws IOException 
     */
    
    public void actualizarVentana(double[] actualizarValores,String[] actualizarNombres,int pasos, String tiempo) throws IOException {
       valoresNumericos = actualizarValores;
       nombre = actualizarNombres;
       this.pasos = pasos;
       this.tiempo = tiempo;
       refrescarGrafica();
    }
    
    /**
     * Este meotodo genera un reporte en formato html al finalizar el proceso de ordenamiento, 
     * el cual incluye el valor mínimo y máximo de los datos ingresados, tambien 
     * las imagenes de la grafica como fueron ingresados los datos y la imagen 
     * de la grafica ya ordenados, tambien muestra una tabla con los datos ya 
     * ordebados.
     * 
     */
    
    public void generarReporte(){
        int maximo = this.nombre.length -1;
        
        String head ="<head>\n" +
"	<title>Reporte USAC Data Plotter</title>\n" +
"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/reporte.css\">\n" +
"       </head>";
        
        String body = "<body>\n" +
"	<div id = principal>\n" +
"		<header>\n" +
"			<div id=\"titulo_principal\">\n" +
"				<h1>Rony Ormandy Ortíz Alvarez</h1>\n" +
"				<h2>201807328</h2>\n" +
"			</div>\n" +
"		</header>\n" +
"		<hr>\n" +
"		<section id=\"datos\">\n" +
"			<div class=\"panelIzquierdo\">\n" +
"				<h3>Algoritmo:" + " " + this.algoritmo + "</h3>\n" +
"				<h3>Velocidad:" + " " + this.velocidad + "</h3>\n" +
"				<h3>Orden:" + " " + ordena(this.orden)  + "</h3>\n" +
"			</div>\n" +
"\n" +
"			<div class=\"panelDerecho\">\n" +
"				<h3>Tiempo:" + " " + this.tiempo + "</h3>\n" +
"				<h3>Pasos:" + " " + this.pasos + "</h3>\n" +
"			</div>\n" +
"		</section> <hr>\n" +
"\n" +
"		<section id=\"tablas\">\n" +
"			<div class=\"panelIzquierdo\">\n" +
"				<table>\n" +
"					<tr>\n" +
"						<td colspan=\"2\">Dato Mínimo</td>\n" +
"					</tr>\n" +
"				 	<tr>\n" + minimo(this.orden, maximo) +
"				 	</tr>\n" +
"				</table>\n" +
"			</div>\n" +
"\n" +
"			<div class=\"panelDerecho\">\n" +
"				<table>\n" +
"					<tr>\n" +
"						<td colspan=\"2\">Dato Máximo</td>\n" +
"					</tr>\n" +
"				 	<tr>\n" + maximo(this.orden, maximo) +
"				 	</tr>\n" +
"				</table>\n" +
"			</div>\n" +
"		</section> <hr>\n" +
"\n" +
"		<section>\n" +
"			<div class=\"grafica\">\n" +
"				<div class=\"titulo\">\n" +
"					<h1>Datos Desordenados</h1>\n" +
"				</div>\n" +
"				<p><img src=\"graficaDesordenada.png\"></p>\n" +
"\n" +
"			</div>\n" +
"		</section> <hr>\n" +
"\n" +
"		<section>\n" +
"			<div class=\"grafica\">\n" +
"				<div class=\"titulo\">\n" +
"					<h1>Datos Ordenados</h1>\n" +
"				</div>\n" + tablaDatos(this.nombreEjes, this.valoresNumericos, this.nombre) +
"				<p><img src=\"graficaOrdenada.png\"></p>\n" +
"\n" +
"			</div>\n" +
"		</section>\n" +
"\n" +
"\n" +
"	</div>\n" +
"\n" +
"</body>";
        
        String html = "<!DOCTYPE html>\n<html>" + head + body + "</html>";
        BufferedWriter Writer;
        try {
            Writer = new BufferedWriter(new FileWriter("Reporte.html", false));
            Writer.write(html);
            Writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ProcesoOrdenamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Este metodo genera la tabla de los datos ordenados en html.
     * 
     * @param nombreEjes
     * @param valoresNumericos
     * @param nombre
     * @return 
     */
    
    private String tablaDatos(String[] nombreEjes, 
            double[] valoresNumericos,String[] nombre){
        String tabla = "";
        tabla += "<table>\n" +
"		 	<tr>\n" +
"	 		<td>" + nombreEjes[0] +"</td>\n" +
"	 		<td>" + nombreEjes[1] +"</td>\n" +
"		 	</tr>\n";
        
        for (int i = 0; i < valoresNumericos.length; i++) {
            tabla +=    "<tr>\n" +
"	 		<td>" + nombre[i] +"</td>\n" +
"	 		<td>" + valoresNumericos[i] +"</td>\n" +
"		 	</tr>\n";
        }
        tabla += "</table>\n";
                
        return tabla;
        
        
    }
    
    /**
     * Este metodo retorna el valor minimo de los valores ingresados, este es importante
     * debido a que los datos se ordenan de forma desencente y ascendente, este metodo
     * valida que opcion de ordenamiento fue ingresada.
     * @param min
     * @param maximo
     * @return 
     */
        
    public String minimo(boolean min, int maximo){
        String tablam = "";
        if (min) {
            tablam += "<td>" + this.nombre[0] + "</td>\n" +
"                   <td>" + this.valoresNumericos[0] + "</td>\n";
        }else{
            tablam += "<td>" + this.nombre[maximo] + "</td>\n" +
"                     <td>" + this.valoresNumericos[maximo] + "</td>\n";
        }
     return tablam;       
    }
    
    /**
     * Este metodo retorna el valor maximo de los valores ingresados, este es importante
     * debido a que los datos se ordenan de forma desencente y ascendente, este metodo
     * valida que opcion de ordenamiento fue ingresada.
     * 
     * @param max
     * @param maximo
     * @return 
     */
    
    public String maximo(boolean max, int maximo){
        String tablam = "";
        if (max) {
            tablam += "<td>" + this.nombre[maximo] + "</td>\n" +
                      "<td>" + this.valoresNumericos[maximo] + "</td>\n";
        }else{
            tablam += "<td>" + this.nombre[0] + "</td>\n" +
                     "<td>" + this.valoresNumericos[0] + "</td>\n";
        }
     return tablam;       
    }
    
}
