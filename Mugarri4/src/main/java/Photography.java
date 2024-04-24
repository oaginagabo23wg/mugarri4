import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class Photography extends JFrame {

    static final String DB_IP = "localhost";
    static final String DB_IZENA = "ikasledb";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://" + DB_IP + ":3306/" + DB_IZENA;

    // Datu basearen kredentzialak
    static final String ERABILTZAILE = "root";
    static final String PASAHITZA = "zubiri";

    private Connection conn;
    public void PhotographySortu()  {
        try
        {
            Class.forName(JDBC_DRIVER);
            System.out.println("Datu-basera konektatzen...");
            conn = DriverManager.getConnection(DB_URL, ERABILTZAILE, PASAHITZA);
            System.out.println("Konektatuta.");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,500));
        setLayout(new GridLayout(2,2));

        JPanel panel1 = new JPanel();
        add(panel1);
        JLabel photographer = new JLabel("Photographer");
        panel1.add(photographer);
        JComboBox<Argazkilari> photographerBox = new JComboBox<>();
        panel1.add(photographerBox);

        JPanel panel2 = new JPanel();
        add(panel2);
        JLabel photos = new JLabel("Photos after");
        panel2.add(photos);
        JComboBox<String> photosBox = new JComboBox<>();
        panel2.add(photosBox);

        JPanel panel3 = new JPanel();
        add(panel3);
        String[] lista = {"asd","asd","asd"};
        JList<String> jList = new JList<>(lista);
        JScrollPane scrollPane = new JScrollPane(jList);
        panel3.add(scrollPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(200,200));

        JPanel panel4 = new JPanel();
        add(panel4);
        JLabel irudia = new JLabel("irudia");
        panel4.add(irudia);

        pack();
        setVisible(true);
    }
    public static void main(String[] args) {
        new Photography().PhotographySortu();
    }
}
