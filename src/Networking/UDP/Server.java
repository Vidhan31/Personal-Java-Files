package Networking.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    public static void main(String[] args) throws Exception {

        String message;

        try (DatagramSocket socket = new DatagramSocket(9999)) {

            socket.setReuseAddress(true);
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            message = new String(packet.getData(), 0, packet.getLength());
            System.out.println(message);
            String modified = message.toUpperCase();

            InetAddress address = InetAddress.getLocalHost();
            DatagramPacket datagramPacket = new DatagramPacket(modified.getBytes(), modified.length(), address, packet.getPort());
            socket.send(datagramPacket);
        }
    }
}
