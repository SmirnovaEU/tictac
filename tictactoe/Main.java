package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] tictac = new char[3][3];

        boolean gameFinished = false;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                tictac[i][j] = ' ';

            }
        }
        tictacOut(tictac);
        int i = 0;
        while (!gameFinished) {

            boolean entered = false;
            int x = 0;
            int y = 0;

            while (!entered) {
                //ввод координат
                System.out.println("Enter coordinates: ");
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNextInt()) {
                    x = scanner.nextInt();
                    if (scanner.hasNextInt()) {
                        y = scanner.nextInt();
                        entered = checkInput(x, y, tictac);
                    } else {
                        System.out.println("You should enter numbers!");
                    }
                } else {
                    System.out.println("You should enter numbers!");
                }
            }
            if (i % 2 == 0) {
                tictac[3 - y][x - 1] = 'X';
            } else {
                tictac[3 - y][x - 1] = 'O';
            }
            tictacOut(tictac);
            gameFinished = currentStatus(tictac);
            i++;
        }
    }

    public static boolean checkInput(int x, int y, char[][] a) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (a[3 - y][x-1] == 'X' || a[3 - y][x-1] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }

    public static void tictacOut(char[][] a) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public static boolean currentStatus(char[][] a) {
        //общее количество x и 0 должно быть равно или отличаться на 1
        // должна быть только одна закрытая линия
        // перебрать в одном цикле все строки и столбцы,
        // посчитать в нем количество закрытых горизонталей и вертикалей
        // и проверить общее количество x и 0
        // еще один цикл чтобы перебрать диагонали
        int sumTotal = 0;
        int countLineX = 0;
        int countLineY = 0;
        int totalCount = 0;
        for (int i = 0; i < 3; i++) {
            int sumXInLine = 0;
            int sumYInLine = 0;
            int sumXInCol = 0;
            int sumYInCol = 0;
            for (int j = 0; j < 3; j++) {
                if (a[i][j] == 'X') {
                    sumTotal++;
                    sumXInLine++;
                }
                if (a[i][j] == 'O') {
                    sumTotal++;
                    sumYInLine++;
                }
                if (a[j][i] == 'X') {
                    sumXInCol++;
                }
                if (a[j][i] == 'O') {
                    sumYInCol++;
                }
            }
            if (sumXInLine == 3 || sumXInCol == 3) {
                countLineX++;
                totalCount++;
            }
            if (sumYInLine == 3 || sumYInCol == 3) {
                countLineY++;
                totalCount++;
            }
        }

        int sumXD1 = 0;
        int sumXD2 = 0;
        int sumYD1 = 0;
        int sumYD2 = 0;

        for (int i = 0; i < 3; i++) {
            if (a[i][i] == 'X') sumXD1++;
            if (a[i][i] == 'O') sumYD1++;
            if (a[i][2 - i] == 'X') sumXD2++;
            if (a[i][2 - i] == 'O') sumYD2++;

        }
        if (sumXD1 == 3) {
            countLineX++;
            totalCount++;
        }
        if (sumXD2 == 3) {
            countLineX++;
            totalCount++;
        }
        if (sumYD1 == 3) {
            countLineY++;
            totalCount++;
        }
        if (sumYD2 == 3) {
            countLineY++;
            totalCount++;
        }

        if (totalCount == 0 && sumTotal == 9) {
            System.out.println("Draw");
            return true;
        }
        if (totalCount == 0 && sumTotal < 9) {
        //    System.out.println("Game not finished");
            return false;
        }
        if (countLineX == 1) {
            System.out.println("X wins");
            return true;
        }
        if (countLineY == 1) {
            System.out.println("O wins");
        }
        return true;
    }
}
