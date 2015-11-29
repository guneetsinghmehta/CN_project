import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.nio.ByteBuffer;
import java.lang.*;
import java.util.*;
import java.lang.instrument.Instrumentation;

public class trash1 {
	public static void main(String args[]) throws IOException
	{
		DatagramPacket request=Functionsv2.createPacket();
		String msg="000000";byte[] b=msg.getBytes();
		
		int i;String requestString,requestServer;i=0;
		int y;
		for(i=0;i<3000;i++)
		{
			msg=Integer.toString(i);
			b=msg.getBytes();
			request.setData(b);
			
			//Functionsv2.display(requestString);
			Functionsv2.displayPacket(request);
			requestServer=Functionsv2.getPacketString(request);
			y=Integer.parseInt(requestServer);
			System.out.println("i="+i+" y="+y+" requestString="+msg+" requestServer="+requestServer);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*
		int i,x,y;
		x=1;
		for(i=0;i<100;i++)
		{
			x=i;
			String s1=Integer.toString(x);
			y=Integer.parseInt(s1);
			System.out.println("x="+x+" y="+y+" s1="+s1);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		
		/*
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
		String s1=new String("2");
		int i;i=Integer.parseInt(s1);
		System.out.println(i);
		
		float[] delaysFinal=new float[3000];
		//Arrays.sort(a);
		for(i=0;i<3000;i++)
		{
			delaysFinal[i]=3000-i;
			//System.out.println(a[i]);
		}
		float S2,S10,S20,S100,S3000;
		S2=Functionsv2.getSk(delaysFinal, 2);
		S10=Functionsv2.getSk(delaysFinal, 10);
		S20=Functionsv2.getSk(delaysFinal, 20);
		S100=Functionsv2.getSk(delaysFinal, 100);
		S3000=Functionsv2.getSk(delaysFinal, 3000);
		System.out.println("S2="+S2+" S10="+S10+" S20="+S20+" S100="+S100+" S3000="+S3000);
		System.out.println("done");
		*/
		
		
	}
	
	
}








