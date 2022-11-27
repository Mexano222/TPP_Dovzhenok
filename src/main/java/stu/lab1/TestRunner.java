package stu.lab1;

public class TestRunner {
    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Exactly 1 argument must be provided");
        }
        Class<?> testClass = Class.forName(args[0]);
/**
 * Add code here
 */
    }
}