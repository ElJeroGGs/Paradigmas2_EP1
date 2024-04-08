package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ControlPanelHIpercubo;
import modelo.Nodo;

public class PanelHipercubo extends JPanel {
    private Nodo[] nodoscubo;
    private ControlPanelHIpercubo control;
    private List<Integer> recorrido1;
    private List<Integer> recorrido2;
    private Color ColorRuta;

    public void setNodos(Nodo[] nodos) {
        JLabel[] etiquetas;
        this.nodoscubo = nodos;
        etiquetas = new JLabel[nodos.length];
        this.setLayout(null);
        for (int i = 0; i < nodos.length; i++) {

            etiquetas[i] = new JLabel(nodos[i].toString());
            Font fuente = new Font("Arial", Font.BOLD, 22);
            etiquetas[i].setFont(fuente);
            etiquetas[i].setOpaque(true);
            int[] coordenadas = this.nodoscubo[i].getCoordenadas();
            this.add(etiquetas[i]);
            etiquetas[i].setBounds(coordenadas[0] - 50, coordenadas[1] + 20, 50, 20);

        }
    }

    public PanelHipercubo() {
        super();

    }

    public void setControl(ControlPanelHIpercubo control){

        this.control = control;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < this.nodoscubo.length; i++) {
            int[] coordenadas1 = this.nodoscubo[i].getCoordenadas();
           //Pinta los puntos
            g.fillOval(coordenadas1[0]-7, coordenadas1[1]-7, 15, 15);
        //Calcula las uniones
            int[] indices = control.CalculaUniones(i);
        //Pinta las lineas con las uniones
            for (int j = 0; j < indices.length; j++) {
                if (indices[j] != 0) {
                    int[] coordenadas2 = this.nodoscubo[indices[j]].getCoordenadas();
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setStroke(new BasicStroke(3)); // Set the thickness of the line
                    g2.drawLine(coordenadas1[0], coordenadas1[1], coordenadas2[0], coordenadas2[1]);
                }
            }
        
            
        }
        //En caso de haber una linea que deba pintarse de otro color
        if (this.recorrido1 != null) {
            for (int k = 0; k < this.recorrido1.size()-1; k++) {
                int indice = recorrido1.get(k);
                int[] coordenadas1 = this.nodoscubo[indice].getCoordenadas();
                 indice = recorrido1.get(k+1);
                int[] coordenadas2 = this.nodoscubo[indice].getCoordenadas();
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3)); // Set the thickness of the line
                g2.setColor(this.ColorRuta);
                g2.drawLine(coordenadas1[0], coordenadas1[1], coordenadas2[0], coordenadas2[1]);
               
            }
        }
        //Lo mismo aplicaria con los puntos
        if (this.recorrido1 != null) {
            for (int k = 0; k < this.recorrido1.size()-1; k++) {
                int indice = recorrido1.get(k);
                int[] coordenadas1 = this.nodoscubo[indice].getCoordenadas();
                indice = recorrido1.get(k+1);
                int[] coordenadas2 = this.nodoscubo[indice].getCoordenadas();
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3)); // Set the thickness of the line
                g2.setColor(ColorRuta);
                //Pinta los puntos
                 g2.fillOval(coordenadas1[0]-7, coordenadas1[1]-7, 15, 15);
                 g2.fillOval(coordenadas2[0]-7, coordenadas2[1]-7, 15, 15);
            }
        }

    }

    public void pintaruta(int indice1, int indice2) {
        if (this.recorrido1 == null) {
            recorrido1 = new ArrayList<>();
        }
    if(this.recorrido1.isEmpty()){
        this.recorrido1.add(indice1);
        this.recorrido1.add(indice2);
    }else{
    if (this.recorrido1.get(recorrido1.size()-1).equals(indice1)) {
    this.recorrido1.add(indice2);
    } else {
    this.recorrido1.add(indice1);
    this.recorrido1.add(indice2);

    }}
        
        this.revalidate();
        this.repaint();
    }

    public void cambiacolor(Color color) {
        this.ColorRuta = color;

    }
}
