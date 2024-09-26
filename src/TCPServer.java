import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

/**
 * TCP Server for Java Socket Programming Implementation
 * Takes two arguments - Server IP and Port Number
 * These arguments should match the IP and Port the TCP Server is listening on
 * To run this server,type "java TCPServer.java 127.0.0.1 32000" into the terminal
 */
public class TCPServer {
    public static void main (String args[]) throws IOException {
        if(args.length != 2){
            System.out.println("Proper input format must be 'java TCPServer.java <server_ip> <port>'");
            return;
        }

        try{
            String serverIP = args[0];
            int serverPort = Integer.parseInt(args[1]);

            // Translate String IP Address into InetAddress type
            InetAddress ip = InetAddress.getByName(serverIP);

            ServerSocket listenSocket = new ServerSocket(serverPort, 50, ip);

            System.out.println("Server listening on IP " + ip + " port " + serverPort);

            //while (true){ uncomment to accept multiple connections
            // Look for and accept single incoming connection
            Socket clientSocket = listenSocket.accept();
            System.out.println("Connection accepted on port " + serverPort);
            Connection c = new Connection(clientSocket); // Handle client connection in new thread
            System.out.println("Client connected on port " + serverPort);
            //}
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

/**
 * Used to manage a client connection inside of a new thread
 */
class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection(Socket s) {
        try {
            clientSocket = s;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start(); // Start thread
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reverses the order and flips the case of a provided input string
     * @param input The string to be reversed and case-flipped
     * @return A new string with the opposite order and case
     */
    public String reverseAndFlipCase(String input) {
        StringBuilder reversedString = new StringBuilder(input.length());

        for(int i = input.length() - 1; i >= 0; i--) {
            char c = input.charAt(i);
            if(Character.isUpperCase(c)){
                reversedString.append(Character.toLowerCase(c));
            } else if (Character.isLowerCase(c)){
                reversedString.append(Character.toUpperCase(c));
            } else {
                // For non-alphabetic chars and whitespace
                reversedString.append(c);
            }
        }
        return reversedString.toString();
    }


    public void run() { // Runs when new thread is created
        try {
            String data = in.readUTF();
            // Return error if string > 80 characters long
            if(data.length() > 80) {
                out.writeUTF("Invalid: String must be less than 80 characters long");
            } else {
                // String reversal and capitalization
                String revData = reverseAndFlipCase(data);
                out.writeUTF(revData);
                return; // Exit upon successful transaction
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                clientSocket.close();
                System.out.println("Client connection closed");
            } catch (IOException e){

            }
        }
    }
}
