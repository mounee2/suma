import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SpaceAllocate {
	
	Map<List<Map<List<Map<String,Integer>>,Map<String,Integer>>>, Map<List<Map<String,Integer>>,Map<String,Integer>>> servicesMap = new HashMap<List<Map<List<Map<String,Integer>>,Map<String,Integer>>>, Map<List<Map<String,Integer>>,Map<String,Integer>>>();
	Map<String,Integer> inner21map = new HashMap<String,Integer>();
	Map<String,Integer> inner22map = new HashMap<String,Integer>();
	Map<String,Integer> inner23map = new HashMap<String,Integer>();
	Map<String,Integer> inner24map = new HashMap<String,Integer>();
	Map<String,Integer> inner25map = new HashMap<String,Integer>();
	
	List<Map<String,Integer>> outputs=new ArrayList<Map<String,Integer>>();
	List<Map<String,Integer>> inputs=new ArrayList<Map<String,Integer>>();
	Map<List<Map<String,Integer>>,Map<String,Integer>> inner11map = new HashMap<List<Map<String,Integer>>,Map<String,Integer>>();
	Map<List<Map<String,Integer>>,Map<String,Integer>> inner12map = new HashMap<List<Map<String,Integer>>,Map<String,Integer>>();
	Map<List<Map<String,Integer>>,Map<String,Integer>> inner13map = new HashMap<List<Map<String,Integer>>,Map<String,Integer>>();
	List<Map<List<Map<String,Integer>>,Map<String,Integer>>> services=new ArrayList<Map<List<Map<String,Integer>>,Map<String,Integer>>>();
	private Integer value;
	
	public static void main(String args[])
	{
		SpaceAllocate s=new SpaceAllocate();
		s.insert();
		s.check();
		//s.inputListCheck(mapMap);
	}
	
	public void check() {
		if (!servicesMap.isEmpty())
		{
			
			//List<Map<List<Map<String,Integer>>,Map<String,Integer>>> servicesList=new ArrayList<Map<List<Map<String,Integer>>,Map<String,Integer>>>();
			Map<List<Map<String,Integer>>,Map<String,Integer>> mapMap = new HashMap<List<Map<String,Integer>>,Map<String,Integer>>();
			mapMap=servicesMap.get(services);
			//Iterator<Integer> itr=servicesMap.keySet().iterator();
			mapMap.keySet().stream().forEach(System.out::println);
			//s.inputListCheck(mapMap);
			Map<List<Map<String,Integer>>,Map<String,Integer>> mapMa = new HashMap<List<Map<String,Integer>>,Map<String,Integer>>();
			List<Map<String,Integer>> mapLi = new ArrayList<Map<String,Integer>>();
			//check.keySet().stream().forEach(mapMa:check)
		   /*for(Map.Entry<List<Map<String,Integer>>,Map<String,Integer>> mapM:check.entrySet())
		   {
			   mapMa=mapM.getKey(inputs);
		   }*/
			mapMap.entrySet().stream().forEach(e -> value=e.getValue().get("inputs"));
			System.out.println(value);
			//return value;
			
		}
		
		
		
	}

	public Map<List<Map<List<Map<String,Integer>>,Map<String,Integer>>>, Map<List<Map<String,Integer>>,Map<String,Integer>>> insert()
	{	
		
		inner21map.put("value",215);
		inner22map.put("name",216);
		inner23map.put("description",217);
		inner24map.put("lies",218);
		inner25map.put("desc",219);
		
		
		outputs.add(inner21map);
		outputs.add(inner22map);
		outputs.add(inner23map);
		inputs.add(inner24map);
		inputs.add(inner25map);
		inputs.add(inner25map);
		
		
		inner11map.put(outputs,inner21map);
		inner12map.put(outputs,inner22map);
		inner13map.put(outputs,inner23map);
		inner11map.put(inputs,inner21map);
		inner12map.put(inputs,inner22map);
		inner13map.put(inputs,inner23map);
		
		
		servicesMap.put(services,inner11map);
		servicesMap.put(services,inner12map);
		servicesMap.put(services,inner13map);
		return servicesMap; 
	}
	
	private void inputListCheck(Map<List<Map<String,Integer>>,Map<String,Integer>> check)
	{

		Map<List<Map<String,Integer>>,Map<String,Integer>> mapMa = new HashMap<List<Map<String,Integer>>,Map<String,Integer>>();
		List<Map<String,Integer>> mapLi = new ArrayList<Map<String,Integer>>();
		//check.keySet().stream().forEach(mapMa:check)
	   /*for(Map.Entry<List<Map<String,Integer>>,Map<String,Integer>> mapM:check.entrySet())
	   {
		   mapMa=mapM.getKey(inputs);
	   }*/
		check.entrySet().stream().forEach(e -> value=e.getValue().get("inputs"));
		System.out.println(value);
		
	}
}
	
