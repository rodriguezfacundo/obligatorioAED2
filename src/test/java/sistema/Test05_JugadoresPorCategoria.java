package sistema;

import interfaz.Categoria;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static sistema.TestUtil.*;

public class Test05_JugadoresPorCategoria {
    private Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);

        s.registrarJugador("mariana","Mariana","Perez", Categoria.PRINCIPIANTE);
        s.registrarJugador("roberto","Roberto","Gomez", Categoria.ESTANDARD);
        s.registrarJugador("zack","Zack","Rodriguez", Categoria.PROFESIONAL);
        s.registrarJugador("otello","Otello","Shake", Categoria.PRINCIPIANTE);
        s.registrarJugador("caliban","Caliban","Estevez", Categoria.PRINCIPIANTE);
        s.registrarJugador("arianna","Arianna","Op", Categoria.PRINCIPIANTE);
        s.registrarJugador("esteban","Esteban","Dendi", Categoria.PROFESIONAL);
        s.registrarJugador("sofia","Sofia","Bert",Categoria.ESTANDARD);
    }

    @Test
    public void testJugadorPorCategoriaOk() {
        assertOk(s.listarJugadoresPorCategoria(Categoria.ESTANDARD),"roberto;Roberto;Gomez;ESTANDARD|sofia;Sofia;Bert;ESTANDARD");
        assertOk(s.listarJugadoresPorCategoria(Categoria.PROFESIONAL),"esteban;Esteban;Dendi;PROFESIONAL|zack;Zack;Rodriguez;PROFESIONAL");
        assertOk(s.listarJugadoresPorCategoria(Categoria.PRINCIPIANTE),"arianna;Arianna;Op;PRINCIPIANTE|caliban;Caliban;Estevez;PRINCIPIANTE|mariana;Mariana;Perez;PRINCIPIANTE|otello;Otello;Shake;PRINCIPIANTE");
    }

    @Test
    public void testReinicio(){
        s.inicializarSistema(20);
        assertOk(s.listarJugadoresPorCategoria(Categoria.ESTANDARD),"");
        assertOk(s.listarJugadoresPorCategoria(Categoria.PROFESIONAL),"");
        assertOk(s.listarJugadoresPorCategoria(Categoria.PRINCIPIANTE),"");

    }

}
