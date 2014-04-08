package Almoss;
import java.io.*;

public class Operations {
	
		public static int[] addition(String filename1,String filename2) throws IOException
		{
			DataInputStream f1=new DataInputStream(new FileInputStream(filename1));
			DataInputStream f2=new DataInputStream(new FileInputStream(filename2));
			DataOutputStream writer = new DataOutputStream (new FileOutputStream (filename1+"+"+filename2+".mcs"));
			int data1[]=new int[512];
			int data2[]=new int[512];
			int somme[]=new int[512];
			int size=512;
			for(int i=0;i<size;i++)
			{
				data1[i]=f1.readInt();
			}
			f1.close();
			for(int i=0;i<size;i++)
			{
				data2[i]=f2.readInt();
			}
			f2.close();
			for(int i=0;i<size;i++)
			{
				somme[i]=data1[i]+data2[i];
			}
			for(int i=0;i<size;i++)
			{
				writer.writeInt(somme[i]);
			}
			writer.close();
			return somme;
		}
		public static int[] soustraction(String filename1,String filename2) throws IOException
		{
			DataInputStream f1=new DataInputStream(new FileInputStream(filename1));
			DataInputStream f2=new DataInputStream(new FileInputStream(filename2));
			DataOutputStream writer = new DataOutputStream (new FileOutputStream (filename1+"-"+filename2+".mcs"));
			int data1[]=new int[512];
			int data2[]=new int[512];
			int diff[]=new int[512];
			int size=512;
			for(int i=0;i<size;i++)
			{
				data1[i]=f1.readInt();
			}
			f1.close();
			for(int i=0;i<size;i++)
			{
				data2[i]=f2.readInt();
			}
			f2.close();
			for(int i=0;i<size;i++)
			{
				diff[i]=1000000+data1[i]-data2[i];
			}
			for(int i=0;i<size;i++)
			{
				writer.writeInt(diff[i]);
			}
			writer.close();
			return diff;
		}
		

}
