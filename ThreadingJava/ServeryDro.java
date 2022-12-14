import java.io.*;
import java.net.*;

public class ServeryDro {
    public static void main(String[] args) {
        try {

            while (true) {
                ServerSocket ss = new ServerSocket(4443);
                while (true) {
                    Socket s = ss.accept();
                    while (true) {

                        DataInputStream sis = new DataInputStream(s.getInputStream());
                        System.out.println(sis.readUTF());
                    }
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
