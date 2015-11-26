import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;
import java.text.*;

public class Datav2 {
	//static class containing the variables and functions
	//variables used
	/* PORT_NUMBER_CLIENT - port number of client
	 * PORT_NUMBER_SERVER - port number of server
	 * BUFFER_SIZE_CLIENT - buffer size - defining how many frames are there in the buffer of client (32 taken for project)
	 * FILESIZE - size of the file in terms of number of number of characters . total chars in file = PACKET_SIZE*FILESIZE
	 * PACKET_SIZE  - defines the size of each packet of data sent
	 * VERBOSE  - if 1 then it prints out the transactions , if 0 then does not
	 * PAUSE_DURATION - duration of pause after each step (units milli seconds)
	 * SERVER1_ADDRESS - stores inet address of server1
	 * SERVER2_ADDRESS
	 * SERVER3_ADDRESS
	 * SERVER4_ADDRESS
	 * CLIENT_ADDRESS -store inet address of server2
	 * FILENAME - filename of the concerned file being requested
	 */ 
	public static final  int PORT_NUMBER_CLIENT=6790;
	public static final  int PORT_NUMBER_SERVER=6789;
	public static final  int BUFFER_SIZE_CLIENT=32;
	public static final  int NUM_UNIQUE_CHARACTERS=2;
	public static final  int PACKET_SIZE=1;
	public static final  int VERBOSE=1;
	public static final  String SERVER1_ADDRESS="10.10.3.2";
	//public static final  String SERVER1_ADDRESS="localhost";
	public static final  String SERVER2_ADDRESS="10.10.4.2";
	public static final  String SERVER3_ADDRESS="10.10.1.2";
	public static final  String SERVER4_ADDRESS="10.10.2.2";
	//public static final  String CLIENT_ADDRESS="localhost";
	public static final  String CLIENT_ADDRESS="10.10.7.1";
	public static final int PAUSE_DURATION=3000;
	public static final String FILENAME="text1.txt";
	
}
