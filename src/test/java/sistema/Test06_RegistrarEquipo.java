package sistema;

import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static sistema.TestUtil.*;

public class Test06_RegistrarEquipo {

    private Sistema s=new ImplementacionSistema();
    @BeforeEach
    public void setUp(){
        s.inicializarSistema(10);
    }

    @Test
    public void testOk(){

        assertOk(s.registrarEquipo("Nombre","Mgr"));
        assertOk(s.registrarEquipo("Equipo4","Mgr"));
        assertOk(s.registrarEquipo("Nombre3","Mgr"));
        assertOk(s.registrarEquipo("Nombre2","Mgr"));
        assertOk(s.registrarEquipo("Nombre4","Otro mgr"));
    }


    @Test
    public void testError1(){
        assertError1(s.registrarEquipo(null,"Mgr"));
        assertError1(s.registrarEquipo("","Mgr"));
        assertError1(s.registrarEquipo("Test",null));
        assertError1(s.registrarEquipo("Test",""));
        assertError1(s.registrarEquipo("",null));
    }

    @Test
    public void testError2(){

        assertOk(s.registrarEquipo("Nombre","Mgr"));
        assertOk(s.registrarEquipo("Equipo4","Mgr"));
        assertOk(s.registrarEquipo("Nombre3","Mgr"));
        assertOk(s.registrarEquipo("Nombre2","Mgr"));
        assertOk(s.registrarEquipo("Nombre4","Otro mgr"));
        assertOk(s.registrarEquipo("Equipo5","Mgr"));

        assertError2(s.registrarEquipo(copiarTexto("Equipo5"),"Mgr"));
        assertError2(s.registrarEquipo(copiarTexto("Nombre2"),"Mgr"));
        assertError2(s.registrarEquipo(copiarTexto("Nombre4"),"Mgr"));
    }

    @Test
    public void testReinicio(){

        assertOk(s.registrarEquipo("Nombre","Mgr"));
        assertOk(s.registrarEquipo("Equipo4","Mgr"));
        assertOk(s.registrarEquipo("Nombre3","Mgr"));
        assertOk(s.registrarEquipo("Nombre2","Mgr"));
        assertOk(s.registrarEquipo("Nombre4","Otro mgr"));
        assertOk(s.registrarEquipo("Equipo5","Mgr"));

        assertError2(s.registrarEquipo(copiarTexto("Equipo5"),"Mgr"));
        s.inicializarSistema(20);
        assertOk(s.registrarEquipo(copiarTexto("Equipo5"),"Mgr"));
        assertOk(s.registrarEquipo(copiarTexto("Nombre2"),"Mgr"));
        assertOk(s.registrarEquipo(copiarTexto("Nombre4"),"Mgr"));
    }
}
