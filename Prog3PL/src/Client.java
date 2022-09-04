import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    public static void main(String[] args) {
        String tcpServerIp = "172.21.48.55";
        int tcpServerPort = 16776;
        int flag = 173443;

        try{
            Socket socket = new Socket(tcpServerIp, tcpServerPort);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            output.println(flag);
            output.println("172.23.129.222:9032");
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
