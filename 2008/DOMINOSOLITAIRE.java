import java.util.*;
class DOMINOSOLITAIRE
{
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        int a[][]=new int[3][N+1];
        for(int i=1;i<=2;i++)
        {
            for(int j=1;j<=N;j++)
            {
                a[i][j]=sc.nextInt();
            }
        }
        int dp[]= new int[N+1];
        dp[1]=Math.abs(a[1][1] - a[2][1]);
        for(int i=2; i<=N ; i++)
        {
          dp[i]= Math.max(dp[i-1] + Math.abs(a[1][i] - a[2][i]) , dp[i-2] + Math.abs(a[1][i-1] - a[1][i]) + Math.abs(a[2][i-1] - a[2][i]));
        }
        System.out.println(dp[N]);
    }
}

