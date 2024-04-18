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
//Aqui hace el barrido de izquierda a derecha, pero hay que pensar en el caso de que debamos "saltarnos" el orden
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

public int[] DireccionSalto(Nodo nodo,int[] tag){
    int[] Destino = nodo.getValor();
    int cuenta = 0;
    //Aqui hace el barrido de izquierda a derecha, pero se salta el primer 1 que encuentre
    for (int i = 0; i<4; i++){ 
    
        if(tag[i]!=0){

            if (cuenta==1){
                if(Destino[i]==0){
                    Destino[i] = 1;
                    }else{
                    Destino[i] = 0; 
        
    }
    break;    
}else{
        cuenta++;
    }
    
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

public boolean PerteneceMismoCubo(Nodo nodo1, Nodo nodo2){
    //Se supone que pertenecen al mismo cubo
    boolean pertenece = true;
    int index1 = this.getIndice(nodo1);
    int index2 = this.getIndice(nodo2);
    if(index1==-1 || index2==-1){
    //Se descarta la suposicion
        return false;
    }
    return pertenece;
}

}
