
/*
Animesh Fatehpuria (animeshf)
USACO - CowHopSkotch
 */
import java.util.*;
import java.io.*;
public class CowHopskotch
{
    static int N,M;
    static long dp[][] = new long[101][101];
    static long a[][]=new long[101][101];
    static long mod=1000000007;
    public static void main(String[]args)throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("hopscotch.in"));
        PrintWriter pw = new PrintWriter( new FileWriter("hopscotch.out"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        int random = Integer.parseInt(st.nextToken());
        for(int i=0;i<M;i++)
        {
            StringTokenizer lol = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++)
            {
                a[i][j]=Long.parseLong(lol.nextToken());   
            }
        }
        for(int i=0;i<=100;i++)
        {
            for(int j=0;j<=100;j++)
            {
                dp[i][j]=-1;
            }
        }
        long ans=solve(0,0);
        //System.out.println(ans);
        pw.println(ans);
        pw.close();
    }

    static long solve(int x , int y)
    { 
        if(x==N-1&&y==M-1)
            return 1;
        if(x>=N||y>=M)
            return 0;
        if(dp[x][y]!=-1)
            return dp[x][y];
        long ans=0;
        for(int i=x+1;i<N;i++)
        {
            for(int j=y+1;j<M;j++)
            {
                if(a[i][j]!=a[x][y])
                    {
                        ans+=solve(i,j);
                        ans%=mod;
                    }
            }
        }
        return dp[x][y]=ans%mod;
    }
}

