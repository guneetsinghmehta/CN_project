import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;

public class Q3_server {
	public static void main(String args[])
	{
		//Declaring Socket for sending and receiving data
			DatagramSocket skt=null;
		//declaring the fields to be used
			String client_name,movie_name,time,cost,password,reply_string;
			int i,j;
		try
		{
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
		catch(Exception e)
		{
			
		}
	}
}
