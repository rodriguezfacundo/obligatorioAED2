package dominio.Auxiliares;

public class ObjectoCantidadAuxiliar<T> {

    private T dato;
    private int cantidad;

    public ObjectoCantidadAuxiliar(T dato, int cantidad) {
        this.dato = dato;
        this.cantidad = cantidad;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
