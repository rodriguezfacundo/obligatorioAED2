package dominio.Grafo.Modelo;

import dominio.ABB.ABB;
import dominio.Lista.Lista;

public class Equipo implements Comparable<Equipo>{
    private String nombre;
    private String mangaer;
    private Lista<Jugador> jugadores;

    public Equipo(String nombre, String mangaer) {
        this.nombre = nombre;
        this.mangaer = mangaer;
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

    @Override
    public int compareTo(Equipo o) {
        return this.nombre.compareTo(o.getNombre());
    }

    public Jugador getJugador(String aliasJugador) {
        return jugadores.obtenerElementoPorDato(new Jugador(aliasJugador,"","",null));
    }

    public boolean agregarJugador(Jugador jugador) {
        if(jugadores.getCantidad()<5){
            jugadores.agregarOrdenado(jugador);
            return true;
        }else{
            return false;
        }

    }
    public boolean ExisteJugador(Jugador jugador){
        return jugadores.existeElementoPorDato(jugador);
    }
}
