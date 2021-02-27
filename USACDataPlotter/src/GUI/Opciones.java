/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.BubbleSort;
import Clases.Quicksort;
import Clases.Shellsort;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Esta clase contiene la ventana de opciones, la que se le presentara la usuario
 * despues de haber introducido la ruta y el nombre de la imagen, en esta clase
 * podra elegir los diver tipos de algoritmo ordenamiento,tipo de ordenamiento y
 * velocidad del ordenamiento.
 * 
 * @author Rony
 */
public class Opciones extends JFrame implements ActionListener{
    JRadioButton ascendente;
    JRadioButton descendente;
    JRadioButton baja;
    JRadioButton media;
    JRadioButton alta;
    JRadioButton bubbleSort;
    JRadioButton quicksort;
    JRadioButton shellsort;
    JButton aceptar;
    JButton cancelar;
    String nombreGrafica;
    String[] nombreEjes;
    double[] valoresNumericos;
    String[] nombre;
    Interfaz interfaz;
    BubbleSort bubble;
    Quicksort quick;
    ProcesoOrdenamiento proceso;
    
    /**
     * Metodo constructo parametrizado
     * @param interfaz 
     * @param nombreGrafica
     * @param nombreEjes
     * @param valoresNumericos
     * @param nombre 
     */

    public Opciones(Interfaz interfaz, String nombreGrafica,String[] nombreEjes, double[] valoresNumericos, String[] nombre) {
        super("Opciones de Ordenamiento");
        setVisible(true);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //Recibimos los parametros
        this.nombreGrafica = nombreGrafica;
        this.nombreEjes = nombreEjes;
        this.valoresNumericos = valoresNumericos;
        this.nombre = nombre;
        this.interfaz = interfaz;
        
       
        
        JPanel panelOpciones = new JPanel();
        GridLayout gl = new GridLayout(6,2,0,0);
    
        panelOpciones.setLayout(gl);
        
        JLabel tipo = new JLabel("Tipo de Ordenamiento");
        panelOpciones.add(tipo);
        
        JLabel vacio = new JLabel(" ");
        panelOpciones.add(vacio);
        
        ascendente = new JRadioButton("Ascendente", true);
        panelOpciones.add(ascendente);
        
        descendente = new JRadioButton("Descendente", false);
        panelOpciones.add(descendente);
        
       
        JLabel velocidad = new JLabel("Velocidad de Ordenamiento");
        panelOpciones.add(velocidad);
        
        JLabel algoritmo = new JLabel("Algoritmo de Ordenamiento");
        panelOpciones.add(algoritmo);
        
        baja = new JRadioButton("Baja", false);
        panelOpciones.add(baja);
        
        bubbleSort = new JRadioButton("Bubble Sort", true);
        panelOpciones.add(bubbleSort);
        
        media = new JRadioButton("Media", true);
        panelOpciones.add(media);
        
        quicksort = new JRadioButton("Quicksort", false);
        panelOpciones.add(quicksort);
        
        alta = new JRadioButton("Alta", false);
        panelOpciones.add(alta);
        
        shellsort = new JRadioButton("Shellsort", false);
        panelOpciones.add(shellsort);
        
        
        
        //Crea una relacion lógica entre los objetos JRadioButton
        ButtonGroup grupoOpcionesOrdenamiento = new ButtonGroup();
        grupoOpcionesOrdenamiento.add(ascendente);
        grupoOpcionesOrdenamiento.add(descendente);
        
        ButtonGroup grupoOpcionesVelocidad = new ButtonGroup();
        grupoOpcionesVelocidad.add(baja);
        grupoOpcionesVelocidad.add(media);
        grupoOpcionesVelocidad.add(alta);
        
        ButtonGroup grupoOpcionesAlgoritmo = new ButtonGroup();
        grupoOpcionesAlgoritmo.add(bubbleSort);
        grupoOpcionesAlgoritmo.add(quicksort);
        grupoOpcionesAlgoritmo.add(shellsort);
        
        
        //Panel de Botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        
        aceptar = new JButton("Ordenar");
        aceptar.addActionListener(this);
        panelBotones.add(aceptar);
        
        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(this);
        panelBotones.add(cancelar);
        
        Container cp = getContentPane();
        cp.add(panelOpciones, BorderLayout.CENTER);
        cp.add(panelBotones, BorderLayout.SOUTH);
        
    }
    
    /**
     * Establece que botones circulares estan pulsados, para realizar uno de los
     * ordenamientos.
     * @param evento 
     */

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (aceptar == evento.getSource()) {
            interfaz.setVisible(false);
            this.dispose();
            this.setVisible(false);
            
            /*
            Verifica el algoritmo de ordenamiento seleccionado y el tipo de
            ordenamiento
            */
             
            if (bubbleSort.isSelected()) {
                
                proceso = new ProcesoOrdenamiento(nombreGrafica, nombreEjes, 
                         valoresNumericos, nombre, "Bubble Sort", velocidadTexto(),
                        ascendente.isSelected());
                
                
                bubble = new BubbleSort(proceso, valoresNumericos, nombre,
                         velocidad(), ascendente.isSelected());
                        bubble.start();
                        
            }
            
            if (quicksort.isSelected()) {
                
                proceso = new ProcesoOrdenamiento(nombreGrafica, nombreEjes, 
                         valoresNumericos, nombre, "Quicksort", velocidadTexto(),
                        ascendente.isSelected());
                
                quick = new Quicksort(proceso, valoresNumericos, nombre, 
                        velocidad(), ascendente.isSelected()); 
                        quick.start();
                       
                
            }
            
            if (shellsort.isSelected()) {
                
                proceso = new ProcesoOrdenamiento(nombreGrafica, nombreEjes, 
                         valoresNumericos, nombre, "Shellsort", velocidadTexto(),
                        ascendente.isSelected());
                
                Shellsort shell = new Shellsort(proceso, valoresNumericos, nombre, 
                        velocidad(), ascendente.isSelected());
                        shell.start();
                
            }
            
        }
        
        if (cancelar == evento.getSource()) {
            setVisible(false);
            this.dispose();
        }
    }
    
    /**
     * Verifica la velocidad seleccionada y inicializa la variable velocidada
     * para enviarla al ordenamiento seleccionado.
     * 
     * @return velocidad 
     */
    
    
    public long velocidad() {
        long velocidad;
        if (baja.isSelected()) {
            velocidad = 900;
            return velocidad;
        }
        
        if (media.isSelected()) {
            velocidad = 500;
            return velocidad;
        }
        
        if (alta.isSelected()) {
            velocidad = 100;
            return velocidad;
        }
        return 0;
        
    } 
    /**
     * Con este metodo enviamos el tipo de velocidad selecionada por el usuario
     * para enviar a la clase ProcesoOrdenamiento.
     * @return 
     */
    
    public String velocidadTexto() {
     
        if (baja.isSelected()) {
            
            return "Baja";
        }
        
        if (media.isSelected()) {
          
            return "Media";
        }
        
        if (alta.isSelected()) {
           
            return "Alta";
        }
        return null;
     
        
    }  
    
}
