package ru.game;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardValidatorTest {

    @Test
    void validateBoard() throws AutomationException {
        @AllArgsConstructor
        class Testcases{
            AutomationException exception;
            String name;
            boolean[][] in;

            void run() throws AutomationException{

                Assertions.assertThrows(this.exception.getClass(),
                        new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        BoardValidator.validateBoard(in);
                    }
                },
                        name);

            }

        }
        List<Testcases> testcases = Arrays.asList(
                new Testcases(
                        new AutomationException("Input board is null"),
                        "null check",
                        null
                ),
                new Testcases(
                        new AutomationException("Input board is null"),
                        "null check2",
                        new boolean[][]{}
                ),
                new Testcases(
                        new AutomationException("Input board is null"),
                        "null check3",
                        new boolean[][]{null}
                ),
                new Testcases(
                        new AutomationException("Input board is null"),
                        "null check4",
                        new boolean[][]{{}}
                ),
                new Testcases(
                        new AutomationException("Input board is null"),
                        "null check5",
                        new boolean[][]{{}}
                ),
                new Testcases(
                        new AutomationException("Input board is null"),
                        "null check6",
                        new boolean[][]{{true}, {}}
                ),
                new Testcases(
                        new AutomationException("Input board is null"),
                        "null check7",
                        new boolean[][]{{false, false}, {false, false, true}}
                )
        );
        for (Testcases test: testcases) {
            test.run();

        }
    }
}