package com.company;

public interface Operation {
    double perform(double a, double b);
}
class Addition implements Operation {
    public double perform(double a, double b) {
        return a + b;
    }
}

class Subtraction implements Operation {
    public double perform(double a, double b) {
        return a - b;
    }
}

class Multiplication implements Operation {
    public double perform(double a, double b) {
        return a * b;
    }
}

class Division implements Operation {
    public double perform(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }
}
