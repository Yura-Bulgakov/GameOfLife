package ru.game;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;

class ConsoleGameBoardInitializerTest {

    private final InputStream originalSystemIn = System.in;
    private ConsoleGameBoardInitializer initializer;

    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @BeforeEach
    public void setUp() {
        initializer = new ConsoleGameBoardInitializer();
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalSystemIn);
    }

    @Test
    void initializeGameBoard() throws Exception {
        @AllArgsConstructor
        class Testcases{
            String name;
            String sizeIn;
            String cellsIn;
            boolean[][] out;
            public void run() throws Exception {
                provideInput(sizeIn);
                provideInput(cellsIn);
                withTextFromSystemIn(sizeIn +"\n"+ cellsIn)
                        .execute(() -> Assertions.assertArrayEquals(initializer.initializeGameBoard(), out, name));
//                try {
//                    boolean[][] resultBoard = initializer.initializeGameBoard();
//                    Assertions.assertArrayEquals(resultBoard, out, name);
//                } finally {
//                    System.setIn(originalSystemIn);
//                }
            }
        }
        List<Testcases> testcases = Arrays.asList(
                new Testcases("1",
                        "3 5",
                        "0,0 0,1 0,2",
                        ArrayConvertor.convertStringToArray(
                                "111\n" +
                                    "000\n" +
                                    "000\n" +
                                    "000\n" +
                                    "000\n"
                        ))
        );
        for (Testcases test:testcases) {
            test.run();
        }
    }
}