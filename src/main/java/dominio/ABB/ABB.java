package dominio.ABB;

import dominio.Grafo.Estructura.ObjetoAuxiliar;

public class ABB<T extends Comparable<T>> implements Comparable {

    private Nodo<T> raiz;

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
            ObjectoCantidadAuxiliar ret = new ObjectoCantidadAuxiliar(nodo.getDato(),cantidad);
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

    public ObjetoAuxiliar obtenerDato(T buscado){
        return obtenerDato(buscado, raiz);
    }
    private ObjetoAuxiliar obtenerDato(T buscado , Nodo<T> nodo){
        if(nodo != null){
            if(nodo.getDato().equals(buscado)){
                 return new ObjetoAuxiliar(1,nodo.getDato().toString());
            } else if (nodo.getDato().compareTo(buscado) > 0) {
                ObjetoAuxiliar ret= obtenerDato(buscado,nodo.getIzq());
                ret.setValorInt(ret.getValorInt()+1);
            } else if (nodo.getDato().compareTo(buscado) < 0 ) {
                ObjetoAuxiliar ret= obtenerDato(buscado,nodo.getDer());
                ret.setValorInt(ret.getValorInt()+1);
                return ret;
            }
        }
        return null;
    }
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
