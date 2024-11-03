package dominio.Tads;

import dominio.Entidades.Conexion;
import dominio.Entidades.ResultadoTorneo;
import dominio.Entidades.Sucursal;

public class Grafo {
    private int cantidad;
    private int tope;
    private Sucursal[] vertices;
    private Arista[][] aristas;
    private final boolean dirigido;

    public Grafo(int cantMaxDeVertices, boolean esDirigido) {
        cantidad = 0;
        tope = cantMaxDeVertices;
        vertices = new Sucursal[tope];
        aristas = new Arista[tope][tope];
        dirigido = esDirigido;

        if (dirigido) {
            for (int i = 0; i < aristas.length; i++) {
                for (int j = 0; j < aristas.length; j++) {
                    aristas[i][j] = new Arista();
                }
            }
        } else {
            for (int i = 0; i < aristas.length; i++) {
                for (int j = 0; j < aristas.length; j++) {
                    Arista a = new Arista();
                    aristas[i][j] = a;
                    aristas[j][i] = a;
                }
            }
        }
    }

    public boolean esLleno() {
        return cantidad == tope;
    }

    private int obtenerPosLibre() {
        for (int i = 0; i < tope; i++) {
            if(vertices[i] == null){
                return i;
            }
        }
        return -1;
    }

    private int obtenerPos(Sucursal sucursal) {
        for (int i = 0; i < tope; i++) {
            if(vertices[i]!=null){
                if(vertices[i].equals(sucursal)){
                    return i;
                }
            }
        }
        return -1;
    }

    public void agregarSucursal(Sucursal sucursal) {
        if(cantidad < tope){
            int pos = obtenerPosLibre();
            if(pos!=-1) {
                vertices[pos] = sucursal;
                cantidad++;
            }
        }
    }
    public boolean existeSucursal(Sucursal sucursal) {
        return obtenerPos(sucursal) != -1;
    }

    public boolean yaExisteConexion(Sucursal origen, Sucursal destino, Conexion conexion){
        int posicionOrigen = obtenerPos(origen);
        int posicionDestino = obtenerPos(destino);
        Arista arista = this.aristas[posicionOrigen][posicionDestino];
        if(!arista.isExiste()) return false;
        return arista.getConexiones().existeElementoPorDato(conexion);
    }

