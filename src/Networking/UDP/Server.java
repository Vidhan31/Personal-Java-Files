package Networking.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server {

    private DatagramSocket datagramSocket;
    private InetAddress address;
    private byte[] buffer = new byte[1024];

    public Server(DatagramSocket datagramSocket) throws UnknownHostException {
        this.datagramSocket = datagramSocket;
    }

    public static void main(String[] args) throws Exception {

        //DatagramSocket creates a socket that "listens" to the port 9999. It doesn't create/establish connection.
        try (DatagramSocket datagramSocket = new DatagramSocket(9999)) {
            Server server = new Server(datagramSocket);
            server.ServerSide();
        }
    }

    public void ServerSide() throws Exception {

        while (true) {

            try {
                //We create DatagramPacket object which is essentially a container containing the message in the
                //form of byte array type.
                //Because UDP is connection-less, we don't actually need to first establish connection to send data.

                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

                //when we receive data, we also receive other info such as header info like port, address
                //from where it is being sent. So we can use the same info to send the data back again to
                //the same destination.
                datagramSocket.receive(datagramPacket);
                String message = new String(datagramPacket.getData(), 0, buffer.length);
                System.out.println("[Server] Client says : " + message);

                buffer = message.toUpperCase().trim().getBytes();

                //address : This is the destination to which the message is to be delivered.
                //port : This is the port to which the message will be delivered.
                //both of these will be received from previous datagramPacket object which contains info
                //of destination from where data is received.
                datagramPacket = new DatagramPacket(buffer, buffer.length, datagramPacket.getAddress(), datagramPacket.getPort());
                datagramSocket.send(datagramPacket);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
