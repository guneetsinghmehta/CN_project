import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;
import java.text.*;

public class client3 {
	public final static int PORT_NUMBER_CLIENT=6790;
	public final static int BUFFER_SIZE_CLIENT=3;
	static int empty_index=0;
	public static void main(String args[]) throws InterruptedException, IOException
	{
		int i,j;
		String serverInetAddress="localhost";
		DatagramSocket skt;
		{
			skt=new DatagramSocket(PORT_NUMBER_CLIENT);//socket used to listen and write
			InetAddress host=InetAddress.getByName(serverInetAddress);
			int serversocket=server3.PORT_NUMBER_SERVER;	
			
			String msg="Send file size";
			byte[] b=msg.getBytes();
			
			//defining a packet called request with parameters b(msg in bytes), b.length, host Internet address and socket number
				DatagramPacket request=new DatagramPacket(b,b.length,host,serversocket);
				System.out.println("request sent from client to server");
				Thread.sleep(server3.PAUSE_DURATION);//for error checks
			
			//Sending the packet- for getting the file size
				skt.send(request);
			
		//getting reply from server........................................................................................
			byte [] buffer =new byte[server3.PACKET_SIZE];//apparently the size of data packet at the receiving side needs to be bigger than the size of incoming datapacket 
			DatagramPacket reply=new DatagramPacket(buffer,buffer.length);
			
			//receiving packet from server - contatining filesize
				skt.receive(reply);
				System.out.println("Response Received from server");
				
				System.out.println("on Client: - filesize= "+new String(reply.getData()));
				int filesize=Integer.parseInt(new String(reply.getData()).trim());
				System.out.println("on Client: - filesize= "+filesize);
				Thread.sleep(server3.PAUSE_DURATION);
				
			//here the client know the size of the file
			// Find the number of times it must make iterations - dividing filesize by packet_size
			// Request that many packets from server 		
			String [] buffer_string=new String[BUFFER_SIZE_CLIENT];
				for(i=0;i<filesize/server3.PACKET_SIZE;i++)
				{
					msg=String.valueOf(i);
					b=msg.getBytes();
					request=new DatagramPacket(b,b.length,host,serversocket);
					
					skt.send(request);skt.receive(reply);
					if(empty_index<BUFFER_SIZE_CLIENT)
					{
						buffer_string[empty_index]=new String(reply.getData());
						empty_index++;
					}
					else
					{
						for(j=0;j<BUFFER_SIZE_CLIENT-1;j++)
						{
							buffer_string[j]=buffer_string[j+1];
						}
						buffer_string[BUFFER_SIZE_CLIENT-1]=new String(reply.getData());
					}
					display_buffer(buffer_string);
				}	
				display_buffer(buffer_string);
			skt.close();
		}
	}
	
	public static void display_buffer(String a[])
	{
		int i;
		System.out.println();
		for(i=0;i<a.length&&a[i]!=null;i++)
		{
			System.out.println(a[i]);
		}
	}
}
