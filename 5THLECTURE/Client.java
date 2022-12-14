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
// ServerSocket ss = new ServerSocket(4443);
// // the port is less than 1000 so thats why it could not work on LINUX
// // ss.accept();
// // it will wait until you start a threeway handsahke on that port
// Socket s = ss.accept();
// DataInputStream dis = new DataInputStream(s.getInputStream());
// for (int i = 0; i < 4; i++) {
// System.out.println(
// dis.readUTF());
// }

// // ! if you run this code twice till here you are going to get an error
// because
// // you can not start another one because the 443 port is bussy

// } catch (UnknownHostException e) {
// e.printStackTrace();
// } catch (IOException e) {
// e.printStackTrace();
// }

// // our fourth lecture code we are connecting to the server that we have
// opened
// // above
// // the domain name is local host and the port is just like the port number
// above
// try {
// Socket s = new Socket("localHost", 4443);

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
// dout.writeUTF("AUIS");
// dout.close();
// s.close();
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
