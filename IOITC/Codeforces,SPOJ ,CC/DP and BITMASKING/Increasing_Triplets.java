
/*
 * Given an Array
 * Find Number of Triplets i,j,k such that A[i] > A[j] > A[k]
 */
import java.io.*;
import java.util.*;
class Increasing_Triplets
{
    static long BIT[][]=new long [2][1000008];
    static int N;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();
        pair arr[]=new pair[N+1];
        for(int i=1;i<=N;i++)
        {
            arr[i] = new pair(sc.nextInt() , i);
        }
        Arrays.sort(arr,1,N+1);
        long ans = 0;
        // Implicit Co-ordinate Compression
        for(int i = N ; i >= 1 ; i--)
        {
            update(0,arr[i].second,1);
            // BIT[0][x] = Number of indices <= x encountered till now.
            // These give us the A[k] values
            update(1,arr[i].second,query(0,arr[i].second-1));
            //BIT[1][x] ---> A[j] values , i.e. values 
            ans += query(1,arr[i].second-1);
        }
        System.out.println(ans);
    }
    static void update(int id , int idx , long val)
    {
        for(;idx<=N;idx+=idx&-idx)
            BIT[id][idx]+=val;
    }
    static long query(int id , int idx)
    {
        long ans = 0;
        for(;idx>0;idx-=idx&-idx)
            ans+=BIT[id][idx];
        return ans;
    }
}
class pair implements Comparable < pair >
{
    int first , second;
    public pair(int first , int second)
    {
        this.first = first;
        this.second = second;
    }
    public int compareTo(pair x)
    {
        return this.first==x.first?this.second-x.second:this.first-x.first;
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
