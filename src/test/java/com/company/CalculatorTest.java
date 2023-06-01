package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    @DisplayName("Тестирование калькулятора")
    public void calculatorTest() {
        assertEquals(22, calculator.calculate("(4*6)-2"));
        assertEquals(1.4285714285714286, calculator.calculate("10/(5+2)"));
        assertThrows(IllegalArgumentException.class,() ->
                calculator.calculate("(2(+2)*2"), "Unbalanced parentheses");
        assertThrows(IllegalArgumentException.class,() ->
                calculator.calculate("(2++2"), "Invalid expression");
        assertThrows(IllegalArgumentException.class,() ->
                calculator.calculate("(2+&2"), "Invalid operator: &");
    }

}