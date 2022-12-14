package ChatApp;

import java.net.*;
import java.io.*;

public class Server {
    // arayak
    static Socket[] clients = new Socket[5];
    static int count = 0;

    public static void main(String... args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            while (true) {
                Socket s = ss.accept();
                System.out.println(s);
                clients[count] = s;

                Thread client = new ClientHandler(s, count, clients);
                count++;
                client.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

class ClientHandler extends Thread {
    private Socket s;
    private int count;
    private Socket[] clients;

    public ClientHandler(Socket s, int count, Socket[] clients) {
        this.s = s;
        this.count = count;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            DataInputStream dio = new DataInputStream(s.getInputStream());

            while (true) {
                String msg = dio.readUTF();
                System.out.println("Receiving a msg from Client #");
                DataOutputStream dos;

                for (int i = 0; i < clients.length; i++) {
                    dos = new DataOutputStream(clients[i].getOutputStream());
                    dos.writeUTF(msg);
                    dos.flush();
                }

            }

            // if(id == 0){

            // System.out.println("Forwarding the msg to Client #" + "1 & 2");
            // }else if(id == 1){
            // dstId = 2; //FIX-ME
            // }else{
            // dstId = 3;
            // }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}