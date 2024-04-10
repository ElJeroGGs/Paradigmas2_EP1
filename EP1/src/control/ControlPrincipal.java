package control;

import vista.InterfazPrincipal;
import vista.PanelHipercubo;

import java.awt.Color;

import modelo.Conexion;
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
    private Conexion hilo;

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

    public void nuevaRuta(Conexion hilo){

        this.hilo = hilo;
        hilo.setNodos1(nodos1);
        hilo.setNodos2(nodos2);

        hilo.setControl(this);
        hipercubo1.addHilo(hilo);
        hipercubo2.addHilo(hilo);
        

        
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

        this.hipercubo1.setControl(controlpanel1);
        this.hipercubo2.setControl(controlpanel2);

        this.Interfaz.setVisible(true);

    }

    public void RutaMismoCubo(int indice1, int indice2, int cubo, int id) {
        switch (cubo) {
            case 1:
                hipercubo1.pintaruta(indice1, indice2, 1);
                break;
            case 2:
                hipercubo2.pintaruta(indice1, indice2, 2);
                break;
        }
    }

    public void rutaSalto(int indice1, int indice2, String lado, Conexion Hilo) {
        switch (lado) {
            case "derecha":
                Interfaz.salto(indice1, indice2, "derecha", Hilo);
                break;
            case "izquierda":
                Interfaz.salto(indice1, indice2, "izquierda", Hilo);
                break;
        }
    }

    public void ruta(Nodo Origen, Nodo Destino, int ruta) {
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
                    hipercubo2.pintaruta(indice1, indice2, ruta);

                } else {
                    hipercubo2.pintaruta(indice1, indice2, ruta);
                    this.ruta(nodos2[indice2], Destino, ruta);
                }
            }else{
            // Se pinta un salto
            // Creo que con un indice es suficiente (Origen)
            //Interfaz.salto(indice1,indice2,"derecha", ruta);
            this.ruta(nodos2[indice2], Destino, ruta);
            }
            

        } else {

            op.setNodos(nodos1);
            int indice2 = controlpanel1.direccion(Origen, Destino);
            // Condicion en caso de que haya un "salto"
            if (op.PerteneceMismoCubo(Origen, Destino)) {
                if (nodos1[indice2].equals(Destino)) {
                    hipercubo1.pintaruta(indice1, indice2, ruta);
                } else {
                    hipercubo1.pintaruta(indice1, indice2, ruta);
                    this.ruta(nodos1[indice2], Destino, ruta);

                }
            }else{
            // Se pinta un salto
            // Creo que con un indice es suficiente (Origen)
            //Interfaz.salto(indice1,indice2,"izquierda", ruta);
            this.ruta(nodos1[indice2], Destino, ruta);
            }
            

        }

    }

    
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
