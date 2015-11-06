import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;
import java.text.*;

public class client {
	public final static int PORT_NUMBER_CLIENT=6790;
	public static void main(String args[])
	{
		int i,j;//declaring random variables
		
		//declaring socket
			DatagramSocket skt;
		
		// finding time at which request is made
			String time,future_time;//defining two instances 
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
			//current time
				time= sdf.format(cal.getTime());
	    
			//future time- when movie is requested to play
			    cal.add(Calendar.SECOND,10);// addition of 10 seconds
			    future_time= sdf.format(cal.getTime());
		    
	    //writing socket program
		try{
			skt=new DatagramSocket(PORT_NUMBER_CLIENT);//socket used to listen and write
			
			//msg would be the message containing User name,movie name and start time in fomat hh:mm:ss
			String msg="";
			String name,movie_name,start_time;
			
			//Assigning values
				name="Guneet Singh Mehta";
				movie_name="Avatar";
				start_time=future_time;
			
			//Defining the message
				msg=msg+"\n"+name+"\n"+movie_name+"\n"+start_time;
			
			//converting message to bytes for DatagramPacket
				byte[] b=msg.getBytes();
			
			//getting localhost's name
				//InetAddress host = InetAddress.getByName("10.10.3.2");
				InetAddress host=InetAddress.getByName("localhost");
			// defining the socket number- completely random
				int serversocket=server.PORT_NUMBER;
			
			//defining a packet called request with parameters b(msg in bytes), b.length, host Internet address and socket number
				DatagramPacket request=new DatagramPacket(b,b.length,host,serversocket);
				System.out.println("request sent from client to server");
				//Thread.sleep(5000);//for error checks
			
			//Sending the packet
				skt.send(request);
			
		//getting reply from server........................................................................................
			byte [] buffer =new byte[10000];//apparently the size of data packet at the receiving side needs to be bigger than the size of incoming datapacket 
			DatagramPacket reply=new DatagramPacket(buffer,buffer.length);
			
			//receiving packet from server
				skt.receive(reply);
				System.out.println("Response Received from server");
			
				String server_response=null;
			//converting byte message to string
				server_response=new String(reply.getData());
				System.out.println("server response="+server_response);
		   //Closing the socket
				skt.close();
		}
		catch(Exception e)
		{
			
		}
		
	}
}
