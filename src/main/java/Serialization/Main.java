/*
It copies the state of an object or object itself to file through byte stream and same goes for
deserialization except process is reversed.
 */

package Serialization;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Number number = new Number();

        File file = new File("C:\\Users\\rautv\\Documents\\Java\\Personal-Java-Files\\src\\Serialization\\objectFile");
        try (FileOutputStream fileOuputStream = new FileOutputStream(file)) {
            try (ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOuputStream)) {
                objectInputStream.writeObject(number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        File objectFile = new File("C:\\Users\\rautv\\Documents\\Java\\Personal-Java-Files\\src\\Serialization\\objectFile");
        try (FileInputStream fileInputStream = new FileInputStream(objectFile)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                number = (Number) objectInputStream.readObject();
                System.out.println(number.i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
