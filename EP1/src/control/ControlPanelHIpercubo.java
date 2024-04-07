package control;

import modelo.Nodo;
import modelo.Matriz;
import vista.InterfazPrincipal;
import vista.PanelHipercubo;

public class ControlPanelHIpercubo {
    private InterfazPrincipal Interfaz;
    private PanelHipercubo panel;
    private Nodo[] nodos;

    public void setInterfaz(InterfazPrincipal interfaz) {
        Interfaz = interfaz;
    }

    public void setPanel(PanelHipercubo panel) {
        this.panel = panel;
    }

    public void setNodos(Nodo[] nodos) {
        this.nodos = nodos;
    }
    public int[] CalculaUniones(int posicion){
        Matriz operaciones = new Matriz();
        int[] resultado = new int[3];
    
            Nodo nodo1= nodos[posicion];
            int contador = 0;
            for (int i = 0; i<nodos.length ;i++){
                int res = operaciones.Diferencias(nodo1, nodos[i]);
                if (res==1){ 
                //Guarda el indice del nodo al que esta unido
                resultado[contador] = i;
                contador++;
                }
                }
        return resultado;
        
    }

}
