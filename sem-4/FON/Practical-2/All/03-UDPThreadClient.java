import java.net.*;
import java.io.*;

public class UDPThreadClient {
    public static void main(String[] args) throws Exception {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress ipAddress = InetAddress.getLocalHost();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage = "";

            while (!clientMessage.equals("bye")) {
                System.out.println("Enter number :");
                clientMessage = br.readLine();
                byte[] sendData = clientMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 5678);
                clientSocket.send(sendPacket);
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String result = (new String(receivePacket.getData())).trim();
                System.out.println(result);
            }
            clientSocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}