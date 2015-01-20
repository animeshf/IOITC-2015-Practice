
import java.util.*;
import java.lang.*;
import java.io.*;
class MIDDLEMAN
{
	public static void main (String[] args)
	{
	    Scanner sc = new Scanner (System.in) ;
	    int M = sc.nextInt();
	    int N = sc.nextInt();
	    int a[][]=new int [M+1] [N+1] ;
	    int dp[][]=new int [M+1] [N+1] ;
	    for(int i=1;i<=M;i++)
	    {
	        for(int j=1;j<=N;j++)
	        {
	            a[i][j]=sc.nextInt();
	            if(a[i][j]==0)
	            a[i][j]=-9999999;
	        }
	    }
	    for(int i=1;i<=M;i++)
	    {
	        for(int j=1;j<=N;j++)
	        {
	            dp[i][j]=dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1]+a[i][j];
	        }
	    }
	    int max=-100000000;
	    for(int i=1;i<=M;i++)
	    {
	        for(int j=1;j<=N;j++)
	        {
	            for(int k=1;k<=M;k++)
	            {
	                if(i+k > M)
	                break;
	                else if(j+k > N)
	                break;
	                int temp = dp[i+k][j+k] - dp[i-1][j+k] - dp[i+k][j-1] +dp[i-1][j-1];
	                max=Math.max(max,temp);
	            }
	        }
	    }
	    System.out.println(max);
	}
}
