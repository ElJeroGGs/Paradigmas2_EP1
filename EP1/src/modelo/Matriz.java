package modelo;

import java.util.Arrays;

public class Matriz {

    Nodo[] nodos;

    public int Diferencias(Nodo nodo1, Nodo nodo2){
    int [] valores1 = nodo1.getValor();
    int [] valores2 = nodo2.getValor();
    int contador = 0;

    for(int i = 0; i<4; i++){
    if(valores1[i]!=valores2[i]){
        contador++;}
    }
    return contador;
}

public void setNodos(Nodo[] nodos){
    this.nodos = nodos;
}

public int[] xor(Nodo nodo1, Nodo nodo2){
    int [] valores1 = nodo1.getValor();
    int [] valores2 = nodo2.getValor();
    int[] resultado = new int[4];
    for(int i = 0; i<4; i++){
        if(valores1[i]==valores2[i]){
            resultado[i] = 0;
        }else{
            resultado[i] = 1;
        }
        }
    return resultado;

}

public int[] Direccion(Nodo nodo,int[] tag){
int[] Destino = nodo.getValor();

for (int i = 0; i<4; i++){ 

    if(tag[i]!=0){
        if(Destino[i]==0){
        Destino[i] = 1;
        }else{
        Destino[i] = 0; 
} break;
}
}
return Destino;

}

public int getIndice(Nodo nodo){
int indice=-1;
    for(int i = 0; i<nodos.length; i++){
        if(Arrays.equals(nodos[i].getValor(), nodo.getValor())){
        
            indice = i;
        }
    }
    return indice;
}

}
