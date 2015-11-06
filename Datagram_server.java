import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;

public class Datagram_server {
	public static void main(String args[])
	{
		DatagramSocket skt=null;
		try
		{
			skt=new DatagramSocket(6789);
			byte [] buffer =new byte[1000];
			while(true)
			{
				DatagramPacket request= new DatagramPacket(buffer,buffer.length);
				skt.receive(request);
				System.out.println("Data received from client");
				System.out.println(new String(request.getData()));
				Thread.sleep(15000);
				
				String [] arrayMsg=(new String(request.getData())).split(" ");
				
				System.out.println(arrayMsg[0]+"server processed");
				
				byte [] sendMsg=(arrayMsg[0]+ "server processed").getBytes();
				
				DatagramPacket reply=new DatagramPacket(sendMsg,sendMsg.length,request.getAddress(),request.getPort());
				
				System.out.println("sending data from server to client");Thread.sleep(15000);;
				skt.send(reply);
				
				
			}
		}
		catch(Exception e)
		{
			
		}
	}
}
