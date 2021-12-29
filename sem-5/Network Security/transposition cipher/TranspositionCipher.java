import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;


class TranspositionCipher {
    public static String getCipherKey() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Jai Swaminarayan: Enter Cipher Key: ");

        String cipherKey = sc.nextLine();
        return cipherKey;
    }

    public static int getRowLength(String plainText, String cipherKey) {
        System.out.println(plainText.length());
        System.out.println(cipherKey.length());
        double rowLength = Double.valueOf(plainText.length()) / Double.valueOf(cipherKey.length());
        int rowLengthConstant = (int) Math.ceil(rowLength);
        return rowLengthConstant;
    }

    public static int getColLength(String cipherKey) {
        return cipherKey.length();
    }

    public static void printTransposeMatrix(String[][] transposeMatrix, int rowLength, int colLength) {
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                System.out.print(transposeMatrix[i][j]);
            }
            System.out.println();
        }
    }

    public static String[][] insertInTransposeMatrix(String plainText, int rowLength, int colLength) {
        String[][] transposeMatrix = new String[rowLength][colLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                try {
                    String currentCharacter = String.valueOf(plainText.charAt((colLength * i) + j));
                    if (currentCharacter.equals(" ")) {
                        transposeMatrix[i][j] = "_";
                    } else {
                        transposeMatrix[i][j] = currentCharacter;
                    }
                } catch (Exception e) {
                    transposeMatrix[i][j] = "@";
                }
            }
        }
        return transposeMatrix;
    }
    
    public static String sortString(String inputString)
    {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public static String generateEncryptedString(String sortedCipherKey, String cipherKey, String [][] transposeMatrix, int rowLength) {
        String encryptedString = "";
        for (int i = 0; i < sortedCipherKey.length(); i++) {
            char c1  = sortedCipherKey.charAt(i);
            
            for (int j = 0; j < cipherKey.length(); j++) {
                char c2  = cipherKey.charAt(j);   
                if(c1 == c2) {
                    for (int k = 0; k < rowLength; k++) {
                        encryptedString += transposeMatrix[k][j];
                    }
                    break;
                }
            }
        }
        return encryptedString;
    }
    
    public static String generateDecryptedString(String encryptedText, String cipherKey) {
        String sortedCipherKey = sortString(cipherKey);

        int rowLength = getRowLength(encryptedText, cipherKey);
        int colLength = getColLength(cipherKey);
        
        String[][] transposeMatrix = new String[rowLength][colLength];
        
        int a = 0;

        for (int i = 0; i < sortedCipherKey.length(); i++) {
            char c1  = sortedCipherKey.charAt(i);
            
            for (int j = 0; j < cipherKey.length(); j++) {
                if(c1 == cipherKey.charAt(j)) {
                    for (int k = 0; k < rowLength; k++) {
                        transposeMatrix[k][j] = String.valueOf(encryptedText.charAt(a));
                        a++;
                    }
                    break;
                }
            }

        }
        printTransposeMatrix(transposeMatrix, rowLength, colLength);

        String originalString = "";
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                originalString += transposeMatrix[i][j];
            }
        }
        return originalString;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking String
        System.out.println("Jai Swaminarayan: Enter A string: ");
        String plainText = sc.nextLine();

        // Reading Cipher Key
        String cipherKey = getCipherKey();
        sc.close();

        int rowLength = getRowLength(plainText, cipherKey);
        int colLength = getColLength(cipherKey);

        String[][] transposeMatrix = insertInTransposeMatrix(plainText, rowLength, colLength);       

        printTransposeMatrix(transposeMatrix, rowLength, colLength);
        String sortedCipherKey = sortString(cipherKey);
        System.out.println(sortedCipherKey);

        String encryptedText = generateEncryptedString(sortedCipherKey, cipherKey, transposeMatrix, rowLength);
        System.out.println(encryptedText);

        
        System.out.println("---------------- Decrypting -----------");
        String decryptedText = generateDecryptedString(encryptedText, cipherKey);
        System.out.println("Decrypted Text:" + decryptedText);
    }
}