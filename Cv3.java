import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.nio.ByteBuffer;
import java.lang.*;
import java.util.*;
import java.lang.instrument.Instrumentation;

public class Cv3 {
	public static void main(String args[]) throws IOException, InterruptedException
	{
		int i,filesize=1;
		DatagramSocket skt=Functionsv2.createClientSocket();
		DatagramPacket request=Functionsv2.createPacket();
		DatagramPacket reply=Functionsv2.createPacket();
		String msg="0";byte[] b=msg.getBytes();
		request.setData(b);
		//now contacting the serverss for the first time
		InetAddress host=InetAddress.getByName(Datav2.SERVER1_ADDRESS);
		float s1avg,s2avg,s3avg,s4avg;
		
		for (i=1;i<=4;i++)
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
		String requestString=new String("1");
		String requestedServerAddress=new String();
		String requestedServerAddressOld=new String();
		int requestedServerNumber;
		float[] delays=new float[Datav2.NUM_UNIQUE_CHARACTERS];float delayTemp;
		float[] delaysFinal=new float[Datav2.NUM_UNIQUE_CHARACTERS];
		
		//initialising s1avg--2,3,4
		s1avg=(float) 1.0*Datav2.DELAY_DURATION;
		s2avg=(float) 1.0001*Datav2.DELAY_DURATION;;
		s3avg=(float) 1.0002*Datav2.DELAY_DURATION;;
		s4avg=(float) 1.0003*Datav2.DELAY_DURATION;;

		int repeats=0;int j;
		for (i=0;i<Datav2.NUM_UNIQUE_CHARACTERS;i++)
		{
			requestString=Integer.toString(i+1);
			//System.out.println(requestString);
			requestedServerAddress=Functionsv2.getAddressOfMinServer(s1avg,s2avg,s3avg,s4avg); 
			if(i==0){requestedServerAddressOld=Datav2.SERVER2_ADDRESS;}
			//requestedServerAddress=Datav2.SERVER1_ADDRESS;
			
			Functionsv2.updatePacket(request, requestedServerAddress, Datav2.PORT_NUMBER_SERVER, requestString);
			delayTemp=System.nanoTime();
			skt.send(request);
			Functionsv2.delay();
			skt.receive(reply);
			delayTemp=System.nanoTime()-delayTemp;
			delays[i]=delayTemp/1000000;
			delayTemp=delayTemp/1000000;
			//reassigining values to savg
			if(requestedServerAddress==Datav2.SERVER1_ADDRESS){s1avg=Datav2.BETA*s1avg+(1-Datav2.BETA)*delayTemp;System.out.println("1"+"\ts1avg = "+s1avg+"\ts2avg = "+s2avg+"\ts3avg = "+s3avg+"\ts4avg = "+s4avg);}
			else if(requestedServerAddress==Datav2.SERVER2_ADDRESS){s2avg=Datav2.BETA*s2avg+(1-Datav2.BETA)*delayTemp;System.out.println("2"+"\ts1avg = "+s1avg+"\ts2avg = "+s2avg+"\ts3avg = "+s3avg+"\ts4avg = "+s4avg);}
			else if(requestedServerAddress==Datav2.SERVER3_ADDRESS){s3avg=Datav2.BETA*s3avg+(1-Datav2.BETA)*delayTemp;System.out.println("3"+"\ts1avg = "+s1avg+"\ts2avg = "+s2avg+"\ts3avg = "+s3avg+"\ts4avg = "+s4avg);}
			else if(requestedServerAddress==Datav2.SERVER4_ADDRESS){s4avg=Datav2.BETA*s4avg+(1-Datav2.BETA)*delayTemp;System.out.println("4"+"\ts1avg = "+s1avg+"\ts2avg = "+s2avg+"\ts3avg = "+s3avg+"\ts4avg = "+s4avg);}
			if(requestedServerAddress==requestedServerAddressOld){repeats++;}
			else{repeats=0;}
			//removing deadlock
			if(repeats==Datav2.MAX_REPEAT)
			{
				for(j=0;j<4;j++)
				{
					//print the value before using -- CAUTION !!
					if(j==0){request.setAddress(InetAddress.getByName(Datav2.SERVER1_ADDRESS));}
					else if(j==1){request.setAddress(InetAddress.getByName(Datav2.SERVER2_ADDRESS));}
					else if(j==2){request.setAddress(InetAddress.getByName(Datav2.SERVER3_ADDRESS));}
					else if(j==3){request.setAddress(InetAddress.getByName(Datav2.SERVER4_ADDRESS));}
					delayTemp=(float)0.0;
					delayTemp=System.nanoTime();
					skt.send(request);
					//Functionsv2.delay();
					skt.receive(reply);
					System.out.println(delayTemp);
					delayTemp=System.nanoTime()-delayTemp;
					System.out.println(delayTemp);
					delayTemp=delayTemp/1000000;
					System.out.println(delayTemp);
					delayTemp=delayTemp+Datav2.DELAY_DURATION;
					if(j==0){s1avg=delayTemp;}
					if(j==1){s2avg=delayTemp;}
					if(j==2){s3avg=delayTemp;}
					if(j==3){s4avg=delayTemp;}
					System.out.println(s1avg+" "+s2avg+" "+s3avg+" "+s4avg+" ");
					//System.out.println(delayTemp);
					Thread.sleep(4000);
				}
				repeats=0;
			}
			requestedServerAddressOld=requestedServerAddress;
			//Functionsv2.displayPacket(reply);
		}
		Arrays.sort(delays);
		for(i=0;i<delays.length;i++)
		{
			delaysFinal[i]=delays[delays.length-i-1];
		}
		float S2,S10,S20,S100,S1000,S3000;
		S2=Functionsv2.getSk(delaysFinal, 2);
		S10=Functionsv2.getSk(delaysFinal, 10);
		S20=Functionsv2.getSk(delaysFinal, 20);
		S100=Functionsv2.getSk(delaysFinal, 100);
		S1000=Functionsv2.getSk(delaysFinal, 1000);
		S3000=Functionsv2.getSk(delaysFinal, 3000);
		System.out.println("S2="+S2+" S10="+S10+" S20="+S20+" S100="+S100+" S1000="+S1000+" S3000="+S3000);
		System.out.println("done");
			
		
	}
}
