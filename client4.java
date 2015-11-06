import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;
import java.text.*;

public class client4 {
	public final static int PORT_NUMBER_CLIENT=6790;
	public final static int BUFFER_SIZE_CLIENT=3;
	static int empty_index=0;
	public static void main(String args[]) throws InterruptedException, IOException
	{
		int i,j;
		String serverInetAddress="localhost";
		
		String server1AddressString="localhost";InetAddress server1Address=InetAddress.getByName(server1AddressString);
		String server2AddressString="localhost";InetAddress server2Address=InetAddress.getByName(server2AddressString);
		String server3AddressString="localhost";InetAddress server3Address=InetAddress.getByName(server3AddressString);
		String server4AddressString="localhost";InetAddress server4Address=InetAddress.getByName(server4AddressString);
		
		DatagramSocket skt;
		{
			skt=new DatagramSocket(PORT_NUMBER_CLIENT);//socket used to listen and write
			InetAddress host=InetAddress.getByName(serverInetAddress);
			int serversocket=server4.PORT_NUMBER_SERVER;	
			
			String msg="Send file size";
			byte[] b=msg.getBytes();
			
			//defining a packet called request with parameters b(msg in bytes), b.length, host Internet address and socket number
				DatagramPacket request=new DatagramPacket(b,b.length,host,serversocket);
				System.out.println("request sent from client to server");
				Thread.sleep(server4.PAUSE_DURATION);//for error checks
			
			//Sending the packet- for getting the file size
				skt.send(request);
			
		//getting reply from server........................................................................................
			byte [] buffer =new byte[server4.PACKET_SIZE];//apparently the size of data packet at the receiving side needs to be bigger than the size of incoming datapacket 
			DatagramPacket reply=new DatagramPacket(buffer,buffer.length);
			
			//receiving packet from server - contatining filesize
				skt.receive(reply);
				System.out.println("Response Received from server");
				
				System.out.println("on Client: - filesize= "+new String(reply.getData()));
				int filesize=Integer.parseInt(new String(reply.getData()).trim());
				System.out.println("on Client: - filesize= "+filesize);
				Thread.sleep(server4.PAUSE_DURATION);
				
			//here the client know the size of the file
			// Find the number of times it must make iterations - dividing filesize by packet_size
			// Request that many packets from server 		
			String [] buffer_string=new String[BUFFER_SIZE_CLIENT];
				for(i=0;i<filesize/server4.PACKET_SIZE;i++)
				{
					msg=String.valueOf(i);
					b=msg.getBytes();
					
					if(i%3==0)
					{host=server1Address;}
					else if(i%3==1)
					{host=server2Address;}
					else if(i%3==2)
					{host=server3Address;}
						
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
