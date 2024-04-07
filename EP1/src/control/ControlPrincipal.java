package control;
import vista.InterfazPrincipal;
import vista.PanelHipercubo;
import modelo.Nodo;
public class ControlPrincipal {

    private InterfazPrincipal Interfaz;
    private PanelHipercubo hipercubo1;
    private PanelHipercubo hipercubo2;
    private ControlPanelHIpercubo  controlpanel;
    Nodo[] nodos1;

    public void setInterfaz(InterfazPrincipal interfaz) {
        Interfaz = interfaz;
    }
    public void setHP1(PanelHipercubo panel){
        this.hipercubo1 = panel;
    }

    public void setHP2 (PanelHipercubo panel){

        this.hipercubo2 = panel;
    }
    public void setControlPanel(ControlPanelHIpercubo control){
        this.controlpanel = control;
    }
    
    public void setNodos1(Nodo[] nodos){
        this.nodos1 = nodos;
    }

    public void Comienzo (){

        ControlPanelHIpercubo controlpanel = new ControlPanelHIpercubo();
        controlpanel.setNodos(this.nodos1);
        this.hipercubo1.setControl(controlpanel);
        this.hipercubo2.setControl(controlpanel);

        this.Interfaz.setVisible(true);
        
    }


}
