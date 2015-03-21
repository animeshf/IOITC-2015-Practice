/*
 * Spoj (BVAAN)- Finding Longest Common Subsequence of Size K such that sum of characters is max
 */
import java.util.*;
import java.io.*;
class BVAAN
{
    static String s,s1;
    static int dp[][][]=new int[101][101][101];
    static int memo[][]=new int[101][101];
    public static void main(String[]args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-->0)
        {
            for(int i=0;i<=100;i++)
            {
                for(int j=0;j<=100;j++)
                {
                    for(int k=0;k<=100;k++)
                    {
                        dp[i][j][k]=-1;
                    }
                }
            }
            for(int i=0;i<=100;i++)
            {
                for(int j=0;j<=100;j++)
                    memo[i][j]=-1;
            }
            s=br.readLine();
            s1=br.readLine();
            int n = s.length();
            int m = s1.length();
            int k = Integer.parseInt(br.readLine());
            int longest = lcs(n,m);
            if(longest>=k)
                System.out.println(solve(n,m,k));
            else
                System.out.println("0");
        }
    }

    static int solve(int pos1,int pos2,int size)
    {
        if(pos1<=0||pos2<=0||size<=0)
        {
            return 0;
        }
        if(dp[pos1][pos2][size]!=-1)
            return dp[pos1][pos2][size];
        int aa=0,bb,c;
        if(s.charAt(pos1-1)==s1.charAt(pos2-1))
        {
            aa=solve(pos1-1,pos2-1,size-1)+(int)s.charAt(pos1-1);
        }
        bb=solve(pos1-1,pos2,size);
        c=solve(pos1,pos2-1,size);
        return dp[pos1][pos2][size]=Math.max(aa,Math.max(bb,c));

    }

    static int lcs(int m, int n )
    {
        int L[][]=new int[m+1][n+1];
        int i, j;
        for (i=0; i<=m; i++)
        {
            for (j=0; j<=n; j++)
            {
                if (i == 0 || j == 0)
                    L[i][j] = 0;

                else if (s.charAt(i-1) == s1.charAt(j-1))
                    L[i][j] = L[i-1][j-1] + 1;

                else
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
            }
        }
        return L[m][n];
    }
}