import java.sql.*;

public class Argazki {
    public int idArgazki;
    public   String izenburua;
    public Date data;
    public  String fitxategia;
    public  int bistaratzeKop;
    public   int idArgazkilari;

    public Argazki(int idArgazki, String izenburua, Date data, String fitxategia, int bistaratzeKop, int idArgazkilari) {
        this.idArgazki = idArgazki;
        this.izenburua = izenburua;
        this.data = data;
        this.fitxategia = fitxategia;
        this.bistaratzeKop = bistaratzeKop;
        this.idArgazkilari = idArgazkilari;
    }

}
