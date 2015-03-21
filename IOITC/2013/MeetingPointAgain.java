import java.util.*;
import java.io.*;
class MeetingPointAgain
{
    static long MOD = 1000000007;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        if(N%2==1)
        {
            System.out.println("1");
            System.exit(0);
        }
        else
        {
            int med=N/2;
            long ans=1;
            if(K==1)
            {
                long a[]=new long[N+1];
                for(int i=1;i<=N;i++)
                    a[i]=sc.nextLong();
                Arrays.sort(a);
                long valid=a[med+1]-a[med]+1;
                valid%=MOD;
                System.out.println(valid);
            }
            if(K==2)
            {
                long a[]=new long[N+1];
                long b[]=new long[N+1];
                for(int i=1;i<=N;i++)
                {
                    a[i]=sc.nextLong();b[i]=sc.nextLong();
                }
                Arrays.sort(a);
                Arrays.sort(b);
                long valid=a[med+1]-a[med]+1;
                valid%=MOD;
                ans*=valid;ans%=MOD;
                valid=b[med+1]-b[med]+1;
                valid%=MOD;
                ans*=valid;ans%=MOD;
                System.out.println(ans);
            }
            if(K==3)
            {
                long a[]=new long[N+1];
                long b[]=new long[N+1];
                long c[]=new long[N+1];
                for(int i=1;i<=N;i++)
                {
                    a[i]=sc.nextLong();b[i]=sc.nextLong();c[i]=sc.nextLong();
                }
                Arrays.sort(a);
                Arrays.sort(b);
                Arrays.sort(c);
                long valid=a[med+1]-a[med]+1;
                valid%=MOD;
                ans*=valid;ans%=MOD;
                valid=b[med+1]-b[med]+1;
                valid%=MOD;
                ans*=valid;ans%=MOD;
                valid=c[med+1]-c[med]+1;
                valid%=MOD;
                ans*=valid;ans%=MOD;
                System.out.println(ans);
            }
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

