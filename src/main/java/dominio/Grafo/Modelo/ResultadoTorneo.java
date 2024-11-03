package dominio.Grafo.Modelo;

public class ResultadoTorneo {
    String resultadoTorneo;
    int latenciaMaxima;

    public ResultadoTorneo(String resultado, int latencia){
        this.resultadoTorneo = resultado;
        this.latenciaMaxima = latencia;
    }

    public String getResultadoTorneo() {
        return resultadoTorneo;
    }

    public int getLatenciaMaxima() {
        return latenciaMaxima;
    }
}
