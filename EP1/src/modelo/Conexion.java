
package modelo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import control.ControlPrincipal;

public class Conexion extends Thread implements Runnable {

    private Color color;
    private List<Integer> recorrido = new ArrayList<>();

    private ControlPrincipal control;
    private Nodo[] nodos1;
    private Nodo[] nodos2;
    private Nodo Origen;
    private Nodo Destino;
    private int id;
    private String Cubo;
    private int contador = 0;
    private boolean HaySalto = false;
    private String LadoSalto;
    private int indexSaltoInicio;
    private int indexSaltoFinal;
    private boolean fin = false;
    private int nodosOrigen;

    public Conexion(int id, String string) {
        this.id = id;
        this.setColorRuta(string);
        start();
    }

    public Conexion() {
    }

    public void setOrigen(Nodo Origen) {
        this.Origen = Origen;
    }

    public void setDestino(Nodo Destino) {
        this.Destino = Destino;
    }

    public String getCubo() {
        return this.Cubo;
    }

    public void setControl(ControlPrincipal control) {
        this.control = control;
    }

    public void setNodos1(Nodo[] nodos1) {
        this.nodos1 = nodos1;
    }

    public void setNodos2(Nodo[] nodos2) {
        this.nodos2 = nodos2;
    }

    public synchronized void borraRecorrido() {
        try {
            control.renovar();
            if (HaySalto == true) {
                int indice1 = this.indexSaltoInicio;
                int indice2 = this.indexSaltoFinal;
                String lado = this.LadoSalto;
                this.borraSalto(indice1, indice2, lado, this);
                HaySalto = false;
            } else {
                //Liberar el nodo de origen
                if (this.nodosOrigen == 1 && nodos1 != null && !recorrido.isEmpty()) {
                    nodos1[this.recorrido.get(0)].liberarNodo();
                } else if (nodos2 != null && !recorrido.isEmpty()) {
                    nodos2[this.recorrido.get(0)].liberarNodo();
                }
                //Borra el primero de recorrido
                if (!recorrido.isEmpty()) {
                    this.recorrido.removeFirst();
                }else{
                    this.fin=false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        control.renovar();
    }

    public void setColorRuta(String color) {
        switch (color) {
            case ("rojo"):
                this.color = Color.RED;
                break;
            case ("azul"):
                this.color = Color.BLUE;
                break;
            case ("verde"):
                this.color = Color.GREEN;
                break;
            case ("amarillo"):
                this.color = Color.YELLOW;
                break;
            case ("naranja"):
                this.color = Color.ORANGE;
                break;
            case ("rosa"):
                this.color = Color.PINK;
                break;
            case ("morado"):
                this.color = Color.MAGENTA;
                break;
            case ("gris"):
                this.color = Color.GRAY;
                break;
        }

    }

    // Metodo que define la ruta de la conexion
    public synchronized void setRuta(int indice1, int indice2) {

        if (this.recorrido == null) {
            recorrido = new ArrayList<>();
        }
        if (this.recorrido.isEmpty() && this.fin == false) {
            this.recorrido.add(indice1);
            this.recorrido.add(indice2);
        } else {
            if (this.recorrido.get(recorrido.size() - 1).equals(indice1) && this.fin == false) {
                this.recorrido.add(indice2);
            } else {
                this.recorrido.add(indice1);

            }
        }

    }

    public synchronized int direccion(Nodo Origen, Nodo Destino, Matriz op, int nodos) {


        int indice = -1;
        // Calcula el tag
        int[] tag = op.xor(Origen, Destino);
        // Calcula la direccion que va a tomar para llegar al destino
        int[] direccion = op.Direccion(Origen, tag);
        //Busca el indice del nodo que tiene la direccion
        for (int i = 0; i < op.nodos.length; i++) {
            if (Arrays.equals(op.nodos[i].getValor(), direccion)) {
                indice = i;
            }
        }
        

//Aqui ya habra obtenido un indice
//Comprobamos que no haya bloqueo y si lo hay vuelve a correr el metodo

    if(nodos==1){

        nodos1[indice].usarNodo();
        if(nodos1[indice].getTagSalto()){
            Nodo direcciontag = op.nodos[indice];
            if (direcciontag.getTagSalto() == true  && control.getCambioDireccion()==false && this.fin==false) {
                control.setCambioDireccion(true);
                direccion = op.DireccionSalto(Origen, tag);
                for (int k = 0; k < op.nodos.length; k++) {
                    if (Arrays.equals(op.nodos[k].getValor(), direccion)) {
                        indice = k;
                    }
                }
            
            }
        }

            
    }else{

        nodos2[indice].usarNodo();
       if(nodos2[indice].getTagSalto()){
        Nodo direcciontag = op.nodos[indice];
        if (direcciontag.getTagSalto() == true && control.getCambioDireccion()==false && this.fin==false) {
            control.setCambioDireccion(true);
            direccion = op.DireccionSalto(Origen, tag);
            for (int k = 0; k < op.nodos.length; k++) {
                if (Arrays.equals(op.nodos[k].getValor(), direccion)) {
                    indice = k;
                }
            }
        
        }
       }
    }

    if(this.fin==true){
        control.setCambioDireccion(false);
    }
        return indice;

    }

    @Override
    public synchronized void run() {

        this.ruta(this.Origen, this.Destino);

    }

    public void ruta(Nodo Origen, Nodo Destino) {

        contador++;

        if (contador > 2) {
            this.borraRecorrido();
        }

        while (this.nodos1 == null || this.nodos2 == null) {
            try {
                Thread.onSpinWait();
                System.out.println("Hilo en espera");
            } catch (Exception e) {
            }
        }

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error en el hilo");
        }

        Matriz op = new Matriz();
        // Indicamos el indice del nodo origen
        int indice1;

        if (Origen.getValorDecimal() > 7) {
            op.setNodos(nodos2);
            indice1 = op.getIndice(Origen);

            //ocupa el nodo
            nodos2[indice1].usarNodo();
            this.nodosOrigen = 2;

        } else {
            op.setNodos(nodos1);
            indice1 = op.getIndice(Origen);

            //ocupa el nodo
            nodos1[indice1].usarNodo();
            this.nodosOrigen = 1;

        }

        // indicamos el indice del nodo destino

        if (Destino.getValorDecimal() > 7) {
            this.Cubo = "derecha";
            op.setNodos(nodos2);
            //Obtiene el indice del nodo destino
            int indice2 = direccion(Origen, Destino, op,2);

            //Intenta usar el nodo
            nodos2[indice2].usarNodo();
            // Condicion en caso de que haya un "salto"

            if (op.PerteneceMismoCubo(Origen, Destino)) {
                // Pertenecen al mismo cubo 2
                int cubo = 2;
                if(this.fin==false){
                    this.setRuta(indice1, indice2);
                }
                control.RutaMismoCubo(cubo);

                if (nodos2[indice2].equals(Destino)) {
                    control.RutaMismoCubo(cubo);
                    //this.nodos2[indice2].liberarNodo();
                    
                  /*   if (this.nodosOrigen == 1) {
                        nodos1[indice1].liberarNodo();
                    } else {
                        nodos2[indice1].liberarNodo();
                    }*/
                    this.fin = true;
                    

                    if (this.recorrido.isEmpty() == false) {
                        this.ruta(nodos2[indice2], Destino);
                        
                     
                    }
                } else {
                    /*if (this.nodosOrigen == 1) {
                        nodos1[indice1].liberarNodo();
                    } else {
                        nodos2[indice1].liberarNodo();
                    }*/
                    // Aquí debemos ver si no hay otro hilo que quiera pasar por el mismo nodo
                   
                    // Si lo hay, entonces debemos esperar a que termine
                    //this.nodos2[indice2].liberarNodo();
                    this.ruta(nodos2[indice2], Destino);

                }

            } else {
                this.Cubo = "derecha";
                // Se pinta un salto
                // Creo que con un indice es suficiente (Origen)
                control.rutaSalto(indice1, indice2, "derecha", this);

               

                HaySalto = true;
                this.indexSaltoInicio = indice1;
                this.indexSaltoFinal = indice2;
                this.LadoSalto = "derecha";

               /*  if (this.nodosOrigen == 1) {
                    nodos1[indice1].liberarNodo();
                } else {
                    nodos2[indice1].liberarNodo();
                }*/

                //this.nodos2[indice2].liberarNodo();

                this.ruta(nodos2[indice2], Destino);

            }

        } else {
            this.Cubo = "izquierda";
            op.setNodos(nodos1);
            int indice2 = direccion(Origen, Destino, op,1);
            nodos1[indice2].usarNodo();

            // Condicion en caso de que haya un "salto"
            if (op.PerteneceMismoCubo(Origen, Destino)) {

                int cubo = 1;

                if(this.fin==false){
                    this.setRuta(indice1, indice2);
                }
                control.RutaMismoCubo(cubo);
               // this.nodos1[indice2].liberarNodo();

                if (nodos1[indice2].equals(Destino)) {
                    control.RutaMismoCubo(cubo);
                    //this.nodos1[indice2].liberarNodo();
                    
                   /*  if (this.nodosOrigen == 1) {
                        nodos1[indice1].liberarNodo();
                    } else {
                        nodos2[indice1].liberarNodo();
                    }
*/
                    this.fin = true;
                    if (this.recorrido.isEmpty() == false) {
                        this.ruta(nodos1[indice2], Destino);
                    }

                } else {
                    
                  
                   
                /*if (this.nodosOrigen == 1) {
                        nodos1[indice1].liberarNodo();
                    } else {
                        nodos2[indice1].liberarNodo();
                    }/* */
                    //this.nodos1[indice2].liberarNodo();
                    this.ruta(nodos1[indice2], Destino);

                }
            } else {
                // Se pinta un salto
                // Creo que con un indice es suficiente (Origen)
                //this.nodos1[indice2].liberarNodo();
                control.rutaSalto(indice1, indice2, "izquierda", this);
                this.Cubo = "izquierda";

                HaySalto = true;
                this.indexSaltoInicio = indice1;
                this.indexSaltoFinal = indice2;
                this.LadoSalto = "izquierda";
                
                /*if (this.nodosOrigen == 1) {
                    nodos1[indice1].liberarNodo();
                } else {
                    nodos2[indice1].liberarNodo();
                }*/
                
                //this.nodos1[indice2].liberarNodo();
                this.ruta(nodos1[indice2], Destino);

            }

        }
    }

    private void borraSalto(int indice1, int indice2, String string, Conexion conexion) {

        control.borraSalto(indice1, indice2, string, conexion);

    }

    public List<Integer> getRecorrido() {
        return this.recorrido;
    }

    public Color getColor() {
        return this.color;
    }

    public int getIde() {
        return this.id;
    }
    public boolean getCambioDireccion(){

        return control.getCambioDireccion();
    }

}
