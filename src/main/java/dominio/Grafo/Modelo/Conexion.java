package dominio.Grafo.Modelo;


import java.util.Objects;

public class Conexion implements Comparable {
    private String codigoSucursalOrigen;
    private String codigoSucursalDestino;
    private int latencia;

    public Conexion(String codigoSucursalOrigen, String codigoSucursalDestino,int latencia) {
        this.codigoSucursalOrigen = codigoSucursalOrigen;
        this.codigoSucursalDestino = codigoSucursalDestino;
        this.latencia = latencia;
    }

    public void actualizarse(Conexion actualizada){
        this.codigoSucursalOrigen=actualizada.getCodigoSucursalOrigen();
        this.codigoSucursalDestino=actualizada.getCodigoSucursalDestino();
        this.latencia = actualizada.getLatencia();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public String getCodigoSucursalOrigen() {
        return codigoSucursalOrigen;
    }

    public void setCodigoSucursalOrigen(String codigoSucursalOrigen) {
        this.codigoSucursalOrigen = codigoSucursalOrigen;
    }

    public String getCodigoSucursalDestino() {
        return codigoSucursalDestino;
    }

    public void setCodigoSucursalDestino(String codigoSucursalDestino) {
        this.codigoSucursalDestino = codigoSucursalDestino;
    }

    public int getLatencia() {
        return latencia;
    }

    public void setLatencia(int latencia) {
        this.latencia = latencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conexion conexion = (Conexion) o;
        return Objects.equals(codigoSucursalOrigen, conexion.codigoSucursalOrigen) && Objects.equals(codigoSucursalDestino, conexion.codigoSucursalDestino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoSucursalOrigen, codigoSucursalDestino);
    }
}
