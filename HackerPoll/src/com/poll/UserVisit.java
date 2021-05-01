import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserVisit {
	public static String filPath="D:\\studies\\learnings\\user_visit_data.csv";

	public static void main(String[] args) {
		UserVisit uv = new UserVisit();
		Scanner s = new Scanner(System.in);
		int inp;
		Map<String,Integer> cusGroup=new HashMap<String,Integer>();
		System.out.println("Processing the File:"+filPath);
		System.out.println("1.UltraPrime customer-range-(100000+) visit");
		System.out.println("2.Prime customer-range-(75000-100000) visit");
		System.out.println("3.Gold customer-range-(50000-75000) visit");
		System.out.println("4.Good customer-range-(25000-50000) visit");
		System.out.println("5.Freq customer-range-(10000-25000) visit");
		System.out.println("6.Simple customer-range-(5000-10000) visit");
		System.out.println("7.Rare customer - range-(1000-5000) visit");
		System.out.println("8.Bypassing customer - range-(0-1000) visit");
		System.out.println("Enter the Customer bucket you want to filter:(1-8)");
	//	filPath = s.next();
		inp=s.nextInt();
		while(inp<0 || inp>8)
		{
			System.out.println("Enter valid option");
			inp=s.nextInt();
		}
		cusGroup=uv.processFile(filPath);
	/*	for(Map.Entry<String,Integer> it : cusGroup.entrySet())
		{
			System.out.println("Cusomer id is"+it.getKey()+"and he visited our store for"+it.getValue());
		}*/
		List<Map.Entry<String,Integer>> cusultipri=cusGroup.entrySet().stream().filter(a -> a.getValue()>100000).collect(Collectors.toList());
		List<Map.Entry<String,Integer>> cuspri=cusGroup.entrySet().stream().filter(a -> a.getValue()>75000 && a.getValue()<100000 ).collect(Collectors.toList());
		List<Map.Entry<String,Integer>> cusgold=cusGroup.entrySet().stream().filter(a -> a.getValue()>50000 && a.getValue()<75000).collect(Collectors.toList());
		List<Map.Entry<String,Integer>> cusgood=cusGroup.entrySet().stream().filter(a -> a.getValue()>25000 && a.getValue()<50000).collect(Collectors.toList());
		List<Map.Entry<String,Integer>> cusfreq=cusGroup.entrySet().stream().filter(a -> a.getValue()>10000 && a.getValue()<25000).collect(Collectors.toList());
		List<Map.Entry<String,Integer>> cussimple=cusGroup.entrySet().stream().filter(a -> a.getValue()>5000 && a.getValue()<10000).collect(Collectors.toList());
		List<Map.Entry<String,Integer>> cusrare=cusGroup.entrySet().stream().filter(a -> a.getValue()>1000 && a.getValue()<5000).collect(Collectors.toList());
		List<Map.Entry<String,Integer>> cusbypass=cusGroup.entrySet().stream().filter(a -> a.getValue()>0 && a.getValue()<1000).collect(Collectors.toList());
		
		switch(inp)
		{
		case 1:
		{
		Iterator<Map.Entry<String,Integer>> it=cusultipri.iterator();
        while(it.hasNext())
        {
        	Map.Entry<String, Integer> ma=it.next();
        	System.out.println("This particular "+ma.getKey()+"customer visted our store more than 100000 times and exact visit count is"+ma.getValue());
        }
        break;
		}
		
		case 2:
		{
        Iterator<Map.Entry<String,Integer>> ita=cuspri.iterator();
        while(ita.hasNext())
        {
        	Map.Entry<String, Integer> mb=ita.next();
        	System.out.println("This particular "+mb.getKey()+"customer visted our store between 75000-100000 and exact visit count is"+mb.getValue());
        }
        break;
		}
		
		case 3:
		{
        Iterator<Map.Entry<String,Integer>> itb=cusgold.iterator();
        while(itb.hasNext())
        {
        	Map.Entry<String, Integer> mc=itb.next();
        	System.out.println("This particular "+mc.getKey()+"customer visted our store between 50000-75000 times and exact visit count is"+mc.getValue());
        }
        break;
		}
        
		case 4:
		{
        Iterator<Map.Entry<String,Integer>> itc=cusgood.iterator();
        while(itc.hasNext())
        {
        	Map.Entry<String, Integer> md=itc.next();
        	System.out.println("This particular "+md.getKey()+"customer visted our store between 25000-50000 times and exact visit count is"+md.getValue());
        }
        break;
		}
	
		case 5:
		{
        Iterator<Map.Entry<String,Integer>> itd=cusfreq.iterator();
        while(itd.hasNext())
        {
        	Map.Entry<String, Integer> me=itd.next();
        	System.out.println("This particular "+me.getKey()+"customer visted our store between 10000-25000 times and exact visit count is"+me.getValue());
        }
        break;
		}
        
		case 6:
		{
        Iterator<Map.Entry<String,Integer>> ite=cussimple.iterator();
        while(ite.hasNext())
        {
        	Map.Entry<String, Integer> mf=ite.next();
        	System.out.println("This particular "+mf.getKey()+"customer visted our store between 5000-10000 times and exact visit count is"+mf.getValue());
        }
        break;
		}
        
		case 7:
		{
        Iterator<Map.Entry<String,Integer>> itf=cusrare.iterator();
        while(itf.hasNext())
        {
        	Map.Entry<String, Integer> mg=itf.next();
        	System.out.println("This particular "+mg.getKey()+"customer visted our store between 1000-5000 times and exact visit count is"+mg.getValue());
        
        }
        break;
		}
        
		case 8:
		{
        Iterator<Map.Entry<String,Integer>> itg=cusbypass.iterator();
        while(itg.hasNext())
        {
        	Map.Entry<String, Integer> mh=itg.next();
        	System.out.println("This particular "+mh.getKey()+"customer visted our store between 0-1000 times and exact visit count is"+mh.getValue());

        }
        break;
		}
		default:
		{
			System.out.println("Invalid option");
			break;
		}
		}
		
        System.out.println("Program Succesfully Completed.......");

		s.close();
	}

	public Map<String, Integer> processFile(String filepath) {
		File proFile = new File(filepath);
		Map<String, Integer> cusMap = new HashMap<String, Integer>();
		String filData;
		try {
			BufferedReader br = new BufferedReader(new FileReader(proFile));
			br.readLine();
			while ((filData = br.readLine()) != null) {
				String arr[] = filData.split("\"*\"");	
				cusMap.put(arr[1], Integer.parseInt(arr[3]));
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Exception occured" + e);
		}
		return cusMap;

	}

}
