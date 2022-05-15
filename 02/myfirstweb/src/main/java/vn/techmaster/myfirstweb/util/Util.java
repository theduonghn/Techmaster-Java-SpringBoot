package vn.techmaster.myfirstweb.util;

import java.util.List;
import java.util.Random;

public class Util {
    static Random random = new Random();

    public static String generateRandomString(int targetStringLength) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static String getRandomStringFromList(List<String> givenList) {
        String randomElement = givenList.get(random.nextInt(givenList.size()));
        return randomElement;
    }
}
