import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;

public class SOK_1_server {

    public static void main(String[] args) throws Exception
    {
        SOK_1_server server =new SOK_1_server();// instantiate  a server object
        server.run();
    }

    public void run() throws Exception
    {
    	/*
    	createFile g= new createFile();
    	g.openFile();
    	g.addRecords("Hello");
    	g.closeFile();
    	*/
        ServerSocket srvsock=new ServerSocket(200);
        Socket sock=srvsock.accept();
        InputStreamReader IR=new InputStreamReader (sock.getInputStream());
        BufferedReader BR=new BufferedReader(IR);

        String message=BR.readLine();// what is received from client
        System.out.println(message);
 
        if(message!=null)
        {
            PrintStream PS=new PrintStream(sock.getOutputStream());
            PS.println("Message received");//message sent to client
        }
        PrintStream PS=new PrintStream(sock.getOutputStream());
        PS.println("Message received");//message sent to client
        System.out.println("in sleep");
        Thread.sleep(5000);
        System.out.println("out of sleep");
        srvsock.close();
    }

}