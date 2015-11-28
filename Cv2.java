import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.nio.ByteBuffer;
import java.lang.*;
import java.util.*;
import java.lang.instrument.Instrumentation;

public class Cv2 {
	public static void main(String args[]) throws IOException, InterruptedException
	{
		int i,filesize=1;
		DatagramSocket skt=Functionsv2.createClientSocket();
		DatagramPacket request=Functionsv2.createPacket();
		DatagramPacket reply=Functionsv2.createPacket();
		
		String msg="Send file size";byte[] b=msg.getBytes();
		request.setData(b);
		
		//now contacting the serverss for the first time
		InetAddress host=InetAddress.getByName(Datav2.SERVER1_ADDRESS);
		
		for (i=1;i<=1;i++)
		{
			if(i==1)host=InetAddress.getByName(Datav2.SERVER1_ADDRESS);
			else if(i==2)host=InetAddress.getByName(Datav2.SERVER2_ADDRESS);
			else if(i==3)host=InetAddress.getByName(Datav2.SERVER3_ADDRESS);
			else if(i==4)host=InetAddress.getByName(Datav2.SERVER4_ADDRESS);
			request.setAddress(host);request.setPort(Datav2.PORT_NUMBER_SERVER);
			
			reply.setAddress(host);reply.setPort(Datav2.PORT_NUMBER_SERVER);
			Functionsv2.display("request sent by client");
			skt.send(request);
			skt.receive(reply);
			Functionsv2.display("reply received by client");
			//filesize=Integer.parseInt(new String(reply.getData()).trim());
		    String s1=new String(reply.getData()).trim();
		    Functionsv2.display("filesize received from server"+i+"="+s1);
			//Working version -25th Nov 10 pm
		}
		
		host=InetAddress.getByName(Datav2.SERVER1_ADDRESS);
		//packet being used - request
		String requestString=new String();
		String requestedServerAddress=new String();
		int requestedServerNumber;
		for (i=0;i<Datav2.NUM_UNIQUE_CHARACTERS;i++)
		{
			requestString=Integer.toString(i);
			
			if(i%4==0){requestedServerAddress=Datav2.SERVER1_ADDRESS;requestedServerNumber=1;}
			else if(i%4==1){requestedServerAddress=Datav2.SERVER2_ADDRESS;requestedServerNumber=2;}
			else if(i%4==2){requestedServerAddress=Datav2.SERVER3_ADDRESS;requestedServerNumber=3;}
			else if(i%4==3){requestedServerAddress=Datav2.SERVER4_ADDRESS;requestedServerNumber=4;}
			requestedServerAddress=Datav2.SERVER1_ADDRESS;
			Functionsv2.updatePacket(request, requestedServerAddress, Datav2.PORT_NUMBER_SERVER, requestString);
			skt.send(request);
			Functionsv2.delay();
			
			skt.receive(reply);
			Functionsv2.displayPacket(reply);
		}
		System.out.println("done");
			
		
	}
}
