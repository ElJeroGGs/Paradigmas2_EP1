package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ControlPanelHIpercubo;
import modelo.Conexion;
import modelo.Nodo;
import modelo.Salto;

public class PanelHipercubo extends JPanel {
    private Nodo[] nodoscubo;
    private ControlPanelHIpercubo control;
    private List<Salto> SaltosInicio;
    private List<Salto> SaltosAterrizaje;
    
   
    private List<Conexion> hilos;
    private String lado;
    private int id;

    public void setId(int id){
        this.id = id;
    }

   

    public void setNodos(Nodo[] nodos) {
        JLabel[] etiquetas;
        this.nodoscubo = nodos;
        etiquetas = new JLabel[nodos.length];
        this.setLayout(null);
        for (int i = 0; i < nodos.length; i++) {

            etiquetas[i] = new JLabel(Integer.toString(nodos[i].getValorDecimal()));
            Font fuente = new Font("Arial", Font.BOLD, 22);
            etiquetas[i].setFont(fuente);
            //etiquetas[i].setOpaque(true);
            int[] coordenadas = this.nodoscubo[i].getCoordenadas();
            this.add(etiquetas[i]);
            etiquetas[i].setBounds(coordenadas[0] - 30, coordenadas[1] + 15, 50, 20);

        }
    }

    public PanelHipercubo() {
        super();
    }

    public void setLado(String lado){

        this.lado = lado;
    }

    public void setControl(ControlPanelHIpercubo control) {

        this.control = control;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < this.nodoscubo.length; i++) {
            int[] coordenadas1 = this.nodoscubo[i].getCoordenadas();
            // Pinta los puntos
            g.fillOval(coordenadas1[0] - 7, coordenadas1[1] - 7, 15, 15);
            // Calcula las uniones
            int[] indices = control.CalculaUniones(i);
            // Pinta las lineas con las uniones
            for (int j = 0; j < indices.length; j++) {
                if (indices[j] != 0) {
                    int[] coordenadas2 = this.nodoscubo[indices[j]].getCoordenadas();
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(Color.GRAY);
                    g2.setStroke(new BasicStroke(7)); // Set the thickness of the line
                    g2.drawLine(coordenadas1[0], coordenadas1[1], coordenadas2[0], coordenadas2[1]);
                }
            }

        }

        
        if(this.hilos!=null){

            for(int i = 0; i<this.hilos.size();i++){

                Conexion hilazo = this.hilos.get(i);

                if(hilazo!=null && this.lado ==hilazo.getCubo()){
                    // En caso de haber una linea que deba pintarse de otro color 
            
                        if (hilazo.getRecorrido() != null) {
                            
                            for (int k = 0; k < hilazo.getRecorrido().size() - 1; k++) {
                                
                                int indice = hilazo.getRecorrido().get(k);
                                int[] coordenadas1 = this.nodoscubo[indice].getCoordenadas();
                                indice = hilazo.getRecorrido().get(k + 1);
                                int[] coordenadas2 = this.nodoscubo[indice].getCoordenadas();
                                Graphics2D g2 = (Graphics2D) g;
                                g2.setStroke(new BasicStroke(3)); // Set the thickness of the line
                                g2.setColor(hilazo.getColor());
                                g2.drawLine(coordenadas1[0], coordenadas1[1], coordenadas2[0], coordenadas2[1]);
                
                            }
                        }
                      
                        // Lo mismo aplicaria con los puntos
                        if (hilazo.getRecorrido() != null) {
                            for (int k = 0; k < hilazo.getRecorrido().size() - 1; k++) {
                                int indice = hilazo.getRecorrido().get(k);
                                int[] coordenadas1 = this.nodoscubo[indice].getCoordenadas();
                                indice = hilazo.getRecorrido().get(k + 1);
                                int[] coordenadas2 = this.nodoscubo[indice].getCoordenadas();
                                Graphics2D g2 = (Graphics2D) g;
                                g2.setStroke(new BasicStroke(3)); // Set the thickness of the line
                                g2.setColor(hilazo.getColor());
                                // Pinta los puntos
                                g2.fillOval(coordenadas1[0] - 7, coordenadas1[1] - 7, 15, 15);
                                g2.fillOval(coordenadas2[0] - 7, coordenadas2[1] - 7, 15, 15);
                            }
                        }
                        
                
                        
                
                }
            }
        }
        
   
   
