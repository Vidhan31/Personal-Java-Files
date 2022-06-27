package Networking.OneWay;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class ClientToServer {

    public void writeToServer() throws IOException {

        String IP = "localhost";
        int port = 9999;
        String sample = "Vidhan";

        try (Socket socket = new Socket(IP, port)) {
            try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream())) {
                try (BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
                    bufferedWriter.write(sample);
                }
            }
        }
    }

    public void read() throws IOException {

        System.out.println("\n\nClient to Server");

        System.out.println("Server started");
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            System.out.println("Server is waiting for client's request");
            writeToServer();
            Socket socket = serverSocket.accept();
            System.out.println("Server and Client connection established.");

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String sample = bufferedReader.readLine();
                System.out.println("Client Data : " + sample);
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {

        ClientToServer clientToServer = new ClientToServer();

        try {
            clientToServer.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
