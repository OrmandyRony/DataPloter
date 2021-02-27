package Clases;

import GUI.Interfaz;
import java.util.logging.Level;
import java.util.logging.Logger;
import GUI.ProcesoOrdenamiento;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Esta clase contiene el metodo del agoritmo de ordenamiento BubbleSort en forma
 * descendete y ascendete, ademas del metodo cronometro.
 * @author Rony
 */
public class BubbleSort extends Thread {
    ProcesoOrdenamiento proceso;
    double[] valoresNumericos;
    String[] nombre;
    long espera;
    int  pasos;
    long tiempoTotal;
    boolean ascendente;
    
    /**
     * Metodo constructor parametrizado
     * @param proceso
     * @param valoresNumericos
     * @param nombre
     * @param espera
     * @param ascendente 
     */

    public BubbleSort(ProcesoOrdenamiento proceso, double[] valoresNumericos, 
            String[] nombre, long espera, boolean ascendente) {
        this.proceso = proceso;
        this.valoresNumericos = valoresNumericos;
        this.nombre = nombre;
        this.espera = espera;
        this.pasos = 0;
        this.tiempoTotal = 0;
        this.ascendente = ascendente;
    }

    
 
    @Override
    public void run(){
        if (ascendente) {
            bubbleSortAscendente();
            int opcion =JOptionPane.showConfirmDialog(null, "Desea realizar otra grafica", 
                    "Finalizar o continuar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            proceso.generarReporte();
            if (opcion == JOptionPane.YES_OPTION) {
                Interfaz ventana = new Interfaz();
                ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventana.setSize(800, 800);
                ventana.setVisible(true);
                ventana.setLocationRelativeTo(null);
                ventana.repaint();
                proceso.dispose();
                
            } 
            
        } else{
            bubbleSortDescendente();
            int opcion =JOptionPane.showConfirmDialog(null, "Desea realizar otra grafica", 
                    "Finalizar o continuar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            proceso.generarReporte();
            if (opcion == JOptionPane.YES_OPTION) {
                Interfaz ventana = new Interfaz();
                ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventana.setSize(800, 800);
                ventana.setVisible(true);
                ventana.setLocationRelativeTo(null);
                ventana.repaint();
                proceso.dispose();
                
            } 
        }
    }
 
    public void sleep(){
        
    }
    
    /**
     * Metodo de algoritmo de ordenamiento Bubble Sort, este ordena los datos de
     * en forma ascendente.
     */
    private void bubbleSortAscendente(){
         long tiempoInicial = System.currentTimeMillis();
         for (int i = 0; i < this.valoresNumericos.length; i++) {
                for (int j = 1; j < (this.valoresNumericos.length - i); j++) {
                    try {
                        try {
                            BubbleSort.sleep(this.espera);
                        } catch (InterruptedException ex) {
                            //Colocar mensaje de error al usuario
                            Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        pasos++;
                        
                        if (this.valoresNumericos[j - 1]> this.valoresNumericos[j]) {
                            double temporal = this.valoresNumericos[j - 1];
                            this.valoresNumericos[j - 1] = this.valoresNumericos[j];
                            this.valoresNumericos[j] = temporal;
                            
                            String textoTemporal = this.nombre[j - 1];
                            this.nombre[j -1] = this.nombre[j];
                            this.nombre[j] = textoTemporal;
                        }
                        long tiempoFinal = System.currentTimeMillis();
                        tiempoTotal = tiempoFinal - tiempoInicial;
                        proceso.actualizarVentana(this.valoresNumericos, this.nombre, pasos,
                                cronometro(tiempoTotal));
                        
                    } catch (IOException ex) {
                        Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
            }
    }
    
    /**
     * Metodo de algoritmo de ordenamiento Bubble Sort, este ordena los datos de
     * en forma descendente.
     */
    private void bubbleSortDescendente(){
        long tiempoInicial = System.currentTimeMillis();
        for (int i = 0; i < this.valoresNumericos.length; i++) {
            for (int j = 1; j < (this.valoresNumericos.length - i); j++) {
                try {
                    try {
                        BubbleSort.sleep(this.espera);
                    } catch (InterruptedException ex) {
                        //Colocar mensaje de error al usuario
                        Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pasos++;
                    
                    if (this.valoresNumericos[j - 1] < this.valoresNumericos[j]) {
                        double temporal = this.valoresNumericos[j - 1];
                        this.valoresNumericos[j - 1] = this.valoresNumericos[j];
                        this.valoresNumericos[j] = temporal;
                        
                        String textoTemporal = this.nombre[j - 1];
                        this.nombre[j - 1] = this.nombre[j];
                        this.nombre[j] = textoTemporal;
                    }
                    long tiempoFinal = System.currentTimeMillis();
                    tiempoTotal = tiempoFinal - tiempoInicial;
                    proceso.actualizarVentana(this.valoresNumericos, this.nombre, pasos,
                            cronometro(tiempoTotal));
                } catch (IOException ex) {
                    Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Este metodo le da un formato, de minutos, segundos y milisegundos al cronometro
     * que se vusualizara el vaenta de proceso de ordenamiento.
     * @param tiempo
     * @return 
     */
    
    public String cronometro(long tiempo){
        String cronometro;
        int minutos = 0, segundos = 0;
        int milesimas =(int) tiempo;
        String min = "", seg = "", mil = "";
        
        if (milesimas >= 1000) {
                segundos = milesimas/1000;
                milesimas = milesimas/100;   
            }
            
            if (segundos >= 60) {
                minutos = segundos/60;
                segundos = 0;
            }

            if (milesimas >= 100) {
                milesimas = milesimas/10;
            }
            
            if( minutos < 10 ) min = "0" + minutos;
            else min = String.valueOf(minutos);
            if( segundos < 10 ) seg = "0" + segundos;
            else seg = String.valueOf(segundos);

            if( milesimas < 10 ) mil = "00" + milesimas;
            else if( milesimas < 10 ) mil = "0" + milesimas;
            else mil = String.valueOf(milesimas);
           
            cronometro = min + ":" + seg + ":" + mil;
        
        return cronometro;   
    }
    
}
