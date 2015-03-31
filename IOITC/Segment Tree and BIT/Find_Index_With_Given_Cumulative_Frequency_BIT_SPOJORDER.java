
/* BIT -- Finding index having a given cumulative frequency
 * Finding Kth smallest element in a dynamic array
 */import java.util.*;
import java.io.*;
class ORDER
{
    static int BIT[] = new int[200001];
    static int ANS[] = new int[200001];
    static int INV[] = new int[200001];
    static int N;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int T = sc.nextInt();
        StringBuilder op = new StringBuilder();
        while(T-->0)

        {
            N = sc.nextInt();
            for(int i=1;i<=N;i++)
            {
                INV[i]=sc.nextInt(); update(i,1);
            }
            for(int i=N;i>=1;i--)
            {
                int L = i-INV[i];
                int s=1 , e=N;
                while(s<=e)
                {
                    int mid = (s+e)/2;int temp = query(mid);
                    if(temp<L) s=mid+1;
                    else if (temp>=L) e=mid-1;
                }
                ANS[i]=s;
                update(s, -1);
            }
            for(int i=1;i<=N;i++)op.append(ANS[i]).append(" ");
            op.append("\n");
        }
        System.out.println(op);
    }
    static void update(int i , int v)
    {
        for(;i<=200000;i+=(i&-i))BIT[i]+=v;
    }
    static int query(int i)
    {
        int ans=0;
        for(;i>0;i-=(i&-i))ans+=BIT[i];
        return ans;
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

    