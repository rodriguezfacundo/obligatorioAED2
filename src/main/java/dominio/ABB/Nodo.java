package dominio.ABB;

public class Nodo<T> {

    private T dato;
    private Nodo<T> der;
    private Nodo<T> izq;

    public Nodo(T dato) {
        this.dato = dato;
    }

    public Nodo(T dato, Nodo<T> der, Nodo<T> izq) {
        this.dato = dato;
        this.der = der;
        this.izq = izq;
    }


    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getDer() {
        return der;
    }

    public void setDer(Nodo<T> der) {
        this.der = der;
    }

    public Nodo<T> getIzq() {
        return izq;
    }

    public void setIzq(Nodo<T> izq) {
        this.izq = izq;
    }
}
