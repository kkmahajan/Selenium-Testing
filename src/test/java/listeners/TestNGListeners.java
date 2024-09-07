package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListeners implements ITestListener {

    public void onStart(ITestResult result) {
        System.out.println("*****Test Suite Started : " + result.getName());
    }

    public void onTestStart(ITestResult result) {
        System.out.println("*****Test Started : " + result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("*****Test Success : " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("*****Test Failed : " + result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("*****Test Skipped : " + result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*****Test Fail but within success percentage : " + result.getName());
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {
        System.out.println("*****Test Completed : " + context.getName());
    }
}
