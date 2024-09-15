//package ExtentReports;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//
//public class ExtentReportManager {
//
//    private static ExtentReports extent;
//
//    public static void setupExtentReport(){
//        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
//        extent = new ExtentReports();
//        extent.attachReporter(extentSparkReporter);
//    }
//
//    public static void createTest(String testName){
//        ExtentTest extentTest = extent.createTest(testName);
//    }
//
//    public static ExtentTest getTest(){
//
//    }
//
//    public static ExtentReports getExtentReport(){}
//
//    public static void flushExtentReport(){}
//}
