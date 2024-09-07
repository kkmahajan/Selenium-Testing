package test;

public class UtilsTest {

    public static void waitForASec() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.getLocalizedMessage();
        }
    }
}
