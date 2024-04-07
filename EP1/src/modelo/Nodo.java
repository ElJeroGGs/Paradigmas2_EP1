package modelo;

public class Nodo {

    private int[] coordenadas;
    private int[] valor;

    public Nodo(int[] coordenadas, int[] valor) {

        this.coordenadas = coordenadas;
        this.valor = valor;
    }

    public int[] getCoordenadas() {
        return coordenadas;
    }

    public int[] getValor() {
        return valor;
    }

    public String toString() {
        String resultado = "";
        for (int i = 0; i < this.valor.length; i++) {
            resultado += this.valor[i];
        }
        return resultado;
    }


}
