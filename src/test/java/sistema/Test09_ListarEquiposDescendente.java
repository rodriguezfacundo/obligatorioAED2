package sistema;

import interfaz.Categoria;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static sistema.TestUtil.assertOk;
import static sistema.TestUtil.copiarTexto;

public class Test09_ListarEquiposDescendente {
    public static final String MARION_S_TEAM = "Marion's Team";
    public static final String ODIN = "Odin";
    private Sistema s=new ImplementacionSistema();


    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);

        assertOk(s.registrarJugador("mariana", "Mariana", "Perez", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("roberto", "Roberto", "Gomez", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("zack", "Zack", "Rodriguez", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("otello", "Otello", "Shake", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("caliban", "Caliban", "Estevez", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("arianna", "Arianna", "Op", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("esteban", "Esteban", "Dendi", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("sofia", "Sofia", "Bert", Categoria.PROFESIONAL));

    }


    @Test
    public void testOk(){
        assertOk(s.registrarEquipo("Jordan", "Mgr 1"));
        assertOk(s.registrarEquipo("Thor", "Mgr 2"));

        assertOk(s.registrarEquipo(copiarTexto(MARION_S_TEAM), "Mgr 1"));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("sofia")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("otello")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("esteban")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("zack")));
        assertOk(s.agregarJugadorAEquipo(MARION_S_TEAM,copiarTexto("mariana")));

        assertOk(s.registrarEquipo("La-raiz", "Mgr 5"));
        assertOk(s.agregarJugadorAEquipo("La-raiz",copiarTexto("roberto")));

        assertOk(s.registrarEquipo(copiarTexto(ODIN), "Mgr 3"));
        assertOk(s.agregarJugadorAEquipo(ODIN,copiarTexto("caliban")));
        assertOk(s.agregarJugadorAEquipo(ODIN,copiarTexto("arianna")));

        assertOk(s.listarEquiposDescendente(),"Thor;Mgr 2;0|Odin;Mgr 3;2|Marion's Team;Mgr 1;5|La-raiz;Mgr 5;1|Jordan;Mgr 1;0");
    }

    @Test
    public void testVacio(){
        assertOk(s.listarEquiposDescendente(),"");
    }
    @Test
    public void testReinicio(){

        assertOk(s.registrarEquipo("La-raiz", "Mgr 5"));
        assertOk(s.registrarEquipo("Jordan", "Mgr 1"));
        assertOk(s.registrarEquipo("Thor", "Mgr 2"));
        assertOk(s.registrarEquipo(copiarTexto(ODIN), "Mgr 3"));
        assertOk(s.registrarEquipo(copiarTexto(MARION_S_TEAM), "Mgr 1"));
        s.inicializarSistema(20);
        assertOk(s.listarEquiposDescendente(),"");
    }
}
