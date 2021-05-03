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

public class UserVisitCount {
	public static String filPath = "D:\\studies\\learnings\\user_visit_data.csv";

	public static void main(String[] args) {
		UserVisit uv = new UserVisit();
		Scanner s = new Scanner(System.in);
		int startrange, endrange;
		int customercount = 0;
		Map<String, Integer> cusGroup = new HashMap<String, Integer>();
		System.out.println("Processing the File:" + filPath);
		System.out.println("Enter the range of customer Visit you want to see");
		System.out.println("From this range _______");
		startrange = s.nextInt();
		s.nextLine();
		System.out.println("To this range _______");
		endrange = s.nextInt();
		// filPath = s.next();
		System.out.println("Please wait processing the file!!!!!!!!!!!!!!");
		cusGroup = uv.processFile(filPath);
		/*
		 * for(Map.Entry<String,Integer> it : cusGroup.entrySet()) {
		 * System.out.println("Cusomer id is"+it.getKey()+"and he visited our store for"
		 * +it.getValue()); }
		 */
		List<Map.Entry<String, Integer>> cuspri = cusGroup.entrySet().stream()
				.filter(a -> a.getValue() > startrange && a.getValue() < endrange).collect(Collectors.toList());
		Iterator<Map.Entry<String, Integer>> it = cuspri.iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> ma = it.next();
			System.out.println("This particular customer with key " + ma.getKey() + "visted our store from "
					+ startrange + " to " + endrange + " and exact visit count is " + ma.getValue());
			customercount++;
		}
		if (customercount > 0)
			System.out.println("Total Number of Customers falling between this range - " + customercount);
		else
			System.out.println("Ooops!!! No customers are falling between this range");
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
