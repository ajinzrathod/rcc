import java.net.*;

public class MultiThreadedSocketServer {
    public static void main(String[] args) throws Exception {
        try {
            ServerSocket server = new ServerSocket(5678);
            int counter = 0;
            System.out.println("Listening on Port 5678...");
            while (true) {
                counter++;
                Socket serverClient = server.accept();
                System.out.println(">>> " + "Client No: " + counter + " connected!");
                ServerClientThread sct = new ServerClientThread(serverClient, counter);
                System.out.println(counter + " clients connected.");
                sct.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
