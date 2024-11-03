package dominio.Tads;

public class NodoLista<T>  {

    private T dato;
    private NodoLista<T>  siguiente;

    //Constructor
    public NodoLista( T elDato){
        this.setDato(elDato);
        this.setSiguiente(null);
    }


    public T getDato() {
        return dato;
    }


    public void setDato(T dato) {
        this.dato = dato;
    }


    public NodoLista<T>  getSiguiente() {
        return siguiente;
    }


    public void setSiguiente(NodoLista<T>  siguiente) {
        this.siguiente = siguiente;
    }
}
