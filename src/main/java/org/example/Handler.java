package org.example;

import java.util.Random;

public class Handler {
    private static String currentLogin;
    private static String[] seedsArray;
    private static int seedNumber;

    public static String getRandInt () {
        Random random = new Random();
        setSeedNumber(random.nextInt(10));
        return String.valueOf(getSeedNumber() + 1);
    }
    public static int getSeedNumber () {
        return seedNumber;
    }
    private static void setSeedNumber(Integer number) {
        seedNumber = number;
    }
    public static void generateRandomWords() {
        String[] randomStrings = new String[10];
        Random random = new Random();
        for(int i = 0; i < 10; i++) {
            char[] word = new char[random.nextInt(8)+3];
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        setSeeds(randomStrings);
    }

    public static String[] getSeeds () {
        return seedsArray;
    }
    private static void setSeeds (String[] seeds) {
        seedsArray = seeds;
    }
    public static void setCurrentLogin(String log) {
        currentLogin = log;
    }
    public static String getCurrentLogin() {
        return currentLogin;
    }
}
