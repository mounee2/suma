import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Sample1 
{
	
	public static void main(String args[])
	{
		Sample1 s=new Sample1();
		s.insert();
		
	}
	
	private void insert()
	{
		Map<List<Map<List<Map<String,Integer>>,Map<String,Integer>>>, Map<List<Map<String,Integer>>,Map<String,Integer>>> servicesMap = new HashMap<List<Map<List<Map<String,Integer>>,Map<String,Integer>>>, Map<List<Map<String,Integer>>,Map<String,Integer>>>();
		Map<String,Integer> inner2map = new HashMap<String,Integer>();
		inner2map.put("value",215);
		List<Map<String,Integer>> innerList=new ArrayList<Map<String,Integer>>();
		innerList.add(inner2map);
		Map<List<Map<String,Integer>>,Map<String,Integer>> inner1map = new HashMap<List<Map<String,Integer>>,Map<String,Integer>>();
		inner1map.put(innerList,inner2map);
		List<Map<List<Map<String,Integer>>,Map<String,Integer>>> services=new ArrayList<Map<List<Map<String,Integer>>,Map<String,Integer>>>();
		servicesMap.put(services,inner1map);
	}
}
