import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Amadeusz on 17.11.2015.
 */
public class EkranStartowy extends JFrame{

    int poziomTrudnosci = 1;            /* ustawienie poziomu trudnosci 1 - latwy, 2 - sredni,3 - trudny
                                           inicjalizujemy jako latwy */
    JButton poziomLatwyBTN, poziomSredniBTN, poziomTrudnyBTN;
    JButton rozpocznijGre;

    public EkranStartowy(int width, int height)
    {
        setLayout(null);
        setSize(width, height);
        init();
        setVisible(true);
    }

    private void init()
    {
        poziomLatwyBTN = new JButton("Latwy");
        poziomLatwyBTN.setSize(100,40);
        poziomLatwyBTN.setLocation(500,300);
        poziomLatwyBTN.addMouseListener(new PoziomLatwyClick());
        add(poziomLatwyBTN);

        poziomSredniBTN = new JButton("Sredni");
        poziomSredniBTN.setSize(100,40);
        poziomSredniBTN.setLocation(500,380);
        poziomSredniBTN.addMouseListener(new PoziomSredniClick());
        add(poziomSredniBTN);

        poziomTrudnyBTN = new JButton("Trudny");
        poziomTrudnyBTN.setSize(100,40);
        poziomTrudnyBTN.setLocation(500,460);
        poziomTrudnyBTN.addMouseListener(new PoziomTrudnyClick());
        add(poziomTrudnyBTN);

        rozpocznijGre = new JButton("Graj");
        rozpocznijGre.setSize(100,60);
        rozpocznijGre.setLocation(500,600);
        rozpocznijGre.addMouseListener(new RozpocznijGreClick());
        add(rozpocznijGre);
    }

    class PoziomLatwyClick extends MouseAdapter              // definicja dzia³ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            poziomTrudnosci = 1;
        }
    }
    class PoziomSredniClick extends MouseAdapter              // definicja dzia³ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            poziomTrudnosci = 2;
        }
    }
    class PoziomTrudnyClick extends MouseAdapter              // definicja dzia³ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            poziomTrudnosci = 3;
        }
    }
    class RozpocznijGreClick extends MouseAdapter              // definicja dzia³ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            Poziom poziom1 = new Poziom(1280, 800, poziomTrudnosci);
        }
    }


}
