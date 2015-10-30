import java.util.*;
import java.io.*;
public class crc2 {
	public static void main(String args[]) throws InterruptedException
	{
		/*
		 * first ask for the degree of polynomial G(x)
		 * 2 - enter the polynomial
		 * 3 - enter degree of I(x)
		 * 4 - enter Ix
		 * 5 keep two arrays- old and new intialise to zero
		 * 6 loop over the degrees of I(x) +1
		 * 7 find new value by exor(of old and )
		 * 8at last print the value of old/new - check
		 */
		int j,k;
		int G_degree,I_degree;
		
		int a[]=new int[1];
		//char2bin(a,'F');
		
		Scanner s1=new Scanner(System.in);
		G_degree=s1.nextInt();
		
		int G[]=new int[G_degree+1];
		String kip;
		for(j=G_degree;j>=0;j--)
		{
			G[j]=s1.nextInt();
		}		
		kip=s1.next();
		System.out.println(kip.length());
			int[] I=new int[4*kip.length()];
			I=str2bin(kip);
			I_degree=4*kip.length();
			int i;
		/*
			for (i=0;i<I_degree;i++)
		{
			System.out.print(I[i]);
		}*/
		
		print_poly(G,G_degree);
		//print_poly(I,I_degree);
		int a_old[]=new int[G_degree];int a_new[]=new int[G_degree];
		int bin;
		for(j=0;j<G_degree;j++){a_old[j]=0;a_new[j]=0;}
		//right till here
		System.out.println();
		for(j=I_degree-1;j>=0;j--)
		{
			
			bin=I[j];System.out.format("a_old[G_degree-1]=%d bin=%d ",a_old[G_degree-1],bin);
			for(k=0;k<G_degree;k++)
			{
				if(k==0)
				{ 
					a_new[0]=a_old[G_degree-1]^bin;
				}
				else if(k>0&&k<=G_degree-1)
				{
					if(G[k]==1)
					{ 
						a_new[k]=a_old[k-1]^a_old[G_degree-1];
					}
					else{a_new[k]=a_old[k-1];}
				}
			}
			for(k=0;k<G_degree;k++)
			{
				a_old[k]=a_new[k];
			}
			print_poly2(a_old,G_degree-1);
		}
	}
	
	public static int[] str2bin(String s) throws InterruptedException
	{
		int temp,j,dec,i;char c;
		int[] a=new int[4*s.length()];
		for(i=0;i<s.length();i++)
		{
			c=s.charAt(i);
			temp=(int)c;
			if (temp>=65)
			{dec=temp-55;}
			else
			{
				dec=temp-48;
			}
			String s2=Integer.toBinaryString(dec);
			System.out.println(s2);Thread.sleep(500);
			
			
			for(j=0;j<4;j++)
			{
				if(j<=s2.length()-1)
				{
					c=s2.charAt(j);
					a[4*i+j]=(int)c-48;
				}
				else
				{
					a[4*i+j]=0;
				}
			}
			
		}
		return a;
	}
	
	public static void print_poly(int a[],int n)
	{
		int i;
		for(i=n;i>=0;i--)
		{
			if(i>0&&a[i]==1)System.out.format("x^%d +",i);
			else if(i==0&&a[0]==1)System.out.println("1");
		}
	}
	
	public static void print_poly2(int a[],int n)
	{
		int i;
		for(i=n;i>=0;i--)
		{
			System.out.print(a[i]);
		}
		System.out.println();
	}
}
