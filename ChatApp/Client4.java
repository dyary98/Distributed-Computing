package ChatApp;

import java.net.*;
import java.util.Scanner;

import java.io.*;

public class Client4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Socket s = new Socket("localhost", 5000);

            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());

            while (true) {
                System.out.println(dis.readUTF());
                System.out.println("please write a message");
                dout.writeUTF(sc.nextLine());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
