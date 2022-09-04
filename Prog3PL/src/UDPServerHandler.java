import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerHandler implements Runnable{
    private DatagramPacket receivedPacket;
    private DatagramSocket serverSocket;

    public UDPServerHandler(DatagramPacket receivedPacket, DatagramSocket serverSocket){
        this.receivedPacket = receivedPacket;
        this.serverSocket = serverSocket;
    }
    @Override
    public void run(){
        String str = new String(receivedPacket.getData(), 8, receivedPacket.getLength());
        System.out.println(str);
    }
}
