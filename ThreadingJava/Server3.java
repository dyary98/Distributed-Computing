
/**
 * Threading
 */

/* 
 !in order to use the Thread API we have to do 4 things:
 1. Create a class in which you extend Thread superclass in it.
 2. Override run() Method that you have in the class that you just created in the first point
 3. Create object of the class 
 4. Invoke(call) start method to execute the custom threads run()
 */

import java.net.*;
import java.io.*;

public class Server3 {

    public static void main(String[] args) {

        // ! Some info about the main
        // Main method represents main thread
        // Whatever we write in here will be executed by the main thread
        // Threads always execute in sequence

        // ! Main Thread is the most important thread of a java program
        // it is executed whenever a java program starts, Every program must contain
        // this thread for in execution

        // ? Why java Main thread is needed ?
        /*
         * 1. from this other child threads are spawned
         * 2. it must be the last thread to finish execution
         */

        try {
            ServerSocket ss = new ServerSocket(5000);
            while (true) {

                // chawarey threeway handshake aka
                Socket s = ss.accept();

                // object drwstakait la jory thread wa "s" pass akaita nawiawa

                //3 
                Thread client = new ClientHandler(s);

                client.start();
                // ishi yakam threadek drwst aka
                // ishi dwam cally yan invoke methody run aka
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

// ! ClientHandler is a thread
class ClientHandler extends Thread {
    Socket s;

    public ClientHandler(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            DataInputStream doss = new DataInputStream(s.getInputStream());

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF(" AUIS");
            dos.flush();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
