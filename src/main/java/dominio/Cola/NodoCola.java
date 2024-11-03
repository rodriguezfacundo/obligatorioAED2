package dominio.Cola;

public class NodoCola {
    int posicion;
    int distancia;

    public NodoCola(int posicion, int distancia) {
        this.posicion = posicion;
        this.distancia = distancia;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
}
