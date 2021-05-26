 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      03-MultiThreadedSocketServer.java 
 * Copyright: By Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.net.*;

public class MultiThreadedSocketServer {
    public static void main(String[] args) throws Exception {
        try {
            DatagramSocket serverSocket = new DatagramSocket(5678);
            System.out.println("Listening on Port 5678...");
            ServerClientThread sct = new ServerClientThread(serverSocket);
            sct.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}