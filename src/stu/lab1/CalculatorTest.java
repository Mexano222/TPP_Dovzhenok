package stu.lab1;

public class CalculatorTest {
    Calculator calculator = new Calculator();

    public void testPlus() throws TestException {
        int result = calculator.plus(2, 2);
        if (result != 4) {
            throw new TestException("2+2 must be 4");
        }
    }

    public void testMinus() throws TestException {
        int result = calculator.subtract(4, 2);
        if (result != 2) {
            throw new TestException("4-2 must be 2");
        }
    }

    //Shouldn't be triggered with appropriate warning. Test name should start with "test"
    public void tesMinus() throws TestException {
        int result = calculator.subtract(4, 2);
        if (result != 2) {
            throw new TestException("4-2 must be 2");
        }
    }

    //Should fail with appropriate error because of a bug in dovzhenok.lab1.Calculator.multiply
    public void testMultiply() throws TestException {
        int result = calculator.multiply(3, 3);
        if (result != 9) {
            throw new TestException("3*3 must be 9");
        }
    }

    //---------------------------------------------------------------------------------------------
    //Shouldn't be triggered with appropriate warning. Test modifier must be "public"
    private void testNotPublic() throws TestException {
        throw new TestException("modifier must be \"public\"");
    }

    //Shouldn't be triggered with appropriate warning. Test must not accept any arguments
    public void testWithArguments(int n) throws TestException {
        throw new TestException("must not accept any arguments");
    }

    //Shouldn't be triggered with appropriate warning. Test shouldn't return value
    public int testNotVoid() throws TestException {
        throw new TestException("shouldn't return value");
    }
}