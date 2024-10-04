import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Java program that implements the famous caeser cipher.
 * Asks user if they want to encrypt or decrypt text file.
 * 
 * 
 * @author Bjorn N. Talastas
 */
public class CaeserCipher {
    private static int shift;

    /**
     * Asks user for text file for encrpytion.
     * 
     * @return text file as string
     */
    public static String getTextString() {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(System.in)) {

            String fileName = "";
            System.out.println("Enter text file you would like to encrypt.");
            fileName = scanner.next();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return sb.toString();
    }

    /**
     * Transforms the text file using caeser cipher encrpytion.
     * Loops through text and shifts the current character by whatever value defined
     * by user.
     * Wraps around alphabet if shifted character goes past the last letter in the
     * alphabet.
     * 
     * @param text  text file user wants to encrypt.
     * @param shift value defined by user.
     * @return transformed text after encrypting with caeser cipher.
     */
    public static String performCaeserEncryption(String text, int shift) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);

            if (character >= 'A' && character <= 'Z') {
                char shiftedChar = (char) (((character - 'A' + shift) % 26) + 'A');
                sb.append(shiftedChar);
            } else
                sb.append(character);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean choice = false;

            while (!choice) {
                System.out.println("Would you like to encrypt or decrypt your file?\n1: Encrypt\n2: Decrypt");
                switch (scanner.nextInt()) {
                    case 1:

                        String text = null;
                        String encryptedString = null;
                        boolean shiftChoice = false;

                        while (!shiftChoice) {
                            System.out.println("Enter shift (1-25): ");
                            shift = scanner.nextInt();

                            if (shift < 1 || shift > 25) {
                                System.out.println("Invalid shift");
                            } else {
                                shiftChoice = true;
                            }
                        }

                        text = getTextString().toUpperCase();
                        System.out.println(text);
                        encryptedString = performCaeserEncryption(text, shift);
                        System.out.println(encryptedString);
                        choice = true;
                        break;
                    case 2:
                        choice = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.\n");
                }
            }
        }

    }
}