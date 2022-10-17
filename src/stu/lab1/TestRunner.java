package stu.lab1;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestRunner {

    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Exactly 1 argument must be provided");
        }
        Class<?> testClass = Class.forName(args[0]);
        System.out.println("Class " + testClass.getName() + " successfully loaded");
        //Get all methods of given class
        Method[] testMethods = testClass.getDeclaredMethods();
        //Loop through all methods of given class
        int totalTests = 0, totalSuccessful = 0, totalFailed = 0;
        for (Method method : testMethods) {
            //Checking if the method meets the requirements
            if (!isTestMethod(method)) {
                System.out.printf("\tMethod: %s is not test method\n", method.getName());
                continue;
            }
            //If method meets the requirements invoke test
            totalTests++;
            if (invokeTestMethod(method))
                totalSuccessful++;
            else
                totalFailed++;
        }
        System.out.printf("\tTotal methods: %d Total tests: %d Successful tests: %d Failed tests: %d\n", testMethods.length, totalTests, totalSuccessful, totalFailed);
    }

    //Check whether the given method is a test method or a regular one
    private static boolean isTestMethod(Method method) {
        return method.getName().startsWith("test") &&
                method.getModifiers() == Modifier.PUBLIC &&
                method.getParameters().length == 0 &&
                method.getGenericReturnType() == Void.TYPE;
    }

    //Creates an instance of the given class and invokes the method
    private static boolean invokeTestMethod(Method method) {
        String result = "\tTest: " + method.getName() + " ";
        try {
            method.invoke(method.getDeclaringClass().getDeclaredConstructor().newInstance());
            result += "SUCCESSFUL";
            System.out.println(result);
            return true;
        } catch (Exception e) {
            result += "FAILED, error: " + e.getCause().getMessage();
            System.out.println(result);
            return false;
        }
    }
}