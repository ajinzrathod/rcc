 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      04-ChatServer.java 
 * Copyright: By Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private final Map<String, ClientHandler> users = new HashMap<>();
    private final int SERVER_PORT;

    public ChatServer(int port) {
        this.SERVER_PORT = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {

            System.out.println("----- Chat Server -----");
            System.out.println("Listening on port " + SERVER_PORT + "...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New User Connected...");
                ClientHandler newUser = new ClientHandler(socket, this);
                newUser.start();
            }
        } catch (IOException e) {
            System.out.println("Error in the server: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int port;
        try {
            port = 5678;
            ChatServer server = new ChatServer(port);
            server.start();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void sendToEveryone(String message, ClientHandler notToUser) {
        for (Map.Entry<String, ClientHandler> aUser : users.entrySet()) {
            if (aUser.getValue() != notToUser) {
                aUser.getValue().sendMessage(message);
            }
        }
    }

    void sendTo(String message, String username) {
        var user = getUser(username);
        if (user != null) {
            user.sendMessage(message);
        }
    }

    private ClientHandler getUser(String username) {
        for (Map.Entry<String, ClientHandler> aUser : users.entrySet()) {
            if (aUser.getKey().equals(username)) {
                return aUser.getValue();
            }
        }
        return null;
    }

    void addUser(String userName, ClientHandler userThread) {
        users.put(userName, userThread);
    }

    void removeUser(String userName) {
        users.remove(userName);
    }

    Set<String> getUserNames() {
        return this.users.keySet();
    }

    boolean hasUsers() {
        return !this.users.isEmpty();
    }
}

// $ javac *.java && java ChatServer
// ----- Chat Server -----
// Listening on port 5678...
// New User Connected...
// 1 clients connected!
// New User Connected...
// 2 clients connected!
// New User Connected...
// 3 clients connected!

// $ java ChatClient
// Connected to the server...

// No other users connected
// Enter your name: nirav

// 1 clients connected!
// [nirav]: [nirav]:
// New User Connected: milind
// [nirav]:
// 2 clients connected!
// [nirav]:
// New User Connected: pradip
// [nirav]:
// 3 clients connected!
// [nirav]:
// [milind]: bye
// [nirav]:
// [milind]: exit
// [nirav]:
// milind disconnected...
// [nirav]:
// [pradip]: exit
// [nirav]:
// pradip disconnected...
// [nirav]: exit

// $ java ChatClient
// Connected to the server...

// Connected users: [nirav]
// Enter your name: milind

// 2 clients connected!
// [milind]: bye
// [milind]:
// [milind]: exit
// [milind]:
// milind disconnected...
// [milind]: exit

// $ java ChatClient
// Connected to the server...

// Connected users: [nirav, milind]
// Enter your name: pradip

// 3 clients connected!
// [pradip]: [pradip]:
// [milind]: bye
// [pradip]:
// [milind]: exit
// [pradip]:
// milind disconnected...
// [pradip]: exit