package modelo;

import java.util.concurrent.Semaphore;

public class Nodo {

    private int[] coordenadas;
    private int[] valor;
    private boolean enUso = false;
    private Semaphore semaforo = new Semaphore(1);


    public Nodo(int[] coordenadas, int[] valor) {

        this.coordenadas = coordenadas;
        this.valor = valor;
    }

    
    public void usarNodo() {
        try {
            semaforo.acquire();
            enUso = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
   public void liberarNodo() {
        enUso = false;
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
