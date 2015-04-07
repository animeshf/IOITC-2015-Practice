import java.util.*;
import java.io.*;
class DivisorJumping2
{
    static int dp[][]=new int[501][501];
    static int arr[][]=new int[501][501];
    static int N,M,Q;
    static ArrayList<ArrayList<Integer>> div = new ArrayList<ArrayList<Integer>>(501);
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N=sc.nextInt();M=sc.nextInt();Q=sc.nextInt();
        for(int i=0;i<N;i++)
            for(int j=0;j<M;j++)
                arr[i][j]=sc.nextInt();
        compute();
        StringBuilder output = new StringBuilder();
        while(Q-->0)
        {
            int i = sc.nextInt() , j = sc.nextInt();
            output.append(dp[i][j]).append("\n");
        }
        System.out.println(output);
    }

    static void compute()
    {
        store_divisors();
        //dp[i][j]=Best way to get from (i,j) to (0,0).
        dp[0][0]=arr[0][0];
        // Initialize base case for row 1 and column 1
        for(int i=1;i<=500;i++)
        {
            int max_row=dp[0][0] ,max_col=dp[0][0];
            // Fill up in bottom up way!
            int sz = div.get(i).size();
            for(int j=0;j<sz;j++)
            {
                int d = div.get(i).get(j);
                max_row = Math.max(max_row,dp[i-d][0]);// Bottom Up Simple DP Table Fill
                max_col = Math.max(max_col,dp[0][i-d]);
            }
            dp[i][0] = max_row+arr[i][0];
            dp[0][i] = max_col+arr[0][i];
        }

        for(int i=1;i<=N;i++)
        {
            for(int j=1;j<=M;j++)
            {
                int p=0,q=0;
                dp[i][j]=dp[i-1][j-1];// Cause 1 is always a factor
                int size_i = div.get(i).size();
                int size_j = div.get(j).size();
                while(p<size_i && q<size_j)// Linear Factor scan for Common Factors
                {
                    int first = div.get(i).get(p);
                    int second = div.get(j).get(q);
                    if(first>second)q++;
                    else if(first<second)p++;
                    else
                    {
                        int d=first; // d is a common factor of i & j
                        if(i-d>=0) // Within the grid
                            dp[i][j]=Math.max(dp[i][j],dp[i-d][j]);//Best way to get currently & from that position
                                                                   // on which we can jump
                        if(j-d>=0)
                            dp[i][j]=Math.max(dp[i][j], dp[i][j-d]); // Same
                        if(i-d>=0 && j-d>=0)
                            dp[i][j]=Math.max(dp[i][j], dp[i-d][j-d]); // Same
                        p++; q++;
                    }
                }
                dp[i][j]+=arr[i][j]; // Add value at current index (i,j)
            }
        }
    }

    static void store_divisors() // Just store divisors of every number between 1-500 , and make sure its sorted!
    {
        for(int i=0;i<=500;i++)div.add(new ArrayList<Integer>());
        div.get(1).add(1);
        for(int i=2;i<=500;i++)
        {
            int limit = (int)Math.sqrt(i);
            for(int j=1;j<=limit;j++)
            {
                if(i%j==0)
                {
                    int temp = i/j;
                    div.get(i).add(j);
                    if(temp!=j)
                        div.get(i).add(temp);
                }
            }
            Collections.sort(div.get(i));            
        }
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

            