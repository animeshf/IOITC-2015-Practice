import java.io.*;
import java.util.*;
class LongestDecreasingSubsequenceNLOGN
{
    static int a[]=new int[100001];
    static int LDS[]=new int[100001];
    static int last=1;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        for(int i=0;i<N;i++)
        {
            a[i]=sc.nextInt();
        }
        LDS[0]=a[0];
        for(int i=1 ; i<N ; i++)
        {
            int position = binarySearch(a[i]);
            if(position==last)
            {
                LDS[last]=a[i];
                last++;
            }
            else
                LDS[position]=a[i];
        }
        System.out.println(last);
    }

    static int binarySearch(int target)
    {
        int low=0,high=last-1,mid;
        //4 1 2
        while(low<high)
        {
            mid=(low+high)/2;
            if(LDS[mid]>target)
                low=mid+1;
            else
                high=mid-1;
        }
        if(LDS[low]>target)
            return low+1;
        else
            return low;
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

