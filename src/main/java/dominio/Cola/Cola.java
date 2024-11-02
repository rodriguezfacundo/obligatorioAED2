package dominio.Cola;

public class Cola<T> implements ICola<T> {

    private NodoCola<T> inicio;
    private NodoCola<T> fin;
    private int largo;

    public void encolar(T dato) {
        if (this.inicio == null) {
            inicio = new NodoCola<>(dato);
            fin = inicio;
        } else {
            fin.sig = new NodoCola<>(dato);
            fin = fin.sig;
        }
        this.largo++;
    }

    // Pre: !esVacia()
    public T desencolar() {
        T dato = this.inicio.dato;
        inicio = inicio.sig;
        this.largo--;
        if (this.inicio == null) {
            fin = null;
        }
        return dato;
    }

    public boolean estaVacia() {
        return this.largo == 0;
    }

    private class NodoCola<Q> {
        private final Q dato;
        private NodoCola<Q> sig;

        public NodoCola(Q dato) {
            this.dato = dato;
        }

        @Override
        public String toString() {
            return dato.toString();
        }
    }
}
