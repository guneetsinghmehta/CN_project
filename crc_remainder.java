import java.util.*;
import java.io.*;
public class crc_remainder {
	public static void main(String args[])
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
		Scanner s1=new Scanner(System.in);
		G_degree=s1.nextInt();I_degree=s1.nextInt();
		int G[]=new int[G_degree+1];int I[]=new int[I_degree+1];
		for(j=G_degree;j>=0;j--)
		{
			G[j]=s1.nextInt();
		}
		
		for(j=I_degree;j>=0;j--)
		{
			I[j]=s1.nextInt();
		}
		print_poly(G,G_degree);
		print_poly(I,I_degree);
		int a_old[]=new int[G_degree];int a_new[]=new int[G_degree];
		int bin;
		for(j=0;j<G_degree;j++){a_old[j]=0;a_new[j]=0;}
		//right till here
		System.out.println();
		for(j=I_degree;j>=0;j--)
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
