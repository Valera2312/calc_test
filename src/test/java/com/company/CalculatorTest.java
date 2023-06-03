package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    @DisplayName("Тестирование калькулятора")
    public void calculatorTest() {
        assertThat(calculator.calculate("(4*6)-2")).isEqualTo(22);

        assertThat(calculator.calculate("10/(5+2)")).isEqualTo(1.4285714285714286);

        assertThatThrownBy(() -> calculator.calculate("(2(+2)*2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unbalanced parentheses");

        assertThatThrownBy(() -> calculator.calculate("(2+2)+"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid expression");

        assertThatThrownBy(() -> calculator.calculate("(2&2)"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid operator: &");

    }

}