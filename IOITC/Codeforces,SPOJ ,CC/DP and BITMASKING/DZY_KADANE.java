import java.io.*;
import java.util.*;
class DZY_KADANE
{
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        int A[] = new int [N+2] , L[] = new int [N+2] , R[] = new int [N+2];
        for(int i=1;i<=N;i++)
        A[i] = sc.nextInt();
        /*424238336 649760493 681692778 714636916 719885387 804289384 846930887 957747794 596516650 189641422
           1         2         3        4          5         6        7        8          1         1
           8         7         6        5          4         3        2        1          1         1*/
        L[N+1]=R[N+1]=0;   
        L[0] = R[0] = 0;
        int best = 0;
        for(int i=1;i<=N;i++)
        {
            L[i] = 1;
            if(A[i] > A[i-1])
                L[i] = L[i-1]+1;
            best = Math.max(best,L[i]);    
        }
        for(int i=N;i>=1;i--)
        {
            R[i] = 1;
            if(A[i] < A[i+1] && i!=N)
                R[i] = R[i+1]+1;
            best = Math.max(best,R[i]);    
        }
        for(int i=2;i<N;i++)
            if(A[i-1]+1<A[i+1])best = Math.max(best,R[i+1]+L[i-1]+1);
        for(int i=1;i<N;i++)
            best=Math.max(L[i]+1,best);
        for(int i=N; i>1 ; i--)
            best=Math.max(best,R[i]+1);
        System.out.println(best);    
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