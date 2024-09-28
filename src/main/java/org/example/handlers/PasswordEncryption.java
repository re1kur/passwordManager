package org.example.handlers;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncryption {
    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 32;
    private Cipher cipher;

    public PasswordEncryption() {
        try {
            cipher = Cipher.getInstance(ALGORITHM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Шифрование пароля
    public String encrypt(String password) {
        try {
            Key key = generateKey();
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedPassword = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedPassword);
        } catch (Exception e) {
            System.err.println("Ошибка при шифровании: " + e.getMessage());
            return null;
        }
    }

    // Расшифрование пароля
    public String decrypt(String encryptedPassword) {
        try {
            Key key = generateKey();
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedPassword = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
            return new String(decryptedPassword);
        } catch (Exception e) {
            System.err.println("Ошибка при шифровании: " + e.getMessage());
            return null;
        }
    }

    private Key generateKey() throws Exception {
        // Генерируем ключ для шифрования
        return new SecretKeySpec(new byte[KEY_SIZE], ALGORITHM);
    }
}

