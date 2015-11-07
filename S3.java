import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.nio.ByteBuffer;
import java.lang.*;
import java.util.*;
import java.lang.instrument.Instrumentation;

public class S3 {
	private static Instrumentation instrumentation;
	
	public final static int PACKET_SIZE=S1.PACKET_SIZE;//packet size is 512 becuase each char is made of 2 bytes
	public final static int FILESIZE=4;
	public final static int PORT_NUMBER_SERVER=6789;
	public final static int PAUSE_DURATION=1000;
	
	public static void main(String args[]) throws Exception
	{
		String clientInetAddress="10.10.7.1";
		//Making a text file
			String filename="test3.txt";
			makeTextFile(filename);

		//Reading the file and puttin it in buffer
			BufferedReader in =new BufferedReader(new FileReader(filename));
			char [] c1=new char[PACKET_SIZE];
			//c1=readData(in);
			//displayPacket(c1);
		
		//Step3 - making a socket , makeing a packet with inet address and sending it
			byte [] buffer =new byte[PACKET_SIZE];
			DatagramSocket skt=new DatagramSocket(PORT_NUMBER_SERVER);
			DatagramPacket request=new DatagramPacket(buffer,buffer.length);
			
			//stop till you receive - request for filesize
				wait(skt,request);
				System.out.println("On server side \nrequest received from Client");
			
			//making a packet with an inet address - 
				int filesize= (int) new File(filename).length();
				String filesizeString=""+filesize;
				System.out.println(filesize);
				InetAddress host = InetAddress.getByName(clientInetAddress);
				DatagramPacket reply=makePacket(filesizeString,host);
				 
			//Sending reply packet
				System.out.println("Sending reply packet to client");
				Thread.sleep(S1.PAUSE_DURATION);
				skt.send(reply);
			
		int i,requested_packet;		
		for(i=0;i<filesize/PACKET_SIZE;i++)
		{
			wait(skt,request);
			requested_packet=Integer.parseInt(new String(request.getData()).trim());
			System.out.println(requested_packet);
			Thread.sleep(S1.PAUSE_DURATION);
			
			c1=readData(in);
			c1=readData(in);
			c1=readData(in);
			reply=makePacket(new String(c1),host);
			
			skt.send(reply);
		}
			
		//closing the socket
		skt.close();
	}
	
	public static DatagramPacket makePacket(String s1,InetAddress inetAddress)
	{
		byte[] sendMsg=s1.getBytes();
		DatagramPacket packet=new DatagramPacket(sendMsg,sendMsg.length,inetAddress,client3.PORT_NUMBER_CLIENT);
		System.out.println(new String(packet.getData()));
		return packet;
	}
	
	public static DatagramPacket makePacket(char c1[],InetAddress inetAddress)
	{
		//byte[] sendMsg=(c1).toString().getBytes();
		String s1=new String(c1);
		System.out.println(c1);
		byte[] sendMsg=s1.getBytes();
		DatagramPacket packet=new DatagramPacket(sendMsg,sendMsg.length,inetAddress,client.PORT_NUMBER_CLIENT);
		System.out.println(new String(packet.getData()));
		return packet;
	}
	
	public static void wait(DatagramSocket skt,DatagramPacket request) throws IOException, InterruptedException
	{
		//stop till you receive 
				skt.receive(request);
				//System.out.println("request received");
				Thread.sleep(S1.PAUSE_DURATION);
				
	}
	
	public static void makeTextFile(String fwrite) throws IOException
	{
		BufferedWriter writer=new BufferedWriter(new FileWriter(fwrite));
		int i,j;
		for(i=65;i<65+FILESIZE;i++)
		{
			for(j=0;j<PACKET_SIZE;j++)
			{
				writer.write((char)i);
			}
		}
		writer.close();
	}
	
	public static void displayPacket(char c1[])
	{
		int i;
		for(i=0;i<c1.length;i++)
		{
			System.out.print(c1[i]);
		}
		System.out.println();
		
	}
	
	public static char[] readData(BufferedReader in) throws IOException
	{
		char [] c1=new char[PACKET_SIZE];
		int i;
		int c_temp=in.read();//c_temp is an int and not a char;
		if(c_temp!=-1)
		{
			c1[0]=(char)c_temp;
		}
			
		for(i=1;i<PACKET_SIZE&&c_temp!=-1;i++)
		{
			c_temp=in.read();
			c1[i]=(char)c_temp;
		}
		return c1;
	}
	
	
	public static String readNextPacket(int packetSize)
	{
		String result="abc";
		
		return result;
	}
	
	public static void default_fn() throws IOException
	{
		//Declaring Socket for sending and receiving data
		DatagramSocket skt=null;
	//declaring the fields to be used
		String client_name,movie_name,time,cost,password,reply_string;
		int i,j;
		//assigning the socket a port number
		skt=new DatagramSocket(6789);
	//defining the buffer and its size
		byte [] buffer =new byte[1000];
		while(true)
		{	
			//defining the packet to be received from client
				DatagramPacket request= new DatagramPacket(buffer,buffer.length);
			
			//receiving the packet
				skt.receive(request);
			
			//comment on the console
				System.out.println("Data received from client");
			
			//Thread.sleep(5000);//for error check
			
			//converting the message to string and then splitting it into fields divided by newline character
				String [] arrayMsg=(new String(request.getData())).split("\n");
				client_name=arrayMsg[1];
				movie_name=arrayMsg[2];
				time=arrayMsg[3];
				cost="$12";
				password="254";
			
			//Composing the reply message by appending start time,cost and password
				reply_string=client_name+"\n"+movie_name+"\n"+time+"\n"+cost+"\n"+password;
				System.out.println(reply_string);
				//Thread.sleep(5000); for error check
				
			//converting string message to byte
				byte [] sendMsg=(reply_string).getBytes();
			
			//composing the data packet and sending it to the client's address and the same port the client used
				DatagramPacket reply=new DatagramPacket(sendMsg,sendMsg.length,request.getAddress(),request.getPort());
			
			System.out.println("sending confirmation");
			
			//Thread.sleep(5000);//for error check
			
			//writing server log file
				PrintWriter writer=new PrintWriter("server_log.txt","UTF-8");
				writer.println(client_name);
				writer.println(movie_name);
				writer.println(time);
				writer.close();
				
			//sending the data packet
				skt.send(reply);
			//Closing the server socket
				skt.close();
	}
	}
		
}
