package sistema;

import interfaz.Categoria;
import org.junit.jupiter.api.Test;

public class Test02_RegistrarTest {

    @Test
    public void simpleTest(){
        ImplementacionSistema sistema=new ImplementacionSistema();
        sistema.inicializarSistema(10);
        sistema.registrarJugador("pepe","Pepe","Suarez", Categoria.PROFESIONAL);
        sistema.registrarJugador("rober","Pepe","Suarez", Categoria.PROFESIONAL);
        sistema.registrarJugador("sofia","Pepe","Suarez", Categoria.PROFESIONAL);
        sistema.registrarJugador("alvarito","Pepe","Suarez", Categoria.PROFESIONAL);
        sistema.registrarJugador("ramon","Pepe","Suarez", Categoria.PROFESIONAL);
        sistema.registrarJugador("gonzalo","Pepe","Suarez", Categoria.PROFESIONAL);
        sistema.registrarJugador("paula","Pepe","Suarez", Categoria.ESTANDARD);
        sistema.registrarEquipo("eq1","Equipo1");
        sistema.registrarEquipo("eq2","Equipo2");
        sistema.agregarJugadorAEquipo("eq1","pepe");
        sistema.agregarJugadorAEquipo("eq1","rober");
        sistema.agregarJugadorAEquipo("eq1","sofia");
        sistema.agregarJugadorAEquipo("eq1","alvarito");
        sistema.agregarJugadorAEquipo("eq1","gonzalo");
        System.out.println(sistema.listarJugadoresAscendente());
        System.out.println(sistema.listarEquiposDescendente());
        System.out.println(sistema.listarJugadoresPorCategoria(Categoria.ESTANDARD));
        System.out.println(sistema.listarJugadoresDeEquipo("eq1"));
        System.out.println(sistema.listarJugadoresDeEquipo("eq2"));

    }
}
