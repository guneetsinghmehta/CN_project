import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.nio.ByteBuffer;
import java.lang.*;
import java.util.*;
import java.lang.instrument.Instrumentation;

public class S4v2 {
	public static void main(String args[]) throws IOException
	{
		Functionsv2.makeTextFile(Datav2.FILENAME);Functionsv2.display("File written\n");
		//Socket declare
		DatagramSocket skt=Functionsv2.createSocket();
		DatagramPacket request=Functionsv2.createPacket();//client request
		DatagramPacket reply=Functionsv2.createPacket();//reply to client request	
		
		skt.receive(request);
		//sending the filesize
		String filesizeString=""+Functionsv2.getFileSize();
		InetAddress host = InetAddress.getByName(Datav2.CLIENT_ADDRESS);
		
		reply.setAddress(InetAddress.getByName(Datav2.SERVER1_ADDRESS));
		reply.setPort(Datav2.PORT_NUMBER_CLIENT);
		skt.send(reply);
	}
	
	
}
