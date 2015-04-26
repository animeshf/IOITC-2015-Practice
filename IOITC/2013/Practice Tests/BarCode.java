import java.io.*;
import java.util.StringTokenizer;
class BarCode
{
    public static void main(String[]args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()),M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken()),y = Integer.parseInt(st.nextToken());
        char a [][]  = new char [N+1][M+1];
        for(int i = 1 ; i<= N ; i++)
        {
            String s = br.readLine();
            for(int j = 1 ; j <= M ; j++)
                a[i][j] = s.charAt(j-1);
        }
        int p[]=new int[M+1]; // Number of '.' from Column 1 to Column 'i'
        int q[]=new int[M+1]; // Number of '#' from Column 1 to Column 'i'
        for(int i = 1 ; i <= M ; i++)
        {
            for(int j = 1 ; j <= N ; j++)
            {
                if(a[j][i]=='.')  p[i]++;
                else              q[i]++;
            }
            p[i]+=p[i-1];
            q[i]+=q[i-1];
        }
        long dp[][]=new long[M+1][2] , INF = Integer.MAX_VALUE;
        for(int i=0;i<=M;i++)
            for(int j=0;j<=1;j++)
                dp[i][j]=INF;
        dp[0][0] = 0; dp[0][1] = 0; // BASE CASES
        for(int i = 1 ; i<= M ; i++)
        {
            for(int j = 1 ; j <= i ; j++)
            {
                if( i - j + 1 >=x && i - j +1 <=y ) // Within Range Allowed
                {
                    // Considering j...i as one whole range of # .
                    dp[i][0] = Math.min(dp[i][0],dp[j-1][1]+p[i]-p[j-1]);
                    // Considering j...i as one whole range of '.'
                    dp[i][1] = Math.min(dp[i][1],dp[j-1][0]+q[i]-q[j-1]);
                }
            }
        }
        long ans = Math.min(dp[M][1] , dp[M][0]);
        if(ans>=INF) // IMPOSSIBLE
            System.out.println("0");
        else
            System.out.println(ans);            
    }
}