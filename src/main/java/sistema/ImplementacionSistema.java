package sistema;

import dominio.ABB.ABB;
import dominio.Grafo.Estructura.Grafo;
import dominio.Grafo.Estructura.ObjetoAuxiliar;
import dominio.Grafo.Modelo.Jugador;
import dominio.Lista.Lista;
import interfaz.*;

public class ImplementacionSistema implements Sistema {
    private Grafo grafo;
    private ABB arbolJugadores;
    private Lista ListaArbolesCategoriaJugadores;

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if(maxSucursales<=3){
            return Retorno.error1("Error: Sucursales <= 3");
        }
        arbolJugadores = new ABB();
        ListaArbolesCategoriaJugadores = new Lista<ABB>(null);
        ListaArbolesCategoriaJugadores.agregarInicio( new ABB());
        ListaArbolesCategoriaJugadores.agregarInicio( new ABB());
        ListaArbolesCategoriaJugadores.agregarInicio( new ABB());
        grafo = new Grafo(maxSucursales);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {
        if (alias == null ||alias.isBlank()|| nombre == null ||nombre.isBlank()|| apellido == null ||apellido.isBlank() || categoria == null) {
            return Retorno.error1("Los datos no pueden ser nulos");
        }
        Jugador jugadorNuevo = new Jugador(alias,nombre,apellido,categoria);
        if(arbolJugadores.existeDato(jugadorNuevo)){
            return Retorno.error3("Ya existe ese jugador en el sistema");
        }else{
            arbolJugadores.agregarDato(jugadorNuevo);
            ABB abb = (ABB) ListaArbolesCategoriaJugadores.obtenerPorIndice(categoria.getIndice()).getDato();
            abb.agregarDato(jugadorNuevo);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno buscarJugador(String alias) {
        if(alias.isBlank() || alias.isEmpty()){
            return Retorno.error1("El alias es Vacio o Null");
        }else{
            Jugador j = new Jugador(alias,"","",Categoria.fromTexto("Principiante"));
            ObjetoAuxiliar aux = arbolJugadores.obtenerDato(j);
            if(aux != null)
                return Retorno.ok((int)aux.getValorInt(),aux.getValorString());
            else
                return Retorno.error2("No Existe jugador registrado con ese Alias");
        }
    }

    @Override
    public Retorno listarJugadoresAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEquiposDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno analizarSucursal(String codigoSucursal) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        return Retorno.noImplementada();
    }
}
