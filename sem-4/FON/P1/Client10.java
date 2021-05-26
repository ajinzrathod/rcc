 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      Client10.java 
 * Copyright: By Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Client10 {
  private static String source = "1";
  private static String destination = "2";

  public static void main(String args[]) throws Exception {
    String sentence;
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    Socket clientSocket = new Socket(InetAddress.getLocalHost(), 6789);
    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
    DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());

    sentence = inFromUser.readLine();

    byte[] sourceAddress = Arrays.copyOf(source.getBytes(), 10);
    byte[] destinationAddress = Arrays.copyOf(destination.getBytes(), 10);
    byte[] data = Arrays.copyOf(sentence.getBytes(), 50);
    byte[] packet = ByteBuffer.allocate(70).put(sourceAddress).put(destinationAddress).put(data).array();

    outToServer.write(packet);

    clientSocket.close();
  }
}
