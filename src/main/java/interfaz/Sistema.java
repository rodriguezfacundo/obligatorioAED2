package interfaz;

/**
 * Provee una interfaz para interactuar con el sistema
 */
public interface Sistema {


    Retorno inicializarSistema(int maxSucursales);

    Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria);

    Retorno buscarJugador(String alias);

    Retorno listarJugadoresAscendente();

    Retorno listarJugadoresPorCategoria(Categoria unaCategoria);

    Retorno registrarEquipo(String nombre, String manager);

    Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador);

    Retorno listarJugadoresDeEquipo(String nombreEquipo);

    Retorno listarEquiposDescendente();

    Retorno registrarSucursal(String codigo, String nombre);

    Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia);

    Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia);

    Retorno analizarSucursal(String codigoSucursal);

    Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite);

}
