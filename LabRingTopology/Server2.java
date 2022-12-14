import java.io.*;
import java.net.*;
import java.util.Random;

public class Server2 {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(2002);

            while (true) {
                try {

                    Socket s2 = new Socket("localhost", 2003);
                    Socket s = ss.accept();
                    Random rand = new Random();
                    int random = rand.nextInt(100);

                    DataInputStream dis = new DataInputStream(s.getInputStream());
                    DataOutputStream dos = new DataOutputStream(s2.getOutputStream());

                    dos.writeInt(dis.readInt() + random);
                    System.out.println(" ");

                } catch (Exception e) {
                    System.out.println(e);

                    continue;
                }
            }
        } catch (Exception e) {

        }

    }

}
