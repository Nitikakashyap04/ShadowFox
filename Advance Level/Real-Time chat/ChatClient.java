
import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket server = new Socket("localhost", 9999);
            System.out.println("Connected to server!");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream dos = new DataOutputStream(server.getOutputStream());
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(server.getInputStream()));

            String s;
            while (true) {
                System.out.print("Message to server: ");
                s = br.readLine();
                dos.writeBytes(s + "\n");
                if (s.equalsIgnoreCase("end")) {
                    break;
                }

                String receivedMessage = serverReader.readLine();
                if (receivedMessage != null) {
                    System.out.println("Message from server: " + receivedMessage);
                } else {
                    break;
                }
            }

            dos.close();
            serverReader.close();
            br.close();
            server.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
