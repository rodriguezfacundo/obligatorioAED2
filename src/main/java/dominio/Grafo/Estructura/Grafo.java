package dominio.Grafo.Estructura;

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

    /*public Ciudad obtenerCiudadPorCodigo(String codigo){
        if(codigo==null) return  null;
        Ciudad aBuscar = new Ciudad(codigo,"");
        for (int i = 0; i < tope; i++) {
            if(vertices[i]!=null){
                if(vertices[i].equals(aBuscar)){
                    return vertices[i];
                }
            }
        }
        return null;
    }
    */
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
            aristas[posDestino][posOrigen].setExiste(true);
            aristas[posOrigen][posDestino].getConexiones().agregarInicio(conexion);
            Conexion conexionInvertida = new Conexion(destino.getCodigo(), origen.getCodigo(), conexion.getLatencia());
            aristas[posDestino][posOrigen].getConexiones().agregarInicio(conexionInvertida);
        }
    }
    public void actualizarConexion(Sucursal origen, Sucursal destino, Conexion conexion) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        aristas[posOrigen][posDestino].getConexiones().obtenerElementoPorDato(conexion).actualizarse(conexion);
    }

    /*public Lista<Sucursal> consulta10(Sucursal origen, int trasbordos){
        Lista<Sucursal> resultado = new Lista<>();
        if (origen==null) return resultado;
        resultado.agregarOrdenado(origen);
        if(trasbordos==0) return  resultado;
        Cola<Tupla> cola = new Cola<>();
        int inicio= obtenerPos(origen);

        boolean[]visitados = new boolean[tope];
        cola.encolar(new Tupla(origen,0));
        visitados[inicio]=true;
        while(!cola.esVacia() ){
            Tupla tupla = cola.desencolar();
            int pos = obtenerPos(tupla.getCiudad());
            for(int j=0;j<tope;j++){
                if(matAdy[pos][j].isExiste() && !visitados[j] && trasbordos>tupla.getNivel()){
                    Ciudad aEncolar = vertices[j];
                    cola.encolar(new Tupla(aEncolar,tupla.getNivel()+1));
                    visitados[j]=true;
                    resultado.agregarOrdenado(aEncolar);
                }
            }
        }
        return  resultado;
    }
    public ObjetoAuxiliar consulta11(Ciudad origen, Ciudad destino){
        ObjetoAuxiliar ret = new ObjetoAuxiliar();
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        Conexion conexionMenorTiempo;

        boolean[] visitsados =new boolean[tope];
        Ciudad[] anterior =new Ciudad[tope];
        double[] costos =new double[tope];
        Conexion[] conexiones= new Conexion[tope];

        for (int i = 0; i <tope ; i++) {
            costos[i]=Double.MAX_VALUE;
        }

        costos[posOrigen]=0;

        for (int i = 0; i < cantidad; i++) {
            int pos = obtenerVerticeNoVisitadoDeMenoCosto(costos,visitsados);
            if(pos!=-1){
                visitsados[pos]=true;

                for (int j = 0; j < tope; j++) {
                    if(matAdy[pos][j].isExiste()&& !visitsados[j]){
                        conexionMenorTiempo = getMenorPeso(matAdy[pos][j].getConexiones());
                        double costoNuevo = costos[pos];
                        costoNuevo = costoNuevo + (int) conexionMenorTiempo.getTiempo();
                        if (costoNuevo < costos[j]) {
                            costos[j] = costoNuevo;
                            anterior[j] = vertices[pos];
                            conexiones[j]=conexionMenorTiempo;
                        }

                    }
                }
            }
        }
        if (!visitsados[posDestino]) {
            ret.setValorString("No existe conexión entre las ciudades");
            ret.setValorInt(0);
            return ret;
        }

        String camino =  "";
        Ciudad ciudadActual=null;
        int pos = posDestino;
        while (anterior[pos] != null) {
            ciudadActual = vertices[pos];
            camino = "|" + conexiones[pos].getTipo() + "|" + ciudadActual.toString() + camino;
            pos=obtenerPos(anterior[pos]);
        }
        camino = vertices[posOrigen].toString()+ camino;
        ret.setValorString(camino);
        ret.setValorInt((int)costos[posDestino]);
        return  ret;
    }


    public Conexion getMenorPeso(Lista<Conexion> lista){
        double minimo = Double.MAX_VALUE;
        NodoLista<Conexion> aux = lista.getInicio();
        Conexion retorno =null;
        while(aux!=null){
            Conexion conexion = aux.getDato();
            if(conexion.getTiempo()<minimo){
                minimo=conexion.getTiempo();
                retorno=aux.getDato();
            }
            aux=aux.getSiguiente();
        }
        return  retorno;
    }

    private int obtenerVerticeNoVisitadoDeMenoCosto(double[]costos,boolean[]visitados){
        int posMin = -1;
        double min=Double.MAX_VALUE;
        for (int i = 0; i < tope; i++) {
            if(!visitados[i] && costos[i]<min){
                min=costos[i];
                posMin=i;
            }
        }
        return posMin;
    }*/


}
