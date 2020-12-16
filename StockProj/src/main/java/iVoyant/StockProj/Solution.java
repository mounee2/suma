import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        List li=new ArrayList();
        try{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        String s=br.readLine();
        li=Arrays.asList(s.split(" "));
        double ou=Solution.mean(li,n);
        System.out.println("%.1f"+ou);
        System.out.println(Solution.median(li,n));
        System.out.println(Solution.mode(li,n));
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

    public static double mean(List<Integer> al,int n)
    {
        double fin=0;
        fin=al.stream().map(a -> a+fin);
        return fin/n;
    }
     public static double median(List al,int n)
    {
        double fin=0;
        for(int a : al)
        fin+=a+fin;
        return fin/n;
    }
     public static double mode(List al,int n)
    {
        double fin=0;
        for(int a: al)
        fin+=a+fin;
        return fin/n;
    }
}