import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {
    private final int p, q;
    private final int n, phi_n, publicKey, privateKey;

    public RSA() {
        p = getRandomPrime();
        q = getRandomPrime();
        n = p * q;
        phi_n = (p - 1) * (q - 1);
        publicKey = getCoPrime(phi_n);
        privateKey = generatePrivateKey();
    }

    private int generatePrivateKey() {
        int i = 0;
        while (true) {
            double d = (1 + phi_n * i) / (double) publicKey;
            if ((d - (int) d) == 0)
                return (int) d;
            ++i;
        }
    }

    private static boolean isPrime(int number) {
        if (number == 1)
            return false;
        if (number == 2)
            return true;
        if (number % 2 == 0)
            return false;

        for (int i = 3; i <= Math.sqrt(number); i += 2)
            if (number % i == 0)
                return false;
        return true;
    }

    private static int getRandomPrime() {
        while (true) {
            int number = new Random().nextInt(50) + 1;
            if (isPrime(number))
                return number;
        }
    }

    private static boolean isCoPrime(long a, long b) {
        for (int i = 2; i <= a && i <= b; ++i) {
            if (a % i == 0 && b % i == 0)
                return false;
        }
        return true;
    }

    private static int getCoPrime(int phi_n) {
        for (int i = 2; i < phi_n; i++) {
            if (isCoPrime(phi_n, i))
                return i;
        }
        return 1;
    }

    public BigInteger encrypt(int plainText) {
        return new BigInteger(String.valueOf(plainText)).pow(publicKey).mod(new BigInteger(String.valueOf(n)));
    }

    public BigInteger decrypt(int cipherText) {
        return new BigInteger(String.valueOf(cipherText)).pow(privateKey).mod(new BigInteger(String.valueOf(n)));
    }

    public String encrypt(String plainText) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char ch = plainText.charAt(i);
            encryptedMessage.append((char) encrypt(ch).intValue());
        }

        return encryptedMessage.toString();
    }

    public String decrypt(String cipherText) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            encryptedMessage.append((char) decrypt(ch).intValue());
        }

        return encryptedMessage.toString();
    }

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // System.out.print("Enter Plain Text: ");
        // String plainText = sc.nextLine();
        String plainText = "Nirav Chavda";
        RSA rsa = new RSA();
        System.out.println(String.format("p: %d, q: %d", rsa.p, rsa.q));
        System.out.println(String.format("n: %d, phi_n: %d", rsa.n, rsa.phi_n));
        System.out.println(String.format("Private Key: %d, Public Key: %d", rsa.privateKey, rsa.publicKey));

        String cipherText = rsa.encrypt(plainText);
        String decrypted = rsa.decrypt(cipherText);

        System.out.println("Plain Text: " + plainText);
        System.out.println("Cipher Text: " + cipherText);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
