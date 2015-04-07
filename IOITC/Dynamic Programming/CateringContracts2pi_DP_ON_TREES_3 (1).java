/**
 * Catering Contracts 2PI

Finding Connected Components of SIZE K in a Tree
  
  
dp[i][j] =  number of ways to take j nodes in the subtree rooted at 'i' , Where 'i' is always included!. 
So our answer is sum of dp[i][K] for all 'i' .
  
  
          1
         / \
        2   3
       / \
      4  5
  
  K  =  3  
  Let's take this tree
  
  Now thinking localised. 
  Lets find dp[2][j] for all j=1,2,3
  
  dp[2][1] = 1 // only way is to take 2 itself. 
  For any dp[i][j] we consider only the subtree rooted at 'i'.
  So for dp[2][1] tou take only 2 and ignore 4.
   
  For dp[2][2]: 
  
  Find dp[4][1]=1; dp[4][2]=0; dp[4][3]=0;
  Its a leaf node.Same for dp[5][j] for all j
  So now for dp[2][2]: 
  After taking 2, we have to take 1 more vertex which can be 4 or 5
  dp[2][2] = 2.// There are 2 ways
  
  So for dp[i][j], after taking i, we're left with j-1 nodes
  let  x = j-1 
  
  Now basically we have to take a TOTAL of x nodes from the children of i.
  
  We need to take x nodes from among the subtrees of i's children .
  
  So, now we have to fix the number of nodes to take from_each_subtree
  Like , We can take 2 from 1st child subtree 3 from 2nd child subtree ... ... or some other permutation , but all such permutations must add up to x 
  We'll have to sum over all such permutations
        
             Say x=3; There are two 2 children of some cur node 'i' , a = first child, b = 2nd child
             dp[a][1]*dp[b][2] + dp[a][2]*dp[b][1] + dp[a][0]*dp[b][3] + dp[a][3]*dp[b][0] .
          
             All possible combos summed!
	     The problem is : How do we compute all possible combos and their sums efficiently? o_O	
          
             So now suppose we calculate dp[p][j] for all children p of i.
          
             So we just need to calculate that sum. We calculate that sum with another dp.
             D[j][z] = sum of dp's j th child of cur node 'i' to the nth child where you have to take z more.
             D[j][z] = D[j+1][z]*dp[jth child][0] + D[j+1][z-1]*dp[j th child][1] + D[j+1][z-2]*dp[jth child][2] .... 
          
          
            
            Once you're at a node, number the children 1...m ,put them in an array.
            
            A[1] = 1st child of  i
            A[2] = 2nd child of  i
            
            Now, first calculate all dp recursively for all of A[i], that is we do bottom up dp.
              
            Now, find D[j][z] 
              
            You find D[j][z] from right to left.
            First D[n][z] then D[n-1][z] ... 
 */
import java.io.*;
import java.util.*;
class CateringContracts2pi
{
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(2505);
    static int mod = 10243;
    static int ans=0;
    static int dp[][]=new int[2501][101];
    static int temp[]=new int[101];
    static int N,K;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        for(int i=0;i<2505;i++)
         adj.add(new ArrayList<Integer>());
        N = sc.nextInt();
        K = sc.nextInt();
        for(int i=1;i<N;i++)
        {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        for(int i = 1; i <= N; i++)
        {
            dp[i][0] = dp[i][1] = 1;
        }
        dfs(1,0);
        System.out.println("lol");
    }
    static void dfs(int node,int par)
    {
        int sz = adj.get(node).size();
        for(int i=0;i<sz;i++)
        {
            int next = adj.get(node).get(i);
            if(next==par)continue;
            dfs(next,node);
            Arrays.fill(temp,0);
            /*temp[i]=number of ways to take 'i' nodes in the subtree rooted at "node" where "node" and "next"
            are always included!*/
            /* temp[i]= Summation Of: 
             *          Number of ways to take x nodes in the subtree rooted at 'node' * number of ways to take (i-x)
             *          nodes in the subtree rooted at the "next" or child node.
             *          Note: dp[node][j] considers the other children of the subtree rooted at node
             *          and excludes the current child "next".
             */
            for(int j=1;j<=K;j++)
            {
                for(int k=1;k+j<=K;k++)
                {
                    temp[j+k]+=dp[node][j]*dp[next][k] % mod;
                }
            }
            for(int j=1;j<=K;j++)
            {
                dp[node][j] += temp[j];
                dp[node][j] %= mod;
            }
        }
        ans+=dp[node][K];
        ans%=mod;
    }
}
class InputReader1 
{

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public InputReader1(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c & 15;
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public long nextLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c & 15;
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String next() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public String nextLine() {
        int c = read();
        //while (c != '\n' && c != '\r' && c != '\t' && c != -1)
        //c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (c != '\n' && c != '\r' && c != '\t' && c != -1);
        return res.toString();
    }

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

}    