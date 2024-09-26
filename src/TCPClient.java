import java.util.Arrays;
import java.net.*;
import java.io.*;

public class TCPClient {
    public static void main(String args[]) throws IOException {
        if(args.length != 2){
            System.out.println("Proper input format must be 'java TCPClient <server_ip> <port>'");
            return;
        }
        String serverIP = null;
        int port = -1;

        try {
            serverIP = args[0];
            port = Integer.parseInt(args[1]);
        }
        catch (Exception e){
            System.out.println("<server_ip> must be type String and <port> must be type int");
        }
        Socket s = null;

        try{
            // Connect to server
            s = new Socket(serverIP, port);

            System.out.println("socket");

            // Input and Output streams for communication with server
            DataInputStream in = new DataInputStream( s.getInputStream());
            DataOutputStream out = new DataOutputStream( s.getOutputStream());

            // Get user input
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter text: ");

            // Send user input to server
            out.writeUTF(input.toString());

            // Read and print message returned from server
            String data = in.readUTF();
            System.out.println("Received: " + data);

        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if(s != null && !s.isClosed()) {
                s.close();
            }
        }


    }

}
