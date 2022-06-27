package Networking.OneWay;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {

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
}
