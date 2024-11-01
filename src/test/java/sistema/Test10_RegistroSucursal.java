package sistema;

import interfaz.Sistema;
import org.junit.jupiter.api.Test;

import static sistema.TestUtil.*;

public class Test10_RegistroSucursal {
    /**
     * Registra 10 sucursales.
     */
    @Test
    public void testOk() {
        Sistema s = new ImplementacionSistema();
        assertOk(s.inicializarSistema(10));
        assertOk(s.registrarSucursal("UnCodigo", "Con nombre"));
        assertOk(s.registrarSucursal("OtroCodigo", "Con otro nombre"));
        assertOk(s.registrarSucursal("Cod3", "Test"));
        assertOk(s.registrarSucursal("Cod4", "Test"));
        assertOk(s.registrarSucursal("Cod5", "Test"));
        assertOk(s.registrarSucursal("Cod6", "Test"));
        assertOk(s.registrarSucursal("Cod7", "Test"));
        assertOk(s.registrarSucursal("Cod8", "Test"));
        assertOk(s.registrarSucursal("Cod9", "Test"));
        assertOk(s.registrarSucursal("Cod10", "Test"));
    }

    @Test
    public void testError1() {

        Sistema s = new ImplementacionSistema();
        assertOk(s.inicializarSistema(5));
        assertOk(s.registrarSucursal("Cod1", "nomb1"));
        assertOk(s.registrarSucursal("Cod2", "nomb"));
        assertOk(s.registrarSucursal("Cod3", "nomb"));
        assertOk(s.registrarSucursal("Cod4", "nomb"));
        assertOk(s.registrarSucursal("Cod5", "nomb"));
        assertError1(s.registrarSucursal("Cod6", "nomb"));
    }

    @Test
    public void testError2() {
        Sistema s = new ImplementacionSistema();
        assertOk(s.inicializarSistema(10));
        assertError2(s.registrarSucursal(new String(""), "Un nombre"));
        assertError2(s.registrarSucursal("CODIGO", new String("")));
        assertError2(s.registrarSucursal(null, new String("Nombre")));
        assertError2(s.registrarSucursal(null, "Un nombre"));
        assertError2(s.registrarSucursal(null, ""));
        assertError2(s.registrarSucursal(null, null));
    }

    @Test
    public void testError3() {

        Sistema s = new ImplementacionSistema();
        assertOk(s.inicializarSistema(5));
        assertOk(s.registrarSucursal("Cod1", "nomb1"));
        assertOk(s.registrarSucursal("Cod2", "nomb"));
        assertOk(s.registrarSucursal("Cod3", "nomb"));
        assertOk(s.registrarSucursal("Cod4", "nomb"));

        assertError3(s.registrarSucursal("Cod1", "Otro nom"));
        assertError3(s.registrarSucursal("Cod2", "Otro nom"));
        assertError3(s.registrarSucursal("Cod3", "Otro nom"));
        assertError3(s.registrarSucursal("Cod4", "Otro nom"));
    }


}
