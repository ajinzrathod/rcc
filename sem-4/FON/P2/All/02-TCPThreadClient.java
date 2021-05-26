 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      02-TCPThreadClient.java 
 * Copyright: By Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.net.*;
import java.io.*;

public class TCPThreadClient {
    public static void main(String[] args) throws Exception {
        try {
            Socket socket = new Socket("127.0.0.1", 5678);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage = "", serverMessage = "";

            while (!clientMessage.equals("bye")) {
                System.out.println("Enter number :");
                clientMessage = br.readLine();
                writer.println(clientMessage);
                writer.flush();
                serverMessage = reader.readLine();
                System.out.println(serverMessage);
            }

            writer.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
