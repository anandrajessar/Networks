/*
Program       : To Implement a simple TCP/IP Echo server
Language used : JAVA
Role          : server in 'Echo server implementation'
How to compile: javac echoserver.java
To run        : java echoserver
*/

import java.net.*; 
import java.io.*; 
import java.util.Scanner;

public class echoserver extends Thread

{ 

 protected static boolean alive = true;
 //create socket
 protected Socket cSocket;

 public static void main(String[] args) throws IOException 

 { 
 //server socket
 ServerSocket sSocket = null; 

 Scanner sc= new Scanner(System.in);
 //get port number to be used 
 System.out.println("Port value must be in (0, 65535].");
 System.out.println("Enter Port No. to use");
 int port =sc.nextInt();

 try 
   { 
   sSocket = new ServerSocket(port); 
   System.out.println ("Initialising.., port "+port+ " opened");

    try 
      { 
 //wait until connection
       while (alive)

           {
                  System.out.println ("Waiting for Connection");
                  new echoserver (sSocket.accept()); 
           }

      } 

    catch (IOException e) 

          { 

                  System.err.println("Failed to connect"); 

                  System.exit(1); 

          } 

   } 
 //failed create socket connection
 catch (IOException e) 

        { 

         System.err.println("Could not open port"+port); 

         System.exit(1); 

        } 

 finally

        {

         try {
 //close server socket
              sSocket.close(); 

             }

         catch (IOException e)

             { 

              System.err.println("Could not close port "+port); 

              System.exit(1); 

             } 

       }

 }


 //socket obtained
 private echoserver (Socket soc)

   {

    cSocket = soc;
 //call run
    start();

   }

 public void run()

   {

    System.out.println ("New Connection Established..");

    String clientmsg; 
    try { 

         PrintWriter out = new PrintWriter(cSocket.getOutputStream(), true);

         BufferedReader in = new BufferedReader(new InputStreamReader( cSocket.getInputStream())); 
 //read client message
         while ((clientmsg = in.readLine()) != null) 

              { 

              System.out.println ("Message from client: " + clientmsg); 
 //respond
              out.println(clientmsg); 
 
             if (clientmsg.equals("exit")) 
             break; 

             if (clientmsg.equals("kill server")) 
                 { 
                System.out.println ("Server operation terminated"); 
                alive = false; 

		System.exit(1);
		 }

             } 

 //close connections and socket

         out.close(); 

         in.close(); 

         cSocket.close(); 

        } 

    catch (IOException e) 

        { 

         System.err.println("Connection problem..");

         System.exit(1); 

        } 

    }

} 
