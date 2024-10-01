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
    public Equipo buscarEquipoPorNombre(String nombreEquipo) {
        return buscarEquipoRec(raiz, nombreEquipo);
    }

    private Equipo buscarEquipoRec(Nodo<Equipo> nodo, String nombreEquipo) {
        if (nodo == null) {
            return null;
        }

        if (nombreEquipo.compareTo(nodo.getDato().getNombre()) == 0) {
            return nodo.getDato();
        } else if (nombreEquipo.compareTo(nodo.getDato().getNombre()) > 0) {
            return buscarEquipoRec(nodo.getDer(), nombreEquipo);
        } else {
            return buscarEquipoRec(nodo.getIzq(), nombreEquipo);
        }
    }

}
