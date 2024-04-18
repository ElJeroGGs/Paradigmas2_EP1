package modelo;

import java.util.concurrent.Semaphore;

public class Nodo {

    private int[] coordenadas;
    private int[] valor;
    private boolean enUso = false;
    private Thread hiloEnUso = null;
    private Semaphore semaforo = new Semaphore(1);


    public Nodo(int[] coordenadas, int[] valor) {

        this.coordenadas = coordenadas;
        this.valor = valor;
    }

    
    public synchronized void usarNodo() {
            while (enUso && (hiloEnUso != Thread.currentThread() || hiloEnUso==null)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("prueba");
                }
            }
        
        enUso = true;
        hiloEnUso = Thread.currentThread();
    }
    
   public synchronized void liberarNodo() {
    enUso = false;
    hiloEnUso = null;
    notifyAll();
    semaforo.release();
    }

   

    public int[] getCoordenadas() {
        return coordenadas.clone();
    }

    public int[] getValor() {
        return valor.clone();
    }

    public int getValorDecimal(){

        int resultado = 0;
        for (int i = 0; i < this.valor.length; i++) {
            resultado += this.valor[i] * Math.pow(2, this.valor.length - i - 1);
        }
        return resultado;
    }

    public String toString() {
        String resultado = "";
        for (int i = 0; i < this.valor.length; i++) {
            resultado += this.valor[i];
        }
        return resultado;
    }


}
