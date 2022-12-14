import java.net.*;
import java.util.Scanner;

//io input output
import java.io.*;

public class Server {
    public static void main(String[] args) {
        // try and catch

        // ? serverekman bo drwst ka ka masj bnere bo client wa warishibgre wa printishi
        // bkat hardwla

        try {
            // portekman krdotawa
            ServerSocket ss = new ServerSocket(5000);

            // chawarey three way handshake akain la clientawa
            Socket s = ss.accept();

            System.out.println("Client Connected");
            Scanner sc = new Scanner(System.in);

            // Dops bo nardny zanyary bakarde
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // dis bo wargrttny zanyary
            DataInputStream dis = new DataInputStream(s.getInputStream());
            // bas text waragrey
            // dis.readUTF();
            // 5 jarek msg bnere mn abe chaware kam 5 sa3at bo away msg ek bnermawa bo hama
            while (true) {
                System.out.print("Enter a Message to the client: ");
                dos.writeUTF(sc.nextLine());
                System.out.println("Client: " + dis.readUTF());
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

/*
 * 
 * client sends a msg
 * server recives a msg
 * server prints the msg
 * server sends a msg
 * client recives a msg
 * clioent prints the msg
 */