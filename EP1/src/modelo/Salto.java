package modelo;

import java.awt.Color;

public class Salto {

    private int indice;
    private Color color;

    public Salto(int indice2, Color col) {
        this.indice = indice2;
        this.color = col;
    }

    public void setColor(Color col){
        this.color = col;
    }

    public void setIndice(int index){
        this.indice = index;
    }

    public int getIndice (){

        return this.indice;
    }

    public Color getColor (){

        return this.color;
    }

}
