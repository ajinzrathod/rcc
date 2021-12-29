import java.util.Scanner;
import java.lang.Math;


public class RsaAlgorithm {
    private long d = -1;
    private long n;

    public String readString() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String you wanna encrypt");
        String string = sc.nextLine();
        return string;
    }

    public long generate1stPrimeNumber() {
        return 13L;
    } 
    public long generate2ndPrimeNumber() {
        return 17L;
    } 

    public Long gcd(Long n1, Long n2) {
        if(n2 == 0) 
            return n1;
        return gcd(n2, n1 % n2);
    }

    public long findPrivateKey(long publicKey, long phiOfN){
        double k = 0;

        double phi = Double.valueOf(phiOfN);
        double e = Double.valueOf(publicKey); 

        double private_key = -1;
        while(k < phi){
            private_key = (1 + (k * phi)) / e;
            if(private_key % 1 == 0) {
                break;
            }
            else {
                k++;
            }
        }

        this.d = (long) private_key;
        return this.d;
    }

    public String encryptStringUsingRSA(String plainText) {
        long p = generate1stPrimeNumber();
        long q = generate2ndPrimeNumber();
        this.n = p * q;
        long phiOfN = (p - 1) * (q - 1);
        
        // public key
        long e = 2;
        while(e < phiOfN) {
            if(gcd(e, phiOfN) == 1)
                break;            
            else
                e++;
        }

        this.d = findPrivateKey(e, phiOfN);

        System.out.println("p:" + p);
        System.out.println("q:" + q);
        System.out.println("n:" + this.n);
        System.out.println("phiOfN:" + phiOfN);
        System.out.println("d:" + d);
        System.out.println("e:"+ e);

        double msg = 2;
        double encryptedString = Math.pow(msg, e);
        encryptedString = encryptedString % this.n;
        System.out.println(encryptedString);

        return String.valueOf(encryptedString);
    }

    public String decryptStringUsingRSA(String encryptedString){
        double encrypted = Double.valueOf(encryptedString);
        double decrypted = Math.pow(encrypted, this.d);
        decrypted = decrypted % this.n;
        System.out.println(decrypted);
        return String.valueOf(decrypted);
    }

    public static void main(String[] args) {
        RsaAlgorithm rsa = new RsaAlgorithm();
        // String plainText = rsa.readString();
        String plainText = "20";
        String encryptedString = rsa.encryptStringUsingRSA(plainText);
        System.out.println("encryptedString: " + encryptedString);
        
        String decryptedString = rsa.decryptStringUsingRSA(encryptedString);
        System.out.println("decryptedString:" +  decryptedString);
    }    
}