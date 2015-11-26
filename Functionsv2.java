import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;
import java.text.*;

public class Functionsv2 {
	//Functions implemented - function attributes are changed by changing factors in constants
		/* createSocket
		 * createPacket - characters
		 * createPacket - string
		 * pause
		 * makeTextFile - input as Datav2.FILENAME , DATA
		 * displayPacket
		 * readData
		 * getFileSize
		 * 
		 */
	public static DatagramSocket createServerSocket() throws SocketException
	{
		DatagramSocket skt=new DatagramSocket(Datav2.PORT_NUMBER_SERVER);
		return skt;
	}
	
	public static DatagramSocket createClientSocket() throws SocketException
	{
		DatagramSocket skt=new DatagramSocket(Datav2.PORT_NUMBER_CLIENT);
		return skt;
	}
	
	
	public static DatagramPacket createPacket()
	{
		byte [] buffer =new byte[Datav2.PACKET_SIZE];
		DatagramPacket pkt=new DatagramPacket(buffer,buffer.length);
		return pkt;
	}
		
	public static void makeTextFile(String fwrite) throws IOException
	{
		BufferedWriter writer=new BufferedWriter(new FileWriter(fwrite));
		int i,j,k;
		k=65;
		for(i=0;i<Datav2.NUM_UNIQUE_CHARACTERS;i++)
		{
			for(j=0;j<Datav2.PACKET_SIZE;j++)
			{
				writer.write((char)k);
			}
			k=k+1;
		}
		writer.close();
	}

	public static void pause() throws InterruptedException
	{
		Thread.sleep(Datav2.PAUSE_DURATION);
	}
	
	public static int getFileSize()
	{
		int filesize= (int) new File(Datav2.FILENAME).length();
		return filesize;
	}

	public static void display(String s1)
	{
		if(Datav2.VERBOSE==1)
		{System.out.println(s1);}
		try {
			Functionsv2.pause();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
