import java.net.*; 
import java.io.*; 
import java.util.*;

//the following codes are based on https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html

public class EchoServer 
{ 
 public static void main(String[] args) throws IOException 
   { 
    ServerSocket serverSocket = null; 

    try{ 
       serverSocket = new ServerSocket(8005); 
    }catch (IOException e) {
       System.err.println("Could not listen on port: 8005."); 
       System.exit(1); 
    } 

    Socket clientSocket = null; 
    System.out.println ("Waiting for connection.....");

    try{ 
        clientSocket = serverSocket.accept(); 
    } catch (IOException e){
        System.err.println("Accept failed."); 
        System.exit(1); 
    } 

    System.out.println ("Connection successful");
    System.out.println ("Waiting for input.....");

    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); 
    BufferedReader in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream())); 

    String inputLine;
    BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

    while ((inputLine = in.readLine()) != null) {
        System.out.println ("Server: " + inputLine); 
        System.out.println("type something to reply?:");
        
        String reply=scan.readLine();

        out.println(reply); 

        if (inputLine.equals("Bye.")) 
            break; 
    } 

    out.close(); 
    in.close(); 
    clientSocket.close(); 
    serverSocket.close(); 
   } 
} 
