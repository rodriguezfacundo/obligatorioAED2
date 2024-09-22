package dominio.Lista;

public class Lista<T extends  Comparable> {
    private NodoLista<T> inicio;
    private int cantidad;


    public Lista(NodoLista<T>  inicio) {
        this.inicio = inicio;
        cantidad=0;
    }

    public Lista() {
    }

    public NodoLista<T>  getInicio() {
        return inicio;
    }

    public boolean esVacia() {
        return inicio == null;
    }


    public void agregarInicio(T n) {
            NodoLista<T>  nuevo = new NodoLista<T>(n);
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
            cantidad++;
    }

    public NodoLista obtenerPorIndice(int indice){
        if(indice<0)return  null;
        int i=0;
        NodoLista<T>  aux = inicio;
        while(i!=indice){
            i++;
            aux=aux.getSiguiente();
        }
        return aux;
    }
    public boolean existeElementoPorDato(T dato){
        if(esVacia() || dato==null)return false;
        if(inicio.getDato().equals(dato)) return true;
        NodoLista<T> aux = inicio;
        while(!aux.getDato().equals(dato)&& aux.getSiguiente()!=null){
            aux=aux.getSiguiente();
        }
        return aux.getDato().equals(dato);
    }
    public T obtenerElementoPorDato(T dato){
        if(esVacia() || dato==null)return null;
        if(inicio.getDato().equals(dato)) return inicio.getDato();
        NodoLista<T> aux = inicio;
        while(!aux.getDato().equals(dato) && aux!=null){
            aux=aux.getSiguiente();
        }
        if(aux==null)return null;
        return aux.getDato();
    }
    public void agregarOrdenado(T dato){
        if(dato!=null){
            if(esVacia() || inicio.getDato().compareTo(dato)>=0){
                agregarInicio(dato);
            }else{
                NodoLista<T> nuevo =  new NodoLista<>(dato);
                NodoLista<T> actual = inicio;
                NodoLista<T> anterior = null;
                while(actual!=null && actual.getDato().compareTo(nuevo.getDato())<0){
                    anterior=actual;
                    actual=actual.getSiguiente();
                }
                anterior.setSiguiente(nuevo);
                nuevo.setSiguiente(actual);
                cantidad++;
            }
        }
    }

    public String obtenerContenidoListaString(){
        String resultado =  obtenerContenidoListaStringREC(inicio,"");
        if (resultado.startsWith("|")) {
            resultado = resultado.substring(1);
        }
        return resultado;
    }
    private String obtenerContenidoListaStringREC(NodoLista<T> aux, String ret){
        if(aux!=null){
            return obtenerContenidoListaStringREC(aux.getSiguiente(),ret+="|"+aux.getDato().toString());
        }
        return ret;
    }

}
