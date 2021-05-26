 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      06-Client.java 
 * Copyright: By Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.*;
import java.net.*;

class Client {
    public static void main(String ar[]) throws Exception {
        Socket s = new Socket("localhost", 1234);
        PrintWriter p = new PrintWriter(s.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader ink = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("How many numbers to sort? ");
        int num = Integer.parseInt(ink.readLine());
        p.println(num);
        p.flush();
        System.out.println("Enter " + num + " numbers to sort :");
        String sarr[] = new String[num];
        for (int i = 0; i < num; i++) {
            sarr[i] = ink.readLine();
            p.println(sarr[i]);
            p.flush();
        }
        String res;
        System.out.println("\nSorted array::\n");
        while ((res = in.readLine()) != null) {
            System.out.println(res);
        }
        s.close();
    }
}
