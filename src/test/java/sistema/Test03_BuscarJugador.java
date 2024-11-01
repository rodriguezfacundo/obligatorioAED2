package sistema;

import interfaz.Categoria;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static sistema.TestUtil.*;

public class Test03_BuscarJugador {
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
    public void testBuscarJugadorOk() {


        assertOk(s.buscarJugador(copiarTexto("mariana")),1,"mariana;Mariana;Perez;PRINCIPIANTE");

        assertOk(s.buscarJugador(copiarTexto("roberto")),2,"roberto;Roberto;Gomez;ESTANDARD");
        assertOk(s.buscarJugador(copiarTexto("caliban")),2,"caliban;Caliban;Estevez;PRINCIPIANTE");

        assertOk(s.buscarJugador(copiarTexto("zack")),3,"zack;Zack;Rodriguez;PROFESIONAL");
        assertOk(s.buscarJugador(copiarTexto("esteban")),3,"esteban;Esteban;Dendi;PROFESIONAL");
        assertOk(s.buscarJugador(copiarTexto("otello")),3,"otello;Otello;Shake;PRINCIPIANTE");
        assertOk(s.buscarJugador(copiarTexto("arianna")),3,"arianna;Arianna;Op;PRINCIPIANTE");

        assertOk(s.buscarJugador(copiarTexto("sofia")),4,"sofia;Sofia;Bert;ESTANDARD");
    }

    @Test
    public void testBuscarConReinicio() {


        assertOk(s.buscarJugador(copiarTexto("mariana")),1,"mariana;Mariana;Perez;PRINCIPIANTE");

        s.inicializarSistema(10);//tiene que quedar vacio
        assertError2(s.buscarJugador(copiarTexto("sofia")));
        assertError2(s.buscarJugador(copiarTexto("mariana")));

        assertOk(s.registrarJugador("ariana", "Nombre 1", "Apellido 1", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("zack", "Nombre 2", "Apellido 2", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("baltazar", "Nombre 3", "Apellido 3", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("rodrigo", "Nombre 4", "Apellido 4", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("carmela", "Nombre 5", "Apellido 5", Categoria.PROFESIONAL));
        assertOk(s.registrarJugador("mariana", "Nombre 6", "Apellido 6", Categoria.PROFESIONAL));

        assertOk(s.buscarJugador("mariana"),6,"mariana;Nombre 6;Apellido 6;PROFESIONAL");
    }

    @Test
    public void testError1(){

        assertError1(s.buscarJugador(null));
        assertError1(s.buscarJugador(copiarTexto("")));
    }
    @Test
    public void testError2(){

        assertError2(s.buscarJugador("zzzzzzzzzzz"));
        assertError2(s.buscarJugador("marianana"));
    }
}
