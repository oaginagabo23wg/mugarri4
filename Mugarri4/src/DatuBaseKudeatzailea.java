import java.sql.*;
import java.util.ArrayList;

public class DatuBaseKudeatzailea {
    static final String url = "jdbc:mysql://localhost/Mugarri4";
    static final String erab = "root";
    static final String pasahitza = "zubiri";
    private Connection conn;
    public DatuBaseKudeatzailea() {
        try {
            conn = DriverManager.getConnection(url, erab, pasahitza);
            System.out.println("Ongi konektatu da!");
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void itxi() {
        try {
            conn.close();
            System.out.println("Datu-basetik deskonektatuta.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Argazkilari[] argazkilariakLortu(){

        try {

            String sql = "SELECT * FROM argazkilari;";
            Statement kontsulta = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = kontsulta.executeQuery(sql);
            int kont = 0;
            while (rs.next()){
                kont++;
            }
            Argazkilari[] argazkilariak = new Argazkilari[kont];
            rs.beforeFirst();
            int i=0;
            while (rs.next()){
                Argazkilari argazkilari = new Argazkilari(rs.getInt(1),rs.getString(2),rs.getBoolean(3));
                argazkilariak[i]=argazkilari;
                i++;
            }
            return argazkilariak;
        } catch (SQLException e) {
            System.out.println("Konektatzerakoan errorea: " + e.getMessage());
            return null;
        }
    }
    public ArrayList<Argazki> argazkiakLortu(){
        ArrayList<Argazki> argazkiak = new ArrayList<>();
        try {

            String sql = "SELECT * FROM argazkiak;";
            Statement kontsulta = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = kontsulta.executeQuery(sql);
            while (rs.next()){
                Argazki argazki = new Argazki(rs.getInt(1),rs.getString(2),rs.getDate(3), rs.getString(4),rs.getInt(5),rs.getInt(6) );
                argazkiak.add(argazki);
            }
            return argazkiak;
        } catch (SQLException e) {
            System.out.println("Konektatzerakoan errorea: " + e.getMessage());
            return null;
        }
    }
}
