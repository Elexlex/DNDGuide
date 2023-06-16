package module;

import DnD.EvaluateController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class EvaluateControllerTest {

    @Test
    void testEvaluate_Addition() {
        String expression = "2 + 3 + 4";
        int expected = 9;
        int result = EvaluateController.evaluate(expression);
        assertEquals(expected, result);
    }

    @Test
    void testEvaluate_Subtraction() {
        String expression = "10 - 5 - 3";
        int expected = 2;
        int result = EvaluateController.evaluate(expression);
        assertEquals(expected, result);
    }

    @Test
    void testEvaluate_DiceRoll() {
        String expression = "2D6 + 3D4";
        int result = EvaluateController.evaluate(expression);
        // Since the result of dice rolls is random, we can only check if the result is within a valid range
        int minExpected = 5; // 2 * (1 + 1) + 3 * (1 + 1)
        int maxExpected = 20; // 2 * (6 + 1) + 3 * (4 + 1)
        assertTrue(result >= minExpected && result <= maxExpected);
    }

    @Test
    void testEvaluate_MixedExpression() {
        String expression = "2D6 + 3 - 2D4";
        int result = EvaluateController.evaluate(expression);
        // Since the result of dice rolls is random, we can only check if the result is within a valid range
        int minExpected = -9; // 2 * (1 + 1) + 3 - 2 * (4 + 1)
        int maxExpected = 10; // 2 * (6 + 1) + 3 - 2 * (1 + 1)
        assertTrue(result >= minExpected && result <= maxExpected);
    }

    @Test
    void testEvaluate_SingleNumber() {
        String expression = "10";
        int expected = 10;
        int result = EvaluateController.evaluate(expression);
        assertEquals(expected, result);
    }
}