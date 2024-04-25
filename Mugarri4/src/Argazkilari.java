import java.sql.*;
public class Argazkilari {

    public Argazkilari() {
    }
    public String[] argazkilariakLortu(){
        String url = "jdbc:mysql://localhost/Mugarri4";
        String erab = "root";
        String pasahitza = "zubiri";
        String[] argazkilariak = new String[0];
        try {
            Connection conn = DriverManager.getConnection(url, erab, pasahitza);
            Statement kontsulta = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println("Ongi konektatu da!");
            String sql = "SELECT izena FROM argazkilari;";
            ResultSet rs = kontsulta.executeQuery(sql);
            int kont=0;
            while (rs.next()){
                kont++;
            }
            rs.beforeFirst();
            argazkilariak = new String[kont];
            kont=0;
            while (rs.next()){
                argazkilariak[kont] = rs.getString("izena");
                kont++;
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Konektatzerakoan errorea: " + e.getMessage());
        }
        return argazkilariak;
    }
}
