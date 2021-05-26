 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      10-SAWServer.java 
 * Copyright: By Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.*;
import java.net.*;
import java.util.*;

public class SAWServer {
    public static void main(String args[]) {
        String h = "Serverhost";
        int q = 5000;
        int i;
        try {
            ServerSocket ss2;
            ss2 = new ServerSocket(8000);
            Socket s1 = ss2.accept();
            DataInputStream dd1 = new DataInputStream(s1.getInputStream());
            Integer i1 = dd1.read();
            for (i = 0; i < i1; i++) {
                ServerSocket ss1;
                ss1 = new ServerSocket(9000 + i);
                Socket s = ss1.accept();
                DataInputStream dd = new DataInputStream(s.getInputStream());
                String sss1 = dd.readUTF();
                System.out.println(sss1);
                System.out.println("Frame " + i + " received");
                DataOutputStream d1 = new DataOutputStream(s.getOutputStream());
                d1.write(i);
                System.out.println("ACK sent for " + i);
                ss1.close();
                ss2.close();
            }
        } catch (Exception ex) {
            System.out.println("Error" + ex);
        }
    }
}