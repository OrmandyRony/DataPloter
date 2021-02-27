package Clases;

import java.util.logging.Level;
import java.util.logging.Logger;
import GUI.Interfaz;
import GUI.ProcesoOrdenamiento;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Esta clase contiene el metodo de algoritmo de ordenamiento quicksort en forma
 * desendente y ascendete, ademas de un cronometro.
 * 
 * @author Rony
 */
public class Quicksort extends Thread {
    ProcesoOrdenamiento proceso;
    String[] nombresEjes;
    double[] valoresNumericos;
    String[] nombre;
    long espera;
    int pasos;
    long tiempoTotal;
    boolean ascendente;

    public Quicksort(ProcesoOrdenamiento proceso, double[] valoresNumericos, 
            String[] nombre, long espera,boolean ascendente) {
        
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
            ordenamientoQuicksortA(this.valoresNumericos, this.nombre, 0, this.valoresNumericos.length - 1);
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
            ordenamientoQuicksortD(this.valoresNumericos, this.nombre, 0, this.valoresNumericos.length - 1);
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
    
    /**
     * Este metodo contiene el algoritmo de ordenamiento quicksort en forma ascendente.
     * 
     * @param datos
     * @param datosNombres
     * @param izq
     * @param der 
     */
    public void ordenamientoQuicksortA(double [] datos, String[] datosNombres, int izq ,int der){
            try {
                double pivote = datos[izq];
                int i = izq;
                int j = der;
                double aux;
                String temporal;
                long tiempoFinal = 0;
                long tiempoInicial = System.currentTimeMillis();
                while(i < j){
                    while(datos[i] <= pivote && i < j) i++;
                    while(datos[j] > pivote) j--;
                    pasos++;
                    if (i < j) {
                        aux = datos[i];
                        datos[i] = datos[j];
                        datos[j] = aux;

                        temporal = datosNombres[i];
                        datosNombres[i] = datosNombres[j];
                        datosNombres[j] = temporal;

                    }
                    try {
                            Quicksort.sleep(this.espera);
                        } catch (InterruptedException ex) {
                            //Colocar mensaje de error al usuario
                            Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        tiempoFinal = System.currentTimeMillis();
                        tiempoTotal = tiempoFinal - tiempoInicial;
                        proceso.actualizarVentana(this.valoresNumericos, this.nombre, pasos, cronometro(tiempoTotal));
                   
                }
                
                datos[izq] = datos[j];
                datos[j] = pivote;
                if (izq < j - 1) {
                    ordenamientoQuicksortA(datos, datosNombres, izq, j - 1);
                }
                
                if (j + 1 < der) {
                    ordenamientoQuicksortA(datos, datosNombres, j + 1, der);
                }
                
                try {
                    Quicksort.sleep(this.espera);
                } catch (InterruptedException ex) {
                    //Colocar mensaje de error al usuario
                    Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                tiempoFinal = System.currentTimeMillis();
                tiempoTotal = tiempoFinal - tiempoInicial;
                proceso.actualizarVentana(this.valoresNumericos, this.nombre, pasos, cronometro(tiempoTotal));
                
                
            } catch (IOException ex) {
                Logger.getLogger(Quicksort.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
            
    }
    
    /**
     * Este metodo contiene el algoritmo de ordenamiento quicksort, en forma descendete.
     * @param datos
     * @param datosNombres
     * @param izq
     * @param der 
     */
    
    public void ordenamientoQuicksortD(double [] datos, String[] datosNombres, int izq ,int der){
            try {
                double pivote = datos[izq];
                int i = izq;
                int j = der;
                double aux;
                long tiempoFinal = 0;
                String temporal;
                long tiempoInicial = System.currentTimeMillis();
                while(i < j){
                    while(datos[i] >= pivote && i < j) i++;
                    while(datos[j] < pivote) j--;
                    pasos ++;
                    if (i < j) {
                        aux = datos[i];
                        datos[i] = datos[j];
                        datos[j] = aux;

                        temporal = datosNombres[i];
                        datosNombres[i] = datosNombres[j];
                        datosNombres[j] = temporal;

                    }
                        try {
                            Quicksort.sleep(this.espera);
                        } catch (InterruptedException ex) {
                            //Colocar mensaje de error al usuario
                            Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        tiempoFinal = System.currentTimeMillis();
                        tiempoTotal = tiempoFinal - tiempoInicial;
                        proceso.actualizarVentana(this.valoresNumericos, this.nombre, pasos, cronometro(tiempoTotal));
                   
                }
                
                datos[izq] = datos[j];
                datos[j] = pivote;
                if (izq < j - 1) {
                    ordenamientoQuicksortD(datos, datosNombres, izq, j - 1);
                }
                
                if (j + 1 < der) {
                    ordenamientoQuicksortD(datos, datosNombres, j + 1, der);
                }
                
                     try {
                            Quicksort.sleep(this.espera);
                        } catch (InterruptedException ex) {
                            //Colocar mensaje de error al usuario
                            Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
                        }
                
                    tiempoTotal = tiempoFinal - tiempoInicial;
                    proceso.actualizarVentana(this.valoresNumericos, this.nombre, pasos, cronometro(tiempoTotal));
                
                
            } catch (IOException ex) {
                Logger.getLogger(Quicksort.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
    }
    
    public void sleep(){
        
    }
    /**
     * Este metodo le da un formato, de minutos, segundos y milisegundos al cronometro
     * que se vusualizara el vaenta de proceso de ordenamiento.
     * 
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

    