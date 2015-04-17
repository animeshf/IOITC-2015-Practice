
import java.util.StringTokenizer;
import java.io.*;
class Granularity
{
    static long DP []     = new long [1001];
    static int  C  []     = new int  [1001];
    static int  adj [] [] = new int  [1001][1001];
    static int  pre [] [] = new int  [1001][1001];
    static int N , M , u , v , ret;
    public static void main(String[]args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); // INPUT
        while(M-->0)
        {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());// INPUT
            v = Integer.parseInt(st.nextToken());// INPUT
            adj[u][v] = 1;
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= N ; i++)
        {
            adj[i][i] = 1; // Every node is connected to itself
            C[i] = Integer.parseInt(st.nextToken());   
            DP[i] = C[i]; // DP[i] , Best you can do in the subarray from (1...i)
        }
        precompute();
        DP[0] = 0;
        for(int i = 1 ; i <= N ; i++) // Starting Index
        {
            for(int j = 1 ; j <= N ; j++) // Group size (g)
            {
                if( i + j - 1 > N)  // out of bounds 
                    break;
                for(int k = i ; k + j - 1 <= N ; k+=j)   
                // (k-i) = g*constant == group size
                {
                    // we need a edge from some x to y such that i<=x<i+j and k<=y<k+j. and Adj[x][y]=1
                    if(sum(i,k,i+j-1,k+j-1)>0)    //k+j-1 is end index of the group!
                        DP[k+j-1] = Math.min(DP[k+j-1],DP[i-1]+C[j]);
                    else 
                        break; 
                }
                // sigma(N/j) = N*logN
            }
        }
        System.out.println(DP[N]);
    }

    static int sum(int dx, int dy, int px, int py)
    {
        // 2D PREFIX SUM QUERY
        ret = pre[px][py];
        ret -= pre[px][dy - 1];
        ret -= pre[dx - 1][py];
        ret += pre[dx - 1][dy - 1];
        return ret;
    }

    static void precompute()
    {
        // Compute 2D Prefix Sums
        for(int i = 1 ; i <= N ; i++)
        {
            for(int j = 1 ; j <= N ; j++)
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + adj[i][j];
        }
    }
}