package sistema;

import interfaz.Categoria;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static sistema.TestUtil.*;

public class Test02_RegistroJugador {
    private Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    public void testOk() {

        assertOk(s.registrarJugador("UnAlias", "Con nomber", "Apellido", Categoria.ESTANDARD));
        assertOk(s.registrarJugador("OtroAlias", "Test", "Apellido 2", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("OtroAlias3", "Otro nombre", "Apellido 3 ", Categoria.PRINCIPIANTE));
        assertOk(s.registrarJugador("TestAlias4", "Nom", "Apellido4", Categoria.PRINCIPIANTE));
    }

    @Test
    public void testError1() {
        assertError1(s.registrarJugador(null, "d", "d", Categoria.PROFESIONAL));
        assertError1(s.registrarJugador("", "d", "d", Categoria.PROFESIONAL));
        assertError1(s.registrarJugador("d", null, "d", Categoria.PROFESIONAL));
        assertError1(s.registrarJugador("d", "", "d", Categoria.PROFESIONAL));
        assertError1(s.registrarJugador("d", "d", null, Categoria.PROFESIONAL));
        assertError1(s.registrarJugador("d", "d", "", Categoria.PROFESIONAL));
        assertError1(s.registrarJugador("d", "d", "a", null));
    }

    @Test
    public void testError2() {

        assertOk(s.registrarJugador("UnAlias", "Con nomber", "Apellido", Categoria.ESTANDARD));
        assertOk(s.registrarJugador("OtroAlias", "Test", "Apellido 2", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("OtroAlias3", "Otro nombre", "Apellido 3 ", Categoria.PRINCIPIANTE));
        assertOk(s.registrarJugador("TestAlias4", "Nom", "Apellido4", Categoria.PRINCIPIANTE));

        assertError2(s.registrarJugador(copiarTexto("OtroAlias3"), "Otron nombee", "Apellido", Categoria.PRINCIPIANTE));

        assertError2(s.registrarJugador(copiarTexto("TestAlias4"), "Nom", "Apellido4", Categoria.PRINCIPIANTE));

    }
}
