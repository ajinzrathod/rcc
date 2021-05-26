 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      05-Client.java 
 * Copyright: By Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.*;
import java.net.*;

public class Client {
    private final String FILENAME = null;
    Connect c = new Connect();
    Socket socket;
    BufferedReader read;
    PrintWriter output;

    public void startClient() throws UnknownHostException, IOException {
        socket = new Socket(c.gethostName(), c.getPort());

        output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter Username : ");
        String username = in.readLine();

        output.println(username);

        System.out.println("Enter Password : ");
        String password = in.readLine();

        output.println(password);
        output.flush();
        read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String response = read.readLine();
        System.out.println(response);
    }

    public void fileInfo() {

    }

    public static void main(String args[]) {
        Client client = new Client();
        try {
            client.startClient();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}