import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.nio.ByteBuffer;
import java.lang.*;
import java.util.*;
import java.lang.instrument.Instrumentation;

public class S2v2 {
	public static void main(String args[]) throws IOException, InterruptedException
	{
		Functionsv2.makeTextFile(Datav2.FILENAME);
		FileReader fr=new FileReader(Datav2.FILENAME);BufferedReader textReader=new BufferedReader(fr);
		char[] textData=textReader.readLine().toCharArray();
		
		//Socket declare
		DatagramSocket skt=Functionsv2.createServerSocket();
		DatagramPacket request=Functionsv2.createPacket();//client request
		DatagramPacket reply=Functionsv2.createPacket();//reply to client request	
		
		Functionsv2.display("server listenting");
		skt.receive(request);
		Functionsv2.display("request received from client");
		//sending the filesize
		String filesizeString=""+Functionsv2.getFileSize();
		InetAddress host = InetAddress.getByName(Datav2.CLIENT_ADDRESS);
		
		reply.setAddress(InetAddress.getByName(Datav2.CLIENT_ADDRESS));
		reply.setPort(Datav2.PORT_NUMBER_CLIENT);
		reply.setData(filesizeString.getBytes());
		Functionsv2.display("reply sent to  client");
		skt.send(reply);
		int query,i;
		String replyString,requestString;
		for(i=0;i<Datav2.NUM_UNIQUE_CHARACTERS;i++)
		{
			skt.receive(request);
			requestString=Functionsv2.getPacketString(request);
			//System.out.println(requestString);
			//requestString=requestString.substring(0, requestString.length());
			query=Integer.parseInt(requestString);
			replyString=Functionsv2.readPacketFromFile(textData, query+1);
			Functionsv2.updatePacket(reply, Datav2.CLIENT_ADDRESS, Datav2.PORT_NUMBER_CLIENT,replyString );
			skt.send(reply);
			//Functionsv2.displayPacket(reply);
		}
		
		
		
	}
	
	
}
