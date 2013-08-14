
EXPLANATION

echoserver.java :

As the Server  should  support multiple clients, we extend our java class to use thread concept.

    public class echoserver extends Thread

Get port number to use as user input.
The server socket waits for requests to come in over the network. 
To establish the socket connection between the client and the server, we use

    new echoserver (sSocket.accept()); 

Accept() is a blocking call. If the connection is accepted by the server, it reads a line from client’s  input stream. Server echoes the message and write to client’s outputstream using PrintWriter

     PrintWriter out = new PrintWriter(cSocket.getOutputStream(), true);
         BufferedReader in = new BufferedReader(new InputStreamReader( cSocket.getInputStream())); 

Continuously read message from client

    while ((clientmsg = in.readLine()) != null) 
              { 
    System.out.println ("Message from client: " + clientmsg); 
              out.println(clientmsg); 
              }

The socket and streams are closed after the use.

    out.close(); 
    in.close(); 
    cSocket.close(); 
 


echoclient.java

Read the hostname and port number to connect.

    Scanner sc= new Scanner(System.in);
    BufferedReader s=new BufferedReader(new
                      InputStreamReader(System.in));
                      
    System.out.println("Enter the IP address");
    String ip=s.readLine();
    System.out.println("Enter Port No. to connect");
    int port =sc.nextInt();


Create a socket to connect to server

    Socket clientsoc =null;

Write to the PrintWriter to send the message to server

    out = new PrintWriter(clientsoc.getOutputStream(), true);

The second parameter of PrintWriter() is boolean (autoflush). 
Read server response from socket’s inputstream.

    in = new BufferedReader(new InputStreamReader(clientsoc.getInputStream()));

On completion, close the open socket.

    clientsoc.close();
