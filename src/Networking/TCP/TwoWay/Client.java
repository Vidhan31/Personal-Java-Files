package Networking.TCP.TwoWay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        String IP = "localhost";
        int port = 9999;

        try (Socket socket = new Socket(IP, port)) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                System.out.println("Server Data : " + bufferedReader.readLine());
            }
        }
    }
}
