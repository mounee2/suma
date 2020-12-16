package learn;

import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

import javax.sound.midi.Sequence;

public class Solution {

    public static void main(String[] args) {
        try {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        List<Integer> li=new ArrayList<Integer>();
        Scanner s=new Scanner(System.in);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int a=s.nextInt();
        int b=s.nextInt();
        String val=br.readLine();
        li=Stream.of(val.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
        System.out.println(a+" "+b);
        System.out.println(li);
        /*li=Arrays.stream(s.split("\\s")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(String.format("%.1f",Solution.mean(li,n)));
        System.out.println(Solution.median(li,n));
        System.out.println(Solution.mode(li,n));*/
        s.close();
        }
        catch(IOException e){
            
        }

    }
    public static double mean(List<Integer> al,int n)
    {
        double fin=0;
        for(int a:al)
            fin+=a;
        return fin/n;
    }
    public static double median(List<Integer> al,int n)
    {
        double pre,pos;
        Collections.sort(al);
        if(n%2==0)
        {
            pre=al.get(n/2);
            pos=al.get((n/2)-1);
            return ((pre+pos)/2);
        }
        else
        {  
            pre=al.get(n/2);
            return pre;
        }
    }
    public static int mode(List<Integer> al,int n)
    {
      //Collections.sort(al);
      Map<Integer, Long> modeMap=new HashMap<Integer,Long>();
      modeMap=al.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
      //return modeMap.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
      return Collections.max(modeMap.entrySet(), Comparator.comparingLong((Map.Entry::getValue))).getKey();
      
    }
}
