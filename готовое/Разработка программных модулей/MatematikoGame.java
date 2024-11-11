package tasks1_10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class MatematikoGame extends JFrame {
    int[][] playerField = new int[5][5];
    int[][] computerField = new int[5][5];
    List<Integer> deck = new ArrayList<>();
    JLabel[][] playerLabels = new JLabel[5][5];
    JLabel currentCardLabel;
    int currentCard = -1;
    JLabel resultLabel;

    public MatematikoGame() {
        setTitle("Математико_0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(666, 666);
        setLayout(new BorderLayout());

        resultLabel = new JLabel("Результаты будут показаны здесь.", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(resultLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                playerLabels[i][j] = new JLabel("", SwingConstants.CENTER);
                playerLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                playerLabels[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                int row = i;
                int col = j;

                playerLabels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (currentCard != -1 && playerField[row][col] == 0) {
                            placeNumber(currentCard, row, col, playerField);
                            playerLabels[row][col].setText(String.valueOf(currentCard));
                            currentCard = -1;
                            currentCardLabel.setText("Выберите новую карту");

                            if (isPlayerFieldFull() && isComputerFieldFull()) {
                                calculateResults();
                            } else {
                                computerMove();
                                if (isPlayerFieldFull() && isComputerFieldFull()) {
                                    calculateResults();
                                }
                            }
                        }
                    }
                });
                gridPanel.add(playerLabels[i][j]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton drawCardButton = new JButton("Вытянуть карту");
        currentCardLabel = new JLabel("Выберите карту");
        controlPanel.add(drawCardButton);
        controlPanel.add(currentCardLabel);

        add(controlPanel, BorderLayout.SOUTH);

        drawCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!deck.isEmpty() && !isPlayerFieldFull()) {
                    currentCard = drawCard();
                    currentCardLabel.setText("Карта: " + currentCard);
                } else {
                    currentCardLabel.setText("Все карты вытянуты");
                    if (isPlayerFieldFull() && isComputerFieldFull()) {
                        calculateResults();
                    }
                }
            }
        });

        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(i);
            }
        }
        Collections.shuffle(deck);
    }

    public int drawCard() {
        return deck.remove(deck.size() - 1);
    }

    public void placeNumber(int num, int row, int col, int[][] field) {
        field[row][col] = num;
    }

    public void computerMove() {
        while (!deck.isEmpty()) {
            int card = drawCard();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (computerField[i][j] == 0) {
                        placeNumber(card, i, j, computerField);
                        return;
                    }
                }
            }
        }
    }

    public boolean isPlayerFieldFull() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (playerField[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isComputerFieldFull() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (computerField[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void calculateResults() {
        int playerScore = calculateScore(playerField);
        int computerScore = calculateScore(computerField);

        resultLabel.setText("Ваши очки: " + playerScore + ", Очки компьютера: " + computerScore);

        String message;
        if (playerScore > computerScore) {
            message = "Вы выиграли!\nВаши очки: " + playerScore + "\nОчки компьютера: " + computerScore;
        } else if (playerScore < computerScore) {
            message = "Вы проиграли.\nВаши очки: " + playerScore + "\nОчки компьютера: " + computerScore;
        } else {
            message = "Ничья.\nВаши очки: " + playerScore + "\nОчки компьютера: " + computerScore;
        }
        JOptionPane.showMessageDialog(this, message, "Итоги игры", JOptionPane.INFORMATION_MESSAGE);
    }

    public int calculateScore(int[][] field) {
        int score = 0;

        for (int i = 0; i < 5; i++) {
            score += checkLine(field[i]);
            int[] column = new int[5];
            for (int j = 0; j < 5; j++) {
                column[j] = field[j][i];
            }
            score += checkLine(column);
        }

        int[] diagonal1 = {field[0][0], field[1][1], field[2][2], field[3][3], field[4][4]};
        int[] diagonal2 = {field[0][4], field[1][3], field[2][2], field[3][1], field[4][0]};
        score += checkLine(diagonal1);
        score += checkLine(diagonal2);

        return score;
    }

    public int checkLine(int[] line) {
        int score = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : line) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        List<Integer> numbers = new ArrayList<>(counts.keySet());
        Collections.sort(numbers);

        if (counts.containsValue(2)) {
            if (isDiagonal(line)) {
                score += 20;
            } else {
                score += 10;
            }
        }

        if (counts.containsValue(3)) {
            if (isDiagonal(line)) {
                score += 30;
            } else {
                score += 20;
            }
        }

        if (counts.containsValue(3) && counts.containsValue(2)) {
            if (isDiagonal(line)) {
                score += 90;
            } else {
                score += 80;
            }
        }

        if (counts.containsValue(4)) {
            if (isDiagonal(line)) {
                score += 170;
            } else {
                score += 160;
            }
        }

        if (numbers.size() == 5 && numbers.get(4) - numbers.get(0) == 4) {
            if (isDiagonal(line)) {
                score += 60;
            } else {
                score += 50;
            }
        }

        if (counts.getOrDefault(1, 0) == 3 && counts.getOrDefault(13, 0) == 2) {
            score += 100;
        }

        if (numbers.containsAll(Arrays.asList(1, 13, 12, 11, 10))) {
            if (isDiagonal(line)) {
                score += 160;
            } else {
                score += 150;
            }
        }

        if (counts.getOrDefault(1, 0) == 4) {
            if (isDiagonal(line)) {
                score += 210;
            } else {
                score += 200;
            }
        }

        return score;
    }

    public boolean isDiagonal(int[] line) {
        return (line[0] != 0 && line[1] != 0 && line[2] != 0 && line[3] != 0 && line[4] != 0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MatematikoGame game = new MatematikoGame();
                game.setVisible(true);
            }
        });
    }
}
