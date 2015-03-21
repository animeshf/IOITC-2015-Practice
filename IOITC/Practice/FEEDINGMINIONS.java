/**
 * M   M    M    M   M   M   M   M   M   M   M   M   M   M

Motivation :

dp[i][2]

dp[i][0]== FEEDING I'TH BEFORE (I+1)th
dp[i][1]== FEEDING I'TH AFTER  (I+1)th

dp[0][0]=a[0]; 
dp[0][1]=b[0];

for(int i=1;i<N;i++)
{

dp[i][0]=Max(dp[i-1][0]+b[i],dp[i-1][1]+a[i]);
dp[i][1]=Max(dp[i-1][0]+c[i],dp[i-1][1]+b[i]);
}

Answer would be dp[N-1][0]

Explanation:

dp[i][0]=Max(dp[i-1][0]+b[i],dp[i-1][1]+a[i]);

dp[i-1][0]+b[i] == feeding the (i-1)th before ith and i'th before (i+1)th 
that means that one neighbour has been fed so add b[i]

dp[i-1][1]+a[i] == feeding the (i-1)th after i'th one and (i+1)th after ith
means no neighbours have been fed so add a[i]

dp[i][1]=Max(dp[i-1][0]+c[i],dp[i-1][1]+b[i]);
dp[i-1][0]+c[i] == feeding i-1 th neighbour before i
th which in turn is fed after i+1th,
Order of feeding : i-1 , i+1 , i , 
therefore add c[i] for i

dp[i-1][1]+b[i] == feeding i-1th neighbour after ith 
which in turn is fed after i+1th , so i'th is second in line and gets b[i]

[i]                     Neighbours:
M M M
1   1                 BOTH ARE FED
0   0                 NONE ARE FED
0   1                 RIGHT WALA FED
1   0                 LEFT WALA FED

*/

import java.io.*;
import java.util.StringTokenizer;
class FEEDINGMINIONS
{
    public static void main(String[]args)throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        while(T-->0)
        {
            int N=Integer.parseInt(br.readLine());
            int a[]=new int[N];
            int b[]=new int[N];
            int c[]=new int[N];
            int dp[][]=new int[N][2];
            for(int i=0;i<N;i++)
            {
                StringTokenizer st= new StringTokenizer(br.readLine());
                a[i]=Integer.parseInt(st.nextToken());
                b[i]=Integer.parseInt(st.nextToken());
                c[i]=Integer.parseInt(st.nextToken());
            }
            dp[0][0]=a[0]; 
            dp[0][1]=b[0];
            for(int i=1;i<N;i++)
            {
                dp[i][0]=Math.max(dp[i-1][0]+b[i],dp[i-1][1]+a[i]);
                dp[i][1]=Math.max(dp[i-1][0]+c[i],dp[i-1][1]+b[i]);
            }
            System.out.println(dp[N-1][0]);
        }
    }
}
