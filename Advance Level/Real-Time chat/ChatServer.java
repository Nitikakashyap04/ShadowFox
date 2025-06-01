
import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9999);
            System.out.println("Server is running and waiting for client connection...");
            Socket client = server.accept();
            System.out.println("Client connected!");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintStream ps = new PrintStream(client.getOutputStream());

            String receivedMessage;
            while (true) {
                receivedMessage = clientReader.readLine();
                if (receivedMessage == null || receivedMessage.equalsIgnoreCase("end")) {
                    System.out.println("Client ended the chat.");
                    break;
                }
                System.out.println("From client: " + receivedMessage);
                System.out.print("To client: ");
                String response = br.readLine();
                ps.println(response);
                if (response.equalsIgnoreCase("end")) {
                    break;
                }
            }

            ps.close();
            clientReader.close();
            br.close();
            client.close();
            server.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
