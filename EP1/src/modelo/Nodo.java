package modelo;

public class Nodo {

    private int[] coordenadas;
    private int[] valor;

    public Nodo(int[] coordenadas, int[] valor) {

        this.coordenadas = coordenadas;
        this.valor = valor;
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
