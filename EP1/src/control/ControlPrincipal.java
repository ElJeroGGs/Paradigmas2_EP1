package control;

import vista.InterfazPrincipal;
import vista.PanelHipercubo;

import java.awt.Color;

import modelo.Matriz;
import modelo.Nodo;

public class ControlPrincipal {

    private InterfazPrincipal Interfaz;
    private PanelHipercubo hipercubo1;
    private PanelHipercubo hipercubo2;
    private ControlPanelHIpercubo controlpanel1;
    private ControlPanelHIpercubo controlpanel2;
    Nodo[] nodos1;
    Nodo[] nodos2;

    public void setInterfaz(InterfazPrincipal interfaz) {
        Interfaz = interfaz;
    }

    public void setHP1(PanelHipercubo panel) {
        this.hipercubo1 = panel;
    }

    public void setHP2(PanelHipercubo panel) {

        this.hipercubo2 = panel;
    }

    public void setControlPanel1(ControlPanelHIpercubo control) {
        this.controlpanel1 = control;
    }

    public void setControlPanel2(ControlPanelHIpercubo control) {
        this.controlpanel2 = control;
    }

    public void setNodos1(Nodo[] nodos1) {
        this.nodos1 = nodos1;
    }

    public void setNodos2(Nodo[] nodos2) {
        this.nodos2 = nodos2;
    }

    public void Comienzo() {

        ControlPanelHIpercubo control1 = new ControlPanelHIpercubo();
        this.setControlPanel1(control1);
        ControlPanelHIpercubo control2 = new ControlPanelHIpercubo();
        this.setControlPanel2(control2);

        controlpanel1.setNodos(this.nodos1);
       
        controlpanel2.setNodos(this.nodos2);
        // Me parece que para dos nodos, hara falta un segundo controlpanel
        this.hipercubo1.setControl(controlpanel1);
        this.hipercubo2.setControl(controlpanel2);

        this.Interfaz.setVisible(true);

    }

    public void ruta(Nodo Origen, Nodo Destino) {
        Matriz op = new Matriz();
        if(Origen.getValorDecimal() > 7 || Destino.getValorDecimal() > 7){
            op.setNodos(nodos2);
            int indice1 = op.getIndice(Origen);
    
            int indice2 = controlpanel2.direccion(Origen, Destino);
    
            if (nodos2[indice2].equals(Destino)) {
                hipercubo2.pintaruta(indice1, indice2);
            } else {
                hipercubo2.pintaruta(indice1, indice2);
                this.ruta(nodos2[indice2], Destino);
            }
            return;
        }else{
            op.setNodos(nodos1);
        int indice1 = op.getIndice(Origen);

        int indice2 = controlpanel1.direccion(Origen, Destino);

        if (nodos1[indice2].equals(Destino)) {
            hipercubo1.pintaruta(indice1, indice2);
        } else {
            hipercubo1.pintaruta(indice1, indice2);
            this.ruta(nodos1[indice2], Destino);
        }
        }
        
    }

    public void setColorRuta(String color,int recorrido){
        Color Col = null;
        switch(color){
            case("rojo"):
            Col = Color.RED;
            break;
            case("azul"):
            Col = Color.BLUE;
            break;
        
        }
        switch (recorrido){
            case 1:
            hipercubo1.cambiacolor(Col);
            break;
            case 2:
            hipercubo2.cambiacolor(Col);
            break;
        }
    
        
    }


}
