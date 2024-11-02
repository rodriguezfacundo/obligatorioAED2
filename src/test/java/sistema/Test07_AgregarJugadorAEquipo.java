package sistema;

import interfaz.Categoria;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static sistema.TestUtil.*;

public class Test07_AgregarJugadorAEquipo {

    public static final String MARION_S_TEAM = "Marion's Team";
    public static final String ODIN = "Odin";
    private Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(20);
        assertOk(s.registrarJugador("mariana", "Mariana", "Perez", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("roberto", "Roberto", "Gomez", Categoria.ESTANDARD));
        assertOk(s.registrarJugador("zack", "Zack", "Rodriguez", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("otello", "Otello", "Shake", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("caliban", "Caliban", "Estevez", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("arianna", "Arianna", "Op", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("esteban", "Esteban", "Dendi", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("sofia", "Sofia", "Bert", Categoria.PROFESIONAL));

        assertOk(s.registrarEquipo("La-raiz", "Mgr 1"));
        assertOk(s.registrarEquipo("Jordan", "Mgr 1"));
        assertOk(s.registrarEquipo("Thor", "Mgr 1"));
        assertOk(s.registrarEquipo(copiarTexto(ODIN), "Mgr 1"));
        assertOk(s.registrarEquipo(copiarTexto(MARION_S_TEAM), "Mgr 1"));
    }

    @Test
    public void testOk() {

        //Hasta 5 se deben poder registrar.
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("sofia")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("otello")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("esteban")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("zack")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("mariana")));

        assertOk(s.agregarJugadorAEquipo(ODIN,copiarTexto("caliban")));
        assertOk(s.agregarJugadorAEquipo(ODIN,copiarTexto("arianna")));

    }
    @Test
    public void testError1() {
        assertError1(s.agregarJugadorAEquipo(null,"test"));
        assertError1(s.agregarJugadorAEquipo("","test"));
        assertError1(s.agregarJugadorAEquipo("test",""));
        assertError1(s.agregarJugadorAEquipo("test",null));
        assertError1(s.agregarJugadorAEquipo("",null));
    }
    @Test
    public void testError2() {

        assertError2(s.agregarJugadorAEquipo("EquipoInexistente","sofia"));
        s.inicializarSistema(10);
        assertOk(s.registrarJugador("sofia","S","S",Categoria.PROFESIONAL));
        assertError2(s.agregarJugadorAEquipo(MARION_S_TEAM,"sofia"));
    }

    @Test
    public void testError3() {

        assertError3(s.agregarJugadorAEquipo(MARION_S_TEAM,"aaaa"));
        assertError3(s.agregarJugadorAEquipo(MARION_S_TEAM,"zzzzz"));
        s.inicializarSistema(10);
        assertOk(s.registrarEquipo(MARION_S_TEAM,"Mgr"));
        assertError3(s.agregarJugadorAEquipo(MARION_S_TEAM,"sofia"));
    }
    @Test
    public void testError4() {

        //Hasta 5 se deben poder registrar.
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("sofia")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("otello")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("esteban")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("zack")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("mariana")));

        assertError4(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("caliban")));

    }

    @Test
    public void testError5() {
        assertError5(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("roberto")));
    }
    @Test
    public void testError6(){
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("sofia")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("otello")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("esteban")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("zack")));

        assertError6(s.agregarJugadorAEquipo(ODIN,copiarTexto("zack")));
    }
}
