package Prog3PL;

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


    public static int maxPower(int x) {
        int k = 1;
        while (Math.pow(k, 6) < x) {
            k++;
        }
        return k-1;
    }

    /*

     1. Utwórz gniazdo UDP. Wyślij do serwera TCP jedną linię w formacie adres:port,
            gdzie adres jest adresem IP Twojego gniazda UDP, a port jego numerem portu. Wykonaj poniższe polecenia używając protokołu UDP i gniazda, które właśnie utworzyłeś.
     2. Odbierz napis. Usuń z niego wszystkie wystąpienia 1 i odeślij wynik.
     3. Odbierz liczbę naturalną x. Oblicz największą liczbę naturalną k, taką, że k podniesione do potęgi 6 jest nie większe niż wartość x. Odeślij wartość k.
     4. W 3 kolejnych liniach odbierz 3 liczb(y) naturalnych(e). Policz sumę tych liczb i odeślij.

     */
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket(Main.PORT);

            long a, b, c, X;
//            2. Odbierz napis. Usuń z niego wszystkie wystąpienia 1 i odeślij wynik.



            byte[] receivedData = new byte[1024];
            DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.receive(receivedPacket);


            String fromServer = getData(receivedPacket);
            String result = fromServer.replaceAll("1", "");
            clientSocket.send(getSendPacket(result, receivedPacket));




            receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.receive(receivedPacket);


//          3. Odbierz liczbę naturalną x. Oblicz największą liczbę naturalną k, taką, że k podniesione do potęgi 6 jest nie większe niż wartość x. Odeślij wartość k.




            fromServer = getData(receivedPacket);
            X = Long.parseLong(fromServer);
            clientSocket.send(getSendPacket("" + maxPower((int)X) + "\n", receivedPacket));



    //          4. W 3 kolejnych liniach odbierz 3 liczb(y) naturalnych(e). Policz sumę tych liczb i odeślij.





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

/*
            int resultNWD = (int) NWD(NWD(a,b),c);

            clientSocket.send(getSendPacket("" + resultNWD + "\n", receivedPacket));


 */

            long sum = a + b + c;

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


/*







 */