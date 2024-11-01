package sistema;

import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static sistema.TestUtil.*;

public class Test11_RegistroConexiones {
    public static final String COD_SUCURSAL_1 = "Codigo1";
    public static final String CODIGO_SUCURSAL_2 = "Codigo2";
    public static final String CODIGO_SUCURSAL_3 = "Codigo3";
    public static final String CODIGO_SUCURSAL_4 = "Codigo4";
    public static final String CODIGO_SUCURSAL_5 = "Codigo5";
    private Sistema s;

    @BeforeEach
    public void setUp() {
        s=new ImplementacionSistema();
        assertOk(s.inicializarSistema(8));
        assertOk(s.registrarSucursal(copiarTexto(COD_SUCURSAL_1),"Nombre 1"));
        assertOk(s.registrarSucursal(copiarTexto(CODIGO_SUCURSAL_2),"Nombre 2"));
        assertOk(s.registrarSucursal(copiarTexto(CODIGO_SUCURSAL_3),"Nombre 3"));
        assertOk(s.registrarSucursal(copiarTexto(CODIGO_SUCURSAL_4),"Nombre 4"));
        assertOk(s.registrarSucursal(copiarTexto(CODIGO_SUCURSAL_5),"Nombre 5"));
    }
    @Test
    public void testConexionesOk(){
        assertOk(s.registrarConexion(COD_SUCURSAL_1,CODIGO_SUCURSAL_2,23));
        assertOk(s.registrarConexion(COD_SUCURSAL_1,CODIGO_SUCURSAL_5,1));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_2,CODIGO_SUCURSAL_3,0));
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_4,CODIGO_SUCURSAL_3,52));
    }

    @Test
    public void testReinicioSistemaOk(){

        assertOk(s.registrarConexion(COD_SUCURSAL_1,CODIGO_SUCURSAL_2,23));
        assertOk(s.registrarConexion(copiarTexto(COD_SUCURSAL_1),copiarTexto(CODIGO_SUCURSAL_5),1));
        assertOk(s.inicializarSistema(10));
        //Si se reinicio, las sucursales no deben existir mas
        assertError3(s.registrarConexion(COD_SUCURSAL_1,CODIGO_SUCURSAL_2,23));
        assertOk(s.registrarSucursal(COD_SUCURSAL_1,"Nombre 1"));
        assertOk(s.registrarSucursal(CODIGO_SUCURSAL_2,"Nombre 2"));
        assertOk(s.registrarSucursal(CODIGO_SUCURSAL_3,"Nombre 3"));
        assertOk(s.registrarConexion(COD_SUCURSAL_1,CODIGO_SUCURSAL_2,23));

    }
    @Test
    public void testError1(){
        assertError1(s.registrarConexion(COD_SUCURSAL_1,CODIGO_SUCURSAL_2,-1));
        assertError1(s.registrarConexion(COD_SUCURSAL_1,CODIGO_SUCURSAL_3,-232));
        assertError1(s.registrarConexion(CODIGO_SUCURSAL_5,COD_SUCURSAL_1,Integer.MIN_VALUE));
    }
    @Test
    public void testError2(){
       assertError2(s.registrarConexion(null,null,12));
        assertError2(s.registrarConexion(new String(""),null,12));
        assertError2(s.registrarConexion(null,new String(""),12));
        assertError2(s.registrarConexion(new String(""),new String(""),12));
    }

    @Test
    public void testError3(){

        assertError3(s.registrarConexion("SUCURSAL",CODIGO_SUCURSAL_3,12));
        assertError3(s.registrarConexion(CODIGO_SUCURSAL_5,CODIGO_SUCURSAL_3+"#@#@@",12));
        assertError3(s.registrarConexion(CODIGO_SUCURSAL_3+"#@#@@","SDS@#!",12));
    }


    @Test
    public void testError4(){
        assertOk(s.registrarConexion(CODIGO_SUCURSAL_5,CODIGO_SUCURSAL_2,23));
        assertError4(s.registrarConexion(CODIGO_SUCURSAL_5,CODIGO_SUCURSAL_2,52));
        //Si las conexiones son bidireccionales, al registrarla al reves tambien deberia darnos error.
        assertError4(s.registrarConexion(CODIGO_SUCURSAL_2,CODIGO_SUCURSAL_5,52));

        assertOk(s.registrarConexion(CODIGO_SUCURSAL_3,CODIGO_SUCURSAL_4,23));
        assertError4(s.registrarConexion(CODIGO_SUCURSAL_4,CODIGO_SUCURSAL_3,52));

    }



}
