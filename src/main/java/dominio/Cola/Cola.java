package dominio.Cola;

import dominio.Lista.NodoLista;

public class Cola<T> {

    private NodoLista<T> primero;
    private NodoLista<T> ultimo;

    public Cola() {
    }

    public void encolar(T dato){
        NodoLista<T> nuevo = new NodoLista<>(dato);
        if(esVacia()){
            primero=nuevo;
        }else {
            ultimo.setSiguiente(nuevo);
        }
        ultimo = nuevo;
    }
    public T desencolar(){
        if(esVacia()) return null;
        T aux = primero.getDato();
        primero=primero.getSiguiente();
        if(primero==null){
            ultimo=null;
        }
        return  aux;
    }

    public boolean esVacia(){
        return primero==null;
    }

}
