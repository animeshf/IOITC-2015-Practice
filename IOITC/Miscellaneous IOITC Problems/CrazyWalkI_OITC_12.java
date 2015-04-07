/*
 * Animesh Fatehpuria
 * 
 */
import java.util.*;
import java.io.*;
class CrazyWalkIOITC
{
    static int dp[][]=new int[12001][1001];
    static int best[]=new int[12001];
    static int INF = 1000000001;
    public static void main(String[]args)
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int shock[]=new int[N+1001];
        int L = sc.nextInt();
        int Q = sc.nextInt();
        int M = sc.nextInt();
        for(int i=0;i<M;i++)
            shock[sc.nextInt()]=1;
        best[0]=0;
        for(int i=1;i<=N+L;i++)
        {
            int min=Integer.MAX_VALUE;
            for(int j=1;j<=L;j++)
            {
                if(shock[i]==1 || j>i)
                    dp[i][j]=INF;
                else if(j==i)
                    dp[i][j]=Q;
                else
                {
                    dp[i][j]=Math.min(dp[i-j][j]+1,best[i-j]+Q);
                }
                min=Math.min(dp[i][j],min);
            }
            if(shock[i]==1)
                best[i]=INF;
            else
                best[i]=min;
        }
        int min = Integer.MAX_VALUE;
        for(int i=N;i<=N+L;i++)
        min=Math.min(min,best[i]);
        if(min>=INF)
            System.out.println("-1");
        else
            System.out.println(min);
    }
}
