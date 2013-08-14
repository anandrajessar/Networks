/*
Program       : To Implement a simple TCP/IP Echo server
Language used : JAVA
Role          : client in 'Echo server implementation'
How to compile: javac echoclient.java
To run        : java echoclient (Run the server before the client)
*/

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class echoclient {

 public static void main(String[] args) throws IOException {
 //create client socket
 Socket clientsoc =null;
 PrintWriter out=null;
 BufferedReader in=null;
 String msg;
 Scanner sc= new Scanner(System.in);
 BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
 //prompt for server hostname and port
 System.out.println("Enter the IP address");
 String ip=s.readLine();

 System.out.println("Enter Port No. to connect");
 int port =sc.nextInt();
 System.out.println ("Connecting ...");


    try {

 //socket           
            clientsoc = new Socket(ip, port);

            out = new PrintWriter(clientsoc.getOutputStream(), true);
 //read server response
            in = new BufferedReader(new InputStreamReader(clientsoc.getInputStream()));

        } catch (UnknownHostException e) {

            System.err.println("Host unavailable " + ip);

            System.exit(1);

        } catch (IOException e) {

            System.err.println("Error in connecting, check Hostname and port");

            System.exit(1);

        }
 //read user input, perform actions
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println ("Type 'exit' to quit, 'kill server' to stop server");
        System.out.println ("Enter your message");
	while ((msg = br.readLine()) != null) 

        {
 //send message to server
	 
        out.println(msg);
        if (msg.equals("exit"))
        break;
 //show response from server	    
        System.out.println("Server response: " + in.readLine());

	}
 //close socket
	out.close();

	in.close();

	br.close();

	clientsoc.close();

    }

}

