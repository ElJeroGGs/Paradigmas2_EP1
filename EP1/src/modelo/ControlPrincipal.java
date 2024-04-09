package modelo;

import vista.InterfazPrincipal;
import vista.PanelHipercubo;

import java.awt.Color;

import control.ControlPanelHIpercubo;

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
        this.Interfaz.setPanel1(hipercubo1);
        ControlPanelHIpercubo control2 = new ControlPanelHIpercubo();
        this.setControlPanel2(control2);
        this.Interfaz.setPanel2(hipercubo2);
        controlpanel1.setNodos(this.nodos1);

        controlpanel2.setNodos(this.nodos2);
        // Me parece que para dos nodos, hara falta un segundo controlpanel
        this.hipercubo1.setControl(controlpanel1);
        this.hipercubo2.setControl(controlpanel2);

        this.Interfaz.setVisible(true);

    }

    public void ruta(Nodo Origen, Nodo Destino) {
        // Funciona si ambos nodos pertenecen al mismo cubo
        Matriz op = new Matriz();
        // Indicamos el indice del nodo origen
        int indice1;
        if (Origen.getValorDecimal() > 7) {
            op.setNodos(nodos2);

        } else {
            op.setNodos(nodos1);
        }
        indice1 = op.getIndice(Origen);

        // indicamos el indice del nodo destino

        if (Destino.getValorDecimal() > 7) {

            op.setNodos(nodos2);
            int indice2 = controlpanel2.direccion(Origen, Destino);
            // Condicion en caso de que haya un "salto"
            if (op.PerteneceMismoCubo(Origen, Destino)) {
                // Pertenecen al mismo cubo
                if (nodos2[indice2].equals(Destino)) {
                    hipercubo2.pintaruta(indice1, indice2);

                } else {
                    hipercubo2.pintaruta(indice1, indice2);
                    this.ruta(nodos2[indice2], Destino);
                }
            }else{
            // Se pinta un salto
            // Creo que con un indice es suficiente (Origen)
            Interfaz.salto(indice1,indice2,"derecha");
            this.ruta(nodos2[indice2], Destino);
            }
            

        } else {

            op.setNodos(nodos1);
            int indice2 = controlpanel1.direccion(Origen, Destino);
            // Condicion en caso de que haya un "salto"
            if (op.PerteneceMismoCubo(Origen, Destino)) {
                if (nodos1[indice2].equals(Destino)) {
                    hipercubo1.pintaruta(indice1, indice2);
                } else {
                    hipercubo1.pintaruta(indice1, indice2);
                    this.ruta(nodos1[indice2], Destino);

                }
            }else{
            // Se pinta un salto
            // Creo que con un indice es suficiente (Origen)
            Interfaz.salto(indice1,indice2,"izquierda");
            this.ruta(nodos1[indice2], Destino);
            }
            

        }

    }

    public void setColorRuta(String color, int recorrido) {
        Color Col = null;
        switch (color) {
            case ("rojo"):
                Col = Color.RED;
                break;
            case ("azul"):
                Col = Color.BLUE;
                break;

        }
        switch (recorrido) {
            case 1:
                hipercubo1.cambiacolor(Col);
                hipercubo2.cambiacolor(Col);

                break;
            case 2:
                hipercubo2.cambiacolor(Col);
                hipercubo1.cambiacolor(Col);
                break;
        }

    }
//Este metodo creo que sobra
    public void setLado(String lado, int hipercubo){

        switch (hipercubo) {
            case 1:
                this.hipercubo1.setLado("lado");
                break;
            case 2:
                this.hipercubo2.setLado("lado");
                break;
        }
    }

}
