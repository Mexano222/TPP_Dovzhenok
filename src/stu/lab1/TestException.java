package stu.lab1;

public class TestException extends Throwable {
    public TestException(String s) {
        super("Test failed due to: " + s);
    }
}
