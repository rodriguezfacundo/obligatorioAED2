package dominio.ABB;

public class ABB<T extends Comparable<T>> implements Comparable {

    private Nodo<T> raiz;

    public boolean estaVacio() {
        return raiz == null;
    }

    public boolean existeDato(T dato) {
        return existeRec(raiz, dato);
    }

    private boolean existeRec(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return false;
        } else if (nodo.getDato().equals(dato)) {
            return true;
        } else {
            if (dato.compareTo(nodo.getDato()) > 0) {
                return existeRec(nodo.getDer(), dato);
            } else {
                return existeRec(nodo.getIzq(), dato);
            }
        }
    }
    public ObjectoCantidadAuxiliar buscarDatoMasCantidadRecorridas(T dato) {
        return buscarDatoMasCantidadRecorridasREC(raiz, dato,0);
    }
    private ObjectoCantidadAuxiliar buscarDatoMasCantidadRecorridasREC(Nodo<T> nodo, T dato, int cantidad) {
        if (nodo == null) {
            return null;
        } else if (nodo.getDato().equals(dato)) {
            ObjectoCantidadAuxiliar ret = new ObjectoCantidadAuxiliar(nodo.getDato(),cantidad+1);
            return ret;
        } else {
            if (dato.compareTo(nodo.getDato()) > 0) {
                return buscarDatoMasCantidadRecorridasREC(nodo.getDer(), dato,cantidad+1);
            } else {
                return buscarDatoMasCantidadRecorridasREC(nodo.getIzq(), dato,cantidad+1);
            }
        }
    }

    public void agregarDato(T dato) {
        if (this.raiz == null) {
            this.raiz = new Nodo<T>(dato);
        } else {
            agregarDatoREC(raiz, dato);
        }
    }

    private void agregarDatoREC(Nodo<T> nodo, T dato) {
        if (dato.compareTo(nodo.getDato()) > 0) {
            if (nodo.getDer() == null) {
                nodo.setDer(new Nodo<T>(dato));
            } else {
                agregarDatoREC(nodo.getDer(), dato);
            }
        } else {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new Nodo<T>(dato));
            } else {
                agregarDatoREC(nodo.getIzq(), dato);
            }
        }
    }

    public String recorrerDescendenteLlamada(){
        String resultado =  recorrerDescendenteREC(this.raiz);
        if (resultado.startsWith("|")) {
            resultado = resultado.substring(1);
        }
        return resultado;
    }
    private String recorrerDescendenteREC(Nodo<T> nodo){
        if(nodo!=null){

            return recorrerDescendenteREC(nodo.getDer()) + "|"+nodo.getDato().toString() + recorrerDescendenteREC(nodo.getIzq());
        }
        return "";
    }

    public String recorrerAscendenteLlamada(){
        String resultado =  recorrerAscendenteREC(this.raiz);
        if (resultado.startsWith("|")) {
            resultado = resultado.substring(1);
        }
        return resultado;
    }
    private String recorrerAscendenteREC(Nodo<T> nodo){
        if(nodo!=null){
            return recorrerAscendenteREC(nodo.getIzq())+"|" +nodo.getDato()+ recorrerAscendenteREC(nodo.getDer());
        }
        return "";
    }

    public int cantNodos() {
        return cantNodosRec(raiz);
    }

    private int cantNodosRec(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return 1 + cantNodosRec(nodo.getIzq()) + cantNodosRec(nodo.getDer());
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
