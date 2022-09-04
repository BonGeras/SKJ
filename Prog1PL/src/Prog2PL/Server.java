package Prog2PL;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static Prog2PL.Util.*;

public class Server {
    static int port = 26777;
    static String address = "172.23.129.218";
    static List<Integer> clientInputs;
    static {
        clientInputs = new ArrayList<>();
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ExecutorService executorService = Executors.newFixedThreadPool(10);

            while (true) {
                System.out.println("im here");

                executorService.submit(new RequestHandler(serverSocket.accept()));
                System.out.println("me too");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void addClientInput(int input) {
        clientInputs.add(input);
    }

    public static void printCI() {
        System.out.println(clientInputs);
    }

    public static int getGcd() {
        return gcdFromList(clientInputs);
    }

    public static long getClientInputsSum() {
        return clientInputs.stream().mapToLong(x->x).sum();
    }
}
