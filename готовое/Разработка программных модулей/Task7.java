package tasks1_10;

import javax.swing.*;
import java.awt.*;

public class Task7 {
    static int wight = 450, height = 450;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Если нет Бога, то чья это коровка?");
        frame.setSize(wight, height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(new DrawБабочка());
    }

    public static class DrawБабочка extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.black);
            g2d.fillOval(150, 50, 100, 120); //голова
            g2d.setColor(Color.gray);
            g2d.fillOval(50, 100, 300, 300);//тело
            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(6));
            g2d.drawLine(200, 80, 200, 400); //полоска
            g2d.drawOval(50, 100, 300, 300); //обводка тела
            //усо1
            g2d.drawLine(185, 50, 180, 30);
            g2d.drawLine(180, 30, 170, 40);
            //усо2
            g2d.drawLine(215, 50, 220, 30);
            g2d.drawLine(220, 30, 230, 40);
            //точки
            g2d.fillOval(75, 175, 50, 60);
            g2d.fillOval(125, 275, 50, 60);
            g2d.fillOval(275, 225, 50, 60);
            g2d.fillOval(225, 325, 50, 60);
            g2d.fillOval(225, 125, 50, 60);
        }
    }
}
