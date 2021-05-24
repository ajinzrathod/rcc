import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

class ms {
    public static void main(String args[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(9999);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            byte[] clientData = new byte[70];
            inFromClient.read(clientData);

            String sSourceAddress = new String(Arrays.copyOfRange(clientData, 0, 10));
            String sDestinationAddress = new String(Arrays.copyOfRange(clientData, 10, 20));
            String receivedData = new String(Arrays.copyOfRange(clientData, 20, 70));

            System.out.println("-- From Client --");
            System.out.println("Sender Source Address: " + sSourceAddress);
            System.out.println("Sender Destination Address: " + sDestinationAddress);
            System.out.println("Data: " + receivedData);
            break;
        }
    }
}

