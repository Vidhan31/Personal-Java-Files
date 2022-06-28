package Networking.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    public static void main(String[] args) throws Exception {

        try (DatagramSocket datagramSocket = new DatagramSocket()) {

            datagramSocket.setReuseAddress(true);
            String sample = "viddd";
            InetAddress address = InetAddress.getLocalHost();

            DatagramPacket packet = new DatagramPacket(sample.getBytes(), sample.length(), address, datagramSocket.getLocalPort());
            datagramSocket.send(packet);
        }

        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte[] buffer = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(datagramPacket);
            String message = new String(datagramPacket.getData(), 0, datagramPacket.getLength()).toLowerCase();
            System.out.println(message);
        }
    }
}
