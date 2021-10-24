package bsr;
import java.util.*;
import java.net.*;
import java.io.*;
/*A code from www.bipinrupadiya.com*/
public class SBOX
{
 public String doEncryption(String s)// throws Exception
 {
  int i,temp;
 

  int plain=Integer.parseInt(s);

  BasicOperation b=new BasicOperation();
  int dec=b.binaryToDecimal(plain);
  String encryptedString="";
  if(dec < 8)
  {
   System.out.println("\n Decimal no :"+dec);
   int base[]=new int[8];
   for(i=0;i<8;i++)
   {
    base[i]=(i==dec)?1:0;
   }

   int p[]=new int[8];
   int pTemp[]=new int[8];

   for(i=0;i<8;i++)
   {
    p[i]=base[i];
    pTemp[i]=base[i];
   }

   //apply sender's pbox
   p[0]=pTemp[2];
   p[1]=pTemp[4];
   p[2]=pTemp[5];
   p[3]=pTemp[0];
   p[4]=pTemp[6];
   p[5]=pTemp[7];
   p[6]=pTemp[1];
   p[7]=pTemp[3];
    
   for(i=0;i<8;i++)
   {
     if(p[i]==1)
    break;
   }  
   b.decimalToBinary(i);

   b.displayBinaryArray();
   int length=b.getLen();
   int bin1[]=new int[length];
   encryptedString=new String("");

   for(i=0;i<length;i++)
   { 
    bin1[i]=b.binaryArrayAtPosition(i);
    encryptedString+=bin1[i];
   }
  }
  else
  {
   System.out.println("\n Please enter 3 digit in binary Form");
    //throw user define error here
   
  }  
  return(encryptedString);
 }
 public String doDecryption(String s)
 {
  int i,temp;
  //char str2char[]=new char[(s.length()/2)];
  char str2char[]=new char[(s.length())];
  int cnt=-1;
  for(i=0;i<s.length();i++)
  {
   // Empty space is denoted 0 and no is in ascii.
   if((int)s.charAt(i)!=0)
   {
    cnt++;
    str2char[cnt]=s.charAt(i);
   }
  } 
    
  int chipher=Integer.parseInt(new String(str2char));
  
  System.out.println("\n No :"+chipher);

  BasicOperation b=new BasicOperation();
  int dec=b.binaryToDecimal(chipher);
  System.out.println("\n Decimal no :"+dec);


  int base[]=new int[8];
  for(i=0;i<8;i++)
  {
   base[i]=(i==dec)?1:0;
  }

  int p[]=new int[8];
  int pTemp[]=new int[8];

  for(i=0;i<8;i++)
  {
    p[i]=base[i];
    pTemp[i]=base[i];
  }

  //apply receiver's pbox.
  p[0]=pTemp[3];
  p[1]=pTemp[6];
  p[2]=pTemp[0];
  p[3]=pTemp[7];
  p[4]=pTemp[1];
  p[5]=pTemp[2];
  p[6]=pTemp[4];
  p[7]=pTemp[5];
  
  for(i=0;i<8;i++)
  {
   if(p[i]==1)
    break;
  }  

  b.decimalToBinary(i);
  b.displayBinaryArray();
  
  int length=b.getLen();
  int c[]=new int[length];
  
  String decryptedString=new String("");
  
  for(i=0;i<length;i++)
  { 
   decryptedString+=b.binaryArrayAtPosition(i);
  }
    
  return(new String(decryptedString));
 }
}