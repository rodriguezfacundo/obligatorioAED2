package sistema;

import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static sistema.TestUtil.*;

public class Test13_SucursalCritica {

    public static final String CODIGO_SUCURSAL_1 = "Codigo1";
    public static final String CODIGO_SUCURSAL_2 = "Codigo2";
    public static final String CODIGO_SUCURSAL_3 = "Codigo3";
    public static final String CODIGO_SUCURSAL_4 = "Codigo4";
    public static final String CODIGO_SUCURSAL_5 = "Codigo5";
    public static final String CODIGO_SUCURSAL_6 = "Codigo6";
    public static final String CODIGO_SUCURSAL_7 = "Codigo7";
    public static final String CODIGO_SUCURSAL_8 = "Codigo8";
    private Sistema s;

    @BeforeEach
    public void setUp() {
        s = new ImplementacionSistema();
        assertOk(s.inicializarSistema(8));
        assertOk(s.registrarSucursal(copiarTexto(CODIGO_SUCURSAL_1), "Nombre 1"));
        assertOk(s.registrarSucursal(copiarTexto(CODIGO_SUCURSAL_2), "Nombre 2"));
        assertOk(s.registrarSucursal(copiarTexto(CODIGO_SUCURSAL_3), "Nombre 3"));
        assertOk(s.registrarSucursal(copiarTexto(CODIGO_SUCURSAL_4), "Nombre 4"));
        assertOk(s.registrarSucursal(copiarTexto(CODIGO_SUCURSAL_5), "Nombre 5"));
        assertOk(s.registrarSucursal(copiarTexto(CODIGO_SUCURSAL_6), "Nombre 5"));
        assertOk(s.registrarSucursal(copiarTexto(CODIGO_SUCURSAL_7), "Nombre 5"));
        assertOk(s.registrarSucursal(copiarTexto(CODIGO_SUCURSAL_8), "Nombre 5"));
    }

    @Test
    public void testGrafoNuloNingunaEsCritica() {
        //No hay conexiones, ninguna en teoria es critica.
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_1), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_2), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_3), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_4), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_5), "NO");
    }

    @Test
    public void testSucursalQueUneDosSucursales() {
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_2, CODIGO_SUCURSAL_1, 23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_5, CODIGO_SUCURSAL_1, 23));
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_1), "SI");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_5), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_5), "NO");
    }

    @Test
    public void testDosSucursalesCriticasConectadas() {

        assertOk(s.registrarConexion(CODIGO_SUCURSAL_2, CODIGO_SUCURSAL_1, 23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_5, CODIGO_SUCURSAL_1, 23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_5, CODIGO_SUCURSAL_4, 23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_5, CODIGO_SUCURSAL_3, 23));
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_1), "SI");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_5), "SI");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_4), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_3), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_2), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_8), "NO");

        //Si conectamos la 2 con otra sucursal, entonces la uno deja de ser el unico vertice por el cual se accede a la dos
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_2, CODIGO_SUCURSAL_4, 23));
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_1), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_5), "SI");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_4), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_3), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_2), "NO");
    }

    @Test
    public void testDosSucursalesCriticasEnDosComponentesConexas() {

        assertOk(s.registrarConexion(CODIGO_SUCURSAL_2, CODIGO_SUCURSAL_1, 23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_5, CODIGO_SUCURSAL_1, 23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_3, CODIGO_SUCURSAL_1, 23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_4, CODIGO_SUCURSAL_1, 23));

        assertOk(s.registrarConexion(CODIGO_SUCURSAL_6, CODIGO_SUCURSAL_7, 23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_7, CODIGO_SUCURSAL_8, 23));
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_1), "SI");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_7), "SI");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_5), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_4), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_3), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_2), "NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_8), "NO");
    }

    @Test
    public void testGrafoCompletoNoHaySucursalesCriticas(){
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_1,CODIGO_SUCURSAL_2,23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_1,CODIGO_SUCURSAL_3,23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_1,CODIGO_SUCURSAL_4,23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_1,CODIGO_SUCURSAL_5,23));

        assertOk(s.registrarConexion(CODIGO_SUCURSAL_2,CODIGO_SUCURSAL_3,23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_2,CODIGO_SUCURSAL_4,23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_2,CODIGO_SUCURSAL_5,23));

        assertOk(s.registrarConexion(CODIGO_SUCURSAL_3,CODIGO_SUCURSAL_4,23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_3,CODIGO_SUCURSAL_5,23));

        assertOk(s.registrarConexion(CODIGO_SUCURSAL_4,CODIGO_SUCURSAL_5,23));

        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_1),"NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_2),"NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_3),"NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_4),"NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_5),"NO");

        //Conectamos un nuevo vertice a la componente conexa ahora el punto por el que se conecta pasa a ser de articulacion

        assertOk(s.registrarConexion(CODIGO_SUCURSAL_6,CODIGO_SUCURSAL_5,23));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_4,CODIGO_SUCURSAL_7,23));
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_1),"NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_2),"NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_3),"NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_4),"SI");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_5),"SI");


        assertOk(s.registrarConexion(CODIGO_SUCURSAL_6,CODIGO_SUCURSAL_7,211));

        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_4),"NO");
        assertOk(s.analizarSucursal(CODIGO_SUCURSAL_5),"NO");

    }

    @Test
    public void testError1() {
        assertError1(s.analizarSucursal(null));
        assertError1(s.analizarSucursal(new String()));
    }

    @Test
    public void testError2() {
        assertError2(s.analizarSucursal("Dsdsd@#@"));
    }
}
