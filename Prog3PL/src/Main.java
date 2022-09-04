import java.io.*;
import java.net.Socket;




public class Main {
    public static final String IP = "172.21.48.55";
    public static final int PORT = 9032;
    public static final String SERVER_IP = "172.21.48.55";
    public static final int SERVER_PORT = 16777;
    public static final int FLAG = 173443;

    public static void main(String[] args) throws IOException {
        //przygotowanie strumieni do wysłania i odebrania danych
        Socket clientSocket = new Socket(SERVER_IP, SERVER_PORT);
        DataOutputStream outToServer = new
                DataOutputStream(clientSocket.getOutputStream());

        //wysłanie danych na serwer
        outToServer.writeBytes(FLAG+"\n");
        outToServer.writeBytes(IP+":"+PORT+"\n");


    }
}
