package modelo;

public class Matriz {

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

}
