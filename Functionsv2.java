import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;
import java.text.*;

public class Functionsv2 {
	//Functions implemented - function attributes are changed by changing factors in constants
		/* makePacket - characters
		 * makePacket - string
		 * pause
		 * makeTextFile - input as Datav2.FILENAME , DATA
		 * displayPacket
		 * readData
		 */
		
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
	
}
