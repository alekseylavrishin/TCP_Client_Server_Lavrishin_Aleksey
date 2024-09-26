### Description

This is my solution for Homework Set 1 - Question 7 for CS6650 Distributed Systems.

The server starts in passive mode listening for a transmission from the client. 
When the client successfully connects to the server, the client passes an 80 character max string that the server then reverses the order and case of. 
The server sends this reversed string back to the client who then displays it via a print statement. 

- Proper input format for the server: java TCPClient.java <server_ip> <port>
- Proper input format for the client: java TCPServer.java <server_ip> <port>

### Instructions to run:
1. Start the server using "java TCPServer.java 127.0.0.1 32000"
2. Start the client using "java TCPClient.java 127.0.0.1 32000"
3. In the "Enter text" prompt on the client, pass a string of up to 80 characters to be reversed.

   For example: "Hello, this is a test!"
5. Receive the reversed string on the client.

   For example: "!TSET A SI SIHT ,OLLEh"

### Some considerations
You are able to specify the IP Address and Port Number that both the client and server interact on. Please choose an IP Address in the localhost range (127.0.0.0/8) and a valid Port Number. 
Ensure the client and server are using the same IP Address and Port Number.

Additionally, make sure to start the server first prior to starting the client.
