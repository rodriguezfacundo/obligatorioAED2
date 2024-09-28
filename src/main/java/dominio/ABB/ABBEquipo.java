package dominio.ABB;

import dominio.Grafo.Modelo.Equipo;
import dominio.Grafo.Modelo.Jugador;

public class ABBEquipo extends ABB<Equipo> {
    private Nodo<Equipo> raiz;

    public boolean existeJugadorEnAlgunEquipo(Jugador jugadorABuscar) {
        return buscarJugadorEnEquipos(jugadorABuscar);
    }

    private boolean buscarJugadorEnEquipos(Jugador jugadorABuscar) {
        return buscarJugadorEnEquiposRec(this.raiz, jugadorABuscar);
    }

    private boolean buscarJugadorEnEquiposRec(Nodo<Equipo> nodo, Jugador jugadorABuscar) {
        if (nodo == null) return false;
        Equipo equipoActual = nodo.getDato();
        ABB<Jugador> jugadores = equipoActual.getArbolJugadores();
        boolean existeJugadorEnEquipo = jugadores.existeDato(jugadorABuscar);
        if (existeJugadorEnEquipo) {
            return true;
        }
        return buscarJugadorEnEquiposRec(nodo.getIzq(), jugadorABuscar) || buscarJugadorEnEquiposRec(nodo.getDer(), jugadorABuscar);
    }
}
