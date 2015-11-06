import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;
import java.text.*;

public class client2 {
	public final static int PORT_NUMBER_CLIENT=6790;
	public static void main(String args[]) throws IOException
	{
		DatagramSocket skt=new DatagramSocket(PORT_NUMBER_CLIENT);//socket used to listen and write
		InetAddress server_address=InetAddress.getByName("localhost");
		//String filename=args[0];
		String filename="test3.txt";
		/*
		 * Step 1 - ask the server if it contains a file - filename given by arg[0]
		 * Step 2 - 
		 */
		
		//Step1 - querying the server for filename
		DatagramPacket request=makePacket(filename,server_address);
			System.out.println("Sending packet to server containing "+(new String(request.getData())));
		skt.send(request);
		System.out.println("Packet Sent");
	}
	
	public static DatagramPacket makePacket(String s1,InetAddress inetAddress)
	{
		byte[] sendMsg=s1.getBytes();
		DatagramPacket packet=new DatagramPacket(sendMsg,sendMsg.length,inetAddress,server.PORT_NUMBER);
		System.out.println(new String(packet.getData()));
		return packet;
	}
}
