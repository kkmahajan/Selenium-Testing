package orangehrm;

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
}
