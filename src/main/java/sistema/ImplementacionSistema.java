package sistema;

import dominio.Auxiliares.ObjectoCantidadAuxiliar;
import dominio.Entidades.*;
import dominio.Tads.ABB;
import dominio.Tads.Grafo;
import dominio.Tads.Lista;
import interfaz.*;

public class ImplementacionSistema implements Sistema {
    private Grafo grafo;
    private ABB arbolJugadores;
    private ABB arbolEquipos;
    private Lista ListaArbolesCategoriaJugadores;

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if(maxSucursales<=3){
            return Retorno.error1("Error: Sucursales <= 3");
        }
        arbolJugadores = new ABB();
        arbolEquipos = new ABB();
        ListaArbolesCategoriaJugadores = new Lista<ABB>(null);
        ListaArbolesCategoriaJugadores.agregarInicio( new ABB());
        ListaArbolesCategoriaJugadores.agregarInicio( new ABB());
        ListaArbolesCategoriaJugadores.agregarInicio( new ABB());
        this.grafo = new Grafo(maxSucursales, false);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {
        if (alias == null ||alias.isBlank()|| nombre == null ||nombre.isBlank()|| apellido == null ||apellido.isBlank() || categoria == null) {
            return Retorno.error1("Los datos no pueden ser nulos");
        }
        Jugador jugadorNuevo = new Jugador(alias,nombre,apellido,categoria);
        if(arbolJugadores.existeDato(jugadorNuevo)){
            return Retorno.error2("Ya existe ese jugador en el sistema");
        }else{
            arbolJugadores.agregarDato(jugadorNuevo);
            ABB abb = (ABB) ListaArbolesCategoriaJugadores.obtenerPorIndice(categoria.getIndice()).getDato();
            abb.agregarDato(jugadorNuevo);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno buscarJugador(String alias) {
        if(alias == null || alias.isBlank()) {
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
        if(jugador.estaEnUnEquipo()){
            return Retorno.error6("Ese jugador ya se encuentra en algun equipo.");
        }
        equipo.agregarJugador(jugador);
        return Retorno.ok();

    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        if (nombreEquipo == null ||nombreEquipo.isBlank()) {
            return Retorno.error1("Debes ingresar un nombre de equipo");
        }
        ObjectoCantidadAuxiliar equipoBuscado = arbolEquipos.buscarDatoMasCantidadRecorridas(new Equipo(nombreEquipo,""));
        if(equipoBuscado==null){
            return Retorno.error2("No existe equipo con ese nombre");
        }
        Equipo equipo = (Equipo)equipoBuscado.getDato();
        return Retorno.ok(equipo.getJugadores().recorrerAscendenteLlamada());
    }

    @Override
    public Retorno listarEquiposDescendente() {
        return Retorno.ok(arbolEquipos.recorrerDescendenteLlamada());
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {
        if(grafo.esLleno()){
            return Retorno.error1("Grafo lleno");
        }
        if(codigo==null || codigo.isBlank() || nombre==null || nombre.isBlank()){
            return Retorno.error2("Datos vacios");
        }
        Sucursal nueva = new Sucursal(codigo,nombre);
        if(grafo.existeSucursal(nueva)){
            return Retorno.error3("Ya existe la sucursal");
        }
        grafo.agregarSucursal(nueva);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if(latencia<0){
            return Retorno.error1("Latencia < 0");
        }
        if(codigoSucursal1==null || codigoSucursal1.isBlank() || codigoSucursal2 ==null || codigoSucursal2.isBlank()){
            return Retorno.error2("Parametros String o Enum son null");
        }
        Sucursal origen = new Sucursal(codigoSucursal1,"");
        Sucursal destino = new Sucursal(codigoSucursal2,"");
        if(!grafo.existeSucursal(origen) || !grafo.existeSucursal(destino)){
            return  Retorno.error3("Una o ambas sucursales no existen");
        }
        Conexion nuevaConexion = new Conexion(codigoSucursal1,codigoSucursal2, latencia);
        if(grafo.yaExisteConexion(origen,destino,nuevaConexion)){
            return  Retorno.error4("Ya existe la conexion");
        }
        grafo.agregarConexion(origen,destino,nuevaConexion);
        return  Retorno.ok();
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if(latencia < 0){
            return Retorno.error1("Latencia < 0");
        }
        if(codigoSucursal1==null || codigoSucursal1.isBlank() || codigoSucursal2 ==null || codigoSucursal2.isBlank()){
            return Retorno.error2("Parametros String o Enum son null");
        }
        Sucursal origen = new Sucursal(codigoSucursal1,"");
        Sucursal destino = new Sucursal(codigoSucursal2,"");
        if(!grafo.existeSucursal(origen) || !grafo.existeSucursal(destino)){
            return  Retorno.error3("No existe el origen o destino");
        }
        Conexion aActualizar = new Conexion(codigoSucursal1,codigoSucursal2, latencia);
        if(!grafo.yaExisteConexion(origen,destino,aActualizar)){
            return  Retorno.error4("No existe la conexion");
        }
        grafo.actualizarConexion(origen,destino,aActualizar);
        return  Retorno.ok();
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
        if(codigoSucursalAnfitriona == null || codigoSucursalAnfitriona.isBlank()){
            return Retorno.error1("Codigo vacio");
        }
        Sucursal sucursal = new Sucursal(codigoSucursalAnfitriona,"");
        if(!grafo.existeSucursal(sucursal)){
            return Retorno.error2("No existe esa sucursal en el grafo");
        }
        if(latenciaLimite<=0){
            return Retorno.error3("Latencia Limite <= 0");
        }
        ResultadoTorneo resultado = grafo.sucursalesParaTorneo(codigoSucursalAnfitriona, latenciaLimite);
        return Retorno.ok(resultado.getLatenciaMaxima(), resultado.getResultadoTorneo());
    }
}
