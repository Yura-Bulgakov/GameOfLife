package ru.project.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.project.board.Board;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;


class ConsoleUserListenerTest {

    private ConsoleUserListener consoleUserListener;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        consoleUserListener = new ConsoleUserListener();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void getInt() {
        int input = 42;
        provideInput(String.valueOf(input));
        int result = consoleUserListener.getInt("Введите число:");
        Assertions.assertEquals(input, result);
    }

    @Test
    void showMessage() {
        String message = "Тестовое сообщение";
        consoleUserListener.showMessage(message);
        Assertions.assertEquals(message, getCapturedOutput());
    }

    @Test
    void showBoard() {
        boolean[][] testBoard = {
                {true, false, true},
                {false, true, false},
                {true, false, true}
        };

        String separator = System.lineSeparator();
        Board board = new Board(testBoard);
        consoleUserListener.showBoard(board);
        Assertions.assertEquals("*-*"+separator+"-*-"+separator+"*-*", getCapturedOutput());
    }

    @Test
    void pickMenuItem() {
        String[] menuItems = {"Item 1", "Item 2", "Item 3"};
        provideInput("2");
        String result = consoleUserListener.pickMenuItem("Выберите пункт:", menuItems);
        Assertions.assertEquals("Item 2", result);
    }

    private void provideInput(String input) {
        InputStream sysInBackup = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        consoleUserListener = new ConsoleUserListener();
        System.setIn(sysInBackup);
    }

    private String getCapturedOutput() {
        System.setOut(originalOut);
        return outContent.toString().trim();
    }
}