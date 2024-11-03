package sistema;

import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static sistema.TestUtil.*;

public class Test14_SucursalesTorneo {

    private Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(100);

        s.registrarSucursal(copiarTexto("I"), "Sucursal I");

        s.registrarSucursal(copiarTexto("H"), "Sucursal H");
        s.registrarSucursal(copiarTexto("J"), "Sucursal J");
        s.registrarSucursal(copiarTexto("K"), "Sucursal K");
        s.registrarSucursal(copiarTexto("L"), "Sucursal L");
        s.registrarSucursal(copiarTexto("F"), "Sucursal F");
        s.registrarSucursal(copiarTexto("B"), "Sucursal B");
        s.registrarSucursal(copiarTexto("D"), "Sucursal D");
        s.registrarSucursal(copiarTexto("A"), "Sucursal A");
        s.registrarSucursal(copiarTexto("G"), "Sucursal G");
        s.registrarSucursal(copiarTexto("E"), "Sucursal E");
        s.registrarSucursal(copiarTexto("C"), "Sucursal C");

        //Una componente que conexa que no vamos a usar en los test, pero queda para que chequeen
        //no explorar vertices que no pertenecen a la componente conexa que estan explorando
        s.registrarConexion("J", "K", 1);
        s.registrarConexion("L", "K", 1);
        s.registrarConexion("L", "M", 1);

    }

    @Test
    public void testOk1() {
        //El grafo en cuestion
        //https://dreampuf.github.io/GraphvizOnline/#graph%20G%7B%0AV_0%5Blabel%3D%22I%3BSucursal%20I%22%5D%3B%0AV_1%5Blabel%3D%22H%3BSucursal%20H%22%5D%3B%0AV_2%5Blabel%3D%22J%3BSucursal%20J%22%5D%3B%0AV_3%5Blabel%3D%22K%3BSucursal%20K%22%5D%3B%0AV_4%5Blabel%3D%22L%3BSucursal%20L%22%5D%3B%0AV_5%5Blabel%3D%22F%3BSucursal%20F%22%5D%3B%0AV_6%5Blabel%3D%22B%3BSucursal%20B%22%5D%3B%0AV_7%5Blabel%3D%22D%3BSucursal%20D%22%5D%3B%0AV_8%5Blabel%3D%22A%3BSucursal%20A%22%5D%3B%0AV_9%5Blabel%3D%22G%3BSucursal%20G%22%5D%3B%0AV_10%5Blabel%3D%22E%3BSucursal%20E%22%5D%3B%0AV_11%5Blabel%3D%22C%3BSucursal%20C%22%5D%3B%0AV_1--V_0%5Blabel%3D%227%22%5D%3B%0AV_3--V_2%5Blabel%3D%221%22%5D%3B%0AV_4--V_3%5Blabel%3D%221%22%5D%3B%0AV_6--V_1%5Blabel%3D%2211%22%5D%3B%0AV_8--V_1%5Blabel%3D%228%22%5D%3B%0AV_8--V_6%5Blabel%3D%224%22%5D%3B%0AV_9--V_0%5Blabel%3D%226%22%5D%3B%0AV_9--V_1%5Blabel%3D%221%22%5D%3B%0AV_9--V_5%5Blabel%3D%222%22%5D%3B%0AV_10--V_5%5Blabel%3D%2210%22%5D%3B%0AV_10--V_7%5Blabel%3D%229%22%5D%3B%0AV_11--V_0%5Blabel%3D%222%22%5D%3B%0AV_11--V_5%5Blabel%3D%224%22%5D%3B%0AV_11--V_6%5Blabel%3D%228%22%5D%3B%0AV_11--V_7%5Blabel%3D%227%22%5D%3B%0A%7D%0A

        s.registrarConexion("A", "B", 4);
        s.registrarConexion("B", "C", 8);
        s.registrarConexion("C", "D", 7);
        s.registrarConexion("D", "E", 9);
        s.registrarConexion("E", "F", 10);
        s.registrarConexion("F", "G", 2);
        s.registrarConexion("G", "H", 1);
        s.registrarConexion("H", "A", 8);
        s.registrarConexion("H", "B", 11);
        s.registrarConexion("E", "F", 14);
        s.registrarConexion("C", "I", 2);
        s.registrarConexion("H", "I", 7);
        s.registrarConexion("G", "I", 6);
        s.registrarConexion("C", "F", 4);

        assertOk(s.sucursalesParaTorneo("A", 21), 21, "A;Sucursal A|B;Sucursal B|C;Sucursal C|D;Sucursal D|E;Sucursal E|F;Sucursal F|G;Sucursal G|H;Sucursal H|I;Sucursal I");
//Excluimos a la de la latencia maxima E, y la segunda latencia maxima la D
        assertOk(s.sucursalesParaTorneo("A", 18), 14,//es la distancia de A a I
                "A;Sucursal A|B;Sucursal B|C;Sucursal C|F;Sucursal F|G;Sucursal G|H;Sucursal H|I;Sucursal I");
        assertOk(s.sucursalesParaTorneo("A", 10), 9,//es la distancia de A a G
                "A;Sucursal A|B;Sucursal B|G;Sucursal G|H;Sucursal H");
//Si ponemos distancia 1, como no hay ninguna arista de valor uno nos queda el vertice A.
        assertOk(s.sucursalesParaTorneo("A", 1), 0,//es la distancia de A a A
                "A;Sucursal A");


    }

    @Test
    public void testOk2() {
        //El grafo en cuestion
        //https://dreampuf.github.io/GraphvizOnline/#graph%20G%7B%0AV_0%5Blabel%3D%22I%3BSucursal%20I%22%5D%3B%0AV_1%5Blabel%3D%22H%3BSucursal%20H%22%5D%3B%0AV_2%5Blabel%3D%22J%3BSucursal%20J%22%5D%3B%0AV_3%5Blabel%3D%22K%3BSucursal%20K%22%5D%3B%0AV_4%5Blabel%3D%22L%3BSucursal%20L%22%5D%3B%0AV_5%5Blabel%3D%22F%3BSucursal%20F%22%5D%3B%0AV_6%5Blabel%3D%22B%3BSucursal%20B%22%5D%3B%0AV_7%5Blabel%3D%22D%3BSucursal%20D%22%5D%3B%0AV_8%5Blabel%3D%22A%3BSucursal%20A%22%5D%3B%0AV_9%5Blabel%3D%22G%3BSucursal%20G%22%5D%3B%0AV_10%5Blabel%3D%22E%3BSucursal%20E%22%5D%3B%0AV_11%5Blabel%3D%22C%3BSucursal%20C%22%5D%3B%0AV_3--V_2%5Blabel%3D%221%22%5D%3B%0AV_4--V_3%5Blabel%3D%221%22%5D%3B%0AV_7--V_5%5Blabel%3D%228%22%5D%3B%0AV_7--V_6%5Blabel%3D%227%22%5D%3B%0AV_8--V_6%5Blabel%3D%229%22%5D%3B%0AV_10--V_5%5Blabel%3D%222%22%5D%3B%0AV_10--V_6%5Blabel%3D%223%22%5D%3B%0AV_10--V_7%5Blabel%3D%224%22%5D%3B%0AV_11--V_6%5Blabel%3D%222%22%5D%3B%0AV_11--V_7%5Blabel%3D%221%22%5D%3B%0AV_11--V_8%5Blabel%3D%224%22%5D%3B%0AV_11--V_10%5Blabel%3D%226%22%5D%3B%0A%7D%0A        s.registrarConexion("A", "B", 9);
        s.registrarConexion("A", "C", 4);
        s.registrarConexion("B", "C", 2);
        s.registrarConexion("B", "D", 7);
        s.registrarConexion("B", "E", 3);
        s.registrarConexion("C", "D", 1);
        s.registrarConexion("C", "E", 6);
        s.registrarConexion("D", "E", 4);
        s.registrarConexion("D", "F", 8);
        s.registrarConexion("E", "F", 2);

        //Con una distancia mayor o igual a la maxima esperamos todos los vertices
        assertOk(s.sucursalesParaTorneo("A", 11), 11,
                "A;Sucursal A|B;Sucursal B|C;Sucursal C|D;Sucursal D|E;Sucursal E|F;Sucursal F");

        //Empezamos a bajar la latencia maxima
        //9 es la distancia al vertice E
        assertOk(s.sucursalesParaTorneo("A", 10), 9,
                "A;Sucursal A|B;Sucursal B|C;Sucursal C|D;Sucursal D|E;Sucursal E");
//5 es la distancia al vertice D
        assertOk(s.sucursalesParaTorneo("A", 5), 5,
                "A;Sucursal A|C;Sucursal C|D;Sucursal D");
    }

    @Test
    public void testOk3() {
        //El grafo en cuestion
        //https://dreampuf.github.io/GraphvizOnline/#graph%20G%7B%0AV_0%5Blabel%3D%22I%3BSucursal%20I%22%5D%3B%0AV_1%5Blabel%3D%22H%3BSucursal%20H%22%5D%3B%0AV_2%5Blabel%3D%22J%3BSucursal%20J%22%5D%3B%0AV_3%5Blabel%3D%22K%3BSucursal%20K%22%5D%3B%0AV_4%5Blabel%3D%22L%3BSucursal%20L%22%5D%3B%0AV_5%5Blabel%3D%22F%3BSucursal%20F%22%5D%3B%0AV_6%5Blabel%3D%22B%3BSucursal%20B%22%5D%3B%0AV_7%5Blabel%3D%22D%3BSucursal%20D%22%5D%3B%0AV_8%5Blabel%3D%22A%3BSucursal%20A%22%5D%3B%0AV_9%5Blabel%3D%22G%3BSucursal%20G%22%5D%3B%0AV_10%5Blabel%3D%22E%3BSucursal%20E%22%5D%3B%0AV_11%5Blabel%3D%22C%3BSucursal%20C%22%5D%3B%0AV_3--V_2%5Blabel%3D%221%22%5D%3B%0AV_4--V_3%5Blabel%3D%221%22%5D%3B%0AV_7--V_5%5Blabel%3D%2215%22%5D%3B%0AV_7--V_6%5Blabel%3D%225%22%5D%3B%0AV_8--V_6%5Blabel%3D%222%22%5D%3B%0AV_9--V_5%5Blabel%3D%226%22%5D%3B%0AV_10--V_7%5Blabel%3D%2210%22%5D%3B%0AV_10--V_9%5Blabel%3D%222%22%5D%3B%0AV_11--V_7%5Blabel%3D%228%22%5D%3B%0AV_11--V_8%5Blabel%3D%226%22%5D%3B%0A%7D%0A
        assertOk(s.registrarConexion("A", "B", 2));
        assertOk(s.registrarConexion("A", "C", 6));
        assertOk(s.registrarConexion("B", "D", 5));
        assertOk(s.registrarConexion("C", "D", 8));

        assertOk(s.registrarConexion("D", "E", 10));
        assertOk(s.registrarConexion("D", "F", 15));

        assertOk(s.registrarConexion("G", "E", 2));
        assertOk(s.registrarConexion("G", "F", 6));


        // la distancia maxima es al vertice F
        assertOk(s.sucursalesParaTorneo("A", 22), 22,
                "A;Sucursal A|B;Sucursal B|C;Sucursal C|D;Sucursal D|E;Sucursal E|F;Sucursal F|G;Sucursal G");

        assertOk(s.sucursalesParaTorneo("A", 15), 7,
                "A;Sucursal A|B;Sucursal B|C;Sucursal C|D;Sucursal D");

        //Pruebo actualizar los datos de la conexion para ver que funcione
        assertOk(s.actualizarConexion("A","C",1));
        assertOk(s.actualizarConexion("C","D",3));
        //ahora llegar a D nos cuesta 4, en vez de 7
        //el vertice E que antes costaba 17, ahora va a costar 17-3=14
        assertOk(s.sucursalesParaTorneo("A", 15), 14,
                "A;Sucursal A|B;Sucursal B|C;Sucursal C|D;Sucursal D|E;Sucursal E");
        assertOk(s.sucursalesParaTorneo("A", 12), 4,
                "A;Sucursal A|B;Sucursal B|C;Sucursal C|D;Sucursal D");
    }

    @Test
    public void testError1() {
        //1. Si el código de la sucursal anfitriona es vacío o null.
        assertError1(s.sucursalesParaTorneo(null, 230));
        assertError1(s.sucursalesParaTorneo("", 230));
    }

    @Test
    public void testError2() {

        //2. Si no existe el código de la sucursal anfitriona.
        assertOk(s.registrarSucursal("COD", "Nom"));
        assertError2(s.sucursalesParaTorneo("COD2", 230));
    }

    @Test
    public void testError3() {

        //3. Si la latencia es menor o igual a cero.
        assertOk(s.registrarSucursal("COD", "Nom"));
        assertError3(s.sucursalesParaTorneo("COD", 0));
        assertError3(s.sucursalesParaTorneo("COD", -20));
    }
}
