package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test01_InicializarSistemaTest {
    Retorno retorno;



    @Test
    void noDeberiaInicializarSistemaConMaxSucursalesMenorOIgualA3() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(3);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.inicializarSistema(2);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.inicializarSistema(-1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.inicializarSistema(0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void deberiaInicializarSistema() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(4);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.inicializarSistema(10);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }


}
