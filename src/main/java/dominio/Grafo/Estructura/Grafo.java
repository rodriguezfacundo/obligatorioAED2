package dominio.Grafo.Estructura;

import dominio.Cola.Cola;
import dominio.Cola.ICola;
import dominio.Grafo.Modelo.Conexion;
import dominio.Grafo.Modelo.Sucursal;

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

    public void bfs(Sucursal vert) {
        int posV = obtenerPos(vert);
        boolean[] visitados = new boolean[tope];
        visitados[posV] = true;
        ICola<Integer> cola = new Cola<>();
        cola.encolar(posV);
        while (!cola.estaVacia()) {
            int posDesencolada = cola.desencolar();
            System.out.print(vertices[posDesencolada] + " ");
            for (int i = 0; i < aristas.length; i++) {
                if (aristas[posDesencolada][i].isExiste() && !visitados[i]) {
                    visitados[i] = true;
                    cola.encolar(i);
                }
            }
        }
    }

    public boolean esPuntoCritico(Sucursal vert) {
        int posVert = obtenerPos(vert);
        if (posVert == -1) return false; // Si no existe, no es crítica

        boolean[] visitadosInicial = new boolean[tope];
        int nodoInicio = (posVert == 0) ? 1 : 0; // Elegir un nodo de inicio distinto al evaluado
        dfs(nodoInicio, visitadosInicial, aristas);

        // Verificar si todos los nodos son alcanzables
        for (int i = 0; i < visitadosInicial.length; i++) {
            if (i != posVert && !visitadosInicial[i]) {
                return false;
            }
        }

        // Ejecutar DFS excluyendo el nodo 'vert'
        boolean[] visitadosExcluyendoVert = new boolean[tope];
        visitadosExcluyendoVert[posVert] = true; // Marcar 'vert' como visitado para simular su desconexión
        dfs(nodoInicio, visitadosExcluyendoVert, aristas);

        // Comparar los resultados de ambas ejecuciones de DFS
        for (int i = 0; i < visitadosInicial.length; i++) {
            if (visitadosInicial[i] != visitadosExcluyendoVert[i]) {
                return true; // Si hay diferencias, 'vert' es crítico
            }
        }

        return false; // Si no hay diferencias, 'vert' no es crítico
    }

    private boolean hayConexion(int nodo) {
        // Verifica si el nodo tiene al menos una conexión a otro nodo en la matriz de adyacencia
        for (int i = 0; i < tope; i++) {
            if (i != nodo && aristas[nodo][i].isExiste()) {
                return true; // Si existe una conexión, retorna true
            }
        }
        return false; // Si no se encontró ninguna conexión, retorna false
    }


}
