import java.util.*;
import java.io.*;
class WorldCup
{
    static int ways[][][]=new int[1801][301][10];
    static int mod=1000000007;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        for(int i=0;i<=1800;i++)
        {
            for(int j=0;j<=300;j++)
            {
                for(int k=0;k<=9;k++)
                {
                    ways[i][j][k]=-1;
                }
            }
        }
        int T = sc.nextInt();
        while(T-->0)
        {
            int R = sc.nextInt();
            int B = sc.nextInt();
            int L = sc.nextInt();
            if(R>1800)
                System.out.println("0");
            else
            {   
                solve(R,B,L);
                System.out.println(ways[R][B][L]);
            }
        }
    }

    static int solve(int runs , int balls , int wickets)
    {
        if(balls<0||wickets<0||runs<0)
            return 0;
        if(runs==0&&balls==0)
            return 1;
        if(ways[runs][balls][wickets]!=-1)
            return ways[runs][balls][wickets];
        int a=solve(runs-4,balls-1,wickets);
        int b=solve(runs-6,balls-1,wickets);
        int c=solve(runs,balls-1,wickets);
        int d=solve(runs,balls-1,wickets-1);
        a+=b;
        a%=mod;
        c+=d;
        c%=mod;
        a+=c;
        a%=mod;
        return ways[runs][balls][wickets]=a;
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

