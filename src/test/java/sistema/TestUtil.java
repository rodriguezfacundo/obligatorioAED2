package sistema;

import interfaz.Retorno;
import org.junit.jupiter.api.Assertions;

public class TestUtil {
    public static void assertOk(Retorno res) {
        Assertions.assertNotNull(res);
        Assertions.assertEquals(Retorno.Resultado.OK, res.getResultado(), "Se esperaba que la operacion, corriese correctamente");

    }

    public static void assertOk(Retorno ret, String valor) {
        assertOk(ret);
        Assertions.assertEquals(valor, ret.getValorString(), "El valor de retorno string no es el que se esperaba");

    }

    public static void assertOk(Retorno ret, int dato, String valor) {
        assertOk(ret, valor);
        Assertions.assertEquals(dato, ret.getValorInteger(), "El valor de retorno numerico no es el que se esperaba");
    }

    public static void assertError(Retorno ret, Retorno.Resultado esperado) {
        Assertions.assertNotNull(ret);
        Assertions.assertEquals(esperado, ret.getResultado());
    }

    public static void assertError1(Retorno ret) {
        assertError(ret, Retorno.Resultado.ERROR_1);
    }

    public static void assertError2(Retorno ret) {
        assertError(ret, Retorno.Resultado.ERROR_2);
    }

    public static void assertError3(Retorno ret) {
        assertError(ret, Retorno.Resultado.ERROR_3);
    }

    public static void assertError4(Retorno ret) {
        assertError(ret, Retorno.Resultado.ERROR_4);
    }

    public static void assertError5(Retorno ret) {
        assertError(ret, Retorno.Resultado.ERROR_5);
    }

    public static void assertError6(Retorno ret) {
        assertError(ret, Retorno.Resultado.ERROR_6);
    }

    /**
     * Retorna una copia del valor pasado por parametro, si falla por esto lo mas probable es que no usen equals para comparar.
     */
    public static String copiarTexto(String valor){
        return new String(valor);

    }
}
