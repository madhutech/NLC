package com.ncr;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.ncr.InputValue.*;
import static com.ncr.Operation.*;

public class Calculate {

    private static final String WHITESPACE = " ";

    public static double parseInputString(String userInput) {
        List<String> list = new LinkedList<>(Arrays.asList(userInput.toUpperCase().split(WHITESPACE)));
        while (list.size() > 1) {

            if (list.indexOf(MULTIPLY.getValue()) > 0) {
                calculateAndCondenseList(list, list.indexOf(MULTIPLY.getValue()));
                continue;
            }
            if (list.indexOf(TIMES.getValue()) > 0) {
                calculateAndCondenseList(list, list.indexOf(TIMES.getValue()));
                continue;
            }
            if (list.indexOf(DIVIDE.getValue()) > 0) {
                calculateAndCondenseList(list, list.indexOf(DIVIDE.getValue()));
                continue;
            }
            if (list.indexOf(OVER.getValue()) > 0) {
                calculateAndCondenseList(list, list.indexOf(OVER.getValue()));
                continue;
            }
            if (list.indexOf(SUBTRACT.getValue()) > 0) {
                calculateAndCondenseList(list, list.indexOf(SUBTRACT.getValue()));
                continue;
            }
            if (list.indexOf(MINUS.getValue()) > 0) {
                calculateAndCondenseList(list, list.indexOf(MINUS.getValue()));
                continue;
            }
            if (list.indexOf(LESS.getValue()) > 0) {
                calculateAndCondenseList(list, list.indexOf(LESS.getValue()));
            }
            if (list.indexOf(ADD.getValue()) > 0) {
                calculateAndCondenseList(list, list.indexOf(ADD.getValue()));
                continue;
            }
            if (list.indexOf(PLUS.getValue()) > 0) {
                calculateAndCondenseList(list, list.indexOf(PLUS.getValue()));
            }
        }
        return roundToTwoDecimal(Double.valueOf(list.get(0)));
    }

    private static void calculateAndCondenseList(List<String> list, int i) {
        double result;
        if (isOfInputValueType(list.get(i - 1)) && isOfInputValueType(list.get(i + 1))) {
            result = calculate(Operation.valueOf(list.get(i)), InputValue.valueOf(list.get(i - 1)),
                    InputValue.valueOf(list.get(i + 1)));

        } else if (isOfInputValueType(list.get(i - 1)) && !isOfInputValueType(list.get(i + 1))) {
            result = calculate(Operation.valueOf(list.get(i)), InputValue.valueOf(list.get(i - 1)),
                    Double.valueOf(list.get(i + 1)));
        } else if (!isOfInputValueType(list.get(i - 1)) && isOfInputValueType(list.get(i + 1))) {
            result = calculate(Operation.valueOf(list.get(i)), Double.valueOf(list.get(i - 1)),
                    InputValue.valueOf(list.get(i + 1)));
        } else {
            result = calculate(Operation.valueOf(list.get(i)), Double.valueOf(list.get(i - 1)),
                    Double.valueOf(list.get(i + 1)));

        }
        list.remove(i);
        list.remove(i);
        list.remove(i - 1);
        list.add(i - 1, String.valueOf(result));
    }

    protected static double calculate(Operation operation, InputValue firstOperand, InputValue secondOperand) {
        return calculate(operation, firstOperand.getValue(), secondOperand.getValue());
    }

    private static double calculate(Operation operation, InputValue firstOperand, double secondOperand) {
        return calculate(operation, firstOperand.getValue(), secondOperand);
    }

    private static double calculate(Operation operation, double firstOperand, InputValue secondOperand) {
        return calculate(operation, firstOperand, secondOperand.getValue());
    }

    private static double calculate(Operation operation, double firstOperand, double secondOperand) {
        double result = 0D;
        switch (operation) {
            case TIMES:
            case MULTIPLY:
                result = multiply(firstOperand, secondOperand);
                break;

            case OVER:
            case DIVIDE:
                result = (divide(firstOperand, secondOperand));
                break;

            case LESS:
            case MINUS:
            case SUBTRACT:
                result = subtract(firstOperand, secondOperand);
                break;

            case PLUS:
            case ADD:
                result = add(firstOperand, secondOperand);
                break;

            default:
                System.out.println("invalud operation");
        }
        return result;

    }

    private static boolean firstOperandGreaterOrEqualToSecond(InputValue firstInputOperand,
                                                              InputValue secondInputOperand) {
        return firstInputOperand.getValue() >= secondInputOperand.getValue();
    }

    private static double add(double firstOperand, double secondOperand) {
        return firstOperand + secondOperand;
    }

    private static double subtract(double firstOperand, double secondOperand) {
        return firstOperand - secondOperand;
    }

    private static double multiply(double firstOperand, double secondOperand) {
        return firstOperand * secondOperand;
    }

    private static double divide(double firstOperand, double secondOperand) {
        return firstOperand / secondOperand;
    }

    private static double roundToTwoDecimal(double input) {
        return (double) Math.round(input * 100) / 100;
    }

    private static boolean isOfInputValueType(String operand) {
        boolean isInputValueType = false;
        if (operand.equals(ZERO.name())
                || operand.equals(ONE.name())
                || operand.equals(TWO.name())
                || operand.equals(THREE.name())
                || operand.equals(FOUR.name())
                || operand.equals(FIVE.name())
                || operand.equals(SIX.name())
                || operand.equals(SEVEN.name())
                || operand.equals(EIGHT.name())
                || operand.equals(NINE.name())
                || operand.equals(TEN.name())) {
            isInputValueType = true;
        }
        return isInputValueType;
    }
}
