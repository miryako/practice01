package tasks1_10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel {
    private String[] itemTitles;
    private char[] symbols;
    private int selectedItem;
    char nullSymbol = 27;

    public Menu() {
        setFocusable(true);
        setPreferredSize(new Dimension(500, 60));
        selectedItem = 0;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE -> {
                        nextItem();
                    }
                    case KeyEvent.VK_ENTER -> {

                        select(true);
                    }
                    case KeyEvent.VK_ESCAPE -> {
                        select(false);
                    }
                }
                repaint();
            }
        });
    }

    public void init() {
        itemTitles = new String[] {"Первый", "Второй", "Третий", "Четвёртый"};
        symbols = new char[] {'a', 'b', 'c', 'd'};
        selectedItem = 0;
    }

    public void select(boolean selected) {
        if (selected) JOptionPane.showMessageDialog(null, leftBoard() + len() + ". Символ: " + whatSel());
        else JOptionPane.showMessageDialog(null, "Символ: " + nullSymbol);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("lyalya", Font.PLAIN, 18));
        int x = 10;
        for (int i = 0; i < itemTitles.length; i++) {
            if (i == selectedItem) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawString(itemTitles[i], x, 30);
            x += g.getFontMetrics().stringWidth(itemTitles[i]) + 20;
        }
    }

    public void nextItem() {
        selectedItem = (selectedItem + 1) % itemTitles.length;
    }

    public String leftBoard() {
        String lol = itemTitles[selectedItem].substring(0, 3);
        return lol;
    }

    public int len() {
        return itemTitles[selectedItem].length();
    }

    public char whatSel() {
        return symbols[selectedItem];
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Простое меню (нет)");
        Menu menu = new Menu();
        menu.init();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(menu);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
