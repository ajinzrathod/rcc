import bsr.*;
/*A code from www.bipinrupadiya.com*/
public class sender
{
 public static void main(String args[])throws Exception
 {
  
  
  /*
  // read data from user 
  Scanner sc=new Scanner(System.in);
  System.out.println("Enter String (Three bit binnary) :");
  myString=sc.next();
  
  */
  
  SBOX obj =new SBOX();
  String myString="111";
  String encryptedString=obj.doEncryption(myString);
  System.out.println("\nEncryted String : "+encryptedString);
  MySocket m = new MySocket();
  m.sendFrame(encryptedString);
 }

}