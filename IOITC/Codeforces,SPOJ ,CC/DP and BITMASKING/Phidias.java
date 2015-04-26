import java.io.*;
import java.util.*;
class Phidias
{
    static int M[][] = new int [610][610];
    static int DP[][] = new int[610][610];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int T = sc.nextInt();
        while(T-->0)
        {
            int W = sc.nextInt() , H = sc.nextInt() , N = sc.nextInt();
            
            for(int i=0;i<=600;i++)
            {
                for(int j=0;j<=600;j++)
                {
                    DP[i][j] = 0;
                    M[i][j] = 0;
                }
            }
            
            for(int i=1;i<=N;i++)
            {
                int X = sc.nextInt() , Y = sc.nextInt();
                M[X][Y] = 1;
            }

            for(int i=1;i<=W;i++)
            {
                for(int j=1;j<=H;j++)
                {
                    if(M[i][j] == 1) continue;
                    DP[i][j] = i*j; 
                    for(int k=1;k<i;k++)
                        DP[i][j]=Math.min(DP[i][j],DP[k][j]+DP[i-k][j]);
                    for(int k=1;k<j;k++)
                        DP[i][j]=Math.min(DP[i][j],DP[i][k]+DP[i][j-k]);
                }
            }
            System.out.println(DP[W][H]);
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

    