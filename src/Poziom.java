import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Created by Amadeusz on 17.11.2015.
 */
public class Poziom extends JFrame {

    JButton dmuchnijBTN;
    double potrzebnyCzasDmuchniecia;
    double aktualnyCzasDmuchniecia = 0;
    final int maksymalnyCzasDmuchniecia = 15;
    javax.swing.Timer timer1 = new javax.swing.Timer(500, new timerListener());
    boolean czyRosnie = false;
    int witdh, height;

    public Poziom (int witdh, int height, int poziomTrudnosci, double p)
    {
        super("Smok Wawelski");
        this.potrzebnyCzasDmuchniecia = p;
        this.witdh = witdh;
        this.height = height;
        setLayout(null);
        setSize(witdh,height);
        init();
        setVisible(true);
    }

    private void init()
    {
        dmuchnijBTN = new JButton("DMUCHNIJ");
        dmuchnijBTN.setSize(200,60);
        System.out.println(witdh + " " + height);
        dmuchnijBTN.setLocation(losujPolozenie(witdh, height));
        dmuchnijBTN.addMouseListener(new DmuchnijClick());
        add(dmuchnijBTN);

        timer1.start();
    }

    public void paint (Graphics g)
    {
        int prosX = 1050;
        int prosY = 450;
        int prosSzer = 25;
        int prosWys = 200;
        double prosWysD = prosWys;
        int wypelnienie = (int)((aktualnyCzasDmuchniecia/maksymalnyCzasDmuchniecia)*prosWysD);
        g.drawRect(prosX,prosY-1,prosSzer,prosWys+1);
        if(potrzebnyCzasDmuchniecia + 1 >= aktualnyCzasDmuchniecia && potrzebnyCzasDmuchniecia - 1 <= aktualnyCzasDmuchniecia)
        {
            g.setColor(Color.green);
        }
        else if (potrzebnyCzasDmuchniecia - 2 == aktualnyCzasDmuchniecia)
        {
            g.setColor((Color.orange));
        }
        else {
            g.setColor(Color.red);
        }
        g.fillRect(prosX+1,(prosY+prosWys-wypelnienie),prosSzer-1,wypelnienie);
    }

    private class timerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if(czyRosnie && aktualnyCzasDmuchniecia < maksymalnyCzasDmuchniecia)
            {
                aktualnyCzasDmuchniecia++;
                repaint();
            }
        }
    }

    class DmuchnijClick extends MouseAdapter
    {
        public void mousePressed(MouseEvent e) {
            czyRosnie = true;
        }

        public void mouseReleased(MouseEvent e) {
            czyRosnie = false;
            sprawdzWynik();
        }
    }

    private boolean sprawdzWynik()
    {
        if(potrzebnyCzasDmuchniecia + 1 >= aktualnyCzasDmuchniecia && potrzebnyCzasDmuchniecia - 1 <= aktualnyCzasDmuchniecia)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Point losujPolozenie(int width, int height)
    {
        Dimension d = dmuchnijBTN.getSize();
        int w = d.height;
        int s = d.width;
        System.out.println(w + " " + s);
        Random rand = new Random();
        int sze = rand.nextInt(width);
        int wys = rand.nextInt(height);
        Point pol = new Point(sze, wys);
        return pol;
    }
}
