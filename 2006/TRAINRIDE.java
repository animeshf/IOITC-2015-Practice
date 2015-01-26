import java.util.*;
import java.io.*;
class TRAINRIDE
{
    public static void main(String[]args)
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt() , M =sc.nextInt();
        List<pair> pairs = new ArrayList<pair>();
        for(int i=0;i<M;i++)
        {
            int startTime = sc.nextInt() , endTime = sc.nextInt();
            pairs.add(new pair(startTime,endTime));
        }
        Collections.sort(pairs);
        int max=-1;
        int dp[]=new int[M];
        
        for(int i=0;i<M;i++)
        {
            if(pairs.get(i).startTime ==1)
            dp[i]=1;
        }
        /*
         * 1 5
         * 2 3
         * 4 7
         * 6 9
         */
        for(int i=1;i<M;i++)
        {
            int tempMax=1;
            for(int j=0 ; j<i ; j++)
            {
                if(pairs.get(j).endTime >= pairs.get(i).startTime)
                tempMax = Math.max(tempMax , dp[j]+1);
            }
            dp[i]=tempMax;
            max=Math.max(dp[i],max);
        }
        System.out.println(max);
    }
}
class pair implements Comparable<pair>
{
    int startTime , endTime;
    public pair(int startTime , int endTime )
    {
        this.startTime = startTime;
        this.endTime=endTime;
    }

    public int compareTo (pair o)
    {
        return this.startTime - o.startTime;
    }
}