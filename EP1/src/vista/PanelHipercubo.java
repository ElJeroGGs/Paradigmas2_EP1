package vista;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ControlPanelHIpercubo;
import modelo.Nodo;

public class PanelHipercubo extends JPanel {
    private Nodo[] nodoscubo;
    private ControlPanelHIpercubo control;

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
    }
}
