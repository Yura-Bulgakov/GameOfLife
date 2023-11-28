package ru.project.game;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.project.board.Board;
import ru.project.utils.ArrayConvertor;

import java.util.Arrays;
import java.util.List;

class CellSurvivalEndConditionCheckerTest {
    private CellSurvivalEndConditionChecker gameOverChecker;

    @BeforeEach
    void setUp() {
        gameOverChecker = new CellSurvivalEndConditionChecker();
    }

    @Test
    void isGameOver() {
        @AllArgsConstructor
        class Testcase{
            String name;
            boolean[][] in;
            boolean out;
            public void run(){
                Assertions.assertEquals(gameOverChecker.isGameOver(new Board(in)), out, name);
            }
        }
        List<Testcase> testcases = Arrays.asList(
                new Testcase("Game over check",
                        ArrayConvertor.convertStringToArray(
                                "0000\n" +
                                        "0000\n" +
                                        "0000\n" +
                                        "0000\n"
                        ),
                        true),
                new Testcase("Game not over check",
                        ArrayConvertor.convertStringToArray(
                                "0010\n" +
                                        "0000\n" +
                                        "0000\n" +
                                        "0000\n"
                        ),
                        false)
        );
        for (Testcase test: testcases) {
            test.run();
        }
    }
}