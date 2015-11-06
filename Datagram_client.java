import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;

public class Datagram_client {
	public static void main(String args[])
	{
		DatagramSocket skt;
		try{
			skt=new DatagramSocket();
			String msg="test message";
			
			byte[] b=msg.getBytes();
			
			InetAddress host=InetAddress.getByName("localhost");
			int serversocket=6789;
			
			DatagramPacket request=new DatagramPacket(b,b.length,host,serversocket);
			System.out.println("request sent from client to server");
			System.out.println(msg);Thread.sleep(15000);
			skt.send(request);
			
			//.......................
			byte [] buffer =new byte[1000];
			DatagramPacket reply=new DatagramPacket(buffer,buffer.length);
			skt.receive(reply);
			
		   System.out.println("Client Received=" +new String(reply.getData()));
		   Thread.sleep(15000);;
			
			skt.close();
		}
		catch(Exception e)
		{
			
		}
	}
}
