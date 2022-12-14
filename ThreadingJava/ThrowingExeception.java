import java.io.*;
import java.net.*;

public class ThrowingExeception {
    public static void main(String[] args) throws Exception {
        Socket s = null;
        while (true) {
            try {
                s = new Socket("localhost", 4443);
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                while (true) {

                    dout.writeUTF("slaw");
                    throw new EOFException();
                }
            } catch (Exception e) {
                try {
                    s = new Socket("localhost", 4443);
                    DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                    dout.writeUTF("slawFromthere");
                    System.out.println("connection regained");
                } catch (Exception er) {
                    s = new Socket("localhost", 4443);
                    System.out.println("connection regained");
                    System.out.println("failed");
                }
            }
        }
    }
}
