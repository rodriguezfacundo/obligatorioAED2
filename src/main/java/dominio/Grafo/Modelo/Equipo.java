package dominio.Grafo.Modelo;

public class Equipo {
    private String nombre;
    private String mangaer;

    public Equipo(String nombre, String mangaer) {
        this.nombre = nombre;
        this.mangaer = mangaer;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMangaer() {
        return mangaer;
    }

    public void setMangaer(String mangaer) {
        this.mangaer = mangaer;
    }
}
