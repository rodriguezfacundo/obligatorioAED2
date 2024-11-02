package sistema;

import dominio.ABB.ABB;
import dominio.ABB.ABBEquipo;
import dominio.ABB.ObjectoCantidadAuxiliar;
import dominio.Grafo.Estructura.Grafo;
import dominio.Grafo.Modelo.Equipo;
import dominio.Grafo.Modelo.Jugador;
import dominio.Lista.Lista;
import interfaz.*;

public class ImplementacionSistema implements Sistema {
    private Grafo grafo;
    private ABB arbolJugadores;
    private ABBEquipo arbolEquipos;
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
        //La letra no hace referencia a retornar error en caso de que unaCategoria venga sin valor o no exista.
        ABB arbolCategoria = (ABB) ListaArbolesCategoriaJugadores.obtenerPorIndice(unaCategoria.getIndice()).getDato();
        return Retorno.ok(arbolCategoria.recorrerAscendenteLlamada());
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        if(nombre == null || nombre.isBlank() || manager == null || manager.isBlank()) {
            return Retorno.error1("Los datos no pueden ser vacios ni nulos");
        }
        Equipo nuevoEquipo = new Equipo(nombre, manager);
        if(arbolEquipos.existeDato(nuevoEquipo)){
            return Retorno.error2("Ya existe un equipo registrado con ese nombre");
        }
        arbolEquipos.agregarDato(nuevoEquipo);
        return Retorno.ok();
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        if(nombreEquipo == null || nombreEquipo.isBlank() || aliasJugador == null || aliasJugador.isBlank()){
            return Retorno.error1("Los datos no pueden ser vacios ni nulos");
        }
        ObjectoCantidadAuxiliar equipoExiste = arbolEquipos.buscarDatoMasCantidadRecorridas(new Equipo(nombreEquipo,""));
        if(equipoExiste==null){
            return Retorno.error2("No existe equipo con ese nombre");
        }
        Equipo equipo = (Equipo)equipoExiste.getDato();
        ObjectoCantidadAuxiliar jugadorExiste = arbolJugadores.buscarDatoMasCantidadRecorridas(new Jugador(aliasJugador,"", "", null));
        if(jugadorExiste==null){
            return Retorno.error3("No existe jugador con ese alias");
        }
        Jugador jugador = (Jugador)jugadorExiste.getDato();
        if(equipo.excedeMaximoJugadores()){
            return Retorno.error4("El equipo ya tiene 5 jugadores");
        }
        if(!jugador.esProfesional()){
            return Retorno.error5("El jugador debe de ser de categoria PROFESIONAL");
        }
        if(this.arbolEquipos.existeJugadorEnAlgunEquipo(jugador)){
            return Retorno.error6("Ese jugador ya se encuentra en algun equipo.");
        }
        equipo.agregarJugador(jugador);
        return Retorno.ok();

    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        if (nombreEquipo == null || nombreEquipo.isBlank()) {
            return Retorno.error1("El nombre del equipo no puede ser vacío o nulo.");
        }

        Equipo equipo = arbolEquipos.buscarEquipoPorNombre(nombreEquipo);
        if (equipo == null) {
            return Retorno.error2("No existe equipo con ese nombre.");
        }

        String jugadoresEnEquipo = equipo.listarJugadores();

        return Retorno.ok(jugadoresEnEquipo);
    }

    @Override
    public Retorno listarEquiposDescendente() {
        return Retorno.ok(arbolEquipos.recorrerDescendenteLlamada());
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {
       /* if (codigo == null || codigo.isBlank() || nombre == null || nombre.isBlank()) {
            return Retorno.error1("Los datos no pueden ser vacíos ni nulos");
        }

        // Verificar si ya existe una sucursal con el mismo código
        ObjectoCantidadAuxiliar sucursal = grafo.buscarVertice(codigo);
        if (sucursal != null) {
            return Retorno.error2("Ya existe una sucursal registrada con ese código");
        }

        // Registrar la nueva sucursal en el grafo
        boolean exito = grafo.agregarVertice(codigo, nombre);
        if (!exito) {
            return Retorno.error3("No se pudo registrar la sucursal");
        }

        return Retorno.ok();*/
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
        if(codigoSucursal == null || codigoSucursal.isBlank()){
            return Retorno.error1("Codigo vacio");
        }
        Sucursal sucursal = new Sucursal(codigoSucursal,"");
        if(!grafo.existeSucursal(sucursal)){
            return Retorno.error2("No existe esa sucursal en el grafo");
        }
        String valoresString = "";
        if(grafo.esPuntoCritico(sucursal)){
            valoresString+= "SI";
        } else{
            valoresString+= "NO";
        }
        return Retorno.ok(valoresString);
    }

    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        return Retorno.noImplementada();
    }
}
