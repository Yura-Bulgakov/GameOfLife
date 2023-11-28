package ru.project.automaton;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.project.utils.ArrayConvertor;

import java.util.Arrays;
import java.util.List;




class GameOfLifeTest {
    private GameOfLife gameOfLife;


    @BeforeEach
    void setUp() {
        gameOfLife = new GameOfLife();
    }

    @Test
    void makeMove() throws AutomationException {

        @AllArgsConstructor
        class Testcase{
            AutomationException exception;
            String name;
            boolean[][] inBoard;
            boolean[][] outBoard;

            void run() throws AutomationException {
                if (this.exception != null){
                    Assertions.assertThrows(this.exception.getClass(), new Executable() {
                        @Override
                        public void execute() throws Throwable {
                            gameOfLife.makeMove(inBoard);
                        }
                    });
                }else {
                    boolean[][] resultBoard = gameOfLife.makeMove(inBoard).getBoard();
                    Assertions.assertArrayEquals(outBoard, resultBoard, name);
                }
            }

        }
        List<Testcase> testcases = Arrays.asList(
            new Testcase(null,
                    "Пустая карта",
                   new boolean[][]{
                           {false, false, false, false, false},
                           {false, false, false, false, false},
                           {false, false, false, false, false},
                           {false, false, false, false, false},
                           {false, false, false, false, false}
                   },
                    new boolean[][]{
                            {false, false, false, false, false},
                            {false, false, false, false, false},
                            {false, false, false, false, false},
                            {false, false, false, false, false},
                            {false, false, false, false, false}
                    }
                    ),
                new Testcase(null,
                        "Переход в пустую карту",
                        new boolean[][]{
                                {false, false, false, false, false},
                                {false, true, true, false, false},
                                {false, false, false, false, false},
                                {false, false, false, false, false},
                                {false, false, false, false, true}
                        },
                        new boolean[][]{
                                {false, false, false, false, false},
                                {false, false, false, false, false},
                                {false, false, false, false, false},
                                {false, false, false, false, false},
                                {false, false, false, false, false}
                        }
                ),
                new Testcase(null,
                        "На месте",
                        new boolean[][]{
                                {false, false, false, false, false},
                                {false, true, true, false, false},
                                {false, true, true, false, false},
                                {false, false, false, false, false},
                                {false, false, false, false, false}
                        },
                        new boolean[][]{
                                {false, false, false, false, false},
                                {false, true, true, false, false},
                                {false, true, true, false, false},
                                {false, false, false, false, false},
                                {false, false, false, false, false}
                        }
                ),
                new Testcase(null,
                        "Переход ячейки в живое состояние",
                        new boolean[][]{
                                {false, false, false, false, false},
                                {false, true, true, false, false},
                                {false, false, true, false, false},
                                {false, false, false, false, false},
                                {false, false, false, false, false}
                        },
                        new boolean[][]{
                                {false, false, false, false, false},
                                {false, true, true, false, false},
                                {false, true, true, false, false},
                                {false, false, false, false, false},
                                {false, false, false, false, false}
                        }
                ),
                new Testcase(null,
                        "Переход ячейки в живое состояние2",
                        ArrayConvertor.convertStringToArray(
                                "00011\n" +
                                      "00000\n" +
                                      "00000\n" +
                                      "00000\n" +
                                      "00001\n"
                        ),
                        ArrayConvertor.convertStringToArray(
                                  "00011\n" +
                                        "00000\n" +
                                        "00000\n" +
                                        "00000\n" +
                                        "00011\n"
                        )
                ),
                new Testcase(null,
                        "Переход ячейки в живое состояние3",
                        ArrayConvertor.convertStringToArray(
                                  "00000\n" +
                                        "00001\n" +
                                        "10001\n" +
                                        "00000\n" +
                                        "00000\n"
                        ),
                        ArrayConvertor.convertStringToArray(
                                  "00000\n" +
                                        "10001\n" +
                                        "10001\n" +
                                        "00000\n" +
                                        "00000\n"
                        )
                )
        );
        for (Testcase test: testcases) {
            test.run();
        }
    }

}