     // En caso de haber saltos de inicio
     if(this.SaltosInicio!=null){
        for (int i = 0; i < this.SaltosInicio.size(); i++) {
            int indice = SaltosInicio.get(i).getIndice();
            int[] coordenadas1 = this.nodoscubo[indice].getCoordenadas();

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(4)); // Set the thickness of the line
            g2.setColor(SaltosInicio.get(i).getColor());
            //Si el lado de inicio es izquierdo
            if(this.lado.equals("izquierda")){
                QuadCurve2D q = new QuadCurve2D.Float(coordenadas1[0],coordenadas1[1],coordenadas1[0]+200,coordenadas1[1]-200,coordenadas1[0]+695,coordenadas1[1]);
            g2.draw(q);
            }else{
        //Si el lado de inicio es derecho
        QuadCurve2D q = new QuadCurve2D.Float(coordenadas1[0],coordenadas1[1],coordenadas1[0]-200,coordenadas1[1]-200,coordenadas1[0]-695,coordenadas1[1]);
        g2.draw(q);
            
        }
       
    }
    
            

        }

        // En caso de haber saltos de aterrizaje
        if(this.SaltosAterrizaje!=null){
            for (int i = 0; i < this.SaltosAterrizaje.size(); i++) {
            int indice = SaltosAterrizaje.get(i).getIndice();
            int[] coordenadas1 = this.nodoscubo[indice].getCoordenadas();
        //podemos usar un switch case para definir los valores de las coordenadas y que no se vea tan feo
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4)); // Set the thickness of the line
        g2.setColor(SaltosAterrizaje.get(i).getColor());

        if(this.lado.equals("izquierda")){
            QuadCurve2D q = new QuadCurve2D.Float(coordenadas1[0],coordenadas1[1],coordenadas1[0]+200,coordenadas1[1]-200,coordenadas1[0]+695,coordenadas1[1]);
        g2.draw(q);
        
        }else{
            QuadCurve2D q = new QuadCurve2D.Float(coordenadas1[0],coordenadas1[1],coordenadas1[0]-200,coordenadas1[1]-200,coordenadas1[0]-695,coordenadas1[1]);
            g2.draw(q);
        
    }
        }

    }
        
    

    }

    public void pintaruta() {
        
        this.revalidate();
        this.repaint();
    }

        
    public void dibujaSaltoInicio(int indice, Color col) {
        // Lo mismo que con las lineas de colores, requiere un recorrido
      Salto sal = new Salto(indice, col);
            if (this.SaltosInicio == null) {
                SaltosInicio = new ArrayList<>();
            }
            this.SaltosInicio.add(sal);
            this.revalidate();
            this.repaint();

        }
        
        
    



    public void dibujaSaltoAterrizaje(int indice, Color col){
    
        Salto sal = new Salto(indice, col);

        if (this.SaltosAterrizaje == null) {
            SaltosAterrizaje = new ArrayList<>();
        }
        this.SaltosAterrizaje.add(sal);
    
    this.revalidate();
    this.repaint();
    }

    

    public void addHilo(Conexion hilo){

        if (this.hilos ==null){
             hilos = new ArrayList<>(); 
        }
        hilos.add(hilo);
    }

    



    public void BorraSaltoInicio(int indice1, Color col) {

        Salto saltito = new Salto(indice1, col);

        this.SaltosInicio.removeIf(salto -> salto.getIndice() == indice1 );
    }



    public void BorraSaltoAterrizaje(int indice2, Color col) {
        Salto saltito = new Salto(indice2, col);

        this.SaltosAterrizaje.removeIf(salto -> salto.getIndice() == indice2 );
    }

}


