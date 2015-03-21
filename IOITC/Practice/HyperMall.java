import java.util.*;
import java.io.*;
class HyperMall 
{
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        int C = sc.nextInt();
        int maxCounterTime[]=new int[C+1];
        Arrays.fill(maxCounterTime,0);
        time a[]=new time[N+1];
        for(int i=0;i<=N;i++)
        {
            a[i]=new time(0,0);
        }
        for(int i=1;i<=N;i++)
        {   
            a[i].a=sc.nextInt();
            a[i].b=sc.nextInt();
        }

        Arrays.sort(a);

        for(int i=1;i<=N;i++)
        {
            int cur=a[i].a;
            int j;
            int minTime=1;
            for(j=1;j<=C;j++)
            {
                if(maxCounterTime[j] <= cur)
                    break;
                if(maxCounterTime[j] < maxCounterTime[minTime])
                    minTime=j;
            }
            minTime= j<=C?j:minTime;
            maxCounterTime[minTime]=Math.max(maxCounterTime[minTime],cur)+a[i].b;
        }
        int max=-100000;
        for(int i=1;i<=C;i++)
            max=Math.max(max,maxCounterTime[i]);
        System.out.println(max);
    }
}
class time implements Comparable<time>
{
    int a,b;
    public time()
    {
        a=0;
        b=0;
    }

    public time(int a ,int b)
    {
        this.a=a;
        this.b=b;
    }

    public int compareTo(time o)
    {
        return this.a-o.a;
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

