package Input_Output;

import java.io.*;
import java.util.Random;

public class InputOutput {
    public static void main(String[] args) {

        Random rand = new Random();
        try {
            BufferedWriter writeFile = new BufferedWriter(new FileWriter("output.txt"));
            int num;
            for (int i = 0; i < 5; i++) {
                num = rand.nextInt(0, 6);
                writeFile.write(num + "\n");
            }
            writeFile.close();
        } catch (IOException e) {
            throw new RuntimeException("File doesn't exist.");
        }

        try {
            BufferedReader readFile = new BufferedReader(new FileReader("output.txt"));
            String line;
            while ((line = readFile.readLine()) != null) {
                System.out.println(Integer.parseInt(line));
            }
            readFile.close();
        } catch (IOException e) {
            throw new RuntimeException("File not found.");
        }
    }
}
