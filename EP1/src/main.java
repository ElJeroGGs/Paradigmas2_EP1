
import vista.InterfazPrincipal;
import vista.PanelHipercubo;
import control.ControlPanelHIpercubo;
import control.ControlPrincipal;
import modelo.Conexion;
import modelo.Nodo;

public class main {

    public static void main(String[] args) {
        // Instanciamos los nodos
        Nodo nodo0 = new Nodo(new int[] { 120, 400 }, new int[] { 0, 0, 0, 0 });
        Nodo nodo1 = new Nodo(new int[] { 220, 350 }, new int[] { 0, 0, 0, 1 });
        Nodo nodo2 = new Nodo(new int[] { 380, 400 }, new int[] { 0, 0, 1, 0 });
        Nodo nodo3 = new Nodo(new int[] { 480, 350 }, new int[] { 0, 0, 1, 1 });
        Nodo nodo4 = new Nodo(new int[] { 120, 150 }, new int[] { 0, 1, 0, 0 });
        Nodo nodo5 = new Nodo(new int[] { 220, 100 }, new int[] { 0, 1, 0, 1 });
        Nodo nodo6 = new Nodo(new int[] { 380, 150 }, new int[] { 0, 1, 1, 0 });
        Nodo nodo7 = new Nodo(new int[] { 480, 100 }, new int[] { 0, 1, 1, 1 });

        // Creamos el arreglo de nodos
        Nodo[] Nodos1 = { nodo0, nodo1, nodo2, nodo3, nodo4, nodo5, nodo6, nodo7 };
        // Instanciamos los nodos
        Nodo nodo8 = new Nodo(new int[] { 120, 400 }, new int[] { 1, 0, 0, 0 });
        Nodo nodo9 = new Nodo(new int[] { 220, 350 }, new int[] { 1, 0, 0, 1 });
        Nodo nodo10 = new Nodo(new int[] { 380, 400 }, new int[] { 1, 0, 1, 0 });
        Nodo nodo11 = new Nodo(new int[] { 480, 350 }, new int[] { 1, 0, 1, 1 });
        Nodo nodo12 = new Nodo(new int[] { 120, 150 }, new int[] { 1, 1, 0, 0 });
        Nodo nodo13 = new Nodo(new int[] { 220, 100 }, new int[] { 1, 1, 0, 1 });
        Nodo nodo14 = new Nodo(new int[] { 380, 150 }, new int[] { 1, 1, 1, 0 });
        Nodo nodo15 = new Nodo(new int[] { 480, 100 }, new int[] { 1, 1, 1, 1 });
        // Creamos el primer panel
        PanelHipercubo HP1 = new PanelHipercubo();
        HP1.setLado("izquierda");
        HP1.setId(1);
        HP1.setNodos(Nodos1);

        // Creamos el segundo panel
        Nodo[] Nodos2 = { nodo8, nodo9, nodo10, nodo11, nodo12, nodo13, nodo14, nodo15 };
        PanelHipercubo HP2 = new PanelHipercubo();
        HP2.setLado("derecha");
        HP2.setId(2);
        HP2.setNodos(Nodos2);

        InterfazPrincipal vista = new InterfazPrincipal(HP1, HP2);

        ControlPrincipal Control = new ControlPrincipal();

        Control.setHP1(HP1);
        Control.setHP2(HP2);
        Control.setNodos1(Nodos1);
        Control.setNodos2(Nodos2);
        Control.setInterfaz(vista);
        Control.Comienzo();

        Conexion ruta1 = new Conexion(1, "azul");
        Control.nuevaRuta(ruta1);
        ruta1.setOrigen(nodo4);
        ruta1.setDestino(nodo15);

        //Conexion ruta2 = new Conexion(2,"rojo");
        //Control.nuevaRuta(ruta2);
        //ruta2.setOrigen(nodo9);
        //ruta2.setDestino(nodo3);

        // ruta2.ruta(nodo5,nodo10);
        // Conexion ruta3 = new Conexion(3);
        // ruta3.setColorRuta("verde");
        // Control.nuevaRuta(ruta3);
        // ruta3.ruta(nodo6,nodo11);
        // Conexion ruta4 = new Conexion(4);
        // ruta4.setColorRuta("amarillo");
        // Control.nuevaRuta(ruta4);
        // ruta4.ruta(nodo7,nodo12);
        // Conexion ruta5 = new Conexion(5);
        // ruta5.setColorRuta("naranja");
        // Control.nuevaRuta(ruta5);
        // ruta5.ruta(nodo10,nodo1);

    }
}
