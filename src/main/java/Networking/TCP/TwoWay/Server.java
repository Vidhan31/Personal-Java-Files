package Networking.TCP.TwoWay;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        String sample = "Raut";

        System.out.println("\n\nServer to Client");
        System.out.println("Server started");

        try (ServerSocket serverSockets = new ServerSocket(9999)) {
            System.out.println("Server is waiting for client's request");
            Socket socket = serverSockets.accept();
            System.out.println("Server and Client connection established.");

            try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream())) {
                try (BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
                    bufferedWriter.write(sample);
                    outputStreamWriter.flush();
                    bufferedWriter.flush();
                }
            }
        }
    }
}
