package ChatAppHomeWork;

import java.net.*;
import java.io.*;

public class Server {
    static Socket[] clients = new Socket[12];
    static int count;

    public static void main(String... args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            while (true) {
                Socket s = ss.accept();
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
    private int id;
    private Socket[] clients;

    public ClientHandler(Socket s, int id, Socket[] clients) {
        this.s = s;
        this.id = id;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            DataInputStream dio = new DataInputStream(s.getInputStream());

            while (true) {
                String msg = dio.readUTF();
                System.out.println("Receiving a msg from Client #" + id);
                DataOutputStream dos;

                for (int i = 0; i < clients.length; i++) {
                    if (s.equals(clients[i])) {
                        int modulo = (i + 3) % 12;
                        dos = new DataOutputStream(clients[modulo].getOutputStream());
                        System.out.println("the array" + clients[modulo]);
                        dos.writeUTF(msg);
                        dos.flush();
                    }
                }
            }
        } catch (

        Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
