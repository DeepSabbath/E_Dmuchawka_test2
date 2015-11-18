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
    final int maksymalnyCzasDmuchniecia = 100;
    javax.swing.Timer timer1 = new javax.swing.Timer(50, new timerListener());
    boolean czyRosnie = false;
    int witdh, height;
    public int mocDmuchniecia;
    Label moc;

    public Poziom (int witdh, int height, int poziomTrudnosci, double p)
    {
        super("Smok Wawelski");
        this.potrzebnyCzasDmuchniecia = p;
        this.witdh = witdh;
        this.height = height;
        setLayout(null);
        setSize(witdh,height);
        this.addKeyListener(new ZmienMoc());
        init();
        setVisible(true);
    }

    private void init()
    {
        dmuchnijBTN = new JButton("DMUCHNIJ");
        dmuchnijBTN.setSize(200,60);
        dmuchnijBTN.setLocation(losujPolozenie(witdh, height));
        dmuchnijBTN.addMouseListener(new DmuchnijClick());
        add(dmuchnijBTN);

        moc = new Label("teskt");
        moc.setSize(200,200);
        moc.setLocation(500,150);
        moc.setVisible(true);
        moc.setForeground(Color.blue);
        moc.setBackground(this.getBackground());
        int fontSize=20;
        Font font = new Font("Helvetica", Font.BOLD, fontSize);
        moc.setFont(font);
        add(moc);

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
        if(potrzebnyCzasDmuchniecia + 10 >= aktualnyCzasDmuchniecia && potrzebnyCzasDmuchniecia - 10 <= aktualnyCzasDmuchniecia)
        {
            g.setColor(Color.green);
        }
        else if (potrzebnyCzasDmuchniecia - 20 <= aktualnyCzasDmuchniecia && aktualnyCzasDmuchniecia < potrzebnyCzasDmuchniecia)
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

    class ZmienMoc extends KeyAdapter
    {
            public void keyTyped(KeyEvent e)
            {
                int a = e.getKeyCode();
                super.keyTyped(e);
                char c = e.getKeyChar();
                String s = "" + 'c';
                System.out.println(a);
                moc.setText(s);
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
        Random rand = new Random();
        int sze = rand.nextInt(width - s);
        int wys = rand.nextInt(height - w);
        Point pol = new Point(sze, wys);
        return pol;
    }
}
