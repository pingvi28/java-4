package ru.kpfu.itis.kashapova.java.histogram;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

import static java.awt.Color.RED;

class DrawingComponent extends JPanel {
    protected double x[];

    public DrawingComponent(double x[]) {
        this.x = x;
    }

    @Override
    protected void paintComponent(Graphics gh) {
        Graphics2D drp = (Graphics2D)gh;

        //горизонтальные линии и обозначения
        for (int i=0; i<9; i++) {
            drp.drawLine(50, 50 + 44*i, (int) (50 * (GrGis.countInterval)), 50 + 44*i);
            int vs = 8*GrGis.interval - i*GrGis.interval;
            drp.drawString(vs+"", 20, 50 + 44*i);
        }

        drp.drawString("w_i", 23, 20);
        drp.drawString("х_i", (int) (50 * (GrGis.countInterval)), 420);


        for (int i = 0; i < GrGis.countInterval; i++) {
            //строим саму гистограмму
            Color color = RED;

            try {
                Field field = Class.forName("java.awt.Color").getField(GrGis.col[0].toLowerCase());
                color = (Color)field.get(null);
            } catch (Exception e) {}
            drp.setColor(color);
            //переводим полученные данные в реальные координаты
            int realY = (int) (400 - 44*GrGis.y[0][i]/GrGis.interval) + 3;
            drp.fillRect(70 + 36*i , realY, 35,(int) 402 - realY);
            drp.drawString(String.valueOf((int)GrGis.y[0][i]), 75 + 36*i, 420);
        }
    }
}