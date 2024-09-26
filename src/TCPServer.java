import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;
import java.io.*;
public class TCPServer {
    public static void main (String args[]) throws IOException {
        if(args.length != 1){
            System.out.println("Proper input format must be 'java TCPServer <port>'");
            return;
        }

        try{
            int serverPort = Integer.parseInt(args[0]);
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Server listening on port " + serverPort);
            while(true){
                Socket clientSocket = listenSocket.accept();
                System.out.println("Connection accepted");
                Connection c = new Connection(clientSocket);
                System.out.println("Client connected");
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection(Socket s) {
        try {
            clientSocket = s;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void run() {
        try {
            String data = in.readUTF();
            out.writeUTF(data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                clientSocket.close();
            } catch (IOException e){

            }
        }
    }
}
