package dominio.Grafo.Modelo;

import dominio.ABB.ABB;

public class Equipo implements Comparable<Equipo> {
    private String nombre;
    private String mangaer;
    private ABB jugadores;

    public Equipo(String nombre, String mangaer) {
        this.nombre = nombre;
        this.mangaer = mangaer;
        jugadores = new ABB();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMangaer() {
        return mangaer;
    }

    public void setMangaer(String mangaer) {
        this.mangaer = mangaer;
    }

    public ABB getJugadores() {
        return jugadores;
    }

    public void setJugadores(ABB jugadores) {
        this.jugadores = jugadores;
    }

    protected int getCantidadJugadores(){
        return this.jugadores.cantNodos();
    }

    public boolean excedeMaximoJugadores(){
        return getCantidadJugadores() > 5;
    }
    @Override
    public int compareTo(Equipo o) {
        return this.nombre.compareTo(o.nombre);
    }

    public void agregarJugador(Jugador jugador) {
        this.jugadores.agregarDato(jugador);
        jugador.registradoEnEquipo();
    }
}
