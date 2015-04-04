/**
 * 2 Arrays
 * dp[5001][5001] = 25000000 * 4 bytes = 100 MB approximately
 * dp2[5001] = 0.05 MB approximately
 * Total Memory Used = 100 MB approximately.
 */
import java.util.*;
import java.io.*;
class Jester
{
    static int dp[][]=new int[5001][5001];
    static int dp2[]=new int[5001];
    static int mod = 1000000007;
    public static void main(String[]args)
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // Input Number of Queries
        int N,M;
        preprocess(); // Preprocess answer for all queries.
        while(T-->0)
        {
            M = sc.nextInt();
            N = sc.nextInt();
            System.out.println(dp[N][M]); // Print Answer for each Query
        }
    }

    static void preprocess()
    {
        for(int i=1;i<=5000;i++)
        {
            dp[1][i]=i; // Base Cases
            dp2[i]=i; // Base Cases
        }
        for(int i=2;i<=5000;i++)
        {
            for(int j=1;j<=5000;j++)
            {
                dp[i][j]=dp[i-1][j] + dp2[j-1];
                dp[i][j]%=mod;
                dp2[j]+=dp[i][j];
                dp2[j]%=mod;
            }
        }
    }
}