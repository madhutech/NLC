package com.nlc.test

import spock.lang.Specification
import spock.lang.Unroll

import static com.nlc.cal.Calculate.parseInputString
import static com.nlc.cal.InputValue.FIVE
import static com.nlc.cal.InputValue.FOUR
import static com.nlc.cal.InputValue.NINE
import static com.nlc.cal.InputValue.TEN
import static com.nlc.cal.InputValue.THREE
import static com.nlc.cal.InputValue.ZERO
import static com.nlc.cal.InputValue.TWO
import static com.nlc.cal.Operation.ADD

import static com.nlc.cal.Calculate.calculate
import static com.nlc.cal.Operation.DIVIDE
import static com.nlc.cal.Operation.LESS
import static com.nlc.cal.Operation.MINUS
import static com.nlc.cal.Operation.MULTIPLY
import static com.nlc.cal.Operation.OVER
import static com.nlc.cal.Operation.PLUS
import static com.nlc.cal.Operation.SUBTRACT
import static com.nlc.cal.Operation.TIMES

class CalculateTest extends Specification {

    @Unroll
    def '#operation #inputOperand1 and #inputOperand2 is #expectedOutput'() {

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
