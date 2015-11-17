import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Amadeusz on 17.11.2015.
 */
public class Poziom extends JFrame {

    double potrzebnyCzasDmuchniecia;
    double aktualnyCzasDmuchniecia = 0;
    final int maksymalnyCzasDmuchniecia = 15;
    javax.swing.Timer timer1 = new javax.swing.Timer(500, new timerListener());
    boolean czyRosnie = false;

    public Poziom (int witdh, int height, int poziomTrudnosci, double p)
    {
        super("Smok Wawelski");
        this.potrzebnyCzasDmuchniecia = p;
        setLayout(null);
        setSize(witdh,height);
        init();
        setVisible(true);
    }

    private void init()
    {
        czyRosnie = true;
        timer1.start();
    }

    public void paint (Graphics g)
    {
        int prosX = 200;
        int prosY = 200;
        int prosSzer = 15;
        int prosWys = 150;
        double prosWysD = prosWys;
        int wypelnienie = (int)((aktualnyCzasDmuchniecia/maksymalnyCzasDmuchniecia)*prosWysD);
        g.drawRect(prosX,prosY-1,prosSzer,prosWys+1);
        if(potrzebnyCzasDmuchniecia + 1 >= aktualnyCzasDmuchniecia && potrzebnyCzasDmuchniecia - 1 <= aktualnyCzasDmuchniecia)
        {
            g.setColor(Color.green);
        }
        else
        {
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
}
