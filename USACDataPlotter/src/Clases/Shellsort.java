package Clases;

import GUI.Interfaz;
import GUI.ProcesoOrdenamiento;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Esta clase contiene el algoritmo de ordenamiento shellsort en forma ascendete y descente,
 * tambien un cronometro para visualizar el tiempo que tarda el algoritmo en ordenar los 
 * datos.
 * @author Rony
 */
public class Shellsort extends Thread{
    
    ProcesoOrdenamiento proceso;
    String[] nombresEjes;
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

    public Shellsort(ProcesoOrdenamiento proceso, double[] valoresNumericos, 
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
            shellsortAscendente();
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
           
        }else {
            shellsortDescendente();
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
     * Metodo shellsort en forma ascendente
     */
    public void shellsortAscendente(){
        int intervalo, l, j ,k;
            int n = valoresNumericos.length;
            
            intervalo = n/2;
            long tiempoInicial = System.currentTimeMillis();
            while (intervalo > 0){
            
                for (l = intervalo; l < n; l++) {
                    try {
                        j = l - intervalo;
                        pasos++;
                        while (j >= 0){
                            k = j + intervalo;
                            if (this.valoresNumericos[j] <= this.valoresNumericos[k]) {
                                j = -1;
                            } else{
                                intercambiar(this.valoresNumericos, j, j + 1);
                                j -= intervalo;
                            }
                        }
                        try {
                            Shellsort.sleep(this.espera);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Shellsort.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        long tiempoFinal = System.currentTimeMillis();
                        tiempoTotal = tiempoFinal - tiempoInicial;
                        proceso.actualizarVentana(this.valoresNumericos, this.nombre, pasos,
                                cronometro(tiempoTotal));
                    } catch (IOException ex) {
                        Logger.getLogger(Shellsort.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                intervalo = intervalo / 2;
            }
            
           
    }
     /**
     * Metodo shellsort en forma descendente
     */
    public void shellsortDescendente(){
        int intervalo, l, j ,k;
        int n = valoresNumericos.length;
            
        intervalo = n/2;
        long tiempoInicial = System.currentTimeMillis();
        while (intervalo > 0){      
            for (l = intervalo; l < n; l++) {
                try {
                    j = l - intervalo;
                    pasos++;
                    while (j >= 0){
                        k = j + intervalo;
                        if (this.valoresNumericos[j] >= this.valoresNumericos[k]) {
                            j = -1;
                        } else{
                            intercambiar(this.valoresNumericos, j, j+1);
                            j -= intervalo;
                        }
                    }
                    try {
                            Shellsort.sleep(this.espera);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Shellsort.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    long tiempoFinal = System.currentTimeMillis();
                    tiempoTotal = tiempoFinal - tiempoInicial;
                    proceso.actualizarVentana(this.valoresNumericos, this.nombre, pasos,
                            cronometro(tiempoTotal));
                } catch (IOException ex) {
                    Logger.getLogger(Shellsort.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            intervalo = intervalo / 2;
        }     
    }

    /**
     * Este metodo intecambia de posicon dos valores contenidos en una matriz.
     * @param valoresNumericos
     * @param i
     * @param j 
     */
    private void intercambiar(double[] valoresNumericos, int i, int j) {
       double aux = valoresNumericos[i];
       valoresNumericos[i] = valoresNumericos[j];
       valoresNumericos[j] = aux;
    }
    
    public void sleep(){
        
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
