package Prog3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static final String IP = "172.23.129.149";
    public static final int PORT = 9873;
    public static final String SERVER_IP = "172.21.48.36";
    public static final int SERVER_PORT = 33155;
    public static final int FLAG = 120547;

    public static void main(String[] args) throws IOException {
        //przygotowanie strumieni do wysłania i odebrania danych
        Socket clientSocket = new Socket(SERVER_IP, SERVER_PORT);
        DataOutputStream outToServer = new
                DataOutputStream(clientSocket.getOutputStream());

        //wysłanie danych na serwer
        outToServer.writeBytes(FLAG+"\n");
        outToServer.writeBytes(IP+":"+PORT+"\n");

        clientSocket.close();
    }
}
