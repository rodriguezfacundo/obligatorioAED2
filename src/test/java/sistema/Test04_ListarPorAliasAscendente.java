package sistema;

import interfaz.Categoria;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static sistema.TestUtil.assertOk;

public class Test04_ListarPorAliasAscendente {
    private Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    public void testOkVacio() {
        assertOk(s.listarJugadoresAscendente(), "");
    }

    @Test
    public void testOk() {

        s.registrarJugador("mariana", "Mariana", "Perez", Categoria.PRINCIPIANTE);
        s.registrarJugador("roberto", "Roberto", "Gomez", Categoria.ESTANDARD);
        s.registrarJugador("zack", "Zack", "Rodriguez", Categoria.PROFESIONAL);
        s.registrarJugador("otello", "Otello", "Shake", Categoria.PRINCIPIANTE);
        s.registrarJugador("caliban", "Caliban", "Estevez", Categoria.PRINCIPIANTE);
        s.registrarJugador("arianna", "Arianna", "Op", Categoria.PRINCIPIANTE);
        s.registrarJugador("esteban", "Esteban", "Dendi", Categoria.PROFESIONAL);
        s.registrarJugador("sofia", "Sofia", "Bert", Categoria.ESTANDARD);

        assertOk(s.listarJugadoresAscendente(),
                "arianna;Arianna;Op;PRINCIPIANTE|" +
                        "caliban;Caliban;Estevez;PRINCIPIANTE|" +
                        "esteban;Esteban;Dendi;PROFESIONAL|" +
                        "mariana;Mariana;Perez;PRINCIPIANTE|" +
                        "otello;Otello;Shake;PRINCIPIANTE|" +
                        "roberto;Roberto;Gomez;ESTANDARD|" +
                        "sofia;Sofia;Bert;ESTANDARD|" +
                        "zack;Zack;Rodriguez;PROFESIONAL");
    }

    @Test
    public void testOk2() {
        assertOk(s.registrarJugador("ariana", "Nombre 1", "Apellido 1", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("zack", "Nombre 2", "Apellido 2", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("baltazar", "Nombre 3", "Apellido 3", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("rodrigo", "Nombre 4", "Apellido 4", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("carmela", "Nombre 5", "Apellido 5", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("mariana", "Nombre 6", "Apellido 6", Categoria.PROFESIONAL));

        assertOk(s.listarJugadoresAscendente(),
                "ariana;Nombre 1;Apellido 1;PROFESIONAL|" +
                        "baltazar;Nombre 3;Apellido 3;PROFESIONAL|" +
                        "carmela;Nombre 5;Apellido 5;PROFESIONAL|" +
                        "mariana;Nombre 6;Apellido 6;PROFESIONAL|" +
                        "rodrigo;Nombre 4;Apellido 4;PROFESIONAL|" +
                        "zack;Nombre 2;Apellido 2;PROFESIONAL");

    }
}
