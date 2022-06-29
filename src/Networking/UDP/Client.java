package Networking.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    byte[] buffer = new byte[1024];

    public Client(DatagramSocket datagramSocket, InetAddress inetAddress) {

        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

    public static void main(String[] args) throws Exception {

        //DatagramSockets are used to send/receive datagram packets where datapackets are used to send and receive data.
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            InetAddress address = InetAddress.getLocalHost();
            Client client = new Client(datagramSocket, address);
            client.ClientSide();
        }
    }

    public void ClientSide() throws Exception {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("[Client] Input : ");
                buffer = scanner.nextLine().getBytes();

                //address and port for the destination we need to send the data.
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 9999);
                datagramSocket.send(datagramPacket);
                datagramSocket.receive(datagramPacket);
                String message = new String(datagramPacket.getData(), 0, buffer.length);
                System.out.println("[Client] Server says : " + message);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
