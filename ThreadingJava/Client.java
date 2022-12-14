import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            // domain yan ip address ipv4 yan ipv6
            // port

            Socket s = new Socket("localhost", 5000);
            Scanner sc = new Scanner(System.in);

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());

            while (true) {
                System.out.println("Server: " + dis.readUTF());
                System.out.print(" Enter a Message to the server: ");
                dos.writeUTF(sc.nextLine());
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
