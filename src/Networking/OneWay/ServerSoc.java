package Networking.OneWay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSoc {

    public static void main(String[] args) throws IOException {

        System.out.println("Server started");

        try (ServerSocket serverSocket = new ServerSocket(9999)) {

            System.out.println("Server waiting for client request");
            Socket socket = serverSocket.accept();

            System.out.println("Server connected");

            String sample;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                sample = bufferedReader.readLine();
            }

            System.out.println("Client Data : " + sample);
        }
    }
}
