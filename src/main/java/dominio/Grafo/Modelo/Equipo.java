package dominio.Grafo.Modelo;

import dominio.ABB.ABB;

public class Equipo implements Comparable<Equipo> {
    private String nombre;
    private String mangaer;
    private ABB arbolJugadores;

    public Equipo(String nombre, String mangaer) {
        this.nombre = nombre;
        this.mangaer = mangaer;
        arbolJugadores = new ABB();
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

    public ABB getArbolJugadores() {
        return arbolJugadores;
    }

    public void setArbolJugadores(ABB arbolJugadores) {
        this.arbolJugadores = arbolJugadores;
    }

    protected int getCantidadJugadores(){
        return this.arbolJugadores.cantNodos();
    }

    public boolean excedeMaximoJugadores(){
        return getCantidadJugadores() > 5;
    }

    @Override
    public int compareTo(Equipo o) {
        return this.nombre.compareTo(o.nombre);
    }

    public void agregarJugador(Jugador jugador) {
        this.arbolJugadores.agregarDato(jugador);
    }

    public String listarJugadores() {
        if (arbolJugadores == null || arbolJugadores.estaVacio()) {
        return "";
        }
        return arbolJugadores.recorrerAscendenteLlamada();
    }

}
