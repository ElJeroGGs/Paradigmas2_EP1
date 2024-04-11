
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

    public Conexion(int id, String string) {
        this.id = id;
        this.setColorRuta(string);
        start();
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
    public void setRuta(int indice1, int indice2) {

        if (this.recorrido == null) {
            recorrido = new ArrayList<>();
        }
        if (this.recorrido.isEmpty()) {
            this.recorrido.add(indice1);
            this.recorrido.add(indice2);
        } else {
            if (this.recorrido.get(recorrido.size() - 1).equals(indice1)) {
                this.recorrido.add(indice2);
            } else {
                this.recorrido.add(indice1);
                this.recorrido.add(indice2);

            }
        }

    }

    public int direccion(Nodo Origen, Nodo Destino, Matriz op) {

        int indice = -1;
        // Calcula el tag
        int[] tag = op.xor(Origen, Destino);
        // Calcula la direccion que va a tomar para llegar al destino
        int[] direccion = op.Direccion(Origen, tag);
        for (int i = 0; i < op.nodos.length; i++) {
            if (Arrays.equals(op.nodos[i].getValor(), direccion)) {
                indice = i;
            }
        }

        return indice;

    }

    @Override
    public void run() {

        this.ruta(this.Origen, this.Destino);

    }

    public void ruta(Nodo Origen, Nodo Destino) {

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
            
            while (nodos2[indice1].isEnUso()) {
                try {
                    Thread.onSpinWait();
                    System.out.println("Hilo en espera");
                } catch (Exception e) {
                }
            }
            nodos1[indice1].setEnUso(true);
        } else {
            op.setNodos(nodos1);
            indice1 = op.getIndice(Origen);
            
            while (nodos1[indice1].isEnUso()) {
                try {
                    Thread.onSpinWait();
                    System.out.println("Hilo en espera");
                } catch (Exception e) {
                }
            }
            nodos1[indice1].setEnUso(true);
        }

        // indicamos el indice del nodo destino

        if (Destino.getValorDecimal() > 7) {
            this.Cubo = "derecha";
            op.setNodos(nodos2);
            int indice2 = direccion(Origen, Destino, op);
            
            while (nodos2[indice2].isEnUso()) {
                try {
                    Thread.onSpinWait();
                    System.out.println("Hilo en espera");
                } catch (Exception e) {
                }
            }
            nodos2[indice1].setEnUso(true);
            // Condicion en caso de que haya un "salto"

            if (op.PerteneceMismoCubo(Origen, Destino)) {
                // Pertenecen al mismo cubo 2
                int cubo = 2;

                this.recorrido.add(indice1);
                this.recorrido.add(indice2);
                control.RutaMismoCubo(cubo);

                if (nodos2[indice2].equals(Destino)) {
                } else {
                    // Aquí debemos ver si no hay otro hilo que quiera pasar por el mismo nodo

                    // Si lo hay, entonces debemos esperar a que termine
                    this.ruta(nodos2[indice2], Destino);
                }

            } else {
                // Se pinta un salto
                // Creo que con un indice es suficiente (Origen)
                control.rutaSalto(indice1, indice2, "derecha", this);
                this.ruta(nodos2[indice2], Destino);
                this.Cubo = "derecha";
            }

        } else {
            this.Cubo = "izquierda";
            op.setNodos(nodos1);
            int indice2 = direccion(Origen, Destino, op);
           
            while (nodos1[indice2].isEnUso()) {
                try {
                    Thread.onSpinWait();
                    System.out.println("Hilo en espera");
                } catch (Exception e) {
                }
            }
            nodos1[indice2].setEnUso(true);
            // Condicion en caso de que haya un "salto"
            if (op.PerteneceMismoCubo(Origen, Destino)) {
                int cubo = 1;
                if (nodos1[indice2].equals(Destino)) {
                    this.recorrido.add(indice1);
                    this.recorrido.add(indice2);
                    control.RutaMismoCubo(cubo);
                } else {
                    this.recorrido.add(indice1);
                    this.recorrido.add(indice2);
                    control.RutaMismoCubo(cubo);

                    this.ruta(nodos1[indice2], Destino);
                }
            } else {
                // Se pinta un salto
                // Creo que con un indice es suficiente (Origen)
                control.rutaSalto(indice1, indice2, "izquierda", this);
                this.Cubo = "izquierda";

                this.ruta(nodos1[indice2], Destino);
            }

        }
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

}
