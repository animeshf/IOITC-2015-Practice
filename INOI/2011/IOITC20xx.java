import java.util.*;
class IOITC20xx 
    {
        public static void main(String [] args)
        {
            Scanner sc=new Scanner(System.in);
            int n=sc.nextInt(),k=sc.nextInt();
            int a[]=new int[n+1];
            for(int i=1;i<=n;i++)
                a[i]=sc.nextInt();
             // Let DP[I][J] denote the maximum sum subarray 
             // ending at I and by omitting at most j elements.
            int dp[][]=new int[n+1][k+1];
            dp[0][0]=0;
            dp[1][0]=a[1];
            int max=a[1];
            //dp[i][0]= Standard Maximum Contiguous subarray as 0 omitted elements
            for(int i=2;i<=n;i++)
            {
                dp[i][0]=Math.max(dp[i-1][0]+a[i],a[i]);// Simple SubArray
                if(dp[i][0]>max)
                max=dp[i][0];
            }
            // System.out.println("Standard Problem Answer ===  "+dp[n][0]);
            for(int i=0;i<=k;i++)
                dp[0][i]=0; // Obvious Base Case
            for(int i=1;i<=n;i++)
            {
                for(int j=1;j<=k;j++)
                {
                    dp[i][j]=Math.max(dp[i-1][j]+a[i],Math.max(dp[i-1][j-1],a[i]));
                    //System.out.println("Maximum Sub Array ending at "+i +" with at most "+j +" omitted elements is "+" "+dp[i][j]);
                    if(dp[i][j]>max)
                    max=dp[i][j];
                }
            }
            System.out.println(max);
        }
    }
            