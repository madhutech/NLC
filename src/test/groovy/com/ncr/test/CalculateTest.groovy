package com.ncr.test

import spock.lang.Specification
import spock.lang.Unroll

import static com.ncr.Calculate.parseInputString
import static com.ncr.InputValue.FIVE
import static com.ncr.InputValue.FOUR
import static com.ncr.InputValue.NINE
import static com.ncr.InputValue.TEN
import static com.ncr.InputValue.THREE
import static com.ncr.InputValue.ZERO
import static com.ncr.InputValue.TWO
import static com.ncr.Operation.ADD

import static com.ncr.Calculate.calculate
import static com.ncr.Operation.DIVIDE
import static com.ncr.Operation.LESS
import static com.ncr.Operation.MINUS
import static com.ncr.Operation.MULTIPLY
import static com.ncr.Operation.OVER
import static com.ncr.Operation.PLUS
import static com.ncr.Operation.SUBTRACT
import static com.ncr.Operation.TIMES

class CalculateTest extends Specification {

    @Unroll
    def '#operation #inputOperand1 & #inputOperand2 is #expectedOutput'() {

        when:
        double actualOutput = roundToTwoDecimal(calculate(operation, inputOperand1, inputOperand2))

        then:
        actualOutput == expectedOutput
        print("result = $actualOutput")

        where:
        operation | inputOperand1 | inputOperand2 || expectedOutput
        ADD       | TWO           | FOUR          || 6
        ADD       | TWO           | TEN           || 12
        SUBTRACT  | TEN           | THREE         || 7
        SUBTRACT  | THREE         | TEN           || -7
        MULTIPLY  | TEN           | THREE         || 30
        MULTIPLY  | ZERO          | TEN           || 0
        DIVIDE    | TEN           | FIVE          || 2
        DIVIDE    | NINE          | THREE         || 3

        /*permitted aliases test*/
        PLUS      | TWO           | TWO           || 4
        PLUS      | TWO           | TEN           || 12
        MINUS     | TEN           | THREE         || 7
        LESS      | THREE         | NINE          || -6
        TIMES     | TEN           | THREE         || 30
        TIMES     | ZERO          | TEN           || 0
        OVER      | TEN           | FIVE          || 2
        OVER      | NINE          | THREE         || 3
        OVER      | THREE         | NINE          || 0.33 as double
        OVER      | THREE         | TEN           || 0.30 as double
    }

    @Unroll
    def '#inputString is #expectedOutput'() {
        when:
        def actualOutput = parseInputString(inputString)

        then:
        actualOutput == expectedOutput
        print("result = $actualOutput")

        where:
        inputString                                        || expectedOutput
        'one multIPly six'                                 || 6
        'oNE pLUs sIX'                                     || 7
        'one plus two times three'                         || 7
        'four minus eight plus six times nine'             || 50
        'seven over nine plus two'                         || 2.78
        'nine over eight plus four times two divide three' || 3.79
        'nine minus three times seven'                     || -12
    }

    private static double roundToTwoDecimal(double input) {
        return (double) Math.round(input * 100) / 100
    }
}
