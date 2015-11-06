import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.nio.ByteBuffer;
import java.lang.*;
import java.util.*;
import java.lang.instrument.Instrumentation;

public class server {
	private static Instrumentation instrumentation;
	
	final static int PACKET_SIZE=512;//packet size is 512 becuase each char is made of 2 bytes
	final static int FILESIZE=2;
	
	public static void main(String args[]) throws Exception
	{
		//Reading the file and puttin it in buffer
		String fwrite="C:\\Users\\S.S. Mehta\\Desktop\\Codes\\ComputerNetworks\\CN_project\\CN_project\\test3.txt";
		BufferedWriter writer=new BufferedWriter(new FileWriter(fwrite));
		int i,j;
		for(i=65;i<65+FILESIZE;i++)
		{
			for(i=0;i<PACKET_SIZE;i++)
			{
				writer.write((char)i);
			}
		}
		writer.close();
		
		String filename="C:\\Users\\S.S. Mehta\\Desktop\\Codes\\ComputerNetworks\\CN_project\\CN_project\\test2.txt";
		BufferedReader in =new BufferedReader(new FileReader(filename));
		//in.wait();
		
		//defining a char array that will store the packet data
		char [] c1=new char[PACKET_SIZE];
		c1=packetRead(in);
		displayPacket(c1);
		char c2[]=new char[]{'a','b','c'};
		displayPacket(c2);
		
		//run a loop which checks whether the input isempty or not
		// read 1st 512 chars
		
		
	}
	
	public static void displayPacket(char c1[])
	{
		int i;
		for(i=0;i<c1.length;i++)
		{
			System.out.print(c1[i]);
		}
	}
	
	public static char[] packetRead(BufferedReader in) throws IOException
	{
		char [] c1=new char[PACKET_SIZE];
		int i;
		int c_temp=in.read();//c_temp is an int and not a char;
		while(c_temp!=-1)
		{
			c1[0]=(char)c_temp;
			for(i=1;i<PACKET_SIZE&&c_temp!=-1;i++)
			{
				c_temp=in.read();
				c1[i]=(char)c_temp;
			}
			
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
	public static void trash1()
	{

		/*
	while((line=in.readLine())!=null)
	{
		System.out.println(line);
	}

	in.close();
	
	try
	{
		//default_fn();
	}
	catch(Exception e)
	{
		
	}
	*/		
		/*
		String s1;
		s1=f1.toString();
		
		String s2="abc";
		System.out.println(s2);
		
		
		byte[] bytes=new byte[32*1024];
		ByteBuffer buffer =ByteBuffer.wrap(bytes);
		buffer.clear();
		byte[] b1=new byte[1024];
		char[] char_array=new char[]{'a','b','c'};
		//System.out.println(instrumentation.getObjectSize(char_array));
		*/
	}
	
}
