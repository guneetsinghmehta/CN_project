import java.io.*;//for input and output
import java.net.*;// for sockets library
import java.lang.*;
import java.util.*;

public class createFile
{
	private Formatter x;
	public createFile(String filename)
	{
		try
		{
			x=new Formatter(filename);
		}
		catch(Exception e)
		{
			System.out.println("Error Guneet");
		}
	}
	public void addRecords(String s)
	{
		x.format("%s \n",s);
	}
	
	public void closeFile()
	{
		x.close();
	}
}