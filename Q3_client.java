import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;
import java.text.*;

public class Q3_client {
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
			skt=new DatagramSocket();//socket used to listen and write
			
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
				InetAddress host = InetAddress.getByName("10.10.3.2");
				//InetAddress host=InetAddress.getByName("localhost");
			// defining the socket number- completely random
				int serversocket=6789;
			
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
		   
		   //Defining new variables to be used in the reply part
				String client_name2,movie_name2,time2,cost2,password2;
		   
		   //splitting the message into fields separated by newline character
		   		String [] arrayMsg=(server_response.split("\n"));
		   		System.out.println(server_response);
				client_name2=arrayMsg[0];
				movie_name2=arrayMsg[1];
				time2=arrayMsg[2];
				cost2=arrayMsg[3];
				password2=arrayMsg[4];
			
			//writing the client log - for the data received from the server 
				PrintWriter writer2=new PrintWriter("client_log.txt","UTF-8");
				writer2.println(client_name2);
				writer2.println(movie_name2);
				writer2.println(time2);
				writer2.println(cost2);
				writer2.println(password2);
				writer2.close();
				//System.out.println(client_name2+" "+movie_name2+" "+time2+" "+" "+cost2+" "+password2);//For error check
				//Thread.sleep(5000);//for error check
			
		   //Closing the socket
				skt.close();
		}
		catch(Exception e)
		{
			
		}
		
	}
}
