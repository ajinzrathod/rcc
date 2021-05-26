import java.net.*;
import java.io.*;

class ServerClientThread extends Thread {
    DatagramSocket serverClient;

    ServerClientThread(DatagramSocket inSocket) {
        serverClient = inSocket;
    }

    public void run() {
        try {
            String clientSentence = "", serverMessage = "";
            while (!clientSentence.equals("bye")) {
                byte[] recieveData = new byte[1024];
                byte[] sendData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(recieveData, recieveData.length);
                serverClient.receive(receivePacket);
                InetAddress ipAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                clientSentence = new String(receivePacket.getData()).trim();
                int n = Integer.parseInt(clientSentence);
                serverMessage = n + " is " + (isPrime(n) ? "Prime" : "Not Prime");
                System.out.println("From Client : " + clientSentence + " Result : " + serverMessage);
                sendData = serverMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                serverClient.send(sendPacket);
            }

            serverClient.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    static boolean isPrime(int n) {
        if (n <= 1)
            return false;

        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }
}