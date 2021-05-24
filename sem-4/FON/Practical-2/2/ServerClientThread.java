import java.net.*;
import java.io.*;

class ServerClientThread extends Thread {
    Socket serverClient;
    int clientNo;

    ServerClientThread(Socket inSocket, int counter) {
        serverClient = inSocket;
        clientNo = counter;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(serverClient.getInputStream()));
            PrintWriter writer = new PrintWriter(serverClient.getOutputStream());
            String clientMessage = "", serverMessage = "";

            while (!clientMessage.equals("bye")) {
                clientMessage = reader.readLine();
                int n = Integer.parseInt(clientMessage);

                System.out.println("From Client - " + clientNo + ": Number is : " + clientMessage);
                serverMessage = n + " is " + (n % 2 == 0 ? "Even" : "Odd");
                writer.println(serverMessage);
                writer.flush();
            }

            reader.close();
            writer.close();
            serverClient.close();

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Client - " + clientNo + " disconnected!");
        }
    }
}