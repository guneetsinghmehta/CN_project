import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;
import java.text.*;

public class C1{
	public final static int PORT_NUMBER_CLIENT=8001;
	public final static int BUFFER_SIZE_CLIENT=32*S1.PACKET_SIZE;
	static int empty_index=0;
	public static void main(String args[]) throws InterruptedException, IOException
	{
		int i,j;
		String serverInetAddress="localhost";
		
		String server1AddressString="10.10.1.1";InetAddress server1Address=InetAddress.getByName(server1AddressString);
		String server2AddressString="10.10.2.2";InetAddress server2Address=InetAddress.getByName(server2AddressString);
		String server3AddressString="10.10.3.2";InetAddress server3Address=InetAddress.getByName(server3AddressString);
		String server4AddressString="localhost";InetAddress server4Address=InetAddress.getByName(server4AddressString);
		
		DatagramSocket skt;
		{
			skt=new DatagramSocket(PORT_NUMBER_CLIENT);//socket used to listen and write
			InetAddress host=InetAddress.getByName(serverInetAddress);
			int serversocket=S1.PORT_NUMBER_SERVER;	
			
			String msg="Send file size";
			byte[] b=msg.getBytes();
			
			//dummy assignments - not used anywhere
			int filesize=1;
			DatagramPacket reply,request;
			reply=new DatagramPacket(b,b.length,host,serversocket);
			request=new DatagramPacket(b,b.length,host,serversocket);
			
			for(i=1;i<=3;i++)
			{
				//defining a packet called request with parameters b(msg in bytes), b.length, host Internet address and socket number
				if(i==1){host=server1Address;}
				else if(i==2){host=server2Address;}
				else if(i==3){host=server3Address;}
				 request=new DatagramPacket(b,b.length,host,serversocket);
				//System.out.println("request sent from client to server");
				Thread.sleep(S1.PAUSE_DURATION);//for error checks
			
			//Sending the packet- for getting the file size
				skt.send(request);
			
			//		getting reply from server........................................................................................
				byte [] buffer =new byte[S1.PACKET_SIZE];//apparently the size of data packet at the receiving side needs to be bigger than the size of incoming datapacket 
				reply=new DatagramPacket(buffer,buffer.length);
			
			//receiving packet from server - contatining filesize
				skt.receive(reply);
				//System.out.println("Response Received from server");
				
				//System.out.println("on Client: - filesize= "+new String(reply.getData()));
				filesize=Integer.parseInt(new String(reply.getData()).trim());
				//System.out.println("on Client: - filesize= "+filesize);
				Thread.sleep(S1.PAUSE_DURATION);
			
			}
				
			//here the client know the size of the file
			// Find the number of times it must make iterations - dividing filesize by packet_size
			// Request that many packets from server 		
			String [] buffer_string=new String[BUFFER_SIZE_CLIENT];
			float delay[]=new float[filesize/S1.PACKET_SIZE];
			System.out.println(filesize);System.out.println(S1.PACKET_SIZE);
			System.out.println(filesize/S1.PACKET_SIZE);
			Thread.sleep(2000);
			byte [] buffer =new byte[S1.PACKET_SIZE];
				for(i=0;i<filesize/S1.PACKET_SIZE;i++)
				{
					if(i%100!=0)
					{
						//System.out.print(" "+i);
					}
					else
					{
						System.out.println(" "+i);
					}
					
					msg=String.valueOf(i);
					b=msg.getBytes();
					
					if(i%3==0)
					{host=server1Address;}
					else if(i%3==1)
					{host=server2Address;}
					else if(i%3==2)
					{host=server3Address;}
						
					request=new DatagramPacket(b,b.length,host,serversocket);
					
					skt.send(request);
					delay[i]=System.nanoTime();
					Thread.sleep(10);
					skt.receive(reply);
					delay[i]=System.nanoTime()-delay[i];
					delay[i]=delay[i]/(1000000);
					/*
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
					}*/
					
					//display_buffer(buffer_string);
				}
				Arrays.sort(delay);
				float delay2[]=new float[filesize/S1.PACKET_SIZE];
				for(i=0;i<delay2.length;i++)
				{
					delay2[i]=delay[delay.length-i-1];
				}
				//delay2 stores the array in descending values
				
				float [] Sk=new float[filesize/S1.PACKET_SIZE];
				Sk[0]=(float) 0.0;
			
				for (i=1;i<filesize/S1.PACKET_SIZE;i++)
				{
					for(j=1;j<=i;j++)
					{
						Sk[i]=Sk[i]+delay2[j];
					}
					Sk[i]=Sk[i]/(10*i);
				}
				make_output(Sk);
				System.out.format("Sk at 2=%f\n,10=%f\n,20=%f\n,100=%f\n and 30000=%f\n ",Sk[1],Sk[9],Sk[19],Sk[99],Sk[29999]);
				//display_buffer(buffer_string);
			skt.close();
		}
	}
	
	public static void make_output(float Sk[]) throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
		float temp=(float) 10.0;
		for(int i=0;i<Sk.length;i++)
		{
			writer.println(String.valueOf(Sk[i]));
		}
		writer.close();
	}
	
 	public static void display_buffer(String a[])
	{
		int i;
		//System.out.println();
		for(i=0;i<a.length&&a[i]!=null;i++)
		{
			//System.out.println(a[i]);
		}
	}
}
