package util;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Utils {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CHARACTERS_LENGTH = CHARACTERS.length();

    public static String generateRandomText(int length) {
        Random random = new Random();
        StringBuilder randomText = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS_LENGTH);
            randomText.append(CHARACTERS.charAt(randomIndex));
        }
        return randomText.toString();
    }

    @NotNull
    public static String captureScreenshotAndSaveInLocal(String fileName, WebDriver chromeDriver) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) chromeDriver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File outputFile = new File(System.getProperty("user.dir") + "\\Screenshots\\" + fileName);
        String destination = System.getProperty("user.dir") + "\\Screenshots\\" + fileName;
        System.out.println("Output File Path : "+outputFile.getAbsolutePath());
        System.out.println("Output File Path 2 : "+destination);
        try {
            FileUtils.copyFile(sourceFile, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }

    public static String captureScreenshotBase64(WebDriver chromeDriver) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) chromeDriver;
        return takesScreenshot.getScreenshotAs(OutputType.BASE64);
    }
}