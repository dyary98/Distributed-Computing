
/* 
 * imagine we have the following code
 * try to reconnect to the server if we lost the connection to the server
  class{
    main{
        try{
            Socket S = new Socket("localhost", 4443");
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        }catch(Exception e){
            System.err.println(e);
        }
    }
  }

  ..............................................................................
! I think This should work
  class{
    main{
        Socket s = null;
        while(true){
            try{
                s = new Socket("localhost", 4443);
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                throw new Exception("connection Failed");
            }catch(Exception e){
                s = new Socket("localhost", 4443);
                System.out.println("connection regained");
            }
        }
    }
  }
 */

/* 

? imagine we are dealing with the server side we loose connection to the client 
* try to reconnect to the client
 class{
   main{
       try{
           ServerSocket Ss = new Socket(4443);
           while(1==1){
                Socket s = ss.accept();
               DataOutputStream dout = new DataOutputStream(s.getOutputStream());
               while(1==1){
                dos.writeUTF("auis")
               }
           }
       }catch(Exception e){
           System.err.println(e);
       }
   }
 }
 its hard to reconnect with the client!
*/
/**
 * Notes
 */
public class Notes {

}

// ! Socket represents a valid Tcp Connection that currently is established
// ! after the three way handshake, and that connection is created with the
// ! permission of the operating system

// ? What are the reasons for having an invalid or imbalanced TCP connection?
// ! a connection can be valid initially but after a while it can be invalid

/*
 * in multi-hub connections if one hub was down, every thing is down, so it can
 * be external factors, nothing wrong with your application layer
 * 
 * Ethier the client or the server send reset tcp packets, which means one of
 * them wants to finish the connection, This happens when one of them sends a
 * tcp packet with reset flag on
 * google: In TCP, packets with the "Reset" (RST or R) flag are sent to
 * abort a connection
 * 
 * Lets say You are sending data, You are the server, you have tcp, but you dont
 * get the acknowledgement back from the client.... Because the client is
 * disabled, Fo;r example it is a mobilie phone and it went into a tunnel and no
 * longer has 3G connection so the operating system of the server sends the data
 * again and again for multyple times but it is not infinte!!
 */
