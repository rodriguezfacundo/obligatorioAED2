package dominio.Auxiliares;

public class ObjetoAuxiliar {
    private long valorInt;
    private String valorString;



    public ObjetoAuxiliar(long valorInt, String valorString) {
        this.valorInt = valorInt;
        this.valorString = valorString;
    }

    public ObjetoAuxiliar() {
        this.valorInt=-1;
        this.valorString="";
    }

    public long getValorInt() {
        return valorInt;
    }

    public void setValorInt(long valorInt) {
        this.valorInt = valorInt;
    }

    public String getValorString() {
        return valorString;
    }

    public void setValorString(String valorString) {
        this.valorString = valorString;
    }
}
