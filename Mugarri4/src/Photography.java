import javax.swing.*;
import org.jdesktop.swingx.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import javax.swing.ImageIcon;

public class Photography extends JFrame {
    ArrayList<Argazki> argazkis;
    Argazkilari[] argazkilaris;
    JLabel irudiaLabel;
    ImageIcon irudi;
    ImageIcon icon;
    DefaultListModel<Argazki> listModel;

    private DatuBaseKudeatzailea datuBaseKudeatzailea;

    public Photography() {
        datuBaseKudeatzailea = new DatuBaseKudeatzailea();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(650, 400));
        setLayout(new GridLayout(2, 2));

        JPanel panel1 = new JPanel();
        add(panel1);
        JLabel photographer = new JLabel("Photographer");
        panel1.add(photographer);
        argazkilaris = datuBaseKudeatzailea.argazkilariakLortu();
        JComboBox photographerBox = new JComboBox<>(argazkilaris);
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
        Argazkilari argazkilariMomentukoa = (Argazkilari) photographerBox.getSelectedItem();
        argazkis = datuBaseKudeatzailea.argazkiakLortu();
        listModel = new DefaultListModel<>();
        for (Argazki argazki : argazkis) {
            assert argazkilariMomentukoa != null;
            if(argazki.getIdArgazkilari() == argazkilariMomentukoa.getIdArgazkilari()){

                listModel.addElement(argazki);
            }
        }
        JList<Argazki> jList = new JList<>(listModel);
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

                listModel.clear();
                Argazkilari argazkilariMomentukoa = (Argazkilari) photographerBox.getSelectedItem();
                Date selectedDate = datePicker.getDate();
                for (Argazki argazki : argazkis) {
                    assert argazkilariMomentukoa != null;
                    if(argazki.getIdArgazkilari() == argazkilariMomentukoa.getIdArgazkilari() && argazki.getData().before(selectedDate)){
                        listModel.addElement(argazki);
                    }
                }
            }
        });

        jList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Argazki selectedImage = jList.getSelectedValue();
                if (selectedImage != null) {
                    irudiaAldatu(selectedImage);
                }
            }
        });
        irudiaLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Argazki selArgazki = jList.getSelectedValue();
                if (selArgazki != null) {
                    inkrementatuBistaratzeak(selArgazki);
                }
            }
        });
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                datuBaseKudeatzailea.itxi(argazkis);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }



    public static void main(String[] args) {
        new Photography();
    }

    private void irudiaAldatu(Argazki imageName) {
        String imagePath = imageName.fitxategia;
        icon = new ImageIcon(imagePath);
        irudiaLabel.setIcon(icon);

    }
    public void inkrementatuBistaratzeak(Argazki a){

        int kop = a.getBistaratzeKop();
        kop = kop + 1;
        a.setBistaratzeKop(kop);
        System.out.println(kop);
    }
}
