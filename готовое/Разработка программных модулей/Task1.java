package tasks1_10;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        boolean find = false;
        System.out.println("Определить принадлежность некоторого значения k интервалам:\n" +
                "(n, m], [n, m), (n, m), [n, m]");
        System.out.println("k =");
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();

        System.out.println("n =");
        int n = scanner.nextInt();
        System.out.println("m =");
        int m = scanner.nextInt();

        if (n > m) {
            System.out.println("Ошибка! Значение n не должно быть больше значения m");
            return;
        }
        if (n == k) {
            System.out.println("Значение к принадлжит интревалам: [n, m), [n, m]");
            find = true;
        } else if (m == k) {
            System.out.println("Значение к принадлжит интревалам: (n, m], [n, m]");
            find = true;
        } else {
            for (int i = n + 1; i < m; i++) {
                if (i == k) {
                    System.out.println("Значение к принадлжит интревалам: (n, m], [n, m), (n, m), [n, m]");
                    find = true;
                }
            }
        }
        if (!find) System.out.println("Значение k не принадлежит ни одному из интервалов");
    }
}
