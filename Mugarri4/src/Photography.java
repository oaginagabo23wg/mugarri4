import javax.swing.*;
import org.jdesktop.swingx.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Date;
import java.io.File;
import javax.swing.ImageIcon;

public class Photography extends JFrame {
    DefaultListModel<String> listModel;
    Argazki[] nireArgObj = new Argazki[5];
    JLabel irudiaLabel;
    ImageIcon irudi;
    ImageIcon icon;

    public Photography() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(650, 400));
        setLayout(new GridLayout(2, 2));

        JPanel panel1 = new JPanel();
        add(panel1);
        JLabel photographer = new JLabel("Photographer");
        panel1.add(photographer);
        JComboBox<String> photographerBox = new JComboBox<>(new Argazkilari().argazkilariakLortu());
        panel1.add(photographerBox);
        photographerBox.setPreferredSize(new Dimension(120, 30));

        JPanel panel2 = new JPanel();
        add(panel2);
        JLabel photos = new JLabel("Photos after");
        panel2.add(photos);
        JXDatePicker datePicker = new JXDatePicker();
        datePicker.setFormats("yyyy/MM/dd");
        datePicker.setPreferredSize(new Dimension(140, 30));
        panel2.add(datePicker);

        JPanel panel3 = new JPanel();
        add(panel3);


        listModel = new DefaultListModel<>();
        argazkiakLortu("ansealdams");
        JList<String> jList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(jList);
        panel3.add(scrollPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(300, 150));

        JPanel panel4 = new JPanel();
        add(panel4);
        irudiaLabel = new JLabel();
        panel4.add(irudiaLabel);
        irudi = new ImageIcon("./irudiak/ansealdams1.jpg");
        irudiaLabel.setSize(new Dimension(260,200));
        icon = new ImageIcon(irudi.getImage().getScaledInstance(irudiaLabel.getWidth(), irudiaLabel.getHeight(), Image.SCALE_DEFAULT));
        irudiaLabel.setIcon(icon);

        pack();
        setVisible(true);
        photographerBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                argazkiakLortu(photographerBox.getSelectedItem().toString());
            }
        });

        jList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedImage = jList.getSelectedValue();
                if (selectedImage != null) {
                    irudiaAldatu(selectedImage);
                }
            }
        });
        irudiaLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String url = "jdbc:mysql://localhost/Argazki";
                String erab = "root";
                String pasahitza = "zubiri";
                try {
                    Connection conn = DriverManager.getConnection(url, erab, pasahitza);
                    String sql = "SELECT bistaratzeKop FROM argazkiak WHERE idArgazki = ?;";
                    PreparedStatement kontsulta = conn.prepareStatement(sql);
                    int id;
                    if (irudiaLabel.getIcon().equals("./irudiak/ansealdams1.jpg")){
                        kontsulta.setString(1,"1");
                        id=1;
                    }
                    else if (irudiaLabel.getIcon().equals("./irudiak/ansealdams2.jpg")){
                        kontsulta.setString(1,"2");
                        id=2;
                    } else if (irudiaLabel.getIcon().equals("./irudiak/rothko1.jpg")){
                        kontsulta.setString(1,"3");
                        id=3;
                    } else if (irudiaLabel.getIcon().equals("./irudiak/vangogh1.jpg")){
                        kontsulta.setString(1,"4");
                        id=4;
                    } else {
                        kontsulta.setString(1,"5");
                        id=5;
                    }
                    ResultSet rs = kontsulta.executeQuery();
                    if (rs.next()) {
                        int bisKop = rs.getInt("bistaratzeKop");
                        inkrementatuBistaratzeak(new Argazki(id, bisKop));
                    } else {
                        System.out.println("Ez da aurkitu emaitzarik");
                    }
                    conn.close();
                } catch (SQLException m) {
                    System.out.println("Konektatzerakoan errorea: " + m.getMessage());
                }



            }
        });
    }

    public static void main(String[] args) {
        new Photography();
    }

    public void argazkiakLortu(String selectedPhotographer) {
        listModel.clear();
        String url = "jdbc:mysql://localhost/Argazki";
        String erab = "root";
        String pasahitza = "zubiri";
        try {
            Connection conn = DriverManager.getConnection(url, erab, pasahitza);
            String sql = "SELECT * FROM argazkiak WHERE idArgazkilari = ?;";
            PreparedStatement kontsulta = conn.prepareStatement(sql);
            String ida;
            if (selectedPhotographer.equals("ansealdams")){
                ida = "1";
            } else if (selectedPhotographer.equals("rothko")) {
                ida = "2";
            }else {
                ida = "3";
            }
            kontsulta.setString(1,ida);
            ResultSet rs = kontsulta.executeQuery();
            int kont = 0;
            while (rs.next()) {
                String izenburua = rs.getString("izenburua");
                listModel.addElement(izenburua);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Konektatzerakoan errorea: " + e.getMessage());
        }
    }

    private void irudiaAldatu(String imageName) {
        String imagePath = "./irudiak/" + imageName + ".jpg";
        icon = new ImageIcon(imagePath);
        irudiaLabel.setIcon(icon);

    }
    public void inkrementatuBistaratzeak(Argazki a){
        String url = "jdbc:mysql://localhost/Argazki";
        String erab = "root";
        String pasahitza = "zubiri";
        try {
            Connection conn = DriverManager.getConnection(url, erab, pasahitza);
            String sql = "UPDATE argazkiak set bistaratzeKop = ? where idArgazkilari = ?;";
            PreparedStatement kontsulta = conn.prepareStatement(sql);
            int bistKop = a.bistaratzeKop+1;
            kontsulta.setString(2,String.valueOf(a.idArgazki));
            kontsulta.setString(1,String.valueOf(bistKop));
            kontsulta.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Konektatzerakoan erroreaasdasd: " + e.getMessage());
        }
    }
}
