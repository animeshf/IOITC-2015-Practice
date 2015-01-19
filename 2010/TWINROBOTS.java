
import java.util.*;
import java.lang.*;
import java.io.*;

class TWINROBOTS
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    Scanner sc = new Scanner(System.in);
	    int N= sc.nextInt();
	    int a[][]=new int [N+1][N+1];
	    int dp[][]=new int[N+1][N+1];
	    for(int i=1;i<=N;i++)
	        for(int j=1;j<=N;j++)
	            a[i][j]=sc.nextInt();
	    
	    for(int i=1;i<=N;i++)
	        for(int j=1;j<=N;j++)
	         dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1])+a[i][j]+a[j][N-i+1];
	        
	        System.out.println(dp[N][N]);
	    
	}
}
