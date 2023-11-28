package ru.project.board.validation;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.project.board.validation.BoardValidationException;
import ru.project.board.validation.BoardValidator;


import java.util.Arrays;
import java.util.List;

class BoardValidatorTest {

    @Test
    void validateBoard() throws BoardValidationException {
        @AllArgsConstructor
        class Testcases{
            BoardValidationException expectedException;
            String name;
            boolean[][] in;

            void run() throws BoardValidationException{

                Exception e = Assertions.assertThrows(this.expectedException.getClass(),
                        new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        BoardValidator.validateBoard(in);
                    }
                },
                        name);
                Assertions.assertEquals(e.getMessage(), expectedException.getMessage(), name);

            }

        }
        List<Testcases> testcases = Arrays.asList(
                new Testcases(
                        new BoardValidationException("Input board is null"),
                        "null check",
                        null
                ),
                new Testcases(
                        new BoardValidationException("Input board is null"),
                        "null check2",
                        new boolean[][]{}
                ),
                new Testcases(
                        new BoardValidationException("Input board is null"),
                        "null check3",
                        new boolean[][]{null}
                ),
                new Testcases(
                        new BoardValidationException("Input board has zero columns"),
                        "empty board check",
                        new boolean[][]{{}}
                ),
                new Testcases(
                        new BoardValidationException("Input board has zero columns"),
                        "empty board check2",
                        new boolean[][]{{}, null}
                ),
                new Testcases(
                        new BoardValidationException("Row 1 is null"),
                        "null row check",
                        new boolean[][]{{true}, null}
                ),
                new Testcases(
                        new BoardValidationException("Input board has inconsistent number of columns at row 1"),
                        "wrong board check",
                        new boolean[][]{{true}, {}}
                ),
                new Testcases(
                        new BoardValidationException("Input board has inconsistent number of columns at row 2"),
                        "wrong board check2",
                        new boolean[][]{{true}, {true}, {}}
                ),
                new Testcases(
                        new BoardValidationException("Input board has inconsistent number of columns at row 1"),
                        "wrong board check3",
                        new boolean[][]{{false, false}, {false, false, true}}
                )
        );
        for (Testcases test: testcases) {
            test.run();

        }
    }
}