import java.net.*;
import java.io.*;

public class ChatClient {

    private final String host;
    private final int port;
    private String username;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try {
            Socket socket = new Socket(host, port);

            System.out.println("Connected to the server...");

            var writeHandler = new ClientWriteHandler(socket, this);
            writeHandler.start();
            (new ClientReadHandler(socket, this)).start();

        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void setUserName(String username) {
        this.username = username;
    }

    String getUsername() {
        return this.username;
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient("127.0.0.1", 5678);
        client.start();
    }
}

// $ java -cp bin/ ChatClient
// Connected to the server...

// No other users connected
// Enter your name: Nirav
// [Nirav]:
// New User Connected: Milind
// [Nirav]:
// [Milind]: Hey
// [Nirav]: Hello
// [Nirav]:
// New User Connected: Pradip
// [Nirav]:
// [Pradip]: Hello Everyone
// [Nirav]:
// [Pradip]: exir
// [Nirav]:
// [Pradip]: exit
// [Nirav]:
// Pradip disconnected...
// [Nirav]:
// [Milind]: Bye
// [Nirav]:
// [Milind]: exit
// [Nirav]:
// Milind disconnected...
// [Nirav]: bye
// [Nirav]: exit

// $ java -cp bin/ ChatClient
// Connected to the server...

// Connected users: [Nirav, Milind]
// Enter your name: Pradip
// [Pradip]: Hello Everyone
// [Pradip]: exir
// [Pradip]: exit

// $ java -cp bin/ ChatClient
// Connected to the server...

// // Connected users: [Nirav]
// // Enter your name: Milind
// // [Milind]: Hey
// // [Milind]:
// // [Nirav]: Hello
// // [Milind]:
// // New User Connected: Pradip
// // [Milind]:
// // [Pradip]: Hello Everyone
// // [Milind]:
// // [Pradip]: exir
// // [Milind]:
// // [Pradip]: exit
// // [Milind]:
// // Pradip disconnected...
// // [Milind]: Bye
// // [Milind]: exit