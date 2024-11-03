package dominio.Tads;

import dominio.Entidades.Sucursal;

public class Tupla  {
    private Sucursal sucursal;
    private int nivel;

    public Tupla(Sucursal sucursal, int nivel) {
        this.sucursal = sucursal;
        this.nivel = nivel;
    }

    public Sucursal getCiudad() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
