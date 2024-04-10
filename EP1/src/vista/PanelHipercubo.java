package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;
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
    private List<Integer> SaltosInicio1;
    private List<Integer> SaltosAterrizaje1;
    private List<Integer> SaltosInicio2;
    private List<Integer> SaltosAterrizaje2;
    private Color ColorRuta1;
    private Color ColorRuta2;
    private String lado;

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
                    g2.setStroke(new BasicStroke(3)); // Set the thickness of the line
                    g2.drawLine(coordenadas1[0], coordenadas1[1], coordenadas2[0], coordenadas2[1]);
                }
            }

        }

        // En caso de haber una linea que deba pintarse de otro color 
        if (this.recorrido1 != null) {
            for (int k = 0; k < this.recorrido1.size() - 1; k++) {
                int indice = recorrido1.get(k);
                int[] coordenadas1 = this.nodoscubo[indice].getCoordenadas();
                indice = recorrido1.get(k + 1);
                int[] coordenadas2 = this.nodoscubo[indice].getCoordenadas();
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3)); // Set the thickness of the line
                g2.setColor(this.ColorRuta1);
                g2.drawLine(coordenadas1[0], coordenadas1[1], coordenadas2[0], coordenadas2[1]);

            }
        }
        if (this.recorrido2 != null) {
            for (int k = 0; k < this.recorrido2.size() - 1; k++) {
                int indice = recorrido2.get(k);
                int[] coordenadas1 = this.nodoscubo[indice].getCoordenadas();
                indice = recorrido2.get(k + 1);
                int[] coordenadas2 = this.nodoscubo[indice].getCoordenadas();
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3)); // Set the thickness of the line
                g2.setColor(this.ColorRuta2);
                g2.drawLine(coordenadas1[0], coordenadas1[1], coordenadas2[0], coordenadas2[1]);

            }
        }
        // Lo mismo aplicaria con los puntos
        if (this.recorrido1 != null) {
            for (int k = 0; k < this.recorrido1.size() - 1; k++) {
                int indice = recorrido1.get(k);
                int[] coordenadas1 = this.nodoscubo[indice].getCoordenadas();
                indice = recorrido1.get(k + 1);
                int[] coordenadas2 = this.nodoscubo[indice].getCoordenadas();
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3)); // Set the thickness of the line
                g2.setColor(ColorRuta1);
                // Pinta los puntos
                g2.fillOval(coordenadas1[0] - 7, coordenadas1[1] - 7, 15, 15);
                g2.fillOval(coordenadas2[0] - 7, coordenadas2[1] - 7, 15, 15);
            }
        }
        if (this.recorrido2 != null) {
            for (int k = 0; k < this.recorrido2.size() - 1; k++) {
                int indice = recorrido2.get(k);
                int[] coordenadas1 = this.nodoscubo[indice].getCoordenadas();
                indice = recorrido2.get(k + 1);
                int[] coordenadas2 = this.nodoscubo[indice].getCoordenadas();
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3)); // Set the thickness of the line
                g2.setColor(ColorRuta2);
                // Pinta los puntos
                g2.fillOval(coordenadas1[0] - 7, coordenadas1[1] - 7, 15, 15);
                g2.fillOval(coordenadas2[0] - 7, coordenadas2[1] - 7, 15, 15);
            }
        }

        // En caso de haber saltos de inicio
        if(this.SaltosInicio1!=null){
            for (int i = 0; i < this.SaltosInicio1.size(); i++) {
                int indice = SaltosInicio1.get(i);
                int[] coordenadas1 = this.nodoscubo[indice].getCoordenadas();
    
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(4)); // Set the thickness of the line
                g2.setColor(ColorRuta1);
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
if(this.SaltosInicio2!=null){
    for (int i = 0; i < this.SaltosInicio2.size(); i++) {
        int indice = SaltosInicio2.get(i);
        int[] coordenadas1 = this.nodoscubo[indice].getCoordenadas();

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4)); // Set the thickness of the line
        g2.setColor(ColorRuta2);
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
        if(this.SaltosAterrizaje1!=null){
            for (int i = 0; i < this.SaltosAterrizaje1.size(); i++) {
                int indice = SaltosAterrizaje1.get(i);
                int[] coordenadas1 = this.nodoscubo[indice].getCoordenadas();
        //podemos usar un switch case para definir los valores de las coordenadas y que no se vea tan feo
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4)); // Set the thickness of the line
        g2.setColor(ColorRuta1);

        if(this.lado.equals("izquierda")){
            QuadCurve2D q = new QuadCurve2D.Float(coordenadas1[0],coordenadas1[1],coordenadas1[0]+200,coordenadas1[1]-200,coordenadas1[0]+695,coordenadas1[1]);
        g2.draw(q);
        
        }else{
            QuadCurve2D q = new QuadCurve2D.Float(coordenadas1[0],coordenadas1[1],coordenadas1[0]-200,coordenadas1[1]-200,coordenadas1[0]-695,coordenadas1[1]);
            g2.draw(q);
        
    }
        }

    }
    if(this.SaltosAterrizaje2!=null){
        for (int i = 0; i < this.SaltosAterrizaje2.size(); i++) {
            int indice = SaltosAterrizaje2.get(i);
            int[] coordenadas1 = this.nodoscubo[indice].getCoordenadas();
    //podemos usar un switch case para definir los valores de las coordenadas y que no se vea tan feo
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(4)); // Set the thickness of the line
    g2.setColor(ColorRuta2);

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

    public void pintaruta(int indice1, int indice2, int recorrido) {
switch (recorrido) {
    case 1:
    if (this.recorrido1 == null) {
        recorrido1 = new ArrayList<>();
    }
    if (this.recorrido1.isEmpty()) {
        this.recorrido1.add(indice1);
        this.recorrido1.add(indice2);
    } else {
        if (this.recorrido1.get(recorrido1.size() - 1).equals(indice1)) {
            this.recorrido1.add(indice2);
        } else {
            this.recorrido1.add(indice1);
            this.recorrido1.add(indice2);

        }
    }
        break;

    case 2:
    if (this.recorrido2 == null) {
        recorrido2 = new ArrayList<>();
    }
    if (this.recorrido2.isEmpty()) {
        this.recorrido2.add(indice1);
        this.recorrido2.add(indice2);
    } else {
        if (this.recorrido2.get(recorrido2.size() - 1).equals(indice1)) {
            this.recorrido2.add(indice2);
        } else {
            this.recorrido2.add(indice1);
            this.recorrido2.add(indice2);

        }
    }
        break;
}
        

        this.revalidate();
        this.repaint();
    }

    public void cambiacolor(Color color,int recorrido) {
        switch(recorrido){
            case 1:
                this.ColorRuta1 = color;
                break;
            case 2:
                this.ColorRuta2 = color;
                break;
        }
        

    }

    public void dibujaSaltoInicio(int indice, int recorrido) {
        // Lo mismo que con las lineas de colores, requiere un recorrido
        switch (recorrido) {
            case 1:
            if (this.SaltosInicio1 == null) {
                SaltosInicio1 = new ArrayList<>();
            }
            this.SaltosInicio1.add(indice);
                break;
        
            case 2:
            if (this.SaltosInicio2 == null) {
                SaltosInicio2 = new ArrayList<>();
            }
            this.SaltosInicio2.add(indice);
                break;
        }
        

        this.revalidate();
        this.repaint();
    }

    public void dibujaSaltoAterrizaje(int indice, int recorrido){
    
    // Lo mismo que con las lineas de colores, requiere un recorrido

    switch (recorrido) {
        case 1:
        if (this.SaltosAterrizaje1 == null) {
            SaltosAterrizaje1 = new ArrayList<>();
        }
        this.SaltosAterrizaje1.add(indice);
        break;
    
    case 2:
    if (this.SaltosAterrizaje2 == null) {
        SaltosAterrizaje2 = new ArrayList<>();
    }
    this.SaltosAterrizaje2.add(indice);
        break;
    }
    this.revalidate();
        this.repaint();
    }
}
