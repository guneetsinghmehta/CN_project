import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.nio.ByteBuffer;
import java.lang.*;
import java.util.*;
import java.lang.instrument.Instrumentation;

public class trash1 {
	public static void main(String args[]) throws IOException
	{
		Functionsv2.makeTextFile(Datav2.FILENAME);
		FileReader fr=new FileReader(Datav2.FILENAME);BufferedReader textReader=new BufferedReader(fr);
		char[] textData=textReader.readLine().toCharArray();
		System.out.println(textData);
		int query=2;int packSize=4;
		char[] temp=Arrays.copyOfRange(textData, (query-1)*packSize,query*packSize);
		System.out.println(temp);
		
		String s1=new String(temp);
		System.out.println(s1);
		
		int i;
		for(i=1;i<Datav2.NUM_UNIQUE_CHARACTERS;i++)
		{
			Functionsv2.display(Functionsv2.readPacketFromFile(textData, i));
		}
	}
	
	
}








