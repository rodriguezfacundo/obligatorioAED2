package dominio.Grafo.Estructura;

import dominio.ABB.Nodo;
import dominio.Cola.Cola;
import dominio.Cola.ICola;
import dominio.Cola.NodoCola;
import dominio.Grafo.Modelo.Conexion;
import dominio.Grafo.Modelo.ResultadoTorneo;
import dominio.Grafo.Modelo.Sucursal;
import dominio.Lista.Lista;
import dominio.Lista.NodoLista;

import java.util.*;

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
        if (posV != -1) {
            dfs(posV, visitados);
        }
    }

    private void dfs(int posV, boolean[] visitados) {
        visitados[posV] = true;
        for (int i = 0; i < aristas.length; i++) {
            if (aristas[posV][i].isExiste() && !visitados[i]) {
                dfs(i, visitados);
            }
        }
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
        if (posVert == -1) return false; // Si la sucursal no existe, no es crítica

        // Paso 1: Contar nodos alcanzables inicialmente
        boolean[] visitadosInicial = new boolean[tope];
        int nodosAlcanzadosAntes = contarNodosAlcanzados(buscarNodoInicial(posVert), visitadosInicial);

        // Paso 2: Simular la desconexión de 'vert' y contar nodos alcanzables nuevamente
        boolean[] visitadosExcluyendoVert = new boolean[tope];
        visitadosExcluyendoVert[posVert] = true; // Marca 'vert' como visitado
        int nodosAlcanzadosDespues = contarNodosAlcanzados(buscarNodoInicialExcluyendo(posVert), visitadosExcluyendoVert);

        // Paso 3: Comparar la cantidad de nodos alcanzados
        return nodosAlcanzadosAntes != nodosAlcanzadosDespues; // Si son diferentes, es crítico
    }

    private int contarNodosAlcanzados(int nodoInicial, boolean[] visitados) {
        if (nodoInicial == -1) return 0; // No hay nodo inicial válido
        Stack<Integer> stack = new Stack<>(); // Usamos stack para DFS
        stack.push(nodoInicial);
        visitados[nodoInicial] = true;

        int count = 0;
        while (!stack.isEmpty()) {
            int nodoActual = stack.pop();
            count++; // Contamos el nodo alcanzado
            for (int i = 0; i < tope; i++) {
                if (aristas[nodoActual][i].isExiste() && !visitados[i]) {
                    visitados[i] = true;
                    stack.push(i);
                }
            }
        }
        return count; // Devuelve la cantidad de nodos alcanzados
    }

    private int buscarNodoInicialExcluyendo(int excluir) {
        for (int i = 0; i < tope; i++) {
            if (i != excluir && aristas[i].length > 0) {
                return i;
            }
        }
        return -1; // No se encontró un nodo de inicio adecuado
    }

    private int buscarNodoInicial(int excluir) {
        for (int i = 0; i < tope; i++) {
            if (i != excluir && vertices[i] != null && hayConexion(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean hayConexion(int nodo) {
        for (int i = 0; i < tope; i++) {
            if (i != nodo && aristas[nodo][i].isExiste()) {
                return true;
            }
        }
        return false;
    }
    //-----------------------------------------------------

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
