import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;


public class Server10 {
    private static String source = "2";
    private static String destination = "3";

    public static void main(String args[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6789);

        String sSourceAddress;
        String sDestinationAddress;
        String receivedData;
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            byte[] clientData = new byte[70];
            inFromClient.read(clientData);

            sSourceAddress = new String(Arrays.copyOfRange(clientData, 0, 10));
            sDestinationAddress = new String(Arrays.copyOfRange(clientData, 10, 20));
            receivedData = new String(Arrays.copyOfRange(clientData, 20, 70));

            System.out.println("-- From Client --");
            System.out.println("Sender Source Address: " + sSourceAddress);
            System.out.println("Sender Destination Address: " + sDestinationAddress);
            System.out.println("Data: " + receivedData);
            break;
        }
        // Server sends to server
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 9999);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        byte[] sourceAddress = Arrays.copyOf(sSourceAddress.getBytes(), 10);
        byte[] destinationAddress = Arrays.copyOf(sDestinationAddress.getBytes(), 10);
        byte[] data = Arrays.copyOf(receivedData.getBytes(), 50);
        byte[] packet = ByteBuffer.allocate(70).put(sourceAddress).put(destinationAddress).put(data).array();

        outToServer.write(packet);
        clientSocket.close();
    }
}
