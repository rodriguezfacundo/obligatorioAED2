package dominio.Grafo.Modelo;

import interfaz.Categoria;

import java.util.Objects;

public class Jugador implements Comparable<Jugador>{
    private String alias;
    private String nombre;
    private String apellido;
    private Categoria categoria;

    public Jugador(String alias, String nombre, String apellido, Categoria categoria) {
        this.alias = alias;
        this.nombre = nombre;
        this.apellido = apellido;
        this.categoria = categoria;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean esProfesional(){
        return this.categoria.equals(Categoria.PROFESIONAL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(alias, jugador.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(alias);
    }

    @Override
    public int compareTo(Jugador o) {
        return this.alias.compareTo(o.getAlias());
    }

    @Override
    public String toString() {
        return alias + ";" + nombre + ";" + apellido + ";" + categoria;
    }
}
