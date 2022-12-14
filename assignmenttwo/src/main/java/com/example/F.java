package com.example;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.*;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class F {
    public static void main(String[] args) throws KeeperException, InterruptedException {
        ArrayList<LinkedList<Integer>> bytes = new ArrayList<LinkedList<Integer>>(32);
        for (int i = 0; i < 32; ++i) {
            bytes.add(new LinkedList<Integer>());
        }
        int queCount = 0;
        
        /*
            Zookeeper
        */
        
        try (ZContext context = new ZContext()) {
            final CountDownLatch latch = new CountDownLatch(1);
            final ZooKeeper zoo = new ZooKeeper("127.0.0.1:2181", 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {

                    
                }
            });
            
            int port = 2005;
            BigInteger bigInt = BigInteger.valueOf(port);      
            byte[] ports = bigInt.toByteArray();
            zoo.create("/nodes/nodeF", ports, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

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
                }
            })){System.out.println(s);}


            final String path= "/nodes/nodeF";
            byte[] bn = zoo.getData(path, new Watcher() {
                public void process(WatchedEvent e){
                    try {
                        if(zoo.exists(path, false) != null){
                            System.out.println("new node has been created from nodeF");
                        }else{
                            System.out.println("nodeF has been deleted");
                        }
                    } catch (KeeperException | InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }, null);

            /*
                Reading the data and putting it in a Queue then in the arraylist using Zeromq
            */

            ZMQ.Socket s = context.createSocket(SocketType.PULL);
            while (!Thread.currentThread().isInterrupted()) {
                Queue<Integer> q = new LinkedList<>();
                try {
                    s.bind("tcp://127.0.0.1:2005");
                    for (int i = 1; i <= 1024; i++) {
                        byte[] data = s.recv(0);
                        if (data[0] == -1 || data[0] == 255) {
                            break;
                        }
                        q.add(ByteBuffer.wrap(data).getInt());
                    }
                    bytes.get(queCount).addAll(q);
                    System.out.println(bytes);
                    queCount++;
                } catch (Exception e) {
                }
            }
        } catch (IOException e1) {
        }
    }
}
