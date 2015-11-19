import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.util.Random;


/**
 * Created by Amadeusz on 17.11.2015.
 */
public class Poziom extends JFrame implements KeyListener{

    JButton dmuchnijBTN;
    double potrzebnyCzasDmuchniecia;
    double aktualnyCzasDmuchniecia = 0;
    final int maksymalnyCzasDmuchniecia = 100;
    final int blad = 10;
    javax.swing.Timer timer1 = new javax.swing.Timer(50, new timerListener());
    boolean czyRosnie = false;
    int witdh, height;
    public int mocDmuchniecia;
    Label moc;
    int poziomTrudnosci;
    String s = "";

    public Poziom (int witdh, int height, int poziomTrudnosci)
    {
        super("Smok Wawelski");
        this.witdh = witdh;
        this.height = height;
        this.poziomTrudnosci = poziomTrudnosci;
        setLayout(null);
        setSize(witdh,height);
        init();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
    }

    private void init()
    {

        zwrocPotrzebnyCzasDmuchniecia(poziomTrudnosci);
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
        if(potrzebnyCzasDmuchniecia + blad >= aktualnyCzasDmuchniecia && potrzebnyCzasDmuchniecia - blad <= aktualnyCzasDmuchniecia)
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int a = e.getKeyCode();
        Character c = e.getKeyChar();
        s +=  c.toString();
        System.out.println(a);
        //moc.setText(s);

        if (a == KeyEvent.VK_UP)
        {
            mocDmuchniecia++;
        }

        if (a == KeyEvent.VK_DOWN)
        {
            mocDmuchniecia--;
        }
        moc.setText("" + mocDmuchniecia);
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
            if (sprawdzWynik())
                moc.setText("Wygrales");
            else
            {
                moc.setText("Przegrales");
            }
        }
    }

    class ZmienMoc extends KeyAdapter
    {
            public void keyTyped(KeyEvent e)
            {
                int a = e.getKeyCode();
                Character c = e.getKeyChar();
                String s = "" + c.toString();
                System.out.println(a);
                moc.setText(s);
            }
    }

    private boolean sprawdzWynik()
    {
        if(potrzebnyCzasDmuchniecia + blad >= aktualnyCzasDmuchniecia && potrzebnyCzasDmuchniecia - blad <= aktualnyCzasDmuchniecia)
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
        int wys = rand.nextInt(height - 2*w);
        Point pol = new Point(sze, wys);
        return pol;
    }

    public double zwrocPotrzebnyCzasDmuchniecia(int poziomTrudnosci)
    {
        switch (poziomTrudnosci)
        {
            case 1:
            {
                potrzebnyCzasDmuchniecia = 35;
            }
            break;
            case 2:
            {
                potrzebnyCzasDmuchniecia = 55;
            }
            break;
            case 3:
            {
                potrzebnyCzasDmuchniecia = 70;
            }
            break;
        }
        return  potrzebnyCzasDmuchniecia;
    }
}
