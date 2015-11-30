import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.nio.ByteBuffer;
import java.lang.*;
import java.util.*;
import java.lang.instrument.Instrumentation;

public class Cv32_trash {
	public static void main(String args[]) throws IOException, InterruptedException
	{
		int i,filesize=1;
		DatagramSocket skt=Functionsv2.createClientSocket();
		skt.setSoTimeout(Datav2.SOCKET_TIMEOUT);
		DatagramPacket request=Functionsv2.createPacket();
		DatagramPacket reply=Functionsv2.createPacket();
		DatagramPacket reply1=Functionsv2.createPacket();
		DatagramPacket reply2=Functionsv2.createPacket();
		DatagramPacket reply3=Functionsv2.createPacket();
		DatagramPacket reply4=Functionsv2.createPacket();
		String msg="0";byte[] b=msg.getBytes();
		request.setData(b);
		//now contacting the serverss for the first time
		InetAddress host=InetAddress.getByName(Datav2.SERVER1_ADDRESS);
		double s1avg,s2avg,s3avg,s4avg;
		
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
		String requestString=new String("1");
		String requestedServerAddress=new String();
		String requestedServerAddressOld=new String();
		int requestedServerNumber;
		double[] delays=new double[Datav2.NUM_UNIQUE_CHARACTERS];
		double delayTemp1,delayTemp2,delayTemp3,delayTemp4;
		delayTemp1=0;delayTemp2=0;delayTemp3=0;delayTemp4=0;
		double[] delaysFinal=new double[Datav2.NUM_UNIQUE_CHARACTERS];
		
		//initialising s1avg--2,3,4
		s1avg=(double) 1.0*Datav2.DELAY_DURATION;
		s2avg=(double) 1.0001*Datav2.DELAY_DURATION;;
		s3avg=(double) 1.0002*Datav2.DELAY_DURATION;;
		s4avg=(double) 1.0003*Datav2.DELAY_DURATION;;

		int repeats=0;int j;
		int repliesReceived=4;
		for (i=0;i<Datav2.NUM_UNIQUE_CHARACTERS;i=i+4)
		{	
			repliesReceived=0;
			for(j=0;j<4;j++)
			{
				requestString=Integer.toString(i+j+1);
				if(j==0)
				{
					requestedServerAddress=Datav2.SERVER1_ADDRESS;
					Functionsv2.updatePacket(request, requestedServerAddress, Datav2.PORT_NUMBER_SERVER, requestString);
					delayTemp1=System.nanoTime();
				}
				else if(j==1)
				{
					requestedServerAddress=Datav2.SERVER1_ADDRESS;
					Functionsv2.updatePacket(request, requestedServerAddress, Datav2.PORT_NUMBER_SERVER, requestString);
					delayTemp2=System.nanoTime();
				}
				else if(j==2)
				{
					requestedServerAddress=Datav2.SERVER1_ADDRESS;
					Functionsv2.updatePacket(request, requestedServerAddress, Datav2.PORT_NUMBER_SERVER, requestString);
					delayTemp3=System.nanoTime();
				}
				else if(j==3)
				{	
					requestedServerAddress=Datav2.SERVER1_ADDRESS;
					Functionsv2.updatePacket(request, requestedServerAddress, Datav2.PORT_NUMBER_SERVER, requestString);
					delayTemp4=System.nanoTime();
				}
				System.out.println("req sent");
				skt.send(request);
				
			}
			System.out.println("receiving 4replies");
			for(j=0;j<4;j++)
			{
				System.out.println("replies received="+repliesReceived);
				if(j==0)
				{
					try
					{
						skt.receive(reply1);
						repliesReceived++;
						delayTemp1=System.nanoTime()-delayTemp1;
						delayTemp1=delayTemp1/1000000;
						delayTemp1=delayTemp1+Datav2.DELAY_DURATION;
					}
					catch(Exception e){System.out.println("client timed out");}
				}
				else if(j==1)
				{
					try
					{
						skt.receive(reply2);
						repliesReceived++;
						delayTemp2=System.nanoTime()-delayTemp2;
						delayTemp2=delayTemp2/1000000;
						delayTemp2=delayTemp2+Datav2.DELAY_DURATION;
					}
					catch(Exception e){System.out.println("client timed out");}
				}
				else if(j==2)
				{
					try
					{
						skt.receive(reply3);
						repliesReceived++;
						delayTemp3=System.nanoTime()-delayTemp3;
						delayTemp3=delayTemp3/1000000;
						delayTemp3=delayTemp3+Datav2.DELAY_DURATION;
					}
					catch(Exception e){System.out.println("client timed out");}
				}
				else if(j==3)
				{
					try
					{
						skt.receive(reply4);
						repliesReceived++;
						delayTemp4=System.nanoTime()-delayTemp4;
						delayTemp4=delayTemp4/1000000;
						delayTemp4=delayTemp4+Datav2.DELAY_DURATION;
					}
					catch(Exception e){System.out.println("client timed out");}
				}
			}
		}
	}
}
