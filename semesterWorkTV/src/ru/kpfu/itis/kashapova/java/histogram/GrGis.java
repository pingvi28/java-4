package ru.kpfu.itis.kashapova.java.histogram;

import ru.kpfu.itis.kashapova.java.Function;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GrGis extends JFrame {
    public static String col[] = {"BLUE"};
    public static double[] x;
    public static double y[][];
    public static double min;
    public static double countInterval = 0;
    public static int interval;
    public static double maxInterval;

    public GrGis () {
        super("Гистограмма");
        JPanel jcp = new JPanel(new BorderLayout());
        setContentPane(jcp);
        jcp.add(new DrawingComponent(x), BorderLayout.CENTER);
        jcp.setBackground(Color.gray);
        setSize((int) (50 + countInterval * 50), 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void draw(List<Integer> ni, double min, long m,long k) {
        maxInterval = 0;
        countInterval = m;

        y = new double[1][ni.size()];
        GrGis.min = min;

        for (int i = 0; i < ni.size(); i++) {
            double q = ni.get(i);
            y[0][i] = q ;

            if(q > maxInterval) maxInterval = q;
        }

        x =  new double[(int) m];

        for (int i = 0; i < m; i++) {
            x[i] = min + k*i;
        }
        interval = (int) Math.ceil(maxInterval/8);

        GrGis gr = new GrGis ();
        gr.setVisible(true);
    }
}