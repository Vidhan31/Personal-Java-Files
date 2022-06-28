package Networking.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    public static void main(String[] args) throws Exception {

        String message;

        try (DatagramSocket socket = new DatagramSocket(9999)) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            message = new String(packet.getData(), 0, packet.getLength()).toUpperCase();
            System.out.println(message);

            InetAddress address = InetAddress.getLocalHost();
            DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.length(), address, 9999);
            socket.send(datagramPacket);
        }
    }
}
