package Networking.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Server {

    private DatagramSocket datagramSocket;
    private InetAddress address;
    private byte[] buffer = new byte[1024];

    public Server(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public static void main(String[] args) throws Exception {

        try (DatagramSocket datagramSocket = new DatagramSocket(9999)) {
            Server server = new Server(datagramSocket);
            server.ServerSide();
        }
    }

    public void ServerSide() throws Exception {

        while (true) {

            try {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, address, 9999);
                datagramSocket.receive(datagramPacket);
                String message = new String(datagramPacket.getData(), 0, buffer.length);
                System.out.println("[Server] Client says : " + message);

                buffer = message.toUpperCase().getBytes();
                datagramPacket = new DatagramPacket(buffer, buffer.length, datagramPacket.getAddress(), datagramPacket.getPort());
                datagramSocket.send(datagramPacket);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
