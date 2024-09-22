package dominio.Grafo.Estructura;


import dominio.Lista.Lista;

public class Arista {

    private boolean existe;
    //private Lista<Conexion> conexiones;

    public Arista() {
        this.existe = false;
        //this.conexiones = new Lista<Conexion>();
    }



    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    /*public Lista<Conexion> getConexiones() {
        return conexiones;
    }

    public void setConexiones(Lista<Conexion> conexiones) {
        this.conexiones = conexiones;
    }*/
}
