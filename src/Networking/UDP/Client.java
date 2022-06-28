package Networking.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    public static void main(String[] args) throws Exception {

        try (DatagramSocket datagramSocket = new DatagramSocket()) {

            datagramSocket.setReuseAddress(true);
            String sample = "hello";
            InetAddress address = InetAddress.getLocalHost();
            DatagramPacket packet = new DatagramPacket(sample.getBytes(), sample.length(), address, 9999);
            datagramSocket.send(packet);

            byte[] buffer = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(datagramPacket);
            String message = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            System.out.println(message);
        }
    }
}
