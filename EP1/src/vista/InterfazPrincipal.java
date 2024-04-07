package vista;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import vista.PanelHipercubo;
public class InterfazPrincipal extends JFrame {

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

}
