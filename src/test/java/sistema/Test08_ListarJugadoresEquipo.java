package sistema;

import interfaz.Categoria;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static sistema.TestUtil.*;

public class Test08_ListarJugadoresEquipo {
    public static final String MARION_S_TEAM = "Marion's Team";
    public static final String ODIN = "Odin";
    private Sistema s=new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
        assertOk(s.registrarJugador("mariana", "Mariana", "Perez", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("roberto", "Roberto", "Gomez", Categoria.ESTANDARD));
        assertOk(s.registrarJugador("zack", "Zack", "Rodriguez", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("otello", "Otello", "Shake", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("caliban", "Caliban", "Estevez", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("arianna", "Arianna", "Op", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("esteban", "Esteban", "Dendi", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("sofia", "Sofia", "Bert", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("otro", "Alias", "Con Apellido", Categoria.PROFESIONAL));

        assertOk(s.registrarEquipo("La-raiz", "Mgr 1"));
        assertOk(s.registrarEquipo("Jordan", "Mgr 1"));
        assertOk(s.registrarEquipo("Thor", "Mgr 1"));
        assertOk(s.registrarEquipo(copiarTexto(ODIN), "Mgr 1"));
        assertOk(s.registrarEquipo(copiarTexto(MARION_S_TEAM), "Mgr 1"));

        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM, copiarTexto("sofia")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM, copiarTexto("otello")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM, copiarTexto("esteban")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM, copiarTexto("zack")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM, copiarTexto("mariana")));

        assertOk(s.agregarJugadorAEquipo(ODIN, copiarTexto("caliban")));
        assertOk(s.agregarJugadorAEquipo(ODIN, copiarTexto("arianna")));
    }

    @Test
    public void testOk() {
        assertOk(s.listarJugadoresDeEquipo(MARION_S_TEAM),
                "esteban;Esteban;Dendi;PROFESIONAL|" +
                        "mariana;Mariana;Perez;PROFESIONAL|" +
                        "otello;Otello;Shake;PROFESIONAL|" +
                        "sofia;Sofia;Bert;PROFESIONAL|" +
                        "zack;Zack;Rodriguez;PROFESIONAL");
        assertOk(s.listarJugadoresDeEquipo(ODIN),
                "arianna;Arianna;Op;PROFESIONAL|" +
                "caliban;Caliban;Estevez;PROFESIONAL");
    }

    @Test
    public void testOkProbamosQueNoSeAgreguenJugadoresFrenteACasosDeError(){
        assertError4(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("otro")));
        assertError5(s.agregarJugadorAEquipo(ODIN,copiarTexto("roberto")));
        assertError6(s.agregarJugadorAEquipo(ODIN,copiarTexto("sofia")));

        assertOk(s.listarJugadoresDeEquipo(MARION_S_TEAM),
                "esteban;Esteban;Dendi;PROFESIONAL|" +
                        "mariana;Mariana;Perez;PROFESIONAL|" +
                        "otello;Otello;Shake;PROFESIONAL|" +
                        "sofia;Sofia;Bert;PROFESIONAL|" +
                        "zack;Zack;Rodriguez;PROFESIONAL");
        assertOk(s.listarJugadoresDeEquipo(ODIN),
                "arianna;Arianna;Op;PROFESIONAL|" +
                        "caliban;Caliban;Estevez;PROFESIONAL");
    }

    @Test
    public void testListadoVacio(){
        assertOk(s.listarJugadoresDeEquipo("Thor"),"");
    }

    @Test
    public void testReinicio(){
        s.inicializarSistema(23);
        assertError2(s.listarJugadoresDeEquipo(MARION_S_TEAM));
        assertError2(s.listarJugadoresDeEquipo(ODIN));

    }

    @Test
    public void testError1(){
        assertError1(s.listarJugadoresDeEquipo(copiarTexto("")));
        assertError1(s.listarJugadoresDeEquipo(null));
    }

    @Test
    public void testError2(){
        assertError2(s.listarJugadoresDeEquipo("NOExiste"));
    }

}
