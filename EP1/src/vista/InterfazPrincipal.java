package vista;

import javax.swing.JFrame;

import modelo.Conexion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import vista.PanelHipercubo;
public class InterfazPrincipal extends JFrame {

    private PanelHipercubo panel1;
    private PanelHipercubo panel2;



    public InterfazPrincipal(PanelHipercubo HC1, PanelHipercubo HC2) {
//POnemos un GridLayout
    this.setLayout(new GridLayout(1,2));
        //Pintamos el primer hipercubo

        this.add(HC1);
        this.add(HC2);

        // Centra la ventana y le pone el marco de acuerdo a los componentes
       
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
       

        //Termina el programa cuando se cierra la ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void setPanel1(PanelHipercubo panel1) {
        this.panel1 = panel1;
    }

    public void salto(int indice1,int indice2, String lado, Conexion hilo){
    Color col = hilo.getColor();
    //Aqui debo pintar un arco desde el nodo origen hasta su contraparte)
    switch(lado){
        case "derecha":
            System.out.println("Salto a la derecha");
        panel1.dibujaSaltoInicio(indice1, col);
        panel2.dibujaSaltoAterrizaje(indice2, col);
        
        
            break;
        case "izquierda":
            System.out.println("Salto a la izquierda");
            panel2.dibujaSaltoInicio(indice1, col);
            panel1.dibujaSaltoAterrizaje(indice2, col);
            
            break;
    }
    
    }


    public void setPanel2(PanelHipercubo panel2) {
        this.panel2 = panel2;
    }

    public void BorraSalto(int indice1, int indice2, String string, Conexion hilo) {

        Color col = hilo.getColor();

    switch(string){
        case "derecha":
            System.out.println("Salto a la derecha");
        panel1.BorraSaltoInicio(indice1, col);
        panel2.BorraSaltoAterrizaje(indice2, col);
        
        
            break;
        case "izquierda":
            System.out.println("Salto a la izquierda");
            panel2.BorraSaltoInicio(indice1, col);
            panel1.BorraSaltoAterrizaje(indice2, col);
            
            break;
    }
    }
}
