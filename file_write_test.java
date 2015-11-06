
public class file_write_test {
	public static void main(String args[]) throws Exception
	{
		String array[]={"a","ab","abc","abcd"};
		createFile x=new createFile("hello.txt");
		int i;
		for(i=0;i<array.length;i++)
		{x.addRecords(array[i]);}
		
		x.closeFile();
	}
}
