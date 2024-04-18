package modelo;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.text.html.HTML.Tag;

public class Nodo {

    private int[] coordenadas;
    private int[] valor;
    private boolean enUso = false;
    private Thread hiloEnUso = null;
    private Semaphore semaforo = new Semaphore(1);
    private ReentrantLock lock = new ReentrantLock();
    private boolean TagSalto = false;


    public Nodo(int[] coordenadas, int[] valor) {

        this.coordenadas = coordenadas;
        this.valor = valor;
    }

    
    public void usarNodo() {

        Conexion con = (Conexion) hiloEnUso;
        synchronized(this) {
            while (enUso && (hiloEnUso != Thread.currentThread() || hiloEnUso==null)) {
                try {
                    Random rand = new Random();
                    int randomNum = rand.nextInt((1501)) + 500; // Genera un número aleatorio entre 1000 y 5000 (1 y 5 segundos)
                    wait(randomNum); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("prueba");
                }
                if (enUso && (hiloEnUso != Thread.currentThread() || hiloEnUso==null)&& con.getCambioDireccion()==false) {
                    // Si después de 3 segundos el nodo todavía está en uso, haz otra cosa
                    // Aquí puedes poner el código para lo que quieras que haga
                    //Aqui que le avise al codigo que se salte haga uso del metodo DirecciónSalto
                
                    TagSalto = true;
                    notifyAll();
                    break;
                }
            }
            enUso = true;
            if(hiloEnUso == null){
                
            hiloEnUso = Thread.currentThread();
            }
        }
    }
    
    public synchronized void liberarNodo() {
        enUso = false;
        hiloEnUso = null;
        TagSalto = false;
        notifyAll();
        
    }
   
    public boolean getTagSalto(){
        return TagSalto;
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
