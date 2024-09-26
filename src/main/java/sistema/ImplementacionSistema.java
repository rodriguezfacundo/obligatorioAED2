package sistema;

import dominio.ABB.ABB;
import dominio.ABB.ObjectoCantidadAuxiliar;
import dominio.Grafo.Estructura.Grafo;
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
        if(alias.isBlank() || alias == null) {
            return Retorno.error1("El alias no puede ser vacio");
        }
        Jugador jugadorBuscado = new Jugador(alias,"","",null);
        ObjectoCantidadAuxiliar resultado = arbolJugadores.buscarDatoMasCantidadRecorridas(jugadorBuscado);
        if(resultado==null){
            return Retorno.error2("No existe ese jugador");
        }
        Jugador jugadorEncontrado = (Jugador)resultado.getDato();
        return Retorno.ok(resultado.getCantidad(),jugadorEncontrado.toString());
    }

    @Override
    public Retorno listarJugadoresAscendente() {
        return Retorno.ok(arbolJugadores.recorrerAscendenteLlamada());
    }

    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {

        ABB arbolCategoria = (ABB) ListaArbolesCategoriaJugadores.obtenerPorIndice(unaCategoria.getIndice()).getDato();

        return Retorno.ok(arbolCategoria.recorrerAscendenteLlamada());
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
