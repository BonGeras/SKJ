import java.io.IOException;
import java.net.*;

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

    public static long NWD(long pierwsza, long druga)
    {
        if (druga == 0)
        {
            return pierwsza;
        }
        else // rekurencyjne wywołanie funkcji, gdzie kolejność parametrów
        {   // została zamieniona, dodatkowo drugi parametr to operacja modulo
            return NWD(druga, pierwsza%druga);  // dwóch liczb.
        }
    }
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket(Main.PORT);

            long a, b, c, d, f, NWD;
//            2. Odbierz jedną linijkę tekstu. Skonkatenuj tekst 3 razy z samym sobą i odeślij wynik.



            byte[] receivedData = new byte[1024];
            DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.receive(receivedPacket);

            String fromServer = getData(receivedPacket);

            receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.receive(receivedPacket);




            System.out.println("\n3 numbers:");
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

            int resultNWD = (int) NWD(NWD(a,b),c);

            clientSocket.send(getSendPacket("" + resultNWD + "\n", receivedPacket));


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
