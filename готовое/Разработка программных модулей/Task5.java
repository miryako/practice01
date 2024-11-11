package tasks1_10;

import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        int k = 5;
        char symbol = '_';
        System.out.println(replaceK(text, k, symbol));
    }

    public static String replaceK(String text, int k, char symbol) {
        String[] words = text.split("\\s+");
        StringBuilder outputText = new StringBuilder();

        for (String word: words) {
            if (word.length() < k) {
                outputText.append(word).append(" ");
            }
            else {
                StringBuilder newWord = new StringBuilder(word);
                newWord.setCharAt(k - 1, symbol);
                outputText.append(newWord).append(" ");
            }
        }
        return outputText.toString().trim();
    }
}
