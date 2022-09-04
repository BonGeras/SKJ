import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("172.21.48.194",  13155);

            PrintWriter ouToServer = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            ouToServer.println("100104");


            ouToServer.println(clientSocket.getLocalPort());

            int a = Integer.parseInt(inFromServer.readLine().replaceAll("\n", ""));
            int b = Integer.parseInt(inFromServer.readLine().replaceAll("\n", ""));
            int c = Integer.parseInt(inFromServer.readLine().replaceAll("\n", ""));
            int d = Integer.parseInt(inFromServer.readLine().replaceAll("\n", ""));

            ouToServer.println(a+b+c+d);

            System.out.println("\nFINAL FLAG: ");
            String finalFlag = inFromServer.readLine();
            System.out.println(finalFlag);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
