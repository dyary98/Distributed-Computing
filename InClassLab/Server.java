package InClassLab;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5544);
            Socket sa = ss.accept();

            DataInputStream in = new DataInputStream(sa.getInputStream());
            System.out.println(in.readUTF());

            // give the numbers
            DataOutputStream stream = new DataOutputStream(sa.getOutputStream());
            stream.writeInt(4);
            stream.writeInt(9);

            DataInputStream inn = new DataInputStream(sa.getInputStream());
            System.out.println(inn.readInt());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

// 172.31.4.48