    public void agregarConexion(Sucursal origen, Sucursal destino, Conexion conexion) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        if(this.dirigido){
            aristas[posDestino][posOrigen].setExiste(true);
            aristas[posOrigen][posDestino].getConexiones().agregarInicio(conexion);
        } else{
            aristas[posOrigen][posDestino].setExiste(true);
            aristas[posOrigen][posDestino].getConexiones().agregarInicio(conexion);

            Conexion conexionInvertida = new Conexion(destino.getCodigo(), origen.getCodigo(), conexion.getLatencia());
            aristas[posDestino][posOrigen].setExiste(true);
            aristas[posDestino][posOrigen].getConexiones().agregarInicio(conexionInvertida);
        }
    }
    public void actualizarConexion(Sucursal origen, Sucursal destino, Conexion conexion) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        aristas[posOrigen][posDestino].getConexiones().obtenerElementoPorDato(conexion).actualizarse(conexion);
        Conexion conexionInvertida = new Conexion(destino.getCodigo(), origen.getCodigo(), conexion.getLatencia());
        aristas[posDestino][posOrigen].getConexiones().obtenerElementoPorDato(conexionInvertida).actualizarse(conexionInvertida);

    }

    public void dfs(Sucursal vert) {
        boolean[] visitados = new boolean[tope];
        int posV = obtenerPos(vert);
        dfs(posV, visitados, aristas);
    }

    private void dfs(int posV, boolean[] visitados, Arista[][] aristas) {
        System.out.print(vertices[posV] + " ");
        visitados[posV] = true;
        for (int i = 0; i < aristas.length; i++) {
            if (aristas[posV][i].isExiste() && !visitados[i]) {
                dfs(i, visitados, aristas);
            }
        }
        System.out.println();
    }

    public boolean esPuntoCritico(Sucursal sucursal) {
        int posV = obtenerPos(sucursal);
        boolean[] visitadosOriginales = new boolean[tope];
        // Realizamos DFS desde el vértice dado y guardamos los visitados
        dfs(posV, visitadosOriginales, aristas);
        // Contamos los vértices visitados
        int contadorVisitados = 0;
        for (boolean visitado : visitadosOriginales) {
            if (visitado) {
                contadorVisitados++;
            }
        }
        // Si solo hay un vértice visitado, significa que no hay conexiones, por ende no es critico
        if (contadorVisitados <= 1) {
            return false;
        }
        // Buscamos el primer vértice visitado diferente a posV
        int primerVisitado = -1;
        for (int i = 0; i < visitadosOriginales.length; i++) {
            if (visitadosOriginales[i] && i != posV) {
                primerVisitado = i;
                break;
            }
        }
        // Realizamos otro DFS excluyendo el vértice sucursal dada
        boolean[] visitadosSinVert = new boolean[tope];
        visitadosSinVert[posV] = true; // Marcamos el vértice vert como visitado
        // Hacemos DFS desde el primer vértice visitado
        dfs(primerVisitado, visitadosSinVert, aristas);
        // Comparamos los arrays de visitados, donde si hay diferencias es porque la sucursal dada es critica.
        for (int i = 0; i < visitadosOriginales.length; i++) {
            if (visitadosOriginales[i] != visitadosSinVert[i]) {
                return true;
            }
        }
        return false; //si no hubieron diferencia entres los visitados entonces no es critico
    }

    public ResultadoTorneo sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        boolean[] visitados = new boolean[tope];
        int[] costos = new int[tope];
        int[] vengo = new int[tope];
        for (int i = 0; i < tope; i++) {
            costos[i] = Integer.MAX_VALUE;
            vengo[i] = -1;
            visitados[i] = false;
        }
        int posVorigen = obtenerPos(new Sucursal(codigoSucursalAnfitriona, ""));
        costos[posVorigen] = 0;
        for (int i = 0; i < cantidad; i++) {
            int pos = obtenerSiguienteVerticeNoVisitadoDeMenorCosto(costos, visitados);
            if (pos != -1) {
                visitados[pos] = true;
                for (int j = 0; j < tope; j++) {
                    if (aristas[pos][j].isExiste() && !visitados[j]) {
                        NodoLista<Conexion> nodoConexion = aristas[pos][j].getConexiones().obtenerPorIndice(0);
                        Conexion conexion = nodoConexion.getDato();
                        int latencia = conexion.getLatencia();
                        int costoNuevo = costos[pos] + latencia;
                        if (costos[j] > costoNuevo) {
                            costos[j] = costoNuevo;
                            vengo[j] = pos;
                        }
                    }
                }
            }
        }
        int latenciaMaxima = Integer.MIN_VALUE;
        Lista<Sucursal> resultado = new Lista<>();
        // Verificar y agregar la sucursal anfitriona si cumple con la latencia
        if (vertices[posVorigen] != null && costos[posVorigen] <= latenciaLimite) {
            resultado.agregarInicio(vertices[posVorigen]);
            latenciaMaxima = Math.max(latenciaMaxima, costos[posVorigen]);
        }
        // Revisar otras sucursales
        for (int i = 0; i < tope; i++) {
            if (i != posVorigen && costos[i] <= latenciaLimite && vertices[i] != null) {
                resultado.agregarOrdenado(vertices[i]);
                latenciaMaxima = Math.max(latenciaMaxima, costos[i]);
            }
        }
        // Si no se encontraron sucursales válidas, establecer latenciaMaxima a 0
        if (latenciaMaxima == Integer.MIN_VALUE) {
            latenciaMaxima = 0;
        }
        StringBuilder resultadoStr = new StringBuilder();
        NodoLista<Sucursal> actual = resultado.getInicio();
        while (actual != null) {
            if (resultadoStr.length() > 0) {
                resultadoStr.append("|");
            }
            resultadoStr.append(actual.getDato().getCodigo()).append(";").append(actual.getDato().getNombre());
            actual = actual.getSiguiente();
        }
        ResultadoTorneo resultadoTorneo = new ResultadoTorneo(resultadoStr.toString(), latenciaMaxima);
        return resultadoTorneo;
    }

    private int obtenerSiguienteVerticeNoVisitadoDeMenorCosto(int[] costos, boolean[] visitados) {
        int posMin = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < tope; i++) {
            if (!visitados[i] && costos[i] < min) {
                min = costos[i];
                posMin = i;
            }
        }
        return posMin;
    }

}
