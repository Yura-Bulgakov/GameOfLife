package ru.project.ui;

import com.google.common.annotations.VisibleForTesting;
import lombok.AllArgsConstructor;
import ru.project.board.Board;

import java.util.Scanner;

public class ConsoleUserListener implements UserListener{

    private final Scanner scanner;
    private final StringBuilder stringBuilder;

    public ConsoleUserListener() {
        this.scanner = new Scanner(System.in);
        this.stringBuilder = new StringBuilder();
    }

    @Override
    public int getInt(String prompt) {
        showMessage(prompt);
        while (!scanner.hasNextInt()) {
            showMessage("Пожалуйста, введите целое число.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showBoard(Board board) {
        if (stringBuilder.length() > 0){
            stringBuilder.delete(0,stringBuilder.length()-1);
        }
        //Unicode symbol black square
        String liveCell = "*";
        String deadCell = "-";
        boolean[][] gameBoard = board.getBoard();
        for (boolean[] row: gameBoard) {
            for (boolean col: row) {
                if (col){
                    stringBuilder.append(liveCell);
                } else {
                    stringBuilder.append(deadCell);
                }
            }
            stringBuilder.append("\n");
        }
        showMessage(stringBuilder.toString());
    }

    @Override
    public String pickMenuItem(String prompt, String... menuItems) {
        showMessage(prompt);
        for (int i = 0; i < menuItems.length; i++) {
            showMessage((i + 1) + ". " + menuItems[i]);
        }
        int choice;
        do {
            choice = getInt("Выберите пункт меню:");
        } while (choice < 1 || choice > menuItems.length);

        return menuItems[choice - 1];
    }

    @VisibleForTesting
    ConsoleUserListener(String str) {
        this.scanner = new Scanner(str);
        this.stringBuilder = new StringBuilder();
    }
}
