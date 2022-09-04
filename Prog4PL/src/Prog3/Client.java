package Prog3;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
public class UDPServer {

    public static String getData(DatagramPacket packet) {
        String fromServer = new String(packet.getData(), 0, packet.getLength() - 1);
        System.err.println("FROM SERVER: " + fromServer);
        return fromServer;
    }

    public static DatagramPacket getSendPacket (String data, DatagramPacket receivedPacket) {
        byte[] sendData = data.getBytes();
        System.out.println("SEND DATA: " + data);
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivedPacket.getAddress(), receivedPacket.getPort());
        return sendPacket;
    }

    public static int NWD(int pierwsza, int druga)
    {
        if (druga == 0)
        {
            return pierwsza;
        }
        else // rekurencyjne wywołanie funkcji, gdzie kolejność parametrów
        {   // została zamieniona, dodatkowo drugi parametr to operacja modulo
            return NWD(druga, pierwsza%druga);  // dwóch liczb.
        }
    }public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket(Main.PORT);

            long a, b, c, d, f, NWD;
//            2. Odbierz jedną linijkę tekstu. Skonkatenuj tekst 3 razy z samym sobą i odeślij wynik.



            byte[] receivedData = new byte[1024];
            DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.receive(receivedPacket);
//            System.out.println("im here");

            String fromServer = getData(receivedPacket);
            String result = fromServer + fromServer + fromServer;

            clientSocket.send(getSendPacket(result, receivedPacket));



/*            byte[] sendData = String.valueOf(NWD).getBytes();
            System.out.println("SEND DATA: " + NWD);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivedPacket.getAddress(), receivedPacket.getPort());
            clientSocket.send(sendPacket);*/

            receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.receive(receivedPacket);

            fromServer = getData(receivedPacket);
            result = fromServer.replace("9", "");
            clientSocket.send(getSendPacket(result, receivedPacket));


            System.out.println("\n5 numbers:");
            receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.receive(receivedPacket);

            fromServer = getData(receivedPacket);
            a = Long.parseLong(fromServer);

            receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.receive(receivedPacket);

            fromServer = getData(receivedPacket);
            b = Long.parseLong(fromServer);

            receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.receive(receivedPacket);

            fromServer = getData(receivedPacket);
            c = Long.parseLong(fromServer);

            receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.receive(receivedPacket);

            fromServer = getData(receivedPacket);
            d = Long.parseLong(fromServer);

            receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.receive(receivedPacket);

            fromServer = getData(receivedPacket);
            f = Long.parseLong(fromServer);

            long sum = a + b + c + d + f;

            clientSocket.send(getSendPacket("" + sum + "\n", receivedPacket));

            receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.receive(receivedPacket);

            System.out.println("\nFINAL FLAG");
            fromServer = getData(receivedPacket);



            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
