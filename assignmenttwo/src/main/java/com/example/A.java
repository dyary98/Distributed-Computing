package com.example;

import java.io.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.*;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/*
 * ***************************************
 * Distributed Computing - SE355  Dr Yad *
 * Mohammed Dyary - Dyary Yassin         *
 * 19-00305 - 19-00179                   *
 * Assignment Two                        *
 * ***************************************
 */

public class A {
    static int data;
    static Scanner sc = new Scanner(System.in);
    static String path;
    static int counter = 0;
    static int countNode = 0;

    public static void main(String[] args) {
        System.out.println("enter a path ");
        path = sc.nextLine();

        /*
            Zookeeper
        */

        try {
            final CountDownLatch latch = new CountDownLatch(1);
            final ZooKeeper zoo = new ZooKeeper("127.0.0.1:2181", 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {

                    
                }
            });  
            zoo.create("/nodes", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            int port = 2000;
            BigInteger bigInt = BigInteger.valueOf(port);      
            byte[] ports = bigInt.toByteArray();
            zoo.create("/nodes/nodeA", ports, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

            List<String> arr = zoo.getChildren("/nodes", false);
            int[] array = new int[arr.size()];
            int counter = 0;

            for( String a : arr){
                String po = new String(zoo.getData("/nodes/"+a, null, null));
                System.out.println(po);
                array[counter] = Integer.parseInt(po);
                counter++;
            }


            for(String s: zoo.getChildren("/nodes", new Watcher(){
                public void process(WatchedEvent ev) {
                    System.out.println("Node changed");
                    try {
                        zoo.getChildren("/nodes", this);
                    } catch (KeeperException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                }
            })){System.out.println(s);}

            final String path= "/nodes/nodeA";
            byte[] bn = zoo.getData(path, new Watcher() {
                public void process(WatchedEvent e){
                    try {
                        if(zoo.exists(path, false) != null){
                            System.out.println("new node has been created from nodeA");
                        }else{
                            System.out.println("nodeA has been deleted");
                        }
                    } catch (KeeperException | InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }, null);

            //Reading the data from the file
            //Use Zeromq to send the data to the other nodes
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
            System.out.println(" path confirmed ");
            int byteRead = -1;
            byteRead = bufferedInputStream.read();

            while (!Thread.currentThread().isInterrupted()) {
                try (ZContext context = new ZContext()){
                    ZMQ.Socket s = context.createSocket(SocketType.PUSH);
                    s.connect("tcp://127.0.0.1:" + (2001 + (countNode % 5)));
                    System.out.print("socket Changed to port: " + (2001 + counter % 5) + "\n");
                    for (int i = 0; i <= 1024; i++) {
                        System.out.println(i + "this is :" + byteRead);
                        s.send(Integer.toString(byteRead));
                    }
                    counter++;
                }
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
