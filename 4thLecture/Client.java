// import java.net.*;

// //socket is a built in class that you can find it in java .net package
// import java.io.*;//sending the actuall data

// public class Client {
// public static void main(String[] args) {
// /*
// *
// * in the osi model if you need anything below, Tcp or in Tcp it is related to
// * the kernel
// *
// * !in java socket library allows you to
// *
// */

// // ?sending data to Google?
// // Source port dont worry
// // Destination port 443
// // Destination IP
// // Desitination mac layer dont worry
// // source IP address dont worry
// // mac address
// // Source mac address dont worry

// try {
// Socket s = new Socket("192.168.168.75", 4000);

// // www.google.com is the domain name we also can write ipv4 or ipv6 also
// // 443 is one of the ports that are free on the google server
// // the default port for http is 80
// // !till now we only created a connection to google.com
// // to send data there are multyiple ways
// // three ways handshake is done
// DataOutputStream dout = new DataOutputStream(s.getOutputStream());
// // this is a bidirectional
// // getoutput srtream gives you the stream to get out the data
// // DataoutputStream is simplyfing our task, that provide some more
// functionality
// // that get output stream does not have .

// // !input channel aloows us to read data
// dout.writeUTF("What is the course code?");

// // ServerSocket ss = new ServerSocket(4443);
// // the port is less than 1000 so thats why it could not work on LINUX
// // ss.accept();
// // it will wait until you start a threeway handsahke on that port
// // Socket s = ss.accept();
// DataInputStream dis = new DataInputStream(s.getInputStream());

// System.out.println(
// dis.readUTF());
// } catch (UnknownHostException e) {
// e.printStackTrace();
// } catch (IOException e) {
// e.printStackTrace();
// }
// // two values required, string (a domain name or an ip address) number
// // (destination port)

// // s.close closes the entire tcp connection,

// }
// }

// // wire shark helps us understand what is happening , monitor from tcp to
// below
