import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;

public class SOK_1_client
{
    public static void main(String args[]) throws Exception
    {
        SOK_1_client client=new SOK_1_client();
        client.run();
    }

    public void run() throws Exception
    {
        Socket sock=new Socket("localhost",200);
        PrintStream PS=new PrintStream(sock.getOutputStream());// message being sent out to the server
        PS.println("Hello to server from client");

        InputStreamReader IR=new InputStreamReader (sock.getInputStream());// to listen to the socket
        BufferedReader BR=new BufferedReader(IR);

        String message=BR.readLine();// what is received from client
        System.out.println(message);
        Thread.sleep(5000);
        sock.close();
    }
}