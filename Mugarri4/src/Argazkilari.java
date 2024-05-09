import java.sql.*;
public class Argazkilari {
    private int idArgazkilari;
    private String izena;
    private boolean saritua;

    public Argazkilari(int idArgazkilari, String izena, boolean saritua) {
        this.idArgazkilari = idArgazkilari;
        this.izena = izena;
        this.saritua = saritua;
    }

    @Override
    public String toString() {
        return this.izena;
    }

    public int getIdArgazkilari() {
        return idArgazkilari;
    }

    public String getIzena() {
        return izena;
    }

    public boolean isSaritua() {
        return saritua;
    }

}
