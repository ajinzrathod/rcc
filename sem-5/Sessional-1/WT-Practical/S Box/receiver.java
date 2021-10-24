//receiver Side.
import bsr.*;
public class receiver
{
 public static void main(String args[])throws Exception
 {
   
  SBOX obj =new SBOX();
  MySocket m = new MySocket();
  String encryptedString=m.receiveFrame();
  String decryptedString=obj.doDecryption(encryptedString);
  System.out.println("\nDecryted String : "+decryptedString);  
 }
}
