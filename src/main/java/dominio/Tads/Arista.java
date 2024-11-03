package dominio.Tads;


import dominio.Entidades.Conexion;

public class Arista {
    private boolean existe;
    private Lista<Conexion> conexiones;

    public Arista() {
        this.existe = false;
        this.conexiones = new Lista<>();
    }



    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public Lista<Conexion> getConexiones() {
        return conexiones;
    }

    public void setConexiones(Lista<Conexion> conexiones) {
        this.conexiones = conexiones;
    }
}
