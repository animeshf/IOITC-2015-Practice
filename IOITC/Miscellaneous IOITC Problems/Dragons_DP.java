/*
 * Here There Be Dragons
 * dp[i][j]=
 * Minimum Number of Moves needed to kill the 'i'th dragon , assuming it is the'j'th dragon being killed.
 * Dragons are numbered from 1......D
 * Base Cases:dp[i][0] = INF
 *            dp[0][i] = INF
 *            dp[0][0] = 0
 * Answer = Minimum(dp[i][K]) for all 'i'
 */
import java.io.*;
import java.util.*;
class Dragons
{
    static long INF = (long)Math.pow(2,50);
    public static void main(String[]args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken()),C=Integer.parseInt(st.nextToken()),
            K = Integer.parseInt(st.nextToken()),D=Integer.parseInt(st.nextToken());
        pair dragons[]=new pair[D+1];
        dragons[0]=new pair(0,0);
        long co_x,co_y;
        for(int i=1;i<=D;i++)
        {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            co_x = Long.parseLong(st2.nextToken());
            co_y = Long.parseLong(st2.nextToken());
            dragons[i]=new pair(co_x,co_y);
        }
        Arrays.sort(dragons,1,1+D);
        long dp[][]=new long[D+1][K+1];
        for(int i=1;i<=D;i++)
            dp[i][0]=INF;
        for(int i=1;i<=K;i++)
            dp[0][i]=INF;
        dp[0][0]=0;
        long min,temp;
        for(int k=1;k<=K;k++)
        {
            for(int i=1;i<=D;i++)
            {
                min = INF;
                for(int j=0;j<i;j++)
                {
                    temp = dp[j][k-1] + distance(dragons[j],dragons[i]);
                    min=Math.min(min,temp);
                }
                dp[i][k]=min;
            }
        }
        min = INF;
        for(int i=1;i<=D;i++) 
            min = Math.min(min,dp[i][K]);
        System.out.println(min);
    }

    static long distance(pair a , pair b)
    {
        return (long)Math.abs(a.x-b.x) + (long)Math.abs(a.y-b.y);
    }
}
class pair implements Comparable<pair>
{
    long x,y;
    public pair()
    {
        x=0;y=0;
    }

    public pair(long a ,long b)
    {
        x=a;y=b;
    }

    public int compareTo(pair o)
    {
        return Long.compare(this.x,o.x);
    }
}
