package tasks1_10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextButBetter {
    static boolean writing = true;
    static String point = "Точка1", finish = "Закончить1", newSentence = "Новое1";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputWord, title;
        System.out.println("Введите заголовок");
        title = scanner.nextLine();
        title = checkInputText(title, false);

        List<Word> words = new ArrayList<>();
        List<Sentence> sentences = new ArrayList<>();

        while (writing) {
            System.out.println("Введи слово или \"Точка1\", чтобы закончить предложение");
            inputWord = scanner.nextLine();
            checkMemory();
            inputWord = checkInputVariant(inputWord);
            if (inputWord.equals("Точка1")) {
                sentences.add(new Sentence(new ArrayList<>(words)));
                System.out.println("Предложение заверешено. Введите \"Закончить1\", чтобы закончить предложение или \"Новое1\" для создания нового предложения");
                String input = scanner.nextLine();
                checkMemory();
                input = checkInputVariant(input);
                if (input.equals("Новое1")) words.clear();
                else writing = false;
            }
            else words.add(new Word(inputWord));
            checkMemory();
        }

        System.out.println("Вывод:");
        Text text = new Text(sentences, title);
        checkMemory();
        System.out.println(text);
    }

    static String checkInputText(String text, boolean noSpace) {
        //проверка на лишнии пробелы
        if (noSpace) text = text.trim().replaceAll("\\s+", "");
        else {
            text = text.trim().replaceAll("\\s+", " ");
        }

        return text;
    }

    static String checkInputVariant(String text) {
        text = TextButBetter.checkInputText(text, true);
        //проверка на неправильный ввод
        int correctCount = 0;

        for (int i = 0; i < Math.min(text.length(), point.length()); i++)
            if (point.charAt(i) == text.charAt(i)) correctCount ++;

        if (correctCount > 3) return "Точка1"; else correctCount = 0;

        for (int i = 0; i < Math.min(text.length(), newSentence.length()); i++)
            if (newSentence.charAt(i) == text.charAt(i)) correctCount ++;

        if (correctCount > 3) return "Новое1"; else return "Закончить1";
    }

    static void checkMemory() {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();

        //проерка на память
        if ((freeMemory + (maxMemory - allocatedMemory)) < maxMemory * 0.1) {
            System.out.println("Памяти маловато. Давай больше!");
            System.exit(0);
        }
    }
}


