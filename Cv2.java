import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.nio.ByteBuffer;
import java.lang.*;
import java.util.*;
import java.lang.instrument.Instrumentation;

public class Cv2 {
	public static void main(String args[]) throws IOException, InterruptedException
	{
		int i,filesize;
		DatagramSocket skt=Functionsv2.createSocket();
		DatagramPacket request=Functionsv2.createPacket();
		DatagramPacket reply=Functionsv2.createPacket();
		
		String msg="Send file size";byte[] b=msg.getBytes();
		request.setData(b);
		
		//now contacting the serverss for the first time
		InetAddress host=InetAddress.getByName(Datav2.SERVER1_ADDRESS);
		for (i=1;i<=4;i++)
		{
			if(i==1)host=InetAddress.getByName(Datav2.SERVER1_ADDRESS);
			else if(i==2)host=InetAddress.getByName(Datav2.SERVER2_ADDRESS);
			else if(i==3)host=InetAddress.getByName(Datav2.SERVER3_ADDRESS);
			else if(i==4)host=InetAddress.getByName(Datav2.SERVER4_ADDRESS);
			request.setAddress(host);request.setPort(Datav2.PORT_NUMBER_SERVER);
			
			skt.send(request);
			skt.receive(reply);
			filesize=Integer.parseInt(new String(reply.getData()).trim());
			
			if(Datav2.VERBOSE==1){System.out.println("size of file="+filesize);Functionsv2.pause();}
		}
		
	}
}
