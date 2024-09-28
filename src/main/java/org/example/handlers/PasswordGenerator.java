package org.example.handlers;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final int MIN_PASSWORD_LENGTH = 8; // минимальная длина пароля
    private static final int MAX_PASSWORD_LENGTH = 20; // максимальная длина пароля

    public String generatePassword(int passwordLength) {
        if (passwordLength < MIN_PASSWORD_LENGTH || passwordLength > MAX_PASSWORD_LENGTH) {
            throw new NumberFormatException("Length password must be from " + MIN_PASSWORD_LENGTH + " to " + MAX_PASSWORD_LENGTH);
        }

        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_', '-', '#', '!', '@', '$', '*', ')', '(', '%', '=', '+', '?',
                '[', ']', '/', '\\', '}', '{'};

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            int index = random.nextInt(chars.length);
            password.append(chars[index]);
        }

        return password.toString();
    }
}

