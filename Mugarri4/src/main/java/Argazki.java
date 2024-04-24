import java.sql.Date;

public class Argazki {
    private int idArgazki;
    private   String izenburua;
    private Date data;
    private  String fitxategia;
    private  int bistaratzeKop;
    private   int idArgazkilari;

    public Argazki(int idArgazki, String izenburua, Date data, String fitxategia, int bistaratzeKop, int idArgazkilari) {
        this.idArgazki = idArgazki;
        this.izenburua = izenburua;
        this.data = data;
        this.fitxategia = fitxategia;
        this.bistaratzeKop = bistaratzeKop;
        this.idArgazkilari = idArgazkilari;
    }
}
