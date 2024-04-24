import java.sql.*;
public class Argazkilari {
    int idArgazkilari;
    String izena;
    boolean saritua;

    public Argazkilari(int idArgazkilari, String izena, boolean saritua) {
        this.idArgazkilari = idArgazkilari;
        this.izena = izena;
        this.saritua = saritua;
    }
}
