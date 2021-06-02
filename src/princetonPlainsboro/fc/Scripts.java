/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package princetonPlainsboro.fc;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Go
 */
public class Scripts {
    
    private static final SecureRandom RAND = new SecureRandom();
    private static final int ITERATIONS = 100;
    private static final int KEY_LENGTH = 128;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    public static Optional<String> generateSalt (final int length) {

      if (length < 1) {
        System.err.println("error in generateSalt: length must be > 0");
        return Optional.empty();
      }

      byte[] salt = new byte[length];
      RAND.nextBytes(salt);

      return Optional.of(Base64.getEncoder().encodeToString(salt));
    }
    
    public static Optional<String> hashPassword (String password, String salt) {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
          SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
          byte[] securePassword = fac.generateSecret(spec).getEncoded();
          return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
          System.err.println("Exception encountered in hashPassword()");
          return Optional.empty();

        } finally {
          spec.clearPassword();
        }
    }
    
    public static boolean verifyPassword (String password, String key, String salt) {
        Optional<String> optEncrypted = hashPassword(password, salt);
        if (!optEncrypted.isPresent()) return false;
        return optEncrypted.get().equals(key);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String salt = generateSalt(512).get();
        String password = "lala";
        String key = hashPassword(password, Salt.SALT).get();
        System.out.println(key);
        System.out.println(verifyPassword("toto", key, Salt.SALT));
        System.out.println(verifyPassword("dqlkdkql", key, Salt.SALT));
    }
    
